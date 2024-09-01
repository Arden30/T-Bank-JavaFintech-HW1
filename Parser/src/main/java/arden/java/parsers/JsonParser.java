package arden.java.parsers;

import arden.java.interfaces.Parser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JsonParser<T> implements Parser<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    @Override
    public T parse(File input) {
        try {
            T parsed = objectMapper.readValue(input, type);
            log.info("File {} was successfully parsed from JSON", input.getName());

            return parsed;
        } catch (IOException e) {
            log.error(e.getMessage());
            log.warn("New parsed file will be created with null fields");

            return null;
        }
    }
}
