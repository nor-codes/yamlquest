package com.yamlquest.client_cli.command;

import com.yamlquest.client_cli.exception.MissingOptionException;
import com.yamlquest.client_cli.exception.MissingParameterException;
import com.yamlquest.client_cli.utils.ExecutorUtil;
import com.yamlquest.client_cli.utils.Formatter;
import com.yamlquest.http_client.ClientResponse;
import com.yamlquest.http_client.RequestClient;
import com.yamlquest.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class ExecutorUtilTest {
    @Mock
    private Parser parser;

    @Mock
    private RequestClient client;

    @Mock
    private Formatter<ClientResponse> formatter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcess_ThrowsExceptionWhenFileOptionNotSet(){
        boolean fileOption = false;
        Exception exception = assertThrows(MissingOptionException.class,()-> ExecutorUtil.process(fileOption, null));

        String expectedExceptionMessage = "The file option was specified, but no valid file was provided. Please specify a valid file path or ensure the file exists.";
        String actualExceptionMessage = exception.getMessage();

        assertEquals(expectedExceptionMessage,actualExceptionMessage);
    }

    @Test
    void testProcess_ThrowsExceptionWhenFileIsNull(){
        boolean fileOption = true;
        Exception exception = assertThrows(MissingParameterException.class,()-> ExecutorUtil.process(fileOption, null));

        String expectedExceptionMessage = "The file to execute request on was not specified.";
        String actualExceptionMessage = exception.getMessage();

        assertEquals(expectedExceptionMessage,actualExceptionMessage);
    }



}