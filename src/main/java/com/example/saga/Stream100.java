package com.example.saga;

import java.util.List;
import java.util.Optional;

public class Stream100 {

    public static void main(String[] args) {
        //sum of all elements in a list

        List<Integer> integers = List.of(1, 2, 3, 4, 5);
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Sum : " + sum);

        //product of all elements
        List<Integer> number= List.of(1,2,3,4,5);
        Optional<Integer> product=number.stream().reduce((a, b)->a*b);
        System.out.println("Product : " + product);

        //average of all elements
        List<Integer> numbers= List.of(1,2,3,4,5);
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println(average);

        //Max element of all elements
        List<Integer> maxelement = List.of(1, 2, 3, 4, 5);
        Integer max = maxelement.stream().max(Integer::compare).orElse(0);
        System.out.println("Max : " +max);

        //Min element of all elements
        List<Integer> minElement = List.of(1, 2, 3, 4, 5);
        Integer min = minElement.stream().min(Integer::compare).orElse(0);
        System.out.println("Min :"+min);

        //Count of all element
        List<Integer> countnumber = List.of(1, 2, 3, 4, 5);
        long count = countnumber.stream().count();
        System.out.println("Count : " +count);


    }
}
