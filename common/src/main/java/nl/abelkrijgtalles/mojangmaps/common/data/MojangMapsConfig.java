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

package nl.abelkrijgtalles.mojangmaps.common.data;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;

@Configuration
public class MojangMapsConfig {

    @Comment({"The database will be stored in the following path: [config folder]/[database path].db, where [config folder] is the folder where this file is located, and [database path] the following value."})
    public String databasePath = "roads";

}
