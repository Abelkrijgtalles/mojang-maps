package nl.abelkrijgtalles.example_mod.fabric;

import net.fabricmc.api.ModInitializer;
import nl.abelkrijgtalles.example_mod.common.ExampleMod;

public class FabricMain implements ModInitializer {

    @Override
    public void onInitialize() {

        ExampleMod.init();
    }

}
