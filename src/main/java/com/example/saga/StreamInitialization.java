package com.example.saga;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamInitialization
{
    public static void main(String[] args) {

        List<String> values = List.of("One","Two","Three");
        Stream<String> stream1 = values.stream();
        System.out.println(stream1);

        List<Integer> list= Arrays.asList(5,10,23,35,20,16);
        List<Integer>list1=list.stream().filter(i->i%2==0).toList();
        System.out.println(list1);
        List<Integer>list3=list.stream().map(i->i*2).toList();
        System.out.println(list3);
    }
}