package org.example;

import org.example.geneticOperators.mutation.singlePointMutation.SinglePointMutation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static enum f{

        ENUM
    }

    public static void main(String[] args) {

        System.out.print(f.ENUM);

        HashMap<Integer, List<Integer>> hashMap = new HashMap<>();

        List<Integer> a = new ArrayList<>();

        Object p = a;

        hashMap.put(1, new ArrayList<>());
        hashMap.get(1).add(2);
        hashMap.get(1).add(3);

        Integer z = 10;

        String thread = "null";
        System.out.print(hashMap.getClass());
    }
}