package xyz.tcreopargh.ctintegration.vanilla.advancement;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.server.IServer;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.util.ResourceLocation;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import xyz.tcreopargh.ctintegration.CTIntegration;

import java.util.ArrayList;
import java.util.List;

@ZenRegister
@ZenClass(CTIntegration.CT_PACKAGE + "advancement.AdvancementHelper")
public class AdvancementHelper {
    @ZenMethod
    public static IAdvancement getAdvancementById(IServer server, String id) {
        AdvancementManager manager = CraftTweakerMC.getMCServer(server).getAdvancementManager();
        if (manager.getAdvancement(new ResourceLocation(id)) == null) {
            return null;
        } else {
            return new AdvancementImpl(manager.getAdvancement(new ResourceLocation(id)));
        }
    }

    @ZenMethod
    public static List<IAdvancement> getAdvancements(IServer server) {
        AdvancementManager manager = CraftTweakerMC.getMCServer(server).getAdvancementManager();
        List<IAdvancement> advancements = new ArrayList<>();
        manager.getAdvancements().forEach(advancement -> advancements.add(new AdvancementImpl(advancement)));
        return advancements;
    }
}
