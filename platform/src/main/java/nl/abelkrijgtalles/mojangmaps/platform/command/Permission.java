/*
 * mojang_maps.platform.main
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

package nl.abelkrijgtalles.mojangmaps.platform.command;

public class Permission {

    private final boolean console;

    public Permission(boolean console) {

        this.console = console;
    }

    /**
     * @return Whether the permission allows executing from the console. If this permission is used in {@link CommandSource}, it's whether it's executed from the console.
     */
    public boolean console() {

        return console;
    }

    public boolean containsPermission(Permission permission) {

        if (console) return true;
        return !permission.console();

    }

}
