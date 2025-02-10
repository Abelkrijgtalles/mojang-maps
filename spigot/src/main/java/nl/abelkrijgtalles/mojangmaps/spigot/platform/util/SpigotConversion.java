/*
 * mojang_maps.spigot.main
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

package nl.abelkrijgtalles.mojangmaps.spigot.platform.util;

import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Particle;
import org.bukkit.HeightMap;
import org.jetbrains.annotations.NotNull;

public class SpigotConversion {

    public static HeightMap heightMapType(@NotNull HeightMapType heightMapType) {

        switch (heightMapType) {
            case WORLD_SURFACE_WG -> {
                return HeightMap.WORLD_SURFACE_WG;
            }
            case WORLD_SURFACE -> {
                return HeightMap.WORLD_SURFACE;
            }
            case OCEAN_FLOOR_WG -> {
                return HeightMap.OCEAN_FLOOR_WG;
            }
            case OCEAN_FLOOR -> {
                return HeightMap.OCEAN_FLOOR;
            }
            case MOTION_BLOCKING -> {
                return HeightMap.MOTION_BLOCKING;
            }
            case MOTION_BLOCKING_NO_LEAVES -> {
                return HeightMap.MOTION_BLOCKING_NO_LEAVES;
            }
            case null, default ->
                    throw new IllegalArgumentException("I don't know how, but somehow a HeightMapType that doesn't exist has been passed.");
        }

    }

    public static org.bukkit.Particle particle(@NotNull Particle particle) {

        switch (particle) {
            case ANGRY_VILLAGER -> {
                return org.bukkit.Particle.ANGRY_VILLAGER;
            }
            case ASH -> {
                return org.bukkit.Particle.ASH;
            }
            case CRIT -> {
                return org.bukkit.Particle.CRIT;
            }
            case DUST -> {
                return org.bukkit.Particle.DUST;
            }
            case GLOW -> {
                return org.bukkit.Particle.GLOW;
            }
            case GUST -> {
                return org.bukkit.Particle.GUST;
            }
            case ITEM -> {
                return org.bukkit.Particle.ITEM;
            }
            case LAVA -> {
                return org.bukkit.Particle.LAVA;
            }
            case NOTE -> {
                return org.bukkit.Particle.NOTE;
            }
            case POOF -> {
                return org.bukkit.Particle.POOF;
            }
            case RAIN -> {
                return org.bukkit.Particle.RAIN;
            }
            case SOUL -> {
                return org.bukkit.Particle.SOUL;
            }
            case SPIT -> {
                return org.bukkit.Particle.SPIT;
            }
            case BLOCK -> {
                return org.bukkit.Particle.BLOCK;
            }
            case CLOUD -> {
                return org.bukkit.Particle.CLOUD;
            }
            case FLAME -> {
                return org.bukkit.Particle.FLAME;
            }
            case FLASH -> {
                return org.bukkit.Particle.FLASH;
            }
            case HEART -> {
                return org.bukkit.Particle.HEART;
            }
            case SMOKE -> {
                return org.bukkit.Particle.SMOKE;
            }
            case TRAIL -> {
                return org.bukkit.Particle.TRAIL;
            }
            case WITCH -> {
                return org.bukkit.Particle.WITCH;
            }
            case BUBBLE -> {
                return org.bukkit.Particle.BUBBLE;
            }
            case EFFECT -> {
                return org.bukkit.Particle.EFFECT;
            }
            case PORTAL -> {
                return org.bukkit.Particle.PORTAL;
            }
            case SCRAPE -> {
                return org.bukkit.Particle.SCRAPE;
            }
            case SHRIEK -> {
                return org.bukkit.Particle.SHRIEK;
            }
            case SNEEZE -> {
                return org.bukkit.Particle.SNEEZE;
            }
            case SPLASH -> {
                return org.bukkit.Particle.SPLASH;
            }
            case WAX_ON -> {
                return org.bukkit.Particle.WAX_ON;
            }
            case DOLPHIN -> {
                return org.bukkit.Particle.DOLPHIN;
            }
            case ENCHANT -> {
                return org.bukkit.Particle.ENCHANT;
            }
            case END_ROD -> {
                return org.bukkit.Particle.END_ROD;
            }
            case FISHING -> {
                return org.bukkit.Particle.FISHING;
            }
            case WAX_OFF -> {
                return org.bukkit.Particle.WAX_OFF;
            }
            case FIREWORK -> {
                return org.bukkit.Particle.FIREWORK;
            }
            case INFESTED -> {
                return org.bukkit.Particle.INFESTED;
            }
            case MYCELIUM -> {
                return org.bukkit.Particle.MYCELIUM;
            }
            case NAUTILUS -> {
                return org.bukkit.Particle.NAUTILUS;
            }
            case COMPOSTER -> {
                return org.bukkit.Particle.COMPOSTER;
            }
            case EGG_CRACK -> {
                return org.bukkit.Particle.EGG_CRACK;
            }
            case EXPLOSION -> {
                return org.bukkit.Particle.EXPLOSION;
            }
            case RAID_OMEN -> {
                return org.bukkit.Particle.RAID_OMEN;
            }
            case SNOWFLAKE -> {
                return org.bukkit.Particle.SNOWFLAKE;
            }
            case SQUID_INK -> {
                return org.bukkit.Particle.SQUID_INK;
            }
            case VIBRATION -> {
                return org.bukkit.Particle.VIBRATION;
            }
            case WHITE_ASH -> {
                return org.bukkit.Particle.WHITE_ASH;
            }
            case BUBBLE_POP -> {
                return org.bukkit.Particle.BUBBLE_POP;
            }
            case DUST_PLUME -> {
                return org.bukkit.Particle.DUST_PLUME;
            }
            case ITEM_SLIME -> {
                return org.bukkit.Particle.ITEM_SLIME;
            }
            case SCULK_SOUL -> {
                return org.bukkit.Particle.SCULK_SOUL;
            }
            case SMALL_GUST -> {
                return org.bukkit.Particle.SMALL_GUST;
            }
            case SONIC_BOOM -> {
                return org.bukkit.Particle.SONIC_BOOM;
            }
            case TRIAL_OMEN -> {
                return org.bukkit.Particle.TRIAL_OMEN;
            }
            case UNDERWATER -> {
                return org.bukkit.Particle.UNDERWATER;
            }
            case DUST_PILLAR -> {
                return org.bukkit.Particle.DUST_PILLAR;
            }
            case ITEM_COBWEB -> {
                return org.bukkit.Particle.ITEM_COBWEB;
            }
            case LARGE_SMOKE -> {
                return org.bukkit.Particle.LARGE_SMOKE;
            }
            case SMALL_FLAME -> {
                return org.bukkit.Particle.SMALL_FLAME;
            }
            case WHITE_SMOKE -> {
                return org.bukkit.Particle.WHITE_SMOKE;
            }
            case BLOCK_MARKER -> {
                return org.bukkit.Particle.BLOCK_MARKER;
            }
            case CURRENT_DOWN -> {
                return org.bukkit.Particle.CURRENT_DOWN;
            }
            case FALLING_DUST -> {
                return org.bukkit.Particle.FALLING_DUST;
            }
            case FALLING_LAVA -> {
                return org.bukkit.Particle.FALLING_LAVA;
            }
            case LANDING_LAVA -> {
                return org.bukkit.Particle.LANDING_LAVA;
            }
            case SCULK_CHARGE -> {
                return org.bukkit.Particle.SCULK_CHARGE;
            }
            case SWEEP_ATTACK -> {
                return org.bukkit.Particle.SWEEP_ATTACK;
            }
            case WARPED_SPORE -> {
                return org.bukkit.Particle.WARPED_SPORE;
            }
            case BLOCK_CRUMBLE -> {
                return org.bukkit.Particle.BLOCK_CRUMBLE;
            }
            case CHERRY_LEAVES -> {
                return org.bukkit.Particle.CHERRY_LEAVES;
            }
            case CRIMSON_SPORE -> {
                return org.bukkit.Particle.CRIMSON_SPORE;
            }
            case DRAGON_BREATH -> {
                return org.bukkit.Particle.DRAGON_BREATH;
            }
            case DRIPPING_LAVA -> {
                return org.bukkit.Particle.DRIPPING_LAVA;
            }
            case ENCHANTED_HIT -> {
                return org.bukkit.Particle.ENCHANTED_HIT;
            }
            case ENTITY_EFFECT -> {
                return org.bukkit.Particle.ENTITY_EFFECT;
            }
            case FALLING_HONEY -> {
                return org.bukkit.Particle.FALLING_HONEY;
            }
            case FALLING_WATER -> {
                return org.bukkit.Particle.FALLING_WATER;
            }
            case ITEM_SNOWBALL -> {
                return org.bukkit.Particle.ITEM_SNOWBALL;
            }
            case LANDING_HONEY -> {
                return org.bukkit.Particle.LANDING_HONEY;
            }
            case DRIPPING_HONEY -> {
                return org.bukkit.Particle.DRIPPING_HONEY;
            }
            case DRIPPING_WATER -> {
                return org.bukkit.Particle.DRIPPING_WATER;
            }
            case ELDER_GUARDIAN -> {
                return org.bukkit.Particle.ELDER_GUARDIAN;
            }
            case ELECTRIC_SPARK -> {
                return org.bukkit.Particle.ELECTRIC_SPARK;
            }
            case FALLING_NECTAR -> {
                return org.bukkit.Particle.FALLING_NECTAR;
            }
            case GLOW_SQUID_INK -> {
                return org.bukkit.Particle.GLOW_SQUID_INK;
            }
            case HAPPY_VILLAGER -> {
                return org.bukkit.Particle.HAPPY_VILLAGER;
            }
            case INSTANT_EFFECT -> {
                return org.bukkit.Particle.INSTANT_EFFECT;
            }
            case REVERSE_PORTAL -> {
                return org.bukkit.Particle.REVERSE_PORTAL;
            }
            case SOUL_FIRE_FLAME -> {
                return org.bukkit.Particle.SOUL_FIRE_FLAME;
            }
            case BUBBLE_COLUMN_UP -> {
                return org.bukkit.Particle.BUBBLE_COLUMN_UP;
            }
            case DAMAGE_INDICATOR -> {
                return org.bukkit.Particle.DAMAGE_INDICATOR;
            }
            case OMINOUS_SPAWNING -> {
                return org.bukkit.Particle.OMINOUS_SPAWNING;
            }
            case SCULK_CHARGE_POP -> {
                return org.bukkit.Particle.SCULK_CHARGE_POP;
            }
            case TOTEM_OF_UNDYING -> {
                return org.bukkit.Particle.TOTEM_OF_UNDYING;
            }
            case VAULT_CONNECTION -> {
                return org.bukkit.Particle.VAULT_CONNECTION;
            }
            case EXPLOSION_EMITTER -> {
                return org.bukkit.Particle.EXPLOSION_EMITTER;
            }
            case SPORE_BLOSSOM_AIR -> {
                return org.bukkit.Particle.SPORE_BLOSSOM_AIR;
            }
            case GUST_EMITTER_LARGE -> {
                return org.bukkit.Particle.GUST_EMITTER_LARGE;
            }
            case GUST_EMITTER_SMALL -> {
                return org.bukkit.Particle.GUST_EMITTER_SMALL;
            }
            case CAMPFIRE_COSY_SMOKE -> {
                return org.bukkit.Particle.CAMPFIRE_COSY_SMOKE;
            }
            case CAMPFIRE_SIGNAL_SMOKE -> {
                return org.bukkit.Particle.CAMPFIRE_SIGNAL_SMOKE;
            }
            case DUST_COLOR_TRANSITION -> {
                return org.bukkit.Particle.DUST_COLOR_TRANSITION;
            }
            case FALLING_OBSIDIAN_TEAR -> {
                return org.bukkit.Particle.FALLING_OBSIDIAN_TEAR;
            }
            case FALLING_SPORE_BLOSSOM -> {
                return org.bukkit.Particle.FALLING_SPORE_BLOSSOM;
            }
            case LANDING_OBSIDIAN_TEAR -> {
                return org.bukkit.Particle.LANDING_OBSIDIAN_TEAR;
            }
            case DRIPPING_OBSIDIAN_TEAR -> {
                return org.bukkit.Particle.DRIPPING_OBSIDIAN_TEAR;
            }
            case FALLING_DRIPSTONE_LAVA -> {
                return org.bukkit.Particle.FALLING_DRIPSTONE_LAVA;
            }
            case DRIPPING_DRIPSTONE_LAVA -> {
                return org.bukkit.Particle.DRIPPING_DRIPSTONE_LAVA;
            }
            case FALLING_DRIPSTONE_WATER -> {
                return org.bukkit.Particle.FALLING_DRIPSTONE_WATER;
            }
            case DRIPPING_DRIPSTONE_WATER -> {
                return org.bukkit.Particle.DRIPPING_DRIPSTONE_WATER;
            }
            case TRIAL_SPAWNER_DETECTED_PLAYER -> {
                return org.bukkit.Particle.TRIAL_SPAWNER_DETECTION;
            }
            case TRIAL_SPAWNER_DETECTED_PLAYER_OMINOUS -> {
                return org.bukkit.Particle.TRIAL_SPAWNER_DETECTION_OMINOUS;
            }
            case null, default ->
                    throw new IllegalArgumentException("I don't know how, but somehow a Particle that doesn't exist has been passed.");
        }
    }

}
