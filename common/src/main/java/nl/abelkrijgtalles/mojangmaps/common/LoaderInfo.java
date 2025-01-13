/*
 * mojang_maps.common.main
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

package nl.abelkrijgtalles.mojangmaps.common;

import net.minecraft.server.MinecraftServer;
import nl.abelkrijgtalles.mojangmaps.common.config.Config;
import nl.abelkrijgtalles.mojangmaps.platform.Platform;

public interface LoaderInfo {

    /**
     * @return The Mojang Maps {@link Config}.
     */
    Config getConfig();

    /**
     * @return Whether Mojang Maps is being run through unit tests.
     */
    boolean isRunningTests();

    /**
     * @return The current {@link MinecraftServer}.
     */
    MinecraftServer getMinecraftServer();

    /**
     * @return Whether all variables can actually be used.
     */
    default boolean isReady() {

        return getMinecraftServer() != null;

    }

    /**
     * @return The {@link Platform} object, which contains most of the needed platform code. You can see it as the Bukkit class, but as an interface.
     */
    Platform getPlatform();

}
