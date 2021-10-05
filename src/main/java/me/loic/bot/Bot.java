package me.loic.bot;

import lombok.Getter;
import me.loic.bot.config.BotConfig;
import me.loic.bot.utils.log.Logger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class Bot {

    private JDA bot;
    private final BotConfig config;

    private static Bot instance;

    public static Bot getInstance() {
        return instance;
    }

    public Bot(String[] args) {
        AtomicBoolean verbose = new AtomicBoolean(false);

        Arrays.stream(args).forEach(arg -> {
            if (arg.equals("-v") || arg.equals("-verbose"))
                verbose.set(true);
        });

        instance = this;
        config = new BotConfig(verbose.get());

        if (config.TOKEN.equalsIgnoreCase("")) {
            Logger.error("Bot token is not set, please define it in the config (" + Constants.CONFIG_PATH + ")");
            System.exit(1);
        }

        JDABuilder builder = JDABuilder.createDefault(config.TOKEN);
        builder.setActivity(Activity.watching(config.ACTIVITY));
        try {
            bot = builder.build();
        } catch (LoginException exception) {
            Logger.error(exception.getMessage());
            Logger.verboseStackTrace(exception);
        }

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("stop")) { //TODO console command system
                System.out.println("Shutting down...");
                System.exit(0);
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Bot(args);
    }

    public void shutdown() {
        config.save();
        bot.shutdown();
    }
}
