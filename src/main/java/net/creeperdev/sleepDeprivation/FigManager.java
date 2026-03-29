package net.creeperdev.sleepDeprivation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FigManager {
    private static final InputStream input = SleepDeprivation.class.getResourceAsStream("/readme.txt");
    private static final Path output = Paths.get("config/sleep_deprivation/config_guide.txt");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File FILE = new File("config/sleep_deprivation/config.json");
    public static Figs FIGS = new Figs();
    public static final Logger logger = LoggerFactory.getLogger("Sleep Deprivation - conFIG manager");
    public static void load() {
        logger.info("Loading figs...");
        try {
            if (!FILE.exists()) {
                createFile();
                logger.info("No figs found, creating them...");
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
    public static void createFile() {
        try {
            Files.createDirectories(output.getParent());
            Files.copy(input, output, StandardCopyOption.REPLACE_EXISTING);
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