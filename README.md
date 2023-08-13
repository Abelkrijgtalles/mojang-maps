# Mojang Maps

A Google Maps like navigation system for Minecraft.

[![Donate](https://img.shields.io/badge/Donate-PayPal-blue?style=for-the-badge&logo=paypal)](https://www.paypal.com/donate/?hosted_button_id=W79VLY89H4XRY)
[![Crowdin](https://badges.crowdin.net/mojang-maps/localized.svg?style=for-the-badge&logo=paypal)](https://crowdin.com/project/mojang-maps)

# Roadmap

- [ ] Creating a UI
- [ ] Implementing a web app
- [X] Saving Roads to disk/config files
- [X] Using Dijkstra's Algorithm

# Commands

- /registerlocation /createlocation - Register a location.
- /registerroad /createroad - Register a road.
- /goto - Go to a specific location.
- /whereamistanding /whichroad /where - Shows where you're standing.
- /reloadconfigsfromdisk /reloadconfigs /reloadconfig - Reloads all the configs from disk.

# Permissions

- mojangmaps.register - Lets you register things.
    - mojangmaps.register.location - Lets you register a location.
    - mojangmaps.register.road - Lets you register a road.
- mojangmaps.using - Lets you use Mojang Maps.
    - mojangmaps.using.goto - Lets you go to a location.
    - mojangmaps.using.viewlocation - Lets you view your location
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

# Credits

- [SamJakob](https://github.com/SamJakob) and [all these people](https://github.com/SamJakob/SpiGUI/graphs/contributors)
  for creating SpiGUI.