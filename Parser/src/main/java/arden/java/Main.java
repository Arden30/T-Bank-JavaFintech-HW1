package arden.java;

import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        JsonToXmlParser jsonToXmlParser = new JsonToXmlParser(dotenv);

        try {
            jsonToXmlParser.toXml(Path.of(dotenv.get("JSON_FILE")));
        } catch (Exception e) {
            log.error("Problems with .env file: {}", e.getMessage());
        }
    }
}