package me.loic.bot;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final Path CONFIG_PATH = Paths.get(System.getProperty("user.home") + "\\DiscordBod\\config.json");
}
