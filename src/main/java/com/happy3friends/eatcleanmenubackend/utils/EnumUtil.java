package com.happy3friends.eatcleanmenubackend.utils;

import com.happy3friends.eatcleanmenubackend.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumUtil<T extends Enum<T>> {

    public static List<String> enumToListName(Class<? extends Enum<?>> e) {
        Enum<?>[] enums = e.getEnumConstants();
        return Arrays.asList(enums).stream()
                .map(name -> name.toString())
                .collect(Collectors.toList());
    }

    public static <T extends Enum<T>> Enum<?> getByValue(Class<? extends Enum<?>> e, String value) {
        return Arrays.stream(e.getEnumConstants())
                .filter(x -> x.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Can not find enum with this enum value: " + value));
    }

    public static <T extends Enum<T>> String convertValueToName(Class<? extends Enum<?>> e, String enumValue) {
        List<? extends Enum<?>> enumList = Arrays.asList(e.getEnumConstants());
        return (enumList.stream()
                .filter(x -> x.name().equals(enumValue)).findAny()
                .orElseThrow(() -> new NotFoundException("Can not find enum name with this enum value: " + enumValue)))
                .name();
    }
}
