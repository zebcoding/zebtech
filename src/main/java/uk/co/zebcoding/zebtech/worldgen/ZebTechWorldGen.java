package uk.co.zebcoding.zebtech.worldgen;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import uk.co.zebcoding.zebtech.blocks.ZBlocks;

import java.util.Random;

public class ZebTechWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world,
                         IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.dimensionId) {
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);

            case -1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);

            case 1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateSurface(World world, Random random, int x, int z) {
        // this.addOreSpawn(Blocks.xOre, world, random, x, z, 16, 16,
        // veinMin+random.nextInt(maxVein), chance%, miny, maxy);
        this.addOreSpawn(ZBlocks.tinOre, world, random, x, z, 16, 16,
                3 + random.nextInt(6), 25, 30, 100);
        this.addOreSpawn(ZBlocks.leadOre, world, random, x, z, 16, 16,
                2 + random.nextInt(5), 20, 25, 55);
        this.addOreSpawn(ZBlocks.copperOre, world, random, x, z, 16, 16,
                3 + random.nextInt(6), 25, 30, 100);
        this.addOreSpawn(ZBlocks.zechoriumOre, world, random, x, z, 16, 16,
                5 + random.nextInt(5), 5, 5, 25);
    }

    private void generateNether(World world, Random random, int x, int z) {

    }

    private void generateEnd(World world, Random random, int x, int z) {

    }

    private void addOreSpawn(Block block, World world, Random random,
                             int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize,
                             int spawnChance, int minY, int maxY) {
        for (int i = 0; i < spawnChance; i++) {
            int posX = blockXPos + random.nextInt(maxX);
            int posY = minY + random.nextInt(maxY - minY);
            int posZ = blockZPos + random.nextInt(maxZ);
            (new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
        }

    }
}
