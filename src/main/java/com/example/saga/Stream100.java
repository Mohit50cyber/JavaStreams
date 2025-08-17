package com.example.saga;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        //Check if a list contains a specific element
        List<Integer> inte = List.of(1, 2, 3, 4, 5);
        boolean contain=inte.stream().anyMatch(i->i==3);
        System.out.println("Contain 3 :" + contain);

        //Even number
        List<Integer> evennum = List.of(1, 2, 3, 4, 5);
        List<Integer> even = evennum.stream().filter(i -> i % 2 == 0).toList();
        System.out.println(even);

        //Odd number
        List<Integer> oddnum = List.of(1, 2, 3, 4, 5);
        List<Integer> odd = oddnum.stream().filter(i -> i % 2 != 0).toList();
        System.out.println(odd);

        //List of strings to uppercase
        List<String> hello = List.of("Hello", "World");
        List<String> list = hello.stream().map(String::toUpperCase).toList();
        System.out.println(list);

        //List of Integers to their squares
        List<Integer> num = List.of(1, 2, 3, 4, 5);
        List<Integer> squares = num.stream().map(n -> n * n).toList();
        System.out.println(squares);

        //find first element in a List
        List<Integer> firstele = List.of(1, 2, 3, 4, 5);
        Integer first = firstele.stream().findFirst().orElse(0);
        System.out.println(first);

        //all element satisfy a condition in a list
        List<Integer> allele = List.of(1, 2, 3, 4, 5);
        boolean all = allele.stream().allMatch(i -> i % 2 == 0);
        System.out.println("Even numbers : " + all);

        //remove duplicate element of a list
        List<Integer> dupl = List.of(1, 2, 3, 4, 5,4,5,2);
        List<Integer> duplicateElement = dupl.stream().distinct().toList();
        System.out.println("Duplicate : " + duplicateElement);

        //sort element in a list
        List<Integer> numb = List.of(1, 2, 3, 4, 5,9,7,6);
        List<Integer> ascending = numb.stream().sorted().toList();
        System.out.println("Ascending : " + ascending);

        //Sort in descending order
        List<Integer> des = List.of(1, 2, 3, 4, 5);
        List<Integer> descending = des.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println("Descending : " + descending);

        //sort a list of strings in alphabetical order
        List<String> fruit = List.of("banana", "apple", "cherry");
        List<String> sortedFruit = fruit.stream().sorted().toList();
        System.out.println(sortedFruit);

        //find the sum of digits of a number
        int numbe =123456;
        int sum1 = String.valueOf(numbe).chars().map(Character::getNumericValue).sum();
        System.out.println("Sum : " + sum1);

        //second largest element in a list
        List<Integer> qwerty = List.of(1, 2, 3, 4, 5, 8, 6);
        Integer secondLargest = qwerty.stream()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst()
                .orElse(0);
        System.out.println("Second largest :" +secondLargest);

        //second smallest element in a list
        List<Integer> secsmall = List.of(1, 2, 3, 4, 5);
        Integer i = secsmall.stream().sorted().skip(1).findFirst().orElse(0);
        System.out.println("Second smallest : " + i);

        //Longest string in a list
        List<String> word = List.of("Apple", "mango", "kiwi");
        String longest =word.stream().max(Comparator.comparingInt(String::length)).orElse("");
        System.out.println("Longest :" +longest);

        //shortest string in a list
        List<String> wordshort = List.of("Apple", "Mango", "Kiwi");
        String shortest=wordshort.stream().min(Comparator.comparingInt(String::length)).orElse("");
        System.out.println("Shortest :" +shortest);

        //merge two list
        List<Integer> list11 = List.of(1, 2, 3);
        List<Integer> list22 = List.of(4, 5, 6, 7, 8);
        List<Integer> merged = Stream.concat(list11.stream(), list22.stream()).toList();
        System.out.println("Merged : " + merged);




    }
}
