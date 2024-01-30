/*
 * MojangMaps
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

package nl.abelkrijgtalles.MojangMaps.util.other;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;

public class CommandUtil {

    private final MojangMaps plugin;

    public CommandUtil(MojangMaps plugin) {

        this.plugin = plugin;
    }

    // Fixed by @frengor: https://github.com/AlessioDP/libby/issues/39#issuecomment-1915524475
    public void loadCommandAPI() {

        CommandAPI.onLoad(new CommandAPIBukkitConfig(plugin));

    }

}
