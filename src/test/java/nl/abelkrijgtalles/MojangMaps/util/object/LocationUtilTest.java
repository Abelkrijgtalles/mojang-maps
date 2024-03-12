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

package nl.abelkrijgtalles.MojangMaps.util.object;

import be.seeseemelk.mockbukkit.MockBukkit;
import be.seeseemelk.mockbukkit.ServerMock;
import be.seeseemelk.mockbukkit.WorldMock;
import java.util.List;
import nl.abelkrijgtalles.MojangMaps.MojangMaps;
import nl.abelkrijgtalles.MojangMaps.util.file.NodesConfigUtil;
import org.bukkit.Location;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocationUtilTest {

    private ServerMock server;

    @AfterEach
    public void tearDown() {

        MockBukkit.unmock();
    }

    @BeforeEach
    public void setUp() {

        server = MockBukkit.mock();
        MojangMaps plugin = MockBukkit.load(MojangMaps.class);

    }

    @Test
    public void getClosestLocationTest() {

        WorldMock world = server.addSimpleWorld("world");

        // location1 is the closest
        Location baseLocation = new Location(world, 0, 0, 0);
        Location baseLocation2 = new Location(world, 250, 0, 250);
        Location location1 = new Location(world, 69, 0, 69);
        Location location2 = new Location(world, 420, 0, 420);

        NodesConfigUtil.addLocation(location1);
        NodesConfigUtil.addLocation(location2);

        List<Location> locations = NodesConfigUtil.getLocations();

        Assertions.assertEquals(location1, LocationUtil.getClosestLocation(locations, baseLocation));
        Assertions.assertNotEquals(location2, LocationUtil.getClosestLocation(locations, baseLocation));

        Assertions.assertEquals(location2, LocationUtil.getClosestLocation(locations, baseLocation2));
        Assertions.assertNotEquals(location1, LocationUtil.getClosestLocation(locations, baseLocation2));

    }

}