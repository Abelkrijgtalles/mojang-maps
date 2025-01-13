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

package nl.abelkrijgtalles.mojangmaps.nms.platform;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import java.util.List;
import net.minecraft.commands.CommandSourceStack;
import nl.abelkrijgtalles.mojangmaps.platform.command.Command;
import nl.abelkrijgtalles.mojangmaps.platform.command.CommandSource;
import nl.abelkrijgtalles.mojangmaps.platform.command.Permission;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

public class NMSCommands {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void register(CommandDispatcher<CommandSourceStack> dispatcher, List<Command> commands) {

        for (Command command : commands) {

            dispatcher.register((LiteralArgumentBuilder)
                    literal(command.getCommand())
                            .executes(context -> {
                                CommandSourceStack nativeSource = (CommandSourceStack) context.getSource();
                                CommandSource source = new CommandSource(new Permission(!nativeSource.isPlayer()));

                                return command.run(source) ? 1 : 0;

                            })
            );

        }

    }

}
