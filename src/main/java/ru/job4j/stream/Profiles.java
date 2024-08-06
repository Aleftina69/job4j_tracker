package ru.job4j.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {

    public static List<Address> collect(List<Profile> profiles) {
        List<Address> rslt = new ArrayList<>();
        return profiles.stream()
                .map(Profile::getAddress)
                .collect(Collectors.toList());
    }
}