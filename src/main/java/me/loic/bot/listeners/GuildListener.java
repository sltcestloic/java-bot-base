package me.loic.bot.listeners;

import me.loic.bot.Bot;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildListener extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        Bot.getInstance().getBot().getGuilds().forEach(guild -> {
            Bot.getInstance().getCommandManager().getRegisteredCommands().forEach(command -> {
                guild.upsertCommand(command.getName(), command.getDescription()).queue();
            });
        });
    }
}
