/*
 * mojang-maps.fabric.gametest
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

import net.fabricmc.fabric.api.gametest.v1.GameTest;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.network.chat.Component;
import nl.abelkrijgtalles.mojangmaps.platform.world.Particle;

public class NMSConversionTest {

    @GameTest
    public void checkIfAllParticlesAreCorrectlyConverted(GameTestHelper gameTestHelper) {

        for (Particle particle : Particle.values()) {

            try {

                NMSConversion.particle(particle);

            } catch (IllegalArgumentException e) {

                throw gameTestHelper.assertionException(Component.literal(particle.name()));

            }

        }

    }

    @GameTest
    public void checkIfThereAreExceptionsInParticlesByConvertingInReverse(GameTestHelper gameTestHelper) {

        BuiltInRegistries.PARTICLE_TYPE.forEach(particleType -> {
            try {

                Particle.valueOf(BuiltInRegistries.PARTICLE_TYPE.getKey(particleType).getPath().toUpperCase());

            } catch (IllegalArgumentException e) {

                throw gameTestHelper.assertionException(Component.literal(String.valueOf(BuiltInRegistries.PARTICLE_TYPE.getKey(particleType))));

            }
        });

    }

}
