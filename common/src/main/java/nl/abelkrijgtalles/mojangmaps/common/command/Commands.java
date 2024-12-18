/*
 * mojang_maps.common.main
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

package nl.abelkrijgtalles.mojangmaps.common.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.pathfinding.AStar;
import nl.abelkrijgtalles.mojangmaps.pathfinding.platform.Location;
import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

public class Commands {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void register() {

        CommandDispatcher<CommandSourceStack> dispatcher = new CommandDispatcher();

        dispatcher.register((LiteralArgumentBuilder)
                literal("test")
                        .executes(context -> {
                            CommandSourceStack source = (CommandSourceStack) context.getSource();
                            AStar aStar = new AStar(new Location(
                                    new Vec3(0, 60, 0), source.getLevel()
                            ), new Location(
                                    new Vec3(2, 60, 30), source.getLevel()
                            ));

                            for (Location location : aStar.getPath()) {

                                MojangMaps.LOGGER.info(location.toString());
                                location.getLevel().setBlock(location.getPositionAsBlockPos(), Blocks.GLASS.defaultBlockState(), 3);

                            }

                            return 1;
                        })
        );

    }

}
