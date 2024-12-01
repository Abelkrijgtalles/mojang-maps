package nl.abelkrijgtalles.example_mod.common;

import org.apache.logging.log4j.LogManager;

public class ExampleMod {

    public static final String MOD_ID = "example_mod";

    public static void init() {

        LogManager.getLogger(MOD_ID).info("It's just really simple, isn't it?");

    }

}
