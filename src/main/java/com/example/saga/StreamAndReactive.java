package com.example.saga;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class StreamAndReactive {

    public static List<Employee> employeesKaList(){

        List<Employee> employeeList= new ArrayList<>();

        employeeList.add(new Employee(6,"Vishal","Bathinda",43,"Male","Security",2016,20000.00));
        employeeList.add(new Employee(64,"qwerty","ssvsv",433,"Female","Fire",2019,20080.00));
        employeeList.add(new Employee(2,"nigga","jfjn",23,"Male","IT",2011,23000.00));
        employeeList.add(new Employee(61,"secret","Gurgaon",3,"Male","Defence",2014,25000.00));
        employeeList.add(new Employee(56,"vertika","ukkgu",5,"Female","Avaiation",2015,20300.00));
        employeeList.add(new Employee(66,"nunu","Gurgaon",23,"Male","HR",2012,278000.00));
        employeeList.add(new Employee(16,"luli","dgsfx",56,"Female","Avaiation",2014,234000.00));
        employeeList.add(new Employee(76,"lund","cncbnc",44,"Male","Security",2018,25000.00));
        employeeList.add(new Employee(68,"chut","bmbm",11,"Female","Buisness",2013,278000.00));
        employeeList.add(new Employee(60,"dede","uiyui",22,"Female","Gym Trainer",2015,289000.00));
        employeeList.add(new Employee(90,"Mohit Singh","Gurgaon",29,"Male","IT",2000,122220.0));

        return employeeList;

    }

    public static void main(String[] args) {

        List<Employee> employees = employeesKaList();
        Flux<Employee> employeeFlux = Flux.fromIterable(employees);

        //get all employees names as a List

        List<String> empNames = employees.stream().map(Employee::getName).toList();
        System.out.println(empNames);

        //names with raective

        Mono<List<String>> empNamesReactive = employeeFlux.map(Employee::getName).collectList();
        empNamesReactive.subscribe(System.out::println);

        //filter all employee names whose age is greater than 25
        List<String> empAge25 = employees.stream().filter(e -> e.getAge() > 25)
                .map(Employee::getName)
                .toList();
        System.out.println(empAge25);

        //with reactive
        Mono<List<String>> empgret25 = employeeFlux.filter(e -> e.getAge() > 25).map(Employee::getName).collectList();
        empgret25.subscribe(System.out::println);

        //print all city names of employees
        List<String> distinctCity = employees.stream().map(Employee::getCity).distinct().toList();
        System.out.println(distinctCity);

        //with reactive
        Mono<List<String>> distinctCityNames = employeeFlux.map(Employee::getCity).distinct().collectList();
        distinctCityNames.subscribe(System.out::println);

        //get count of employees whose salary greater than 20k
        long count = employees.stream().filter(e -> e.getSalary() > 20000).count();
        System.out.println(count);

        //with reactive
        Mono<Long> countSalg20k = employeeFlux.filter(e -> e.getSalary() > 20000).count();
        countSalg20k.subscribe(System.out::println);

        //get name of employees whose salary is greater than 25k
        List<String> empNameSal25k = employees.stream().filter(e -> e.getSalary() > 25000)
                .map(Employee::getName).toList();
        System.out.println(empNameSal25k);

        //with reactive
        Mono<List<String>> empNameg25k = employeeFlux.filter(e -> e.getSalary() > 25000)
                .map(Employee::getName).collectList();
        empNameg25k.subscribe(System.out::println);

        //get first 3 employee object as a list
        List<Employee> first3 = employees.stream().limit(3).toList();
        System.out.println(first3);

        //with reactive
        Mono<List<Employee>> take3items = employeeFlux.take(3).collectList();
        take3items.subscribe(System.out::println);


        //skip first 3 employees and give the remaining employees
        List<Employee> skip3 = employees.stream().skip(3).toList();
        System.out.println(skip3);

        //with raective
        Mono<List<Employee>> skipfirst3 = employeeFlux.skip(3).collectList();
        skipfirst3.subscribe(System.out::println);

        //anymatch
        //verify any employee under age 18

        boolean empl18 = employees.stream().anyMatch(e -> e.getAge() < 18);
        System.out.println(empl18);

        //with reactive
        Mono<Boolean> any = employeeFlux.any(e -> e.getAge() < 18);
        any.subscribe(System.out::println);

        //allmatch
        //check every employee joined after 2010 or not
        boolean empjoin2010 = employees.stream().allMatch(e -> e.getYearOfJoining() > 2010);
        System.out.println(empjoin2010);

        //with reactive
        Mono<Boolean> allemp2010 = employeeFlux.all(e -> e.getYearOfJoining() > 2010);
        allemp2010.subscribe(System.out::println);

        //nonematch
        //any one matching then false otherwise none matching true
        //every employee salary under 10k
        boolean noneEmpl = employees.stream().noneMatch(e -> e.getSalary() < 10000);
        System.out.println(noneEmpl);

        //with reactive
        Mono<Boolean> noneempl = employeeFlux.all(Predicate.not(e -> e.getSalary() < 10000));
        noneempl.subscribe(System.out::println);

        //findAny
        //findFirst - always return the first employee from the list
        Employee empfirst = employees.stream().findFirst().get();
        System.out.println(empfirst);

        //with  reactive
        Mono<Employee> next = employeeFlux.next();
        next.subscribe(System.out::println);

        //sorted
        //get emp id in sorted order
        List<Integer> sortEmp = employees.stream().map(emp -> emp.getId()).sorted().toList();
        System.out.println();

        //with reactive
        Mono<List<Integer>> sortList = employeeFlux.map(e -> e.getId()).sort().collectList();
        sortList.subscribe(System.out::println);

        Mono<List<Employee>> sorted = employeeFlux.sort(Comparator.comparing(Employee::getId)).collectList();
        sorted.subscribe(System.out::println);

        Mono<List<Employee>> monosorted = employeeFlux.sort(Comparator.comparing(Employee::getId).reversed()).collectList();
        monosorted.subscribe(System.out::println);


        //minimum salary employee details
        Employee minsalary = employees.stream()
                .min((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .get();
        System.out.println(minsalary);

        //with reactive



    }
}
