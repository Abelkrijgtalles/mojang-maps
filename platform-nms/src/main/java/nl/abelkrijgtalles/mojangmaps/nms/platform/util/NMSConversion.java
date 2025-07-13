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
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Particle;
import org.jetbrains.annotations.NotNull;

public class NMSConversion {

    public static ParticleOptions particle(@NotNull Particle particle) {

        try {
            return (ParticleOptions) BuiltInRegistries.PARTICLE_TYPE.getValue(ResourceLocation.withDefaultNamespace(particle.name().toLowerCase()));
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Heightmap.Types heightMapType(@NotNull HeightMapType heightMapType) {

        try {
            return Heightmap.Types.valueOf(heightMapType.name());
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }

}
