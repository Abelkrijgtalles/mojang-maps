/*
 * mojang_maps.neoforge.main
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

package nl.abelkrijgtalles.mojangmaps.neoforge;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.command.Commands;
import nl.abelkrijgtalles.mojangmaps.neoforge.platform.NeoforgeLoaderInfo;
import nl.abelkrijgtalles.mojangmaps.nms.platform.command.NMSCommands;

@Mod(MojangMaps.MOD_ID)
public class NeoforgeMojangMaps {

    public static MinecraftServer MINECRAFT_SERVER = null;

    public NeoforgeMojangMaps() {

        NeoForge.EVENT_BUS.addListener(this::onServerStarting);

        MojangMaps.init(new NeoforgeLoaderInfo(false));
    }

    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {

        NMSCommands commands = new NMSCommands();
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        commands.register(dispatcher, Commands.getCommands());

    }

    private void onServerStarting(ServerStartedEvent event) {

        MINECRAFT_SERVER = event.getServer();

    }

}
