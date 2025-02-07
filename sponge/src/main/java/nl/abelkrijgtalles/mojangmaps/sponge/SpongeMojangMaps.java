/*
 * mojang_maps.sponge.main
 * Copyright (C) 2025 Abel van Hulst/Abelkrijgtalles/Abelpro678
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nl.abelkrijgtalles.mojangmaps.sponge;

import com.google.inject.Inject;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import nl.abelkrijgtalles.mojangmaps.common.LoaderInfo;
import nl.abelkrijgtalles.mojangmaps.common.MojangMaps;
import nl.abelkrijgtalles.mojangmaps.common.command.Commands;
import nl.abelkrijgtalles.mojangmaps.platform.command.Command;
import nl.abelkrijgtalles.mojangmaps.platform.command.CommandSource;
import nl.abelkrijgtalles.mojangmaps.platform.command.Permission;
import nl.abelkrijgtalles.mojangmaps.sponge.platform.SpongeLoaderInfo;
import nl.abelkrijgtalles.mojangmaps.sponge.platform.SpongePlatform;
import nl.abelkrijgtalles.mojangmaps.sponge.platform.world.SpongeLevel;
import org.spongepowered.api.Server;
import org.spongepowered.api.command.CommandCause;
import org.spongepowered.api.command.CommandCompletion;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.exception.CommandException;
import org.spongepowered.api.command.parameter.ArgumentReader;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.living.player.server.ServerPlayer;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.RegisterCommandEvent;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;
import org.spongepowered.api.event.server.query.QueryServerEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

@Plugin(MojangMaps.MOD_ID)
public class SpongeMojangMaps {

    @Inject
    PluginContainer container;
    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path defaultConfig;

    @Listener
    public void onServerStart(final StartedEngineEvent<Server> event) {

        LoaderInfo loaderInfo = new SpongeLoaderInfo(defaultConfig, false);

        MojangMaps.init(loaderInfo);

    }

    @Listener
    public void onQueryServer(final QueryServerEvent.Full event) {

        SpongePlatform.setMinecraftVersion(event.version());

    }

    @Listener
    public void onRegisterCommands(final RegisterCommandEvent<org.spongepowered.api.command.Command.Raw> event) {

        for (Command command : Commands.getCommands()) {

            event.register(this.container, new org.spongepowered.api.command.Command.Raw() {


                @Override
                public CommandResult process(CommandCause cause, ArgumentReader.Mutable arguments) {

                    CommandSource source;

                    if (cause.first(ServerPlayer.class).isPresent())
                        source = new CommandSource(new Permission(false), new SpongeLevel(cause.location().get().worldKey()));
                    else source = new CommandSource(new Permission(true));

                    return command.run(source) ? CommandResult.success() : CommandResult.error(Component.text("Something went wrong when executing %s.".formatted(command.getCommand())));
                }

                @Override
                public List<CommandCompletion> complete(CommandCause cause, ArgumentReader.Mutable arguments) throws CommandException {

                    return List.of();
                }

                @Override
                public boolean canExecute(CommandCause cause) {

                    return true;
                }

                @Override
                public Optional<Component> shortDescription(CommandCause cause) {

                    return Optional.empty();
                }

                @Override
                public Optional<Component> extendedDescription(CommandCause cause) {

                    return Optional.empty();
                }

                @Override
                public Component usage(CommandCause cause) {

                    return null;
                }
            }, command.getCommand());

        }

    }

}
