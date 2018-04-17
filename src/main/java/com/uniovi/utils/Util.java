package com.uniovi.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.entities.Incidence;
import com.uniovi.entities.IncidenceReceived;

public class Util {

	private static ObjectMapper mapper;

	public static String IncidenceToJSON(Incidence incidence) {
		try {
			return getMapper().writeValueAsString(incidence);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public static IncidenceReceived JsonToIncidence(String JSON) {
		try {
			return getMapper().readValue(JSON, IncidenceReceived.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
		}
		return mapper;
	}

	public static String addString(String newString, String csv) {
		List<String> list = toList(csv);
		list.add(newString);
		return toCsv(list);
	}

	public static String deleteProperty(String key, String properties) {
		Map<String, String> map = toMap(properties);
		map.remove(key);
		return toCsp(map);
	}

	public static String deleteString(String stringToDelete, String csv) {
		List<String> list = toList(csv);
		list.remove(stringToDelete);
		return toCsv(list);
	}

	public static String toCsp(Map<String, String> properyMap) {
		List<String> propertyList = new ArrayList<>();
		for (String key : properyMap.keySet()) {
			propertyList.add(key + ":" + properyMap.get(key));
		}
		return toCsv(propertyList);
	}

	public static String toCsv(List<String> values) {
		return values != null && !values.isEmpty()
				? values.stream().map(v -> v.trim().toLowerCase()).collect(Collectors.joining(","))
				: null;
	}

	public static List<String> toList(String csv) {
		List<String> out = new ArrayList<>();
		if (csv != null) {
			for (String tag : csv.split(",")) {
				out.add(tag.toLowerCase().trim());
			}
		}
		return out;
	}

	public static Map<String, String> toMap(String csp) {
		Map<String, String> out = new HashMap<>();
		for (String property : csp.split(",")) {
			if (property.contains(":")) {
				out.put(property.split(":")[0].trim(), property.split(":")[1].trim());
			}
		}
		return out;
	}

}
