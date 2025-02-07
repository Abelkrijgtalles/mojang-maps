/*
 * mojang_maps.platform-nms.main
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

package nl.abelkrijgtalles.mojangmaps.nms.platform.world;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.level.levelgen.Heightmap;
import nl.abelkrijgtalles.mojangmaps.nms.platform.NMSPlatform;
import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Level;
import nl.abelkrijgtalles.mojangmaps.platform.world.Particle;
import nl.abelkrijgtalles.mojangmaps.platform.world.Vec3;
import org.jetbrains.annotations.NotNull;

public class NMSLevel implements Level {

    public static final String resourceKeyDivider = "\\";
    private final ResourceKey<net.minecraft.world.level.Level> resourceKey;

    public NMSLevel(ResourceKey<net.minecraft.world.level.Level> resourceKey) {

        this.resourceKey = resourceKey;
    }

    public NMSLevel(net.minecraft.world.level.Level level) {

        this(level.dimension());

    }

    @Override
    public String getIdentifier() {

        return resourceKey.location().getNamespace() + resourceKeyDivider + resourceKey.location().getPath();
    }

    @Override
    public int getHeightAtLocation(@NotNull HeightMapType type, int x, int z) {

        return getNMSLevel().getHeight(convertHeightMapType(type), x, z);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Vec3 location, int count) {

        ParticleUtils.spawnParticles(getNMSLevel(), new BlockPos((int) Math.floor(location.x), (int) Math.floor(location.y), (int) Math.floor(location.z)), count, 3, 1, true, convertParticle(particle));
    }

    private net.minecraft.world.level.Level getNMSLevel() {

        if (getNMSLevel() == null)
            throw new IllegalStateException("Level with identifier %s does not exist.".formatted(getIdentifier()));

        return NMSPlatform.getUtils().getServer().getLevel(resourceKey);
    }

    private Heightmap.Types convertHeightMapType(@NotNull HeightMapType heightMapType) {

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

            case null, default -> {
                throw new IllegalArgumentException("I don't know how, but somehow a HeightMapType that doesn't exist has been passed.");
            }
        }

    }

    private ParticleOptions convertParticle(@NotNull Particle particle) {

        switch (particle) {
            case SIGMA -> {
                return ParticleTypes.ANGRY_VILLAGER;
            }
            case null, default -> {
                throw new IllegalArgumentException("I don't know how, but somehow a Particle that doesn't exist has been passed.");
            }
        }
    }

}
