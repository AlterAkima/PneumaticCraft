package pneumaticCraft.common.thirdparty.igwmod;

import igwmod.api.WikiRegistry;
import net.minecraft.item.ItemStack;
import pneumaticCraft.common.block.Blockss;
import pneumaticCraft.common.item.ItemProgrammingPuzzle;
import pneumaticCraft.common.item.Itemss;
import pneumaticCraft.lib.Log;

public class IGWHandler{
    public static void init(){
        WikiRegistry.registerWikiTab(new PneumaticCraftWikiTab());

        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("start"), "item/programmingPuzzle_start");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("inventoryExport"), "item/programmingPuzzle_Inv-E");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("inventoryImport"), "item/programmingPuzzle_Inv-I");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("itemFilter"), "item/programmingPuzzle_I-ftr");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("entityAttack"), "item/programmingPuzzle_attk");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("dig"), "item/programmingPuzzle_dig");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("place"), "item/programmingPuzzle_place");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("area"), "item/programmingPuzzle_area");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("goto"), "item/programmingPuzzle_goto");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("pickupItem"), "item/programmingPuzzle_I-pick");
        WikiRegistry.registerBlockAndItemPageEntry(ItemProgrammingPuzzle.getStackForWidgetKey("text"), "item/programmingPuzzle_text");

        WikiRegistry.registerRecipeIntegrator(new IntegratorPressureChamber());
        WikiRegistry.registerRecipeIntegrator(new IntegratorAssembly());

        //Add redirections, so if you click on a Pressure Chamber Wall you'll see the general page about Pressure Chambers.
        WikiRegistry.registerBlockAndItemPageEntry(Blockss.pressureTube, "menu/pressureTubes");
        WikiRegistry.registerBlockAndItemPageEntry(Blockss.advancedPressureTube, "menu/pressureTubes");

        WikiRegistry.registerBlockAndItemPageEntry(Itemss.cannonBarrel, "block/airCannon");
        WikiRegistry.registerBlockAndItemPageEntry(Itemss.stoneBase, "block/airCannon");

        WikiRegistry.registerBlockAndItemPageEntry(Itemss.ingotIronCompressed, "menu/baseConcepts");
        WikiRegistry.registerBlockAndItemPageEntry(Blockss.compressedIron, "menu/baseConcepts");

        WikiRegistry.registerBlockAndItemPageEntry(Blockss.pressureChamberValve, "menu/pressureChamber");
        WikiRegistry.registerBlockAndItemPageEntry(new ItemStack(Blockss.pressureChamberWall, 0, 0), "menu/pressureChamber");
        WikiRegistry.registerBlockAndItemPageEntry(new ItemStack(Blockss.pressureChamberWall, 0, 6), "menu/pressureChamber");

        WikiRegistry.registerBlockAndItemPageEntry(Blockss.elevatorBase, "menu/elevator");
        WikiRegistry.registerBlockAndItemPageEntry(Blockss.elevatorFrame, "menu/elevator");

        WikiRegistry.registerBlockAndItemPageEntry(Blockss.pneumaticDoor, "menu/pneumaticDoor");
        WikiRegistry.registerBlockAndItemPageEntry(Blockss.pneumaticDoorBase, "menu/pneumaticDoor");

        WikiRegistry.registerBlockAndItemPageEntry(Blockss.assemblyController, "menu/assemblyMachines");
        WikiRegistry.registerBlockAndItemPageEntry(Blockss.assemblyDrill, "menu/assemblyMachines");
        WikiRegistry.registerBlockAndItemPageEntry(Blockss.assemblyIOUnit, "menu/assemblyMachines");
        WikiRegistry.registerBlockAndItemPageEntry(Blockss.assemblyLaser, "menu/assemblyMachines");
        WikiRegistry.registerBlockAndItemPageEntry(Blockss.assemblyPlatform, "menu/assemblyMachines");
        WikiRegistry.registerBlockAndItemPageEntry(Itemss.assemblyProgram, "menu/assemblyMachines");

        WikiRegistry.registerBlockAndItemPageEntry(Itemss.machineUpgrade, "menu/machineUpgrades");

        WikiRegistry.registerBlockAndItemPageEntry(Itemss.PCBBlueprint, "menu/printedCircuitBoards");
        WikiRegistry.registerBlockAndItemPageEntry(Itemss.emptyPCB, "menu/printedCircuitBoards");
        WikiRegistry.registerBlockAndItemPageEntry(Itemss.unassembledPCB, "menu/printedCircuitBoards");
        WikiRegistry.registerBlockAndItemPageEntry(Itemss.printedCircuitBoard, "menu/printedCircuitBoards");
        WikiRegistry.registerBlockAndItemPageEntry(Itemss.failedPCB, "item/etchingAcidBucket");

        WikiRegistry.registerBlockAndItemPageEntry(Itemss.compressedIronGear, "block/pneumaticEngine");

        WikiRegistry.registerBlockAndItemPageEntry(Itemss.networkComponent, "block/securityStation");

        Log.info("Loaded PneumaticCraft IGW-Mod plug-in! Thanks IGW-Mod!");
    }
}
