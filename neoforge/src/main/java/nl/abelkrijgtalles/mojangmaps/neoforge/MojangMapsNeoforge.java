/*
 * mojang_maps.neoforge.main
 * Copyright (C) 2024 Abel van Hulst/Abelkrijgtalles/Abelpro678
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
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.command.Commands;

@Mod(MojangMaps.MOD_ID)
public class MojangMapsNeoforge {

    public MojangMapsNeoforge(
            #if MC_VER > MC_1_20_4
            net.neoforged.fml.ModContainer modContainer
            #endif ) {

        #if MC_VER > MC_1_20_4
        modContainer.registerConfig(ModConfig.Type.SERVER, NeoforgeConfig.CONFIG);
        #else
        net.neoforged.fml.ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, NeoforgeConfig.CONFIG);
        #endif

        MojangMaps.init(new LoaderInfoNeoforge(false));
    }

    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {

        Commands commands = new Commands();
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        commands.register(dispatcher);

    }

}
