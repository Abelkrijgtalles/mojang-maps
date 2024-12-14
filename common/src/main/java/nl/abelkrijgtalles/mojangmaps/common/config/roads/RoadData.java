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

package nl.abelkrijgtalles.mojangmaps.common.config.roads;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Deflater;
import net.minecraft.core.Position;
import net.minecraft.world.phys.Vec3;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.model.Road;
import org.apache.commons.lang3.ArrayUtils;

public class RoadData {

    // To see the specs for roads.mmd, see roads.mmd_spec.md in this folder/package.

    public static List<Road> roads = List.of(
            new Road("Test road", "world", List.of(
                    new Vec3(0, 60, 0),
                    new Vec3(0, 60, 5)
            )),
            new Road("Unnamed Road", "world", List.of(
                    new Vec3(0, 60, 0),
                    new Vec3(0, 60, 5)
            )),
            new Road("Exploring the nether or something like that", "world_nether", List.of(
                    new Vec3(0, 60, 0),
                    new Vec3(0, 60, 5)
            ))
    );

    public static class Generator {

        private final static String MESSAGE = """
                ---
                DO NOT DELETE THIS FILE!!!
                This rest of this file may look like gibberish, but it's not. This stores all the road data for Mojang Maps.
                If you delete this file, you'll delete all your Mojang Maps data and essentially start from scratch.
                ---
                """;

        public void generateRoadData(List<Road> roads) {

            List<Byte> bytes = new ArrayList<>();

            bytes.add((byte) 0x01);

            List<Byte> roadsDataBytes = new ArrayList<>();

            for (Road road : roads) {

                List<Byte> roadBytes = new ArrayList<>();

                roadBytes.addAll(generateByteListOfStringWithDividingBit(road.getName()));
                roadBytes.addAll(generateByteListOfStringWithDividingBit(road.getWorldName()));

                List<Byte> waypointBytes = new ArrayList<>();

                for (Position waypoint : road.getWaypoints()) {

                    waypointBytes.addAll(byteArrayToByteList(ByteBuffer.allocate(8).putDouble(waypoint.x()).array()));
                    waypointBytes.addAll(byteArrayToByteList(ByteBuffer.allocate(8).putDouble(waypoint.y()).array()));
                    waypointBytes.addAll(byteArrayToByteList(ByteBuffer.allocate(8).putDouble(waypoint.z()).array()));

                }

                roadBytes.addAll(byteArrayToByteList(ByteBuffer.allocate(4).putInt(waypointBytes.size()).array()));
                roadBytes.addAll(waypointBytes);

                roadsDataBytes.addAll(byteArrayToByteList(ByteBuffer.allocate(4).putInt(roadBytes.size()).array()));
                roadsDataBytes.addAll(roadBytes);

            }

            bytes.addAll(byteArrayToByteList(ByteBuffer.allocate(4).putInt(roadsDataBytes.size()).array()));
            bytes.addAll(roadsDataBytes);

            // To file conversion

            byte[] byteArray = new byte[bytes.size()];
            for (int i = 0; i < bytes.size(); i++) {
                byteArray[i] = bytes.get(i);
            }

            try {
                if (!MojangMaps.loaderInfo.getConfig().getDataDirectory().toFile().exists()) {
                    MojangMaps.loaderInfo.getConfig().getDataDirectory().toFile().mkdirs();
                }

                if (Path.of(MojangMaps.loaderInfo.getConfig().getDataDirectory().toString(), "roads.mmd").toFile().exists()) {
                    Path.of(MojangMaps.loaderInfo.getConfig().getDataDirectory().toString(), "roads.mmd").toFile().createNewFile();
                }

                Deflater deflater = new Deflater();
                deflater.setInput(byteArray);
                deflater.finish();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                while (!deflater.finished()) {

                    int compressedSize = deflater.deflate(buffer);
                    byteArrayOutputStream.write(buffer, 0, compressedSize);

                }

                OutputStream outputStream = new FileOutputStream(Path.of(MojangMaps.loaderInfo.getConfig().getDataDirectory().toString(), "roads.mmd").toFile());
                outputStream.write(ArrayUtils.addAll(MESSAGE.getBytes(StandardCharsets.UTF_8), ArrayUtils.addAll(byteArrayOutputStream.toByteArray())));
                outputStream.close();

            } catch (IOException e) {
                MojangMaps.LOGGER.error("Couldn't generate roads.mmd.");
                throw new RuntimeException(e);
            }
        }

        private byte generateByteThatIsntUsed(List<Byte> bytes) {

            if (!bytes.contains((byte) 0x69)) return (byte) 0x69;

            byte currentByte = (byte) 0x00;
            while (true) {

                currentByte += 1;
                if (!bytes.contains(currentByte)) return currentByte;
                if (currentByte == Byte.MAX_VALUE) {

                    // TODO: maybe add multiple bytes in this case?
                    MojangMaps.LOGGER.error("Could not find an unused byte.");
                    throw new RuntimeException();

                }

            }

        }

        private List<Byte> generateByteListOfStringWithDividingBit(String string) {

            List<Byte> bytes = new ArrayList<>();

            List<Byte> stringBytes = Arrays.asList(ArrayUtils.toObject(string.getBytes(StandardCharsets.UTF_8)));
            byte dividingBit = generateByteThatIsntUsed(stringBytes);
            bytes.add(dividingBit);
            bytes.addAll(stringBytes);
            bytes.add(dividingBit);

            return bytes;

        }

        private List<Byte> byteArrayToByteList(byte[] array) {

            List<Byte> bytes = new ArrayList<>();

            for (byte byyte : array) {

                bytes.add(byyte);

            }

            return bytes;
        }

    }

}
