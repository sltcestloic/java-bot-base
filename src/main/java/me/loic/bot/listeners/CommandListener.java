package me.loic.bot.listeners;

import me.loic.bot.Bot;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(@Nonnull SlashCommandEvent event) {
        Bot.getInstance().getCommandManager().getRegisteredCommands().stream()
                .filter(command -> command.getName().equalsIgnoreCase(event.getName()))
                .forEach(command -> command.execute(event));
    }

    @Override
    public void onButtonClick(@Nonnull ButtonClickEvent event) {
    }
}
