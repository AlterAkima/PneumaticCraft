package pneumaticCraft.common.block.tubes;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.ForgeDirection;
import pneumaticCraft.client.model.IBaseModel;
import pneumaticCraft.client.model.tubemodules.ModelGauge;
import pneumaticCraft.lib.Names;
import pneumaticCraft.proxy.CommonProxy;

public class ModulePressureGauge extends TubeModuleRedstoneEmitting{
    private final IBaseModel model = new ModelGauge(this);

    public ModulePressureGauge(){
        lowerBound = 0;
        higherBound = 7.5F;
    }

    @Override
    public void update(){
        if(!pressureTube.world().isRemote) {
            if(pressureTube.world().getWorldTime() % 20 == 0) sendDescriptionPacket();
            setRedstone(getRedstone(pressureTube.getAirHandler().getPressure(ForgeDirection.UNKNOWN)));
        }
    }

    private int getRedstone(float pressure){
        return (int)((pressure - lowerBound) / (higherBound - lowerBound) * 15);
    }

    @Override
    public String getType(){
        return Names.MODULE_GAUGE;
    }

    @Override
    public IBaseModel getModel(){
        return model;
    }

    @Override
    public double getWidth(){
        return 0.5;
    }

    @Override
    protected double getHeight(){
        return 0.25;
    }

    @Override
    public void addItemDescription(List<String> curInfo){
        curInfo.add(EnumChatFormatting.BLUE + "Formula: Redstone = 2.0 x pressure(bar)");
        curInfo.add("This module emits a redstone signal of which");
        curInfo.add("the strength is dependant on how much pressure");
        curInfo.add("the tube is at.");
    }

    @Override
    public boolean onActivated(EntityPlayer player){
        return super.onActivated(player);
    }

    @Override
    protected int getGuiId(){
        return CommonProxy.GUI_ID_PRESSURE_MODULE;
    }
}
