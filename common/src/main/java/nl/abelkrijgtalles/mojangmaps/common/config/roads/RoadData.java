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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import net.minecraft.core.Position;
import net.minecraft.world.phys.Vec3;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.model.Road;
import org.apache.commons.lang3.ArrayUtils;

public class RoadData {

    // To see the specs for roads.mmd, see roads.mmd_spec.md in this folder/package.

    public final static List<Road> roads = List.of(
            new Road("Test road", "world", List.of(
                    new Vec3(0, 60, 0),
                    new Vec3(0, 60, 5)
            )),
            new Road("Unnamed Road", "world", List.of(
                    new Vec3(1, 2, 3),
                    new Vec3(4, 5, 6)
            )),
            new Road("Exploring the nether or something like that", "world_nether", List.of(
                    new Vec3(46, 62, 56),
                    new Vec3(6, 60, 55555)
            ))
    );
    private final static String MESSAGE = """
            ---
            DO NOT DELETE THIS FILE!!!
            This rest of this file may look like gibberish, but it's not. This stores all the road data for Mojang Maps.
            If you delete this file, you'll delete all your Mojang Maps data and essentially start from scratch.
            ---
            """;
    private final static Path FILE_PATH = Path.of(MojangMaps.loaderInfo.getConfig().getDataDirectory().toString(), "roads.mmd");

    public void generateRoadData(List<Road> roads) {

        List<Byte> bytes = new ArrayList<>();

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

            if (FILE_PATH.toFile().exists()) {
                FILE_PATH.toFile().createNewFile();
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

            OutputStream outputStream = new FileOutputStream(FILE_PATH.toFile());
            outputStream.write(
                    ArrayUtils.addAll(ArrayUtils.addAll(MESSAGE.getBytes(StandardCharsets.UTF_8),
                                    // version marking
                                    new byte[]{0x06, 0x01, 0x07}),
                            ArrayUtils.addAll(byteArrayOutputStream.toByteArray())));
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

    public List<Road> readRoadData() {

        byte[] files;

        try {
            files = Files.readAllBytes(FILE_PATH);
        } catch (IOException e) {
            MojangMaps.LOGGER.error("Couldn't read {}.", FILE_PATH);
            throw new RuntimeException(e);
        }

        int version = 0;
        byte[] compressedArray = null;

        for (int i = 0; i < files.length; i++) {

            switch (files[i]) {

                case 0x06:
                    version = files[i + 1];
                case 0x07:
                    compressedArray = Arrays.copyOfRange(files, i + 1, files.length);

            }


        }

        assert version != 0;

        Inflater inflater = new Inflater();
        inflater.setInput(compressedArray);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        while (!inflater.finished()) {
            int decompressedSize = 0;
            try {
                decompressedSize = inflater.inflate(buffer);
            } catch (DataFormatException e) {
                MojangMaps.LOGGER.error("Couldn't inflate/decompress {}", FILE_PATH);
                throw new RuntimeException(e);
            }
            outputStream.write(buffer, 0, decompressedSize);
        }

        byte[] uncompressedData = outputStream.toByteArray();
        int roadsDataSize = ByteBuffer.wrap(getFirstNBytes(uncompressedData, 0, 4)).getInt();
        if (roadsDataSize == 0) return new ArrayList<>();
        byte[] roadData = Arrays.copyOfRange(uncompressedData, 4, roadsDataSize);

        // i very dumb so claude go help me
        int offset = 0;
        List<Road> roads = new ArrayList<>();

        while (offset < roadData.length) {
            // Road data size (4 bytes)
            int roadDataSize = ByteBuffer.wrap(Arrays.copyOfRange(roadData, offset, offset + 4)).getInt();
            offset += 4;

            // Road name begin byte
            byte roadNameBegin = roadData[offset];
            offset++;

            // Road name
            List<Byte> roadNameBytes = new ArrayList<>();
            while (roadData[offset] != roadNameBegin) {
                roadNameBytes.add(roadData[offset]);
                offset++;
            }
            String roadName = new String(convertByteListToArray(roadNameBytes), StandardCharsets.UTF_8);
            offset++; // skip road name end byte

            // Road world begin byte
            byte roadWorldBegin = roadData[offset];
            offset++;

            // Road world identification
            List<Byte> roadWorldBytes = new ArrayList<>();
            while (roadData[offset] != roadWorldBegin) {
                roadWorldBytes.add(roadData[offset]);
                offset++;
            }
            String roadWorld = new String(convertByteListToArray(roadWorldBytes), StandardCharsets.UTF_8);
            offset++; // skip road world end byte

            // Waypoints data size
            int waypointsDataSize = ByteBuffer.wrap(Arrays.copyOfRange(roadData, offset, offset + 4)).getInt();
            offset += 4;

            // Parse waypoints
            List<Position> waypoints = new ArrayList<>();
            int waypointOffset = 0;
            while (waypointOffset < waypointsDataSize) {
                double x = ByteBuffer.wrap(Arrays.copyOfRange(roadData, offset + waypointOffset, offset + waypointOffset + 8)).getDouble();
                waypointOffset += 8;
                double y = ByteBuffer.wrap(Arrays.copyOfRange(roadData, offset + waypointOffset, offset + waypointOffset + 8)).getDouble();
                waypointOffset += 8;
                double z = ByteBuffer.wrap(Arrays.copyOfRange(roadData, offset + waypointOffset, offset + waypointOffset + 8)).getDouble();
                waypointOffset += 8;

                waypoints.add(new Vec3(x, y, z));
            }
            offset += waypointsDataSize;

            roads.add(new Road(roadName, roadWorld, waypoints));
        }

        return roads;

    }

    private byte[] getFirstNBytes(byte[] bytes, int index, int first) {

        byte[] firstBytes = new byte[first];
        System.arraycopy(bytes, index, firstBytes, 0, first);

        return firstBytes;

    }

    private byte[] convertByteListToArray(List<Byte> byteList) {

        byte[] byteArray = new byte[byteList.size()];
        for (int i = 0; i < byteList.size(); i++) {
            byteArray[i] = byteList.get(i);
        }
        return byteArray;

    }

    public List<Road> setupRoadData() {

        if (FILE_PATH.toFile().exists()) return readRoadData();
        else {

            generateRoadData(new ArrayList<>());
            return new ArrayList<>();

        }

    }

}
