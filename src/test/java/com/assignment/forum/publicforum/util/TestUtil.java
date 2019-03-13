package com.assignment.forum.publicforum.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class TestUtil {

    
    public static String convertRequestObjectToString(Object request) throws JsonProcessingException {
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        
        return objectWriter.writeValueAsString(request);
    }
}
