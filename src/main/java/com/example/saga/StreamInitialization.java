package com.example.saga;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamInitialization
{
    public static void main(String[] args) {

        List<String> values = List.of("One","Two","Three");
        Stream<String> stream1 = values.stream();
        System.out.println(stream1);

        List<Integer> list= Arrays.asList(5,10,23,35,20,16,24,12,56,45,90,11);
        List<Integer>list1=list.stream().filter(i->i%2==0).toList();
        System.out.println(list1);
        List<Integer>list3=list.stream().map(i->i*2).toList();
        System.out.println(list3);

        List<Integer>passed = list.stream()
                .filter(i->i>30)
                .collect(Collectors.toList());
        System.out.println(passed);
        List<Integer>graceMarks=list.stream()
                .filter(i->i<35)
                .map(j->j+5)
                .toList();
        System.out.println(graceMarks);
        List<Integer> j=list.stream()
                .filter(i->i<10).toList();
        System.out.println(j);
    }
}