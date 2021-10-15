package me.loic.bot.commands;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;

@Getter
public class CommandManager {

    private ArrayList<Command> registeredCommands;

    public CommandManager() {
        registerCommands(
                new TestCommand());
    }

    private void registerCommands(Command... commands) {
        this.registeredCommands = new ArrayList<>();
        this.registeredCommands.addAll(Arrays.asList(commands));
    }
}
