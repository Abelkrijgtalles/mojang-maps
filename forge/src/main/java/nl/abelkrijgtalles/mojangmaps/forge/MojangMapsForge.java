/*
 * mojang_maps.forge.main
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

package nl.abelkrijgtalles.mojangmaps.forge;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;

@Mod(MojangMaps.MOD_ID)
public class MojangMapsForge {

    public MojangMapsForge() {

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ForgeConfig.CONFIG);

        MojangMaps.init(new LoaderInfoForge(false));
    }

}