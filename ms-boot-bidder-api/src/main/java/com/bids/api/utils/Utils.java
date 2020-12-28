package com.bids.api.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	
	public static String ObjectToJson(Object entity) {
		String json = null;
		try {
			json = new ObjectMapper().writeValueAsString(entity);
		} catch (JsonProcessingException e) { 
			e.printStackTrace();
		}		
		return json;
	}
	
	
	public static String MapToJson(Map<String, Object> elements) {  
		String json = null;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            json = objectMapper.writeValueAsString(elements);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
		return json;
    }

}
