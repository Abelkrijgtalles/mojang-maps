/*
 * nl.abelkrijgtalles.mojangmaps.mojang_maps.fabric.main
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

package nl.abelkrijgtalles.mojangmaps.fabric;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.command.Commands;
import nl.abelkrijgtalles.mojangmaps.fabric.platform.FabricLoaderInfo;
import nl.abelkrijgtalles.mojangmaps.nms.platform.command.NMSCommands;

public class FabricMojangMaps implements DedicatedServerModInitializer {

    private static final boolean isRunningTests = false;
    public static MinecraftServer MINECRAFT_SERVER = null;

    public static void init() {

        CommandRegistrationCallback.EVENT.register((commandDispatcher, commandBuildContext, commandSelection) -> {

            NMSCommands commands = new NMSCommands();
            commands.register(commandDispatcher, Commands.getCommands());

        });

        MojangMaps.init(new FabricLoaderInfo(isRunningTests));

    }

    @Override
    public void onInitializeServer() {

        ServerTickEvents.END_SERVER_TICK.register(this::onEndTick);

        init();
    }

    public void onEndTick(MinecraftServer minecraftServer) {

        MINECRAFT_SERVER = minecraftServer;

    }

}
