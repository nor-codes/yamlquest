package com.yamlquest.parser;

import com.yamlquest.parser.exception.EmptyFilePathException;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Parser parser = new Parser();
    @Test
    void parseSingleRequestTest() throws EmptyFilePathException, IOException {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("files/single-request.yaml")).getPath();
        ParseOutput parseOutput = parser.parse(filePath);
        assertEquals(1,parseOutput.getNumberOfRequests());
    }
    @Test
    void parseMultipleRequestTest() throws EmptyFilePathException, IOException {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource("files/multiple-requests.yaml")).getPath();
        ParseOutput parseOutput = parser.parse(filePath);
        assertTrue(parseOutput.getNumberOfRequests()>1);
    }
}