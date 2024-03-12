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

package nl.abelkrijgtalles.MojangMaps.command.using;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandArguments;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import nl.abelkrijgtalles.MojangMaps.object.ActiveNavigation;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class StartNavigationCommand {

    public static List<ActiveNavigation> activeNavigations = new ArrayList<>();

    public StartNavigationCommand(Player p, CommandArguments args) throws WrapperCommandSyntaxException {

        if (playerIsNavigating(p.getUniqueId())) {

            throw CommandAPI.failWithString("You are already navigating to another place.");

        }

        Location beginning = p.getLocation().toBlockLocation();
        ActiveNavigation activeNavigation = new ActiveNavigation(p.getUniqueId(), beginning, (Location) args.get("location"));
        activeNavigations.add(activeNavigation);

    }

    public static List<UUID> getNavigatingPlayers() {

        List<UUID> players = new ArrayList<>();

        for (ActiveNavigation activeNavigation : activeNavigations) {

            players.add(activeNavigation.getPlayer());

        }

        return players;

    }

    public static boolean playerIsNavigating(UUID p) {

        for (UUID navigatingPlayer : getNavigatingPlayers()) {

            if (p == navigatingPlayer) {

                return true;

            }

        }

        return false;

    }

    public static ActiveNavigation playerIsNavigatingWithActiveNavigation(UUID p) {

        for (ActiveNavigation activeNavigation : activeNavigations) {

            if (p == activeNavigation.getPlayer()) {

                return activeNavigation;

            }

        }

        return null;

    }

}
