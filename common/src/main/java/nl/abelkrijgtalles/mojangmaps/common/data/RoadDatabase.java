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

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.model.Edge;
import nl.abelkrijgtalles.mojangmaps.common.model.Node;

/**
 * The road database currently consists out of the edges-table.
 * Edges can be compared with roads in the old system.
 * <p>
 * Differences with the old system:
 * - In the old system there could be more than 1 location per block, which cannot happen in this system.
 * - Roads consisted out of an array of locations, while an edge just connects two nodes/points.
 * <p>
 * Why?
 * Everytime I try to write a pathfinding algorithm, it fails in the weirdest way, and this is graphhopper's system, which we're using under the hood.
 * <p>
 * Format:
 * Format of data string: [x1]|[y1]|z1]|[World identifier]|[x2]|[y2]|[z2]|[World identifier]
 * (please replace anything between brackets ([]) with its respective value)
 */
public class RoadDatabase {

    private final Dao<DatabaseEdge, String> edgesTableDao;

    public RoadDatabase() throws SQLException {

        ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:sqlite:" + Path.of(MojangMaps.loaderInfo.getDataDirectory().toAbsolutePath().toString(), MojangMaps.config.databasePath + ".db"));
        TableUtils.createTableIfNotExists(connectionSource, DatabaseEdge.class);
        edgesTableDao = DaoManager.createDao(connectionSource, DatabaseEdge.class);

    }

    public void addEdge(Node node1, Node node2) throws SQLException {

        addEdge(new Edge(node1, node2));

    }

    public void addEdge(Node node1, Node node2, String name) throws SQLException {

        addEdge(new Edge(node1, node2, name));

    }

    public void addEdge(Edge edge) throws SQLException {

        DatabaseEdge databaseEdge = new DatabaseEdge();
        databaseEdge.setEdge(edge.toString());
        if (!edge.getName().isBlank()) {
            databaseEdge.setName(edge.getName());
        }
        edgesTableDao.create(databaseEdge);

    }

    public List<Edge> getEdges() throws SQLException {

        List<Edge> edges = new ArrayList<>();

        for (DatabaseEdge databaseEdge : edgesTableDao.queryForAll()) {

            edges.add(Edge.fromCompactFlooredString(databaseEdge.getEdge(), databaseEdge.getName()));

        }

        return edges;

    }

}
