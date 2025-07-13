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
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.world.HeightType;
import org.spongepowered.api.world.HeightTypes;

public class SpongeConversion {

    public static HeightType heightMapType(@NotNull HeightMapType heightMapType) {

        try {

            return HeightTypes.registry().findValue(ResourceKey.sponge(heightMapType.name().toLowerCase())).get();

        } catch (Exception e) {
            throw new IllegalArgumentException("I don't know how, but somehow a HeightMapType that doesn't exist has been passed.");
        }

    }

    public static ParticleType particle(@NotNull Particle particle) {

        try {

            return ParticleTypes.registry().findValue(ResourceKey.minecraft(particle.name().toLowerCase())).get();

        } catch (Exception e) {
            throw new IllegalArgumentException("I don't know how, but somehow a Particle that doesn't exist has been passed.");
        }

    }

}
