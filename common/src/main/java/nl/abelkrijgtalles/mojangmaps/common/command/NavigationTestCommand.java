/*
 * mojang-maps.common.main
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

package nl.abelkrijgtalles.mojangmaps.common.command;

import nl.abelkrijgtalles.mojangmaps.common.model.Navigator;
import nl.abelkrijgtalles.mojangmaps.common.model.Node;
import nl.abelkrijgtalles.mojangmaps.platform.command.Command;
import nl.abelkrijgtalles.mojangmaps.platform.command.CommandSource;

public class NavigationTestCommand implements Command {

    @Override
    public boolean run(CommandSource source) {

        new Navigator().calculatePath(new Node(15.15, 65, 20.2, "balls"), new Node(15.25, 92, 20.21, "balls"));
        return true;
    }

    @Override
    public String getCommand() {

        return "tast";
    }

}
