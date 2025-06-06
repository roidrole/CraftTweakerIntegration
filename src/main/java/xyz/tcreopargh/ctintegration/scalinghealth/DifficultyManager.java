package xyz.tcreopargh.ctintegration.scalinghealth;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.player.IPlayer;
import crafttweaker.api.world.IBlockPos;
import crafttweaker.api.world.IWorld;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.silentchaos512.scalinghealth.config.Config;
import net.silentchaos512.scalinghealth.utils.SHPlayerDataHandler;
import net.silentchaos512.scalinghealth.world.ScalingHealthSavedData;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import xyz.tcreopargh.ctintegration.CTIntegration;
import xyz.tcreopargh.ctintegration.date.CalendarDate;
import xyz.tcreopargh.ctintegration.date.IDate;

import java.util.Objects;

@ModOnly("scalinghealth")
@ZenRegister
@ZenClass(CTIntegration.CT_PACKAGE + "scalinghealth.DifficultyManager")
public class DifficultyManager {

    @ZenMethod
    public static void setDifficulty(IPlayer player, double value) {
        EntityPlayer entPlayer = CraftTweakerMC.getPlayer(player);
        if (SHPlayerDataHandler.get(entPlayer) != null) {
            Objects.requireNonNull(SHPlayerDataHandler.get(entPlayer)).setDifficulty(value);
        } else {
            CraftTweakerAPI.logInfo("cannot set difficulty because the player's scaling health data is null");
        }
    }

    @ZenMethod
    public static void addDifficulty(IPlayer player, double value) {
        EntityPlayer entPlayer = CraftTweakerMC.getPlayer(player);
        if (SHPlayerDataHandler.get(entPlayer) != null) {
            Objects.requireNonNull(SHPlayerDataHandler.get(entPlayer)).incrementDifficulty(value);
        } else {
            CraftTweakerAPI.logInfo("cannot add difficulty because the player's scaling health data is null");
        }
    }

    @ZenMethod
    public static void addDifficulty(IPlayer player, double value, boolean affectWorldDifficulty) {
        EntityPlayer entPlayer = CraftTweakerMC.getPlayer(player);
        if (SHPlayerDataHandler.get(entPlayer) != null) {
            Objects.requireNonNull(SHPlayerDataHandler.get(entPlayer)).incrementDifficulty(value, affectWorldDifficulty);
        } else {
            CraftTweakerAPI.logInfo("cannot add difficulty because the player's scaling health data is null");
        }
    }

    @ZenMethod
    public static double getDifficulty(IPlayer player) {
        EntityPlayer entPlayer = CraftTweakerMC.getPlayer(player);
        if(entPlayer == null || SHPlayerDataHandler.get(entPlayer) == null){
            CraftTweakerAPI.logInfo("cannot get difficulty because the player's scaling health data is null or player is null");
            return Double.NaN;
        }
        return SHPlayerDataHandler.get(entPlayer).getDifficulty();
    }

    @ZenMethod
    public static double getWorldDifficulty(IWorld world) {
        World mcWorld = CraftTweakerMC.getWorld(world);
        ScalingHealthSavedData data = ScalingHealthSavedData.get(mcWorld);
        return data.difficulty;
    }

    @ZenMethod
    public static void setWorldDifficulty(IWorld world, double value) {
        World mcWorld = CraftTweakerMC.getWorld(world);
        ScalingHealthSavedData data = ScalingHealthSavedData.get(mcWorld);
        data.difficulty = value;
    }

    @ZenMethod
    public static void addWorldDifficulty(IWorld world, double value) {
        World mcWorld = CraftTweakerMC.getWorld(world);
        ScalingHealthSavedData data = ScalingHealthSavedData.get(mcWorld);
        data.difficulty += value;
    }

    @ZenMethod
    public static IDate getLastTimePlayed(IPlayer player) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        if (SHPlayerDataHandler.get(mcPlayer) != null) {
            return new CalendarDate(Objects.requireNonNull(SHPlayerDataHandler.get(mcPlayer)).getLastTimePlayed());
        } else {
            CraftTweakerAPI.logInfo("cannot get last time played because the player's scaling health data is null");
        }
        return null;
    }

    @ZenMethod
    public static float getMaxHealth(IPlayer player) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        if (SHPlayerDataHandler.get(mcPlayer) != null) {
            return Objects.requireNonNull(SHPlayerDataHandler.get(mcPlayer)).getMaxHealth();
        } else {
            CraftTweakerAPI.logInfo("cannot get max health because the player's scaling health data is null");
        }
        return Float.NaN;
    }

    @ZenMethod
    public static void setMaxHealth(IPlayer player, float value) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        if (SHPlayerDataHandler.get(mcPlayer) != null) {
            Objects.requireNonNull(SHPlayerDataHandler.get(mcPlayer)).setMaxHealth(value);
        } else {
            CraftTweakerAPI.logInfo("cannot set max health because the player's scaling health data is null");
        }
    }

    @ZenMethod
    public static void addMaxHealth(IPlayer player, float value) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        if (SHPlayerDataHandler.get(mcPlayer) != null) {
            Objects.requireNonNull(SHPlayerDataHandler.get(mcPlayer)).incrementMaxHealth(value);
        } else {
            CraftTweakerAPI.logInfo("cannot add max health because the player's scaling health data is null");
        }
    }

    @ZenMethod
    public static double getAreaDifficulty(IPlayer player) {
        EntityPlayer mcPlayer = CraftTweakerMC.getPlayer(player);
        return Config.Difficulty.AREA_DIFFICULTY_MODE.getAreaDifficulty(mcPlayer.world, mcPlayer.getPosition());
    }

    @ZenMethod
    public static double getAreaDifficulty(IWorld world, IBlockPos pos) {
        World mcWorld = CraftTweakerMC.getWorld(world);
        BlockPos mcBlockPos = CraftTweakerMC.getBlockPos(pos);
        return Config.Difficulty.AREA_DIFFICULTY_MODE.getAreaDifficulty(mcWorld, mcBlockPos);
    }
}
