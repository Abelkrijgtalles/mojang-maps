/*
 * mojang-maps.sponge.main
 * Copyright (C) 2025 Abel van Hulst/Abelkrijgtalles/Abelpro678
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.mojangmaps.sponge.platform.util;

import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Particle;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.world.HeightType;
import org.spongepowered.api.world.HeightTypes;

public class SpongeConversion {

    public static HeightType heightMapType(@NotNull HeightMapType heightMapType) {

        switch (heightMapType) {
            case WORLD_SURFACE_WG -> {
                return (HeightType) HeightTypes.WORLD_SURFACE_WG;
            }
            case WORLD_SURFACE -> {
                return (HeightType) HeightTypes.WORLD_SURFACE;
            }
            case OCEAN_FLOOR_WG -> {
                return (HeightType) HeightTypes.OCEAN_FLOOR_WG;
            }
            case OCEAN_FLOOR -> {
                return (HeightType) HeightTypes.OCEAN_FLOOR;
            }
            case MOTION_BLOCKING -> {
                return (HeightType) HeightTypes.MOTION_BLOCKING;
            }
            case MOTION_BLOCKING_NO_LEAVES -> {
                return (HeightType) HeightTypes.MOTION_BLOCKING_NO_LEAVES;
            }
            case null, default ->
                    throw new IllegalArgumentException("I don't know how, but somehow a HeightMapType that doesn't exist has been passed.");
        }

    }

    public static ParticleType particle(@NotNull Particle particle) {

        switch (particle) {
            case ANGRY_VILLAGER -> {
                return ParticleTypes.ANGRY_VILLAGER.get();
            }
            case ASH -> {
                return ParticleTypes.ASH.get();
            }
            case CRIT -> {
                return ParticleTypes.CRIT.get();
            }
            case DUST -> {
                return ParticleTypes.DUST.get();
            }
            case GLOW -> {
                return ParticleTypes.GLOW.get();
            }
            case GUST -> {
                return ParticleTypes.GUST.get();
            }
            case ITEM -> {
                return ParticleTypes.ITEM.get();
            }
            case LAVA -> {
                return ParticleTypes.LAVA.get();
            }
            case NOTE -> {
                return ParticleTypes.NOTE.get();
            }
            case POOF -> {
                return ParticleTypes.POOF.get();
            }
            case RAIN -> {
                return ParticleTypes.RAIN.get();
            }
            case SOUL -> {
                return ParticleTypes.SOUL.get();
            }
            case SPIT -> {
                return ParticleTypes.SPIT.get();
            }
            case BLOCK -> {
                return ParticleTypes.BLOCK.get();
            }
            case CLOUD -> {
                return ParticleTypes.CLOUD.get();
            }
            case FLAME -> {
                return ParticleTypes.FLAME.get();
            }
            case FLASH -> {
                return ParticleTypes.FLASH.get();
            }
            case HEART -> {
                return ParticleTypes.HEART.get();
            }
            case SMOKE -> {
                return ParticleTypes.SMOKE.get();
            }
            case TRAIL -> {
                return ParticleTypes.TRAIL.get();
            }
            case WITCH -> {
                return ParticleTypes.WITCH.get();
            }
            case BUBBLE -> {
                return ParticleTypes.BUBBLE.get();
            }
            case EFFECT -> {
                return ParticleTypes.EFFECT.get();
            }
            case PORTAL -> {
                return ParticleTypes.PORTAL.get();
            }
            case SCRAPE -> {
                return ParticleTypes.SCRAPE.get();
            }
            case SHRIEK -> {
                return ParticleTypes.SHRIEK.get();
            }
            case SNEEZE -> {
                return ParticleTypes.SNEEZE.get();
            }
            case SPLASH -> {
                return ParticleTypes.SPLASH.get();
            }
            case WAX_ON -> {
                return ParticleTypes.WAX_ON.get();
            }
            case DOLPHIN -> {
                return ParticleTypes.DOLPHIN.get();
            }
            case ENCHANT -> {
                return ParticleTypes.ENCHANT.get();
            }
            case END_ROD -> {
                return ParticleTypes.END_ROD.get();
            }
            case FISHING -> {
                return ParticleTypes.FISHING.get();
            }
            case WAX_OFF -> {
                return ParticleTypes.WAX_OFF.get();
            }
            case FIREWORK -> {
                return ParticleTypes.FIREWORK.get();
            }
            case INFESTED -> {
                return ParticleTypes.INFESTED.get();
            }
            case MYCELIUM -> {
                return ParticleTypes.MYCELIUM.get();
            }
            case NAUTILUS -> {
                return ParticleTypes.NAUTILUS.get();
            }
            case COMPOSTER -> {
                return ParticleTypes.COMPOSTER.get();
            }
            case EGG_CRACK -> {
                return ParticleTypes.EGG_CRACK.get();
            }
            case EXPLOSION -> {
                return ParticleTypes.EXPLOSION.get();
            }
            case RAID_OMEN -> {
                return ParticleTypes.RAID_OMEN.get();
            }
            case SNOWFLAKE -> {
                return ParticleTypes.SNOWFLAKE.get();
            }
            case SQUID_INK -> {
                return ParticleTypes.SQUID_INK.get();
            }
            case VIBRATION -> {
                return ParticleTypes.VIBRATION.get();
            }
            case WHITE_ASH -> {
                return ParticleTypes.WHITE_ASH.get();
            }
            case BUBBLE_POP -> {
                return ParticleTypes.BUBBLE_POP.get();
            }
            case DUST_PLUME -> {
                return ParticleTypes.DUST_PLUME.get();
            }
            case ITEM_SLIME -> {
                return ParticleTypes.ITEM_SLIME.get();
            }
            case SCULK_SOUL -> {
                return ParticleTypes.SCULK_SOUL.get();
            }
            case SMALL_GUST -> {
                return ParticleTypes.SMALL_GUST.get();
            }
            case SONIC_BOOM -> {
                return ParticleTypes.SONIC_BOOM.get();
            }
            case TRIAL_OMEN -> {
                return ParticleTypes.TRIAL_OMEN.get();
            }
            case UNDERWATER -> {
                return ParticleTypes.UNDERWATER.get();
            }
            case DUST_PILLAR -> {
                return ParticleTypes.DUST_PILLAR.get();
            }
            case ITEM_COBWEB -> {
                return ParticleTypes.ITEM_COBWEB.get();
            }
            case LARGE_SMOKE -> {
                return ParticleTypes.LARGE_SMOKE.get();
            }
            case SMALL_FLAME -> {
                return ParticleTypes.SMALL_FLAME.get();
            }
            case WHITE_SMOKE -> {
                return ParticleTypes.WHITE_SMOKE.get();
            }
            case BLOCK_MARKER -> {
                return ParticleTypes.BLOCK_MARKER.get();
            }
            case CURRENT_DOWN -> {
                return ParticleTypes.CURRENT_DOWN.get();
            }
            case FALLING_DUST -> {
                return ParticleTypes.FALLING_DUST.get();
            }
            case FALLING_LAVA -> {
                return ParticleTypes.FALLING_LAVA.get();
            }
            case LANDING_LAVA -> {
                return ParticleTypes.LANDING_LAVA.get();
            }
            case SCULK_CHARGE -> {
                return ParticleTypes.SCULK_CHARGE.get();
            }
            case SWEEP_ATTACK -> {
                return ParticleTypes.SWEEP_ATTACK.get();
            }
            case WARPED_SPORE -> {
                return ParticleTypes.WARPED_SPORE.get();
            }
            case BLOCK_CRUMBLE -> {
                return ParticleTypes.BLOCK_CRUMBLE.get();
            }
            case CHERRY_LEAVES -> {
                return ParticleTypes.CHERRY_LEAVES.get();
            }
            case CRIMSON_SPORE -> {
                return ParticleTypes.CRIMSON_SPORE.get();
            }
            case DRAGON_BREATH -> {
                return ParticleTypes.DRAGON_BREATH.get();
            }
            case DRIPPING_LAVA -> {
                return ParticleTypes.DRIPPING_LAVA.get();
            }
            case ENCHANTED_HIT -> {
                return ParticleTypes.ENCHANTED_HIT.get();
            }
            case ENTITY_EFFECT -> {
                return ParticleTypes.ENTITY_EFFECT.get();
            }
            case FALLING_HONEY -> {
                return ParticleTypes.FALLING_HONEY.get();
            }
            case FALLING_WATER -> {
                return ParticleTypes.FALLING_WATER.get();
            }
            case ITEM_SNOWBALL -> {
                return ParticleTypes.ITEM_SNOWBALL.get();
            }
            case LANDING_HONEY -> {
                return ParticleTypes.LANDING_HONEY.get();
            }
            case DRIPPING_HONEY -> {
                return ParticleTypes.DRIPPING_HONEY.get();
            }
            case DRIPPING_WATER -> {
                return ParticleTypes.DRIPPING_WATER.get();
            }
            case ELDER_GUARDIAN -> {
                return ParticleTypes.ELDER_GUARDIAN.get();
            }
            case ELECTRIC_SPARK -> {
                return ParticleTypes.ELECTRIC_SPARK.get();
            }
            case FALLING_NECTAR -> {
                return ParticleTypes.FALLING_NECTAR.get();
            }
            case GLOW_SQUID_INK -> {
                return ParticleTypes.GLOW_SQUID_INK.get();
            }
            case HAPPY_VILLAGER -> {
                return ParticleTypes.HAPPY_VILLAGER.get();
            }
            case INSTANT_EFFECT -> {
                return ParticleTypes.INSTANT_EFFECT.get();
            }
            case REVERSE_PORTAL -> {
                return ParticleTypes.REVERSE_PORTAL.get();
            }
            case SOUL_FIRE_FLAME -> {
                return ParticleTypes.SOUL_FIRE_FLAME.get();
            }
            case BUBBLE_COLUMN_UP -> {
                return ParticleTypes.BUBBLE_COLUMN_UP.get();
            }
            case DAMAGE_INDICATOR -> {
                return ParticleTypes.DAMAGE_INDICATOR.get();
            }
            case OMINOUS_SPAWNING -> {
                return ParticleTypes.OMINOUS_SPAWNING.get();
            }
            case SCULK_CHARGE_POP -> {
                return ParticleTypes.SCULK_CHARGE_POP.get();
            }
            case TOTEM_OF_UNDYING -> {
                return ParticleTypes.TOTEM_OF_UNDYING.get();
            }
            case VAULT_CONNECTION -> {
                return ParticleTypes.VAULT_CONNECTION.get();
            }
            case EXPLOSION_EMITTER -> {
                return ParticleTypes.EXPLOSION_EMITTER.get();
            }
            case SPORE_BLOSSOM_AIR -> {
                return ParticleTypes.SPORE_BLOSSOM_AIR.get();
            }
            case GUST_EMITTER_LARGE -> {
                return ParticleTypes.GUST_EMITTER_LARGE.get();
            }
            case GUST_EMITTER_SMALL -> {
                return ParticleTypes.GUST_EMITTER_SMALL.get();
            }
            case CAMPFIRE_COSY_SMOKE -> {
                return ParticleTypes.CAMPFIRE_COSY_SMOKE.get();
            }
            case CAMPFIRE_SIGNAL_SMOKE -> {
                return ParticleTypes.CAMPFIRE_SIGNAL_SMOKE.get();
            }
            case DUST_COLOR_TRANSITION -> {
                return ParticleTypes.DUST_COLOR_TRANSITION.get();
            }
            case FALLING_OBSIDIAN_TEAR -> {
                return ParticleTypes.FALLING_OBSIDIAN_TEAR.get();
            }
            case FALLING_SPORE_BLOSSOM -> {
                return ParticleTypes.FALLING_SPORE_BLOSSOM.get();
            }
            case LANDING_OBSIDIAN_TEAR -> {
                return ParticleTypes.LANDING_OBSIDIAN_TEAR.get();
            }
            case DRIPPING_OBSIDIAN_TEAR -> {
                return ParticleTypes.DRIPPING_OBSIDIAN_TEAR.get();
            }
            case FALLING_DRIPSTONE_LAVA -> {
                return ParticleTypes.FALLING_DRIPSTONE_LAVA.get();
            }
            case DRIPPING_DRIPSTONE_LAVA -> {
                return ParticleTypes.DRIPPING_DRIPSTONE_LAVA.get();
            }
            case FALLING_DRIPSTONE_WATER -> {
                return ParticleTypes.FALLING_DRIPSTONE_WATER.get();
            }
            case DRIPPING_DRIPSTONE_WATER -> {
                return ParticleTypes.DRIPPING_DRIPSTONE_WATER.get();
            }
            case TRIAL_SPAWNER_DETECTED_PLAYER -> {
                return ParticleTypes.TRIAL_SPAWNER_DETECTION.get();
            }
            case TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS -> {
                return ParticleTypes.TRIAL_SPAWNER_DETECTION_OMINOUS.get();
            }
            case null, default ->
                    throw new IllegalArgumentException("I don't know how, but somehow a Particle that doesn't exist has been passed.");
        }

    }

}
