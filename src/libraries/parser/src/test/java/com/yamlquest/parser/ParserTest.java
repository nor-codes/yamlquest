package com.yamlquest.parser;

import com.yamlquest.parser.exception.EmptyFilePathException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @TempDir
    Path tempDir;

    @Test
    void testParse_shouldThrowEmptyFilePathException_whenFilePathIsEmpty() {
        Parser parser = new Parser();

        EmptyFilePathException exception = assertThrows(
                EmptyFilePathException.class,
                () -> parser.parse("")
        );

        assertEquals("File path was empty", exception.getMessage());
    }

    @Test
    void testParse_shouldThrowFileNotFoundException_whenFileDoesNotExist() {
        Parser parser = new Parser();
        String nonExistentPath = tempDir.resolve("nonexistent.yml").toString();

        FileNotFoundException exception = assertThrows(
                FileNotFoundException.class,
                () -> parser.parse(nonExistentPath)
        );

        assertTrue(exception.getMessage().contains("The request(s) specification file was not found"));
    }

    @Test
    void testParse_shouldThrowRuntimeException_whenFileIsInvalidYAML() throws IOException {
        // Write invalid YAML to a temp file
        Path yamlFile = tempDir.resolve("invalid.yml");
        Files.writeString(yamlFile, "this is not valid: yaml : ::");

        Parser parser = new Parser();

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> parser.parse(yamlFile.toString())
        );

        assertTrue(exception.getMessage().contains("Error occurred while attempting to parse request specification file"));
    }

    @Test
    void testParse_shouldReturnParseOutput_whenYAMLIsValid() throws IOException, EmptyFilePathException {
        // Setup valid YAML
        String yamlContent = """
        requests:
          - name: "Create Post"
            method: "POST"
            url: "https://jsonplaceholder.typicode.com/posts"
            headers:
              Authorization: "Bearer sampleToken"
            contentType: "application/json"
            body: |
              {
                "title": "foo",
                "body": "bar",
                "userId": 1
              }
          - name: "Get Comments"
            method: "GET"
            url: "https://jsonplaceholder.typicode.com/comments"
            contentType: "application/json"
        """;

        Path yamlFile = tempDir.resolve("valid.yml");
        Files.writeString(yamlFile, yamlContent);

        Parser parser = new Parser();

        ParseOutput result = parser.parse(yamlFile.toString());

        assertNotNull(result);
        assertEquals(2, result.getNumberOfRequests());
        assertTrue(result.getRequestHashMap().containsKey(1));
    }
}