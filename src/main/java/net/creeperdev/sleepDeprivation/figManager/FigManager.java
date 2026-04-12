package net.creeperdev.sleepDeprivation.figManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.creeperdev.sleepDeprivation.SleepDeprivation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FigManager {

    private static final String projectName = "sleep_deprivation";
    private static final InputStream template = SleepDeprivation.class.getResourceAsStream("/readme.txt");
    private static final Path guide = Paths.get("config/"+projectName+"/config_guide.txt");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File FILE = new File("config/"+projectName+"/config.json");
    public static Figs FIGS = new Figs();
    public static final Logger logger = LoggerFactory.getLogger("CreeperDev ConFIG Manager");
    public static void load() {
        logger.info("Loading figs...");
        try {
            if (!FILE.exists()) {
                save();
                logger.info("No figs found, creating figs...");
                return;
            }
            FileReader reader = new FileReader(FILE);
            FIGS = GSON.fromJson(reader, Figs.class);
            reader.close();
            logger.info("Figs loaded!");

        } catch (Exception e) {
            logger.warn("Something went wrong while loading figs. :(");
            logger.error(e.getMessage());
        }
    }
    public static void save() {
        try {
            
            FILE.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(FILE);
            GSON.toJson(FIGS, writer);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Something went wrong while saving figs. :(");
            logger.error(e.getMessage());
        }
    }
}