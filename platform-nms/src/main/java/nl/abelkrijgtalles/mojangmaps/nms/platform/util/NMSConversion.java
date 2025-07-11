/*
 * mojang-maps.platform-nms.main
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

package nl.abelkrijgtalles.mojangmaps.nms.platform.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Particle;
import org.jetbrains.annotations.NotNull;

public class NMSConversion {

    public static ParticleOptions particle(@NotNull Particle particle) {

        switch (particle) {
            case ANGRY_VILLAGER -> {
                return ParticleTypes.ANGRY_VILLAGER;
            }
            case ASH -> {
                return ParticleTypes.ASH;
            }
            case CRIT -> {
                return ParticleTypes.CRIT;
            }
            case DUST -> {
                return (ParticleOptions) ParticleTypes.DUST;
            }
            case GLOW -> {
                return ParticleTypes.GLOW;
            }
            case GUST -> {
                return ParticleTypes.GUST;
            }
            case ITEM -> {
                return (ParticleOptions) ParticleTypes.ITEM;
            }
            case LAVA -> {
                return ParticleTypes.LAVA;
            }
            case NOTE -> {
                return ParticleTypes.NOTE;
            }
            case POOF -> {
                return ParticleTypes.POOF;
            }
            case RAIN -> {
                return ParticleTypes.RAIN;
            }
            case SOUL -> {
                return ParticleTypes.SOUL;
            }
            case SPIT -> {
                return ParticleTypes.SPIT;
            }
            case BLOCK -> {
                return (ParticleOptions) ParticleTypes.BLOCK;
            }
            case CLOUD -> {
                return ParticleTypes.CLOUD;
            }
            case FLAME -> {
                return ParticleTypes.FLAME;
            }
            case FLASH -> {
                return ParticleTypes.FLASH;
            }
            case HEART -> {
                return ParticleTypes.HEART;
            }
            case SMOKE -> {
                return ParticleTypes.SMOKE;
            }
            case TRAIL -> {
                return (ParticleOptions) ParticleTypes.TRAIL;
            }
            case WITCH -> {
                return ParticleTypes.WITCH;
            }
            case BUBBLE -> {
                return ParticleTypes.BUBBLE;
            }
            case EFFECT -> {
                return ParticleTypes.EFFECT;
            }
            case PORTAL -> {
                return ParticleTypes.PORTAL;
            }
            case SCRAPE -> {
                return ParticleTypes.SCRAPE;
            }
            case SHRIEK -> {
                return (ParticleOptions) ParticleTypes.SHRIEK;
            }
            case SNEEZE -> {
                return ParticleTypes.SNEEZE;
            }
            case SPLASH -> {
                return ParticleTypes.SPLASH;
            }
            case WAX_ON -> {
                return ParticleTypes.WAX_ON;
            }
            case DOLPHIN -> {
                return ParticleTypes.DOLPHIN;
            }
            case ENCHANT -> {
                return ParticleTypes.ENCHANT;
            }
            case END_ROD -> {
                return ParticleTypes.END_ROD;
            }
            case FISHING -> {
                return ParticleTypes.FISHING;
            }
            case WAX_OFF -> {
                return ParticleTypes.WAX_OFF;
            }
            case FIREWORK -> {
                return ParticleTypes.FIREWORK;
            }
            case INFESTED -> {
                return ParticleTypes.INFESTED;
            }
            case MYCELIUM -> {
                return ParticleTypes.MYCELIUM;
            }
            case NAUTILUS -> {
                return ParticleTypes.NAUTILUS;
            }
            case COMPOSTER -> {
                return ParticleTypes.COMPOSTER;
            }
            case EGG_CRACK -> {
                return ParticleTypes.EGG_CRACK;
            }
            case EXPLOSION -> {
                return ParticleTypes.EXPLOSION;
            }
            case RAID_OMEN -> {
                return ParticleTypes.RAID_OMEN;
            }
            case SNOWFLAKE -> {
                return ParticleTypes.SNOWFLAKE;
            }
            case SQUID_INK -> {
                return ParticleTypes.SQUID_INK;
            }
            case VIBRATION -> {
                return (ParticleOptions) ParticleTypes.VIBRATION;
            }
            case WHITE_ASH -> {
                return ParticleTypes.WHITE_ASH;
            }
            case BUBBLE_POP -> {
                return ParticleTypes.BUBBLE_POP;
            }
            case DUST_PLUME -> {
                return ParticleTypes.DUST_PLUME;
            }
            case ITEM_SLIME -> {
                return ParticleTypes.ITEM_SLIME;
            }
            case SCULK_SOUL -> {
                return ParticleTypes.SCULK_SOUL;
            }
            case SMALL_GUST -> {
                return ParticleTypes.SMALL_GUST;
            }
            case SONIC_BOOM -> {
                return ParticleTypes.SONIC_BOOM;
            }
            case TRIAL_OMEN -> {
                return ParticleTypes.TRIAL_OMEN;
            }
            case UNDERWATER -> {
                return ParticleTypes.UNDERWATER;
            }
            case DUST_PILLAR -> {
                return (ParticleOptions) ParticleTypes.DUST_PILLAR;
            }
            case ITEM_COBWEB -> {
                return ParticleTypes.ITEM_COBWEB;
            }
            case LARGE_SMOKE -> {
                return ParticleTypes.LARGE_SMOKE;
            }
            case SMALL_FLAME -> {
                return ParticleTypes.SMALL_FLAME;
            }
            case WHITE_SMOKE -> {
                return ParticleTypes.WHITE_SMOKE;
            }
            case BLOCK_MARKER -> {
                return (ParticleOptions) ParticleTypes.BLOCK_MARKER;
            }
            case CURRENT_DOWN -> {
                return ParticleTypes.CURRENT_DOWN;
            }
            case FALLING_DUST -> {
                return (ParticleOptions) ParticleTypes.FALLING_DUST;
            }
            case FALLING_LAVA -> {
                return ParticleTypes.FALLING_LAVA;
            }
            case LANDING_LAVA -> {
                return ParticleTypes.LANDING_LAVA;
            }
            case SCULK_CHARGE -> {
                return (ParticleOptions) ParticleTypes.SCULK_CHARGE;
            }
            case SWEEP_ATTACK -> {
                return ParticleTypes.SWEEP_ATTACK;
            }
            case WARPED_SPORE -> {
                return ParticleTypes.WARPED_SPORE;
            }
            case BLOCK_CRUMBLE -> {
                return (ParticleOptions) ParticleTypes.BLOCK_CRUMBLE;
            }
            case CHERRY_LEAVES -> {
                return ParticleTypes.CHERRY_LEAVES;
            }
            case CRIMSON_SPORE -> {
                return ParticleTypes.CRIMSON_SPORE;
            }
            case DRAGON_BREATH -> {
                return ParticleTypes.DRAGON_BREATH;
            }
            case DRIPPING_LAVA -> {
                return ParticleTypes.DRIPPING_LAVA;
            }
            case ENCHANTED_HIT -> {
                return ParticleTypes.ENCHANTED_HIT;
            }
            case ENTITY_EFFECT -> {
                return (ParticleOptions) ParticleTypes.ENTITY_EFFECT;
            }
            case FALLING_HONEY -> {
                return ParticleTypes.FALLING_HONEY;
            }
            case FALLING_WATER -> {
                return ParticleTypes.FALLING_WATER;
            }
            case ITEM_SNOWBALL -> {
                return ParticleTypes.ITEM_SNOWBALL;
            }
            case LANDING_HONEY -> {
                return ParticleTypes.LANDING_HONEY;
            }
            case DRIPPING_HONEY -> {
                return ParticleTypes.DRIPPING_HONEY;
            }
            case DRIPPING_WATER -> {
                return ParticleTypes.DRIPPING_WATER;
            }
            case ELDER_GUARDIAN -> {
                return ParticleTypes.ELDER_GUARDIAN;
            }
            case ELECTRIC_SPARK -> {
                return ParticleTypes.ELECTRIC_SPARK;
            }
            case FALLING_NECTAR -> {
                return ParticleTypes.FALLING_NECTAR;
            }
            case GLOW_SQUID_INK -> {
                return ParticleTypes.GLOW_SQUID_INK;
            }
            case HAPPY_VILLAGER -> {
                return ParticleTypes.HAPPY_VILLAGER;
            }
            case INSTANT_EFFECT -> {
                return ParticleTypes.INSTANT_EFFECT;
            }
            case REVERSE_PORTAL -> {
                return ParticleTypes.REVERSE_PORTAL;
            }
            case SOUL_FIRE_FLAME -> {
                return ParticleTypes.SOUL_FIRE_FLAME;
            }
            case BUBBLE_COLUMN_UP -> {
                return ParticleTypes.BUBBLE_COLUMN_UP;
            }
            case DAMAGE_INDICATOR -> {
                return ParticleTypes.DAMAGE_INDICATOR;
            }
            case OMINOUS_SPAWNING -> {
                return ParticleTypes.OMINOUS_SPAWNING;
            }
            case SCULK_CHARGE_POP -> {
                return ParticleTypes.SCULK_CHARGE_POP;
            }
            case TOTEM_OF_UNDYING -> {
                return ParticleTypes.TOTEM_OF_UNDYING;
            }
            case VAULT_CONNECTION -> {
                return ParticleTypes.VAULT_CONNECTION;
            }
            case EXPLOSION_EMITTER -> {
                return ParticleTypes.EXPLOSION_EMITTER;
            }
            case SPORE_BLOSSOM_AIR -> {
                return ParticleTypes.SPORE_BLOSSOM_AIR;
            }
            case GUST_EMITTER_LARGE -> {
                return ParticleTypes.GUST_EMITTER_LARGE;
            }
            case GUST_EMITTER_SMALL -> {
                return ParticleTypes.GUST_EMITTER_SMALL;
            }
            case CAMPFIRE_COSY_SMOKE -> {
                return ParticleTypes.CAMPFIRE_COSY_SMOKE;
            }
            case CAMPFIRE_SIGNAL_SMOKE -> {
                return ParticleTypes.CAMPFIRE_SIGNAL_SMOKE;
            }
            case DUST_COLOR_TRANSITION -> {
                return (ParticleOptions) ParticleTypes.DUST_COLOR_TRANSITION;
            }
            case FALLING_OBSIDIAN_TEAR -> {
                return ParticleTypes.FALLING_OBSIDIAN_TEAR;
            }
            case FALLING_SPORE_BLOSSOM -> {
                return ParticleTypes.FALLING_SPORE_BLOSSOM;
            }
            case LANDING_OBSIDIAN_TEAR -> {
                return ParticleTypes.LANDING_OBSIDIAN_TEAR;
            }
            case DRIPPING_OBSIDIAN_TEAR -> {
                return ParticleTypes.DRIPPING_OBSIDIAN_TEAR;
            }
            case FALLING_DRIPSTONE_LAVA -> {
                return ParticleTypes.FALLING_DRIPSTONE_LAVA;
            }
            case DRIPPING_DRIPSTONE_LAVA -> {
                return ParticleTypes.DRIPPING_DRIPSTONE_LAVA;
            }
            case FALLING_DRIPSTONE_WATER -> {
                return ParticleTypes.FALLING_DRIPSTONE_WATER;
            }
            case DRIPPING_DRIPSTONE_WATER -> {
                return ParticleTypes.DRIPPING_DRIPSTONE_WATER;
            }
            case TRIAL_SPAWNER_DETECTED_PLAYER -> {
                return ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER;
            }
            case TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS -> {
                return ParticleTypes.TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS;
            }
            case null, default ->
                    throw new IllegalArgumentException("I don't know how, but somehow a Particle that doesn't exist has been passed.");
        }
    }

    public static Heightmap.Types heightMapType(@NotNull HeightMapType heightMapType) {

        switch (heightMapType) {
            case WORLD_SURFACE_WG -> {
                return Heightmap.Types.WORLD_SURFACE_WG;
            }
            case WORLD_SURFACE -> {
                return Heightmap.Types.WORLD_SURFACE;
            }
            case OCEAN_FLOOR_WG -> {
                return Heightmap.Types.OCEAN_FLOOR_WG;
            }
            case OCEAN_FLOOR -> {
                return Heightmap.Types.OCEAN_FLOOR;
            }
            case MOTION_BLOCKING -> {
                return Heightmap.Types.MOTION_BLOCKING;
            }
            case MOTION_BLOCKING_NO_LEAVES -> {
                return Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
            }

            case null, default ->
                    throw new IllegalArgumentException("I don't know how, but somehow a HeightMapType that doesn't exist has been passed.");
        }

    }

}
