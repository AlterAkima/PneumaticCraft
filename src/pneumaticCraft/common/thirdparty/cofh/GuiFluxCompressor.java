package pneumaticCraft.common.thirdparty.cofh;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import pneumaticCraft.client.gui.GuiPneumaticContainerBase;
import pneumaticCraft.client.gui.widget.GuiAnimatedStat;
import pneumaticCraft.lib.Textures;

public class GuiFluxCompressor extends GuiPneumaticContainerBase<TileEntityFluxCompressor>{

    private final ContainerRF container;
    private GuiAnimatedStat inputStat;

    public GuiFluxCompressor(InventoryPlayer inventoryPlayer, TileEntityFluxCompressor te){
        super(new ContainerRF(inventoryPlayer, te), te, Textures.GUI_4UPGRADE_SLOTS);
        container = (ContainerRF)inventorySlots;
    }

    @Override
    public void initGui(){
        super.initGui();
        inputStat = addAnimatedStat("Input", (ItemStack)null, 0xFF555555, false);

        addWidget(new WidgetEnergy(guiLeft + 20, guiTop + 20, container));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y){
        super.drawGuiContainerForegroundLayer(x, y);
        fontRendererObj.drawString("Upgr.", 53, 19, 4210752);
    }

    @Override
    public void updateScreen(){
        super.updateScreen();
        inputStat.setText(getOutputStat());
    }

    private List<String> getOutputStat(){
        List<String> textList = new ArrayList<String>();
        textList.add(EnumChatFormatting.GRAY + "Maximum RF usage:");
        textList.add(EnumChatFormatting.BLACK.toString() + container.rfPerTick + " RF/tick");
        textList.add(EnumChatFormatting.GRAY + "Maximum input rate:");
        textList.add(EnumChatFormatting.BLACK.toString() + container.rfPerTick * 2 + " RF/tick");
        textList.add(EnumChatFormatting.GRAY + "Current stored RF:");
        textList.add(EnumChatFormatting.BLACK.toString() + container.energy + " RF");
        return textList;
    }

    @Override
    protected void addPressureStatInfo(List<String> pressureStatText){
        super.addPressureStatInfo(pressureStatText);
        pressureStatText.add("\u00a77Max Production:");
        pressureStatText.add("\u00a70" + container.airPerTick + " mL/tick.");
    }

    @Override
    protected void addProblems(List<String> textList){
        super.addProblems(textList);
        if(container.rfPerTick > container.energy) {
            textList.add("gui.tab.problems.fluxCompressor.noRF");
        }
    }
}
