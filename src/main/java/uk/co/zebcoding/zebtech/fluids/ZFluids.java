package uk.co.zebcoding.zebtech.fluids;

import net.minecraftforge.fluids.Fluid;
import uk.co.zebcoding.zebtech.help.RegisterHelper;

public class ZFluids {

    public static Fluid liquidZechorium;

    public static void init() {
        final Fluid[] fluids = new Fluid[]{
                liquidZechorium = new FluidZechorium()
        };

        for (int i = 0; i < fluids.length; i++) {
            RegisterHelper.registerFluid(fluids[i]);
        }
    }
}
