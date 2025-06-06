package xyz.tcreopargh.ctintegration.cot;

import crafttweaker.CraftTweakerAPI;
import net.minecraftforge.fml.common.Loader;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethodStatic;

@ZenExpansion("mods.contenttweaker.VanillaFactory")
public class BaubleVanillaFactoryExpansion {
    @ZenMethodStatic
    public static BaubleItemRepresentation createBaubleItem(String unlocalizedName) {
        if(Loader.isModLoaded("baubles")) {
            return new BaubleItemRepresentation(unlocalizedName);
        } else {
            CraftTweakerAPI.logError("You can only create a bauble item when Baubles mod is loaded!");
            return null;
        }
    }
}
