/*
 *    This file is part of the Universal Mod Template
 *    licensed under the GNU GPL v3 License.
 *    (some parts of this file are originally from the Distant Horizons mod by James Seibel)
 *
 *    Copyright (C) 2024  Leander Knüttel
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.example_mod.neoforge;


import net.neoforged.fml.common.Mod;
import nl.abelkrijgtalles.example_mod.common.ExampleMod;

@Mod(ExampleMod.MOD_ID)
public class NeoforgeMain
{
	public NeoforgeMain()
	{
		ExampleMod.init();
	}
	
}
