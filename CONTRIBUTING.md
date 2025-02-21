# Contributing to Mojang Maps

Contributing to Mojang Maps is easy!

## Building

Just run `./gradlew build` (`.\gradlew build` on Windows), go into the `build/libs` folder of your preferred
loader/server software, and get the jar named `mojang_maps-VERSION-LOADER.jar`, as some other jars may get generated but
not work correctly.

If you only want a specific loader, run `./gradlew :LOADER:build` (`.\gradlew :LOADER:build` on Windows), and do the
same steps as before.