package me.loic.bot.config;

import me.loic.bot.Constants;
import me.loic.bot.utils.log.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;

public class BotConfig {

    public boolean VERBOSE;
    public String TOKEN;
    public String ACTIVITY;

    public BotConfig(boolean verbose) {
        if (!Files.exists(Constants.CONFIG_PATH)) {
            Logger.info("Creating config...");
            try {
                Files.createDirectories(Paths.get(Constants.CONFIG_PATH.toString().substring(0, Constants.CONFIG_PATH.toString().lastIndexOf("\\"))));
                createConfig();
                Logger.info("Config created.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        load();
        //VERBOSE = verbose;
        VERBOSE = true; //temp
    }

    /**
     * Charge la config au lancement du bot
     */
    public void load() {
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(new FileInputStream(Constants.CONFIG_PATH.toString())));
            Arrays.stream(getClass().getFields())
                    .filter(field -> !field.getName().equals("VERBOSE"))
                    .forEach(field -> {
                        try {
                            field.set(this, jsonObject.get(field.getName().toLowerCase()));
                            Logger.info("(Config) " + field.getName().toLowerCase() + '=' + jsonObject.get(field.getName().toLowerCase()));
                        } catch (IllegalAccessException e) {
                            Logger.error("Failed to load config option " + field.getName() + ": field not found in config !");
                            e.printStackTrace();
                        }
                    });
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Sauvegarde la config (TODO: boolean async)
     */
    public void save() {
        JSONObject jsonObject = new JSONObject();
        Arrays.stream(getClass().getFields())
                .filter(field -> !field.getName().equals("VERBOSE"))
                .forEach(field -> {
                    try {
                        jsonObject.put(field.getName().toLowerCase(), field.get(this));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        Logger.info("Config saved successfully");
        Logger.verbose(jsonObject.toJSONString());
    }

    /**
     * Copie la config du dossier resources du jar vers le disque lors de la premiere utilisation
     */
    private void createConfig() {
        try {
            Files.copy(getClass().getClassLoader().getResourceAsStream("config.json"),
                    Constants.CONFIG_PATH,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
