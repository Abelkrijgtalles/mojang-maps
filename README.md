# Mojang Maps

## Status: I really don't know what I'm going to do. It's been dead since February. Maybe I'll make a cross-platform & cross-version rewrite, or maybe I'll leave it to die.

A Google Maps like navigation system for Minecraft.

[![Donate](https://img.shields.io/badge/Donate-PayPal-blue?style=for-the-badge&logo=paypal)](https://www.paypal.com/donate/?hosted_button_id=W79VLY89H4XRY)
[![Crowdin](https://badges.crowdin.net/mojang-maps/localized.svg?style=for-the-badge&logo=paypal)](https://crowdin.com/project/mojang-maps)

View more information on [the Mojang Maps wiki](https://mojangmaps.abelkrijgtalles.nl)!

# Roadmap

Moved to [the Mojang Maps wiki](https://mojangmaps.abelkrijgtalles.nl)
and [#14](https://github.com/Abelkrijgtalles/mojang-maps/discussions/14).

# Commands

- /registerlocation /createlocation - Register a location.
- /registerroad /createroad - Register a road.
- /goto - Go to a specific location.
- /whereamistanding /whichroad /where - Shows where you're standing.
- /reloadconfigsfromdisk /reloadconfigs /reloadconfig - Reloads all the configs from disk.
- /navigation /gotogui - Go to a specific location and view the navigation in GUI form.

# Permissions

- mojangmaps.register - Lets you register things.
    - mojangmaps.register.location - Lets you register a location.
    - mojangmaps.register.road - Lets you register a road.
- mojangmaps.using - Lets you use Mojang Maps.
    - mojangmaps.using.goto - Lets you go to a location.
    - mojangmaps.using.viewlocation - Lets you view your location
    - mojangmaps.using.navigation - Lets you go to a location and view the navigation in GUI form.
- mojangmaps.util - Some useful tools fo an admin.
    - mojangmaps.util.reloadconfigs - Lets you reload your configs from disk.

# Links

- https://github.com/Abelkrijgtalles/mojang-maps, GitHub repository.
- https://tinyurl.com/mmgithub, TinyUrl of the GitHub repository.
- https://www.spigotmc.org/resources/mojang-maps.111455/, SpigotMC page.
- https://modrinth.com/plugin/mojang-maps, Modrinth page.
- https://crowdin.com/project/mojang-maps, Crowdin page (for translations).
- https://github.com/Abelkrijgtalles/mojang-maps-data, The GitHub page for data used by Mojang Maps.
- https://bstats.org/plugin/bukkit/Mojang%20Maps/19295, The bStats of Mojang Maps.
- https://mojangmaps.abelkrijgtalles.nl, The wiki of Mojang Maps.

# Credits/Code I used

- [thebuildcraft](https://github.com/thebuildcraft) which made a multi-platform & multi-version mod template (based on
  scripts from [Distant Horizons](https://gitlab.com/jeseibel/distant-horizons)). I did delete a few things for my
  smooth brain, but it's still largely based on this template.
- [Simple Config](https://github.com/magistermaks/fabric-simplelibs/tree/master/simple-config) made
  by [magistermaks](https://github.com/magistermaks).

> NOT AN OFFICIAL MINECRAFT/MOJANG PRODUCT. NOT APPROVED BY OR ASSOCIATED WITH MOJANG OR MICROSOFT.
