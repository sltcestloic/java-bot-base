/* ************************************************************************** */
/*                                                                            */
/*                                                                            */
/*   Constants.java                                                           */
/*                                                                            */
/*   By: Loïc <lbertran@student.42lyon.fr>                                    */
/*                                                                            */
/*   Created: 2020/11/06 11:38:18 by Loïc                                     */
/*   Updated: 2020/11/11 15:00:09 by Loïc                                     */
/*                                                                            */
/* ************************************************************************** */
package me.loic.bot;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Constants {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static final Path CONFIG_PATH = Paths.get(System.getProperty("user.home") + "\\DiscordBod\\config.json");
}
