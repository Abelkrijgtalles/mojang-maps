This file shows the structure of `roads.mmd` file.

Words between brackets (`[]`) aren't literally in the file. They are replaced with actual values.
There aren't any new lines or spaces in the file, except for in the message. All text is stored in UTF-8.

All sizes are the amount of bytes it takes to store a certain thing.

```
---
DO NOT DELETE THIS FILE!!!
This rest of this file may look like gibberish, but it's not. This stores all the road data for Mojang Maps.
If you delete this file, you'll delete all your Mojang Maps data and essentially start from scratch.
---
[version byte - currently 0x01]
[road data begins]
    [roads data size in bytes as int - 4 bytes]
    [the following block will be repeated for every road]
        [road data size in bytes as int - 4 bytes]
        [road name begin byte - randomly generated so it doesn't conflict, defaults to 0x69]
            [road name]
        [road name end byte - randomly generated so it doesn't conflict, defaults to 0x69]
        [road world begin byte - randomly generated so it doesn't conflict, defaults to 0x69]
            [road world identification - the name for now]
        [road world end byte - randomly generated so it doesn't conflict, defaults to 0x69]
        [waypoints data size in bytes as int - 4 bytes]
        [the following block will be repeated for every waypoint]
            [x as double - 8 bytes]
            [y as double - 8 bytes]
            [z as double - 8 bytes]
        [end repetition for waypoints]
    [end repetition for roads]
[road data ends]
```
