package com.edivaldo.api.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ProfileEnum {
	ROLE_ADMIN,
	ROLE_USUARIO;
	
	private static final Map<String, ProfileEnum> nameToValueMap = new HashMap<String, ProfileEnum>();

	    static {
	        for (ProfileEnum value : EnumSet.allOf(ProfileEnum.class)) {
	            nameToValueMap.put(value.name(), value);
	        }
	    }

	    public static ProfileEnum forName(String name) {
	        return nameToValueMap.get(name);
	    }
}
