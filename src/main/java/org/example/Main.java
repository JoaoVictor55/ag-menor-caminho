package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();

        List<Integer> a = new ArrayList<>();

        Object p = a;

        hashMap.put(1, new ArrayList<>());
        hashMap.get(1).add(2);
        hashMap.get(1).add(3);
    }
}