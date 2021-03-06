package pneumaticCraft.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import pneumaticCraft.api.PneumaticRegistry.IPneumaticCraftInterface;
import pneumaticCraft.api.client.pneumaticHelmet.IBlockTrackEntry;
import pneumaticCraft.api.client.pneumaticHelmet.IEntityTrackEntry;
import pneumaticCraft.api.client.pneumaticHelmet.IHackableBlock;
import pneumaticCraft.api.client.pneumaticHelmet.IHackableEntity;
import pneumaticCraft.api.drone.IPathfindHandler;
import pneumaticCraft.api.item.IInventoryItem;
import pneumaticCraft.client.render.pneumaticArmor.blockTracker.BlockTrackEntryList;
import pneumaticCraft.client.render.pneumaticArmor.hacking.HackableHandler.HackingEntityProperties;
import pneumaticCraft.common.util.PneumaticCraftUtils;
import pneumaticCraft.lib.Log;

/**
 * With this class you can register your entities to give more info in the tooltip of the Entity Tracker.
 */
public class PneumaticCraftAPIHandler implements IPneumaticCraftInterface{
    private final static PneumaticCraftAPIHandler INSTANCE = new PneumaticCraftAPIHandler();
    public final List<Class<? extends IEntityTrackEntry>> entityTrackEntries = new ArrayList<Class<? extends IEntityTrackEntry>>();
    public final Map<Class<? extends Entity>, Class<? extends IHackableEntity>> hackableEntities = new HashMap<Class<? extends Entity>, Class<? extends IHackableEntity>>();
    public final Map<Block, Class<? extends IHackableBlock>> hackableBlocks = new HashMap<Block, Class<? extends IHackableBlock>>();
    public final Map<String, Class<? extends IHackableEntity>> stringToEntityHackables = new HashMap<String, Class<? extends IHackableEntity>>();
    public final Map<String, Class<? extends IHackableBlock>> stringToBlockHackables = new HashMap<String, Class<? extends IHackableBlock>>();
    public final Map<Block, IPathfindHandler> pathfindableBlocks = new HashMap<Block, IPathfindHandler>();
    public final List<IInventoryItem> inventoryItems = new ArrayList<IInventoryItem>();
    public final List<Integer> concealableRenderIds = new ArrayList<Integer>();
    public final Map<Fluid, Integer> liquidXPs = new HashMap<Fluid, Integer>();

    private PneumaticCraftAPIHandler(){
        concealableRenderIds.add(0);
        concealableRenderIds.add(31);
        concealableRenderIds.add(39);
        concealableRenderIds.add(10);
        concealableRenderIds.add(16);
        concealableRenderIds.add(26);
    }

    public static PneumaticCraftAPIHandler getInstance(){
        return INSTANCE;
    }

    @Override
    public void registerEntityTrackEntry(Class<? extends IEntityTrackEntry> entry){
        if(entry == null) throw new IllegalArgumentException("Can't register null!");
        entityTrackEntries.add(entry);
    }

    @Override
    public void addHackable(Class<? extends Entity> entityClazz, Class<? extends IHackableEntity> iHackable){
        if(entityClazz == null) throw new NullPointerException("Entity class is null!");
        if(iHackable == null) throw new NullPointerException("IHackableEntity is null!");
        if(Entity.class.isAssignableFrom(iHackable)) {
            Log.warning("Entities that implement IHackableEntity shouldn't be registered as hackable! Registering entity: " + entityClazz.getCanonicalName());
        } else {
            try {
                IHackableEntity hackableEntity = iHackable.newInstance();
                if(hackableEntity.getId() != null) stringToEntityHackables.put(hackableEntity.getId(), iHackable);
                hackableEntities.put(entityClazz, iHackable);
            } catch(InstantiationException e) {
                Log.error("Not able to register hackable entity: " + iHackable.getName() + ". Does the class have a parameterless constructor?");
                e.printStackTrace();
            } catch(IllegalAccessException e) {
                Log.error("Not able to register hackable entity: " + iHackable.getName() + ". Is the class a public class?");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addHackable(Block block, Class<? extends IHackableBlock> iHackable){
        if(block == null) throw new NullPointerException("Block is null!");
        if(iHackable == null) throw new NullPointerException("IHackableBlock is null!");

        if(Block.class.isAssignableFrom(iHackable)) {
            Log.warning("Blocks that implement IHackableBlock shouldn't be registered as hackable! Registering block: " + block.getLocalizedName());
        } else {
            try {
                IHackableBlock hackableBlock = iHackable.newInstance();
                if(hackableBlock.getId() != null) stringToBlockHackables.put(hackableBlock.getId(), iHackable);
                hackableBlocks.put(block, iHackable);
            } catch(InstantiationException e) {
                Log.error("Not able to register hackable block: " + iHackable.getName() + ". Does the class have a parameterless constructor?");
                e.printStackTrace();
            } catch(IllegalAccessException e) {
                Log.error("Not able to register hackable block: " + iHackable.getName() + ". Is the class a public class?");
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<IHackableEntity> getCurrentEntityHacks(Entity entity){
        HackingEntityProperties hackingProps = (HackingEntityProperties)entity.getExtendedProperties("PneumaticCraftHacking");
        if(hackingProps != null) {
            List<IHackableEntity> hackables = hackingProps.getCurrentHacks();
            if(hackables != null) return hackables;
        } else {
            Log.warning("Extended entity props HackingEntityProperties couldn't be found in the entity " + entity.getCommandSenderName());
        }
        return new ArrayList<IHackableEntity>();
    }

    @Override
    public void registerBlockTrackEntry(IBlockTrackEntry entry){
        if(entry == null) throw new IllegalArgumentException("Block Track Entry can't be null!");
        BlockTrackEntryList.instance.trackList.add(entry);
    }

    @Override
    public void addPathfindableBlock(Block block, IPathfindHandler handler){
        if(block == null) throw new IllegalArgumentException("Block can't be null!");
        pathfindableBlocks.put(block, handler);
    }

    @Override
    public int getProtectingSecurityStations(World world, int x, int y, int z, EntityPlayer player, boolean showRangeLines){
        if(world.isRemote) throw new IllegalArgumentException("This method can only be called from the server side!");
        return PneumaticCraftUtils.getProtectingSecurityStations(world, x, y, z, player, showRangeLines);
    }

    @Override
    public void registerInventoryItem(IInventoryItem handler){
        inventoryItems.add(handler);
    }

    @Override
    public void registerConcealableRenderId(int id){
        concealableRenderIds.add(id);
    }

    @Override
    public void registerXPLiquid(Fluid fluid, int liquidToPointRatio){
        if(fluid == null) throw new NullPointerException("Fluid can't be null!");
        if(liquidToPointRatio <= 0) throw new IllegalArgumentException("liquidToPointRatio can't be <= 0");
        liquidXPs.put(fluid, liquidToPointRatio);
    }

}
