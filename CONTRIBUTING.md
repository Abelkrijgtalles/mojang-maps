# Contributing to Mojang Maps

Contributing to Mojang Maps is easy!

## Some standards

Everything has to be compatible with Java 8/Minecraft 1.16.5. You can check this by running
`./gradlew build -PmcVer=1.16.5`.
Replace `./gradlew` with `.\gradlew` or just `gradlew`.

Here are some replacements of common functions:

```java
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Replacements() {

    private Random random = new Random();

    public void replacements() {

        // Instead of
        List.of();
        // Use
        Arrays.asList();
        // or
        Collections.singletonList();

        // Instead of
        Path.of();
        // Use
        Paths.get();

        // Instead of
        """
                Message block
                Cool
                """;
        // Use
        "Message block\n" +
                "Cool\n";

        // Don't know if this is a Java 8 thing, but it didn't work on 1.16.5
        // Instead of
        int number = random.nextInt(256);
        // Use
        int number = random.nextInt() * 256;

    }

}
```

Instead of using a record, please write it as a fully written class.

## Writing to the pathfinding project

Because pathfinding is based on another project, it uses patches.

### Setup

To set up the project, run
`./gradlew applyPatches`
If you get any errors, run `./gradlew updateSubmodules` beforehand.

### Making a new patch

Make a commit in the pathfinding folder. The name should be the name of the commit. Then run (in the root folder)
`./gradlew makePatches`.

### Editing a new patch

I'm to lazy to write this. Go
to [this part (Method 1) of Papers CONTRIBUTING.MD](https://github.com/PaperMC/Paper/blob/93b435dbab92d071bd685c6d8dda9fd32c4c2990/CONTRIBUTING.md#method-1)
and follow the steps in the pathfinding.

Instead of using `git rebase -i base`, use `git rebase -i upstream`. And instead of using `./gradlew rebuildPatches`,
use `./gradlew makePatches`.
Instead of using `git rebase -i base`, use `git rebase -i upstream`. And instead of using `./gradlew rebuildPatches`,
use `./gradlew makePatches`.

## Building

Because the pathfinding api uses patches, please set up the pathfinding project. Please follow the setup instructions
for pathfinding.

After that, run `./gradlew build`