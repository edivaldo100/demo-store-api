package com.edivaldo.api.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum StatusCarEnum {
	ON,
	OFF;
	
	private static final Map<String, StatusCarEnum> nameToValueMap = new HashMap<String, StatusCarEnum>();

    static {
        for (StatusCarEnum value : EnumSet.allOf(StatusCarEnum.class)) {
            nameToValueMap.put(value.name(), value);
        }
    }

    public static StatusCarEnum forName(String name) {
        return nameToValueMap.get(name);
    }
}
