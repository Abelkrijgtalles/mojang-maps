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

package nl.abelkrijgtalles.mojangmaps.sponge.platform.world;

import nl.abelkrijgtalles.mojangmaps.platform.world.HeightMapType;
import nl.abelkrijgtalles.mojangmaps.platform.world.Level;
import nl.abelkrijgtalles.mojangmaps.platform.world.Particle;
import nl.abelkrijgtalles.mojangmaps.platform.world.Vec3;
import nl.abelkrijgtalles.mojangmaps.platform.world.WorldBorder;
import nl.abelkrijgtalles.mojangmaps.sponge.platform.util.SpongeConversion;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.api.ResourceKey;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.world.server.ServerWorld;
import org.spongepowered.math.vector.Vector3d;

public class SpongeLevel implements Level {

    private final ResourceKey resourceKey;

    public SpongeLevel(ResourceKey resourceKey) {

        this.resourceKey = resourceKey;
    }

    public SpongeLevel(ServerWorld world) {

        this(world.key());

    }

    @Override
    public String getIdentifier() {

        return resourceKey.asString();
    }

    @Override
    public int getHeightAtLocation(@NotNull HeightMapType type, int x, int z) {

        return getWorld().height(SpongeConversion.heightMapType(type), x, z);
    }

    @Override
    public void spawnParticle(@NotNull Particle particle, @NotNull Vec3 location, int count) {

        ParticleEffect effect = ParticleEffect.builder()
                .type(SpongeConversion.particle(particle))
                .quantity(count)
                .build();
        getWorld().spawnParticles(effect, new Vector3d(location.x, location.y, location.z));

    }

    @Override
    public WorldBorder getWorldBorder() {

        return new SpongeWorldBorder(getWorld().border());
    }

    private ServerWorld getWorld() {

        if (Sponge.server().worldManager().world(resourceKey).isEmpty())
            throw new IllegalStateException("World with identifier %s does not exist.".formatted(getIdentifier()));

        return Sponge.server().worldManager().world(resourceKey).get();

    }

}
