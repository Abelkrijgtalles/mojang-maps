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
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.ParticleUtils;
import nl.abelkrijgtalles.mojangmaps.nms.platform.NMSPlatform;
import nl.abelkrijgtalles.mojangmaps.nms.platform.util.NMSConversion;
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

        return getNMSLevel().getHeight(NMSConversion.heightMapType(type), x, z);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Vec3 location, int count) {

        ParticleUtils.spawnParticles(getNMSLevel(), new BlockPos((int) Math.floor(location.x), (int) Math.floor(location.y), (int) Math.floor(location.z)), count, 3, 1, true, NMSConversion.particle(particle));
    }

    private net.minecraft.world.level.Level getNMSLevel() {

        if (getNMSLevel() == null)
            throw new IllegalStateException("Level with identifier %s does not exist.".formatted(getIdentifier()));

        return NMSPlatform.getUtils().getServer().getLevel(resourceKey);
    }

}
