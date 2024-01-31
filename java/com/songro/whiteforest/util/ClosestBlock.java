package com.songro.whiteforest.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.List;

public class ClosestBlock {

    /**
     * Finds the closest block in a vertical column.
     * @param origin The location around which to search.
     *               This location will NOT be included in the search, but all other locations in the column will.
     * @param types  A Set (preferably a HashSet) that contains the type IDs of blocks to search for
     * @return The closest block, or null if one was not found in the column.
     *         In the case of a tie, the higher block wins.
     */
    public boolean isLocationNearBlock(Location loc, List<Integer> blocks, int radius) {

        final World world = loc.getWorld();
        for (int y = 1; y > -radius; y--) {
            for (int x = 1; x > -radius; x--) {
                for (int z = 1; z > -radius; z--) {
                    Block scan = world
                            .getBlockAt(
                                    (int) loc
                                            .getX()
                                            + x,
                                    (int) loc
                                            .getY()
                                            + y,
                                    (int) loc
                                            .getZ()
                                            + z);
                    if (blocks.contains(scan
                            .getType().getId())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

}
