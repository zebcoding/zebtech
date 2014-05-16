package uk.co.zebcoding.zebtech.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import uk.co.zebcoding.zebtech.help.Reference;
import uk.co.zebcoding.zebtech.help.RegisterHelper;

public class ZBlocks {

    public static Block tinOre;
    public static Block leadOre;
    public static Block copperOre;
    public static Block zechoriumOre;
    public static Block tinBlock;
    public static Block leadBlock;
    public static Block copperBlock;
    public static Block liquidZechorium;
    public static Block alloySmelterCoalIdle;
    public static Block alloySmelterCoalActive;
    public static Block alloySmelterBasicIdle;
    public static Block alloySmelterBasicActive;
    public static Block rockPummelerBasicIdle;
    public static Block rockPummelerBasicActive;
    public static Block zechoriumExciterIdle;
    public static Block zechoriumExciterActive;
    public static Block zechoriumInfuserIdle;
    public static Block zechoriumInfuserActive;
    public static Block zechoriumCompressorIdle;
    public static Block zechoriumCompressorActive;
    public static Block infusedIronBlock;
    public static Block pipe;
    public static Block tank;

    public static final int guiIDAlloySmelterCoal = 0;
    public static final int guiIDAlloySmelterBasic = 1;
    public static final int guiIDRockPummelerBasic = 2;
    public static final int guiIDZechoriumExciter = 3;
    public static final int guiIDZechoriumInfuser = 4;
    public static final int guiIDZechoriumCompressor = 5;

    public static void init() {
        final Block[] blocks = new Block[]{
                tinOre = new BlockOre("tinOre"),
                leadOre = new BlockOre("leadOre"),
                copperOre = new BlockOre("copperOre"),
                zechoriumOre = new BlockOre("zechoriumOre"),
                tinBlock = new BlockTin(),
                leadBlock = new BlockLead(),
                copperBlock = new BlockCopper(),
                infusedIronBlock = new BlockInfusedIron(),
                liquidZechorium = new BlockLiquidZechorium(),
                alloySmelterCoalIdle = new BlockAlloySmelterCoal(false).setHardness(3.5F).setResistance(10.0F),
                alloySmelterCoalActive = new BlockAlloySmelterCoal(true).setHardness(3.5F).setResistance(10.0F),
                alloySmelterBasicIdle = new BlockAlloySmelterBasic(false).setHardness(3.5F).setResistance(10.0F),
                alloySmelterBasicActive = new BlockAlloySmelterBasic(true).setHardness(3.5F).setResistance(10.0F),
                rockPummelerBasicIdle = new BlockRockPummelerBasic(false).setHardness(3.5F).setResistance(10.0F),
                rockPummelerBasicActive = new BlockRockPummelerBasic(true).setHardness(3.5F).setResistance(10.0F),
                zechoriumExciterIdle = new BlockZechoriumExciter(false).setHardness(3.5F).setResistance(10.0F),
                zechoriumExciterActive = new BlockZechoriumExciter(true).setHardness(3.5F).setResistance(10.0F),
                zechoriumInfuserIdle = new BlockZechoriumInfuser(false).setHardness(3.5F).setResistance(10.0F),
                zechoriumInfuserActive = new BlockZechoriumInfuser(true).setHardness(3.5F).setResistance(10.0F),
                zechoriumCompressorIdle = new BlockZechoriumCompressor(false).setHardness(3.5F).setResistance(10.0F),
                zechoriumCompressorActive = new BlockZechoriumCompressor(true).setHardness(3.5F).setResistance(10.0F),
                pipe = new BlockPipe().setHardness(1.5F).setResistance(10.0F),
        };

        tank = new BlockTank().setHardness(3.5F).setResistance(10.0F);

        for (int i = 0; i < blocks.length; i++) {
            RegisterHelper.registerBlock(blocks[i]);
        }
        GameRegistry.registerBlock(tank, ItemBlockTank.class, Reference.MODID + "_"
                + tank.getUnlocalizedName().substring(5));
    }
}
