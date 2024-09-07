package arden.java;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
public class Main {
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