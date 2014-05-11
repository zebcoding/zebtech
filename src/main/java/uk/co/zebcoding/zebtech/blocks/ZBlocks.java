package uk.co.zebcoding.zebtech.blocks;

import net.minecraft.block.Block;
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
    public static Block pipe;

    public static final int guiIDAlloySmelterCoal = 0;
    public static final int guiIDAlloySmelterBasic = 1;
    public static final int guiIDRockPummelerBasic = 2;
    public static final int guiIDZechoriumExciter = 3;
    public static final int guiIDZechoriumInfuser = 4;

    public static void init() {
        final Block[] blocks = new Block[]{
                tinOre = new BlockOre("tinOre"),
                leadOre = new BlockOre("leadOre"),
                copperOre = new BlockOre("copperOre"),
                zechoriumOre = new BlockOre("zechoriumOre"),
                tinBlock = new BlockTin(),
                leadBlock = new BlockLead(),
                copperBlock = new BlockCopper(),
                liquidZechorium = new BlockLiquidZechorium(),
                alloySmelterCoalIdle = new BlockAlloySmelterCoal(false),
                alloySmelterCoalActive = new BlockAlloySmelterCoal(true),
                alloySmelterBasicIdle = new BlockAlloySmelterBasic(false),
                alloySmelterBasicActive = new BlockAlloySmelterBasic(true),
                rockPummelerBasicIdle = new BlockRockPummelerBasic(false),
                rockPummelerBasicActive = new BlockRockPummelerBasic(true),
                zechoriumExciterIdle = new BlockZechoriumExciter(false),
                zechoriumExciterActive = new BlockZechoriumExciter(true),
                zechoriumInfuserIdle = new BlockZechoriumInfuser(false),
                zechoriumInfuserActive = new BlockZechoriumInfuser(true),
                pipe = new BlockPipe()
        };

        for (int i = 0; i < blocks.length; i++) {
            RegisterHelper.registerBlock(blocks[i]);
        }
    }
}
