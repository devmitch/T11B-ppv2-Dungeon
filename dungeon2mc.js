// $Id$
/*
 * Copyright (c) 2011 Bentech
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

importPackage(Packages.com.sk89q.worldedit);
importPackage(Packages.com.sk89q.worldedit.math);
importPackage(Packages.com.sk89q.worldedit.blocks);
importClass(Packages.com.sk89q.worldedit.world.block.BaseBlock);
importPackage(Packages.com.sk89q.jnbt);
var blocks = context.remember();
var session = context.getSession();
var player = context.getPlayer();

origin = player.getBlockIn().toVector().toBlockPoint();
player.print("Starting script to construct dungeon");

var fileObj = new java.io.File("dungeon2mc.txt");
var scanner = new java.util.Scanner(fileObj);

while (scanner.hasNextLine()) {
    var strings = scanner.nextLine().split(";");
    var type = strings[0];
    var x = Number(strings[1]);
    var z = Number(strings[2]);
    if (type.equals("wall")) {
        for (i = 0; i <= 3; i++) {
            blocks.setBlock(origin.add(x, i, z), context.getBlock("cobblestone"));
        }
    } else if (type.equals("door")) {
        blocks.setBlock(origin.add(x, 0, z), context.getBlock("minecraft:oak_fence_gate[facing=north]"));
    } else if (type.equals("enemy")) {
        setCMDBLK(x, z, "summon minecraft:zombie ~ ~4 ~")
    } else if (type.equals("wizard")) {
        setCMDBLK(x, z, "summon minecraft:witch ~ ~4 ~")
    } else if (type.equals("sword")) {
        setCMDBLK(x, z, "/summon minecraft:item ~ ~4 ~ {Item:{id:\"minecraft:diamond_sword\",Count:1}}")
    } else if (type.equals("treasure")) {
        setCMDBLK(x, z, "/summon minecraft:item ~ ~4 ~ {Item:{id:\"minecraft:gold_ingot\",Count:1}}")
    }
}

//context.checkArgs(1, 1, "<type>");

//var blocktype = context.getBlock(argv[1]);

function setCMDBLK(x, z, cmd) {
    dirtpt = origin.add(x, -4, z);
    blocks.setBlock(dirtpt, context.getBlock("dirt"));
    redstonept = origin.add(x, -3, z);
    blocks.setBlock(redstonept, context.getBlock("redstone_torch"));
    
    pt = origin.add(x, -2, z);
    context.getBlock("command_block").getId
    
    var newTags = {}
    newTags["id"] = new StringTag("command_block");
    newTags["Command"] = new StringTag(cmd);
    newTags["x"] = new IntTag(origin.add(x, -2, z).getX());
    newTags["y"] = new IntTag(origin.add(x, -2, z).getY());
    newTags["z"] = new IntTag(origin.add(x, -2, z).getZ());
    var newCompoundTag = new CompoundTag(newTags);
    
    var commandBlock = context.getBlock("command_block").toBaseBlock(newCompoundTag);
    blocks.setBlock(pt, commandBlock);
}