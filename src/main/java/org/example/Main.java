package org.example;

import org.example.geneticOperators.mutation.singlePointMutation.SinglePointMutation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static enum f{

        ENUM
    }

    public static <T> List<T> foo(Supplier<T> supplier){

        return Stream.generate(supplier).limit(5).collect(Collectors.toList());
    }

    public static void main(String[] args) {

        List<String> a = foo(String::new);

        List<Object> b = new ArrayList<>();

        b.add(Integer.class);
        Object t = b.get(0).getClass();

    }
}