package com.yamlquest.parser;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.yamlquest.parser.exception.EmptyFilePathException;
import com.yamlquest.parser.util.ParserValidator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Parser {

    public ParseOutput parse(String filePath) throws EmptyFilePathException, IOException {
        if (filePath.isEmpty()){
            throw new EmptyFilePathException("File path was empty");
        }

        if (!Files.exists(Paths.get(filePath))){
            throw new FileNotFoundException("The request(s) specification file was not found at " + filePath);
        }

        YAMLMapper mapper = new YAMLMapper();

        ParseInput parseInput;

        try
        {
            parseInput = mapper.readValue(new File(filePath), ParseInput.class);
        }catch (Exception ex)
        {
            throw new RuntimeException("Error occurred while attempting to parse request specification file at "+filePath);
        }
        return ParserValidator.validate(parseInput);
    }
}
