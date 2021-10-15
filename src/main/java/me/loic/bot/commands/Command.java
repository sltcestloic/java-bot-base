package me.loic.bot.commands;

import lombok.Getter;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

@Getter
public abstract class Command {

    private String name, description, usage;

    public Command(String name, String description, String usage) {
        this.name = name;
        this.description = description;
        this.usage = usage;
    }

    public abstract boolean execute(SlashCommandEvent event);
}
