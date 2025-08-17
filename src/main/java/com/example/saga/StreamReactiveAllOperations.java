package com.example.saga;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

public class StreamReactiveAllOperations {

    public static List<Employee> reactiveEmployyesList() {

        List<Employee> employeeList = new ArrayList<>();


        employeeList.add(new Employee(246, "KunwarSingh", "Bathindaa", 30, "Male", "Tech Lead", 2019, 20000000.00));
        employeeList.add(new Employee(6, "Vishal", "Bathinda", 43, "Male", "Security", 2016, 20000.00));
        employeeList.add(new Employee(64, "qwerty", "ssvsv", 433, "Female", "Fire", 2019, 20080.00));
        employeeList.add(new Employee(2, "nigga", "jfjn", 23, "Male", "IT", 2011, 23000.00));
        employeeList.add(new Employee(61, "secret", "Gurgaon", 3, "Male", "Defence", 2014, 25000.00));
        employeeList.add(new Employee(56, "vertika", "ukkgu", 5, "Female", "Avaiation", 2015, 20300.00));
        employeeList.add(new Employee(66, "maharana", "Gurgaon", 23, "Male", "HR", 2012, 278000.00));
        employeeList.add(new Employee(16, "diljeet", "dgsfx", 56, "Female", "Avaiation", 2014, 234000.00));
        employeeList.add(new Employee(76, "brar", "cncbnc", 44, "Male", "Security", 2018, 25000.00));
        employeeList.add(new Employee(68, "jassi", "bmbm", 11, "Female", "Buisness", 2013, 278000.00));
        employeeList.add(new Employee(60, "sandy", "uiyui", 22, "Female", "Gym Trainer", 2015, 289000.00));
        employeeList.add(new Employee(90, "Mohit Singh", "Gurgaon", 29, "Male", "IT", 2000, 122220.0));

        return employeeList;

    }


    public static void main(String[] args) {

        List<Employee> employees = reactiveEmployyesList();
        Flux<Employee> employeeFlux = Flux.fromIterable(employees);

        //get all employees names as a List

        List<String> empnames = employees.stream().map(Employee::getName).toList();
        System.out.println(empnames);

        List<String> namesEmployees = employees.stream().map(e -> e.getName()).toList();
        System.out.println(namesEmployees);

        Mono<List<String>> listMono = employeeFlux.map(Employee::getName).collectList();
        listMono.subscribe(System.out::println);

        //filter : all emp name whose age is more than 25

        List<String> empageName = employees.stream().filter(e -> e.getAge() > 25)
                .map(Employee::getName).toList();
        System.out.println(empageName);

        List<String> empage = employees.stream().filter(e -> e.getAge() > 25)
                .map(Employee::getName).toList();
        System.out.println(empage);

        Mono<List<String>> empNameAge = employeeFlux.filter(e -> e.getAge() > 25)
                .map(Employee::getName).collectList();
        empNameAge.subscribe(System.out::println);

        //print all city names of employees

        List<String> getCityNames = employees.stream().map(Employee::getCity).distinct().toList();
        System.out.println(getCityNames);

        List<String> cityemp = employees.stream().map(e -> e.getCity()).distinct().toList();
        System.out.println(cityemp);

        Mono<List<String>> distinctCityNames = employeeFlux.map(Employee::getCity).distinct().collectList();
        distinctCityNames.subscribe(System.out::println);

        //get count of employees whose salary greater than 20k

        long count = employees.stream().filter(e -> e.getSalary() > 20000).count();
        System.out.println(count);

        Mono<Long> count1 = employeeFlux.filter(e -> e.getSalary() > 20000).count();
        count1.subscribe(System.out::println);

        //get name of employees whose salary is greater than 25k

        List<String> salGt25 = employees.stream().filter(e -> e.getSalary() > 25000)
                .map(Employee::getName).toList();
        System.out.println(salGt25);

        Mono<List<String>> salg = employeeFlux.filter(e -> e.getSalary() > 25000)
                .map(Employee::getName).collectList();
        salg.subscribe(System.out::println);

        //get first 3 employee object as a list

        List<Employee> firstthree = employees.stream().limit(3).toList();
        System.out.println(firstthree);

        Mono<List<Employee>> limit3 = employeeFlux.take(3).collectList();
        limit3.subscribe(System.out::println);

        //skip first 3 employees and give the remaining employees

        List<Employee> skip3 = employees.stream().skip(3).toList();
        System.out.println(skip3);

        employeeFlux.skip(3).collectList().subscribe(System.out::println);

        //anymatch
        //verify any employee under age 18

        boolean any = employees.stream().anyMatch(e -> e.getAge() < 18);
        System.out.println(any);

        boolean anyMatch = employeeFlux.any(e -> e.getAge() < 18).block();
        System.out.println(anyMatch);

        //allmatch
        //check every employee joined after 2010 or not

        boolean all = employees.stream().allMatch(e -> e.getYearOfJoining() > 2010);
        System.out.println(all);

        boolean allMatch = employeeFlux.all(e -> e.getYearOfJoining() > 2010).block();
        System.out.println(allMatch);

        //nonematch
        //any one matching then false otherwise none matching true
        //every employee salary under 10k

        boolean none = employees.stream().noneMatch(e -> e.getSalary() < 10000);
        System.out.println(none);

        employeeFlux.any(e -> e.getSalary() < 10000)
                .map(matched -> !matched)
                .subscribe(nonematch -> System.out.println(nonematch));

        //findAny
        //findFirst - always return the first employee from the list

        Employee employee = employees.stream().findFirst().get();
        System.out.println(employee);

        //sorted
        //get emp id in sorted order

        List<Integer> idSort = employees.stream().map(Employee::getId).sorted().toList();
        System.out.println(idSort);

        List<Integer> empIdSort = employees.stream().map(e -> e.getId()).sorted().toList();
        System.out.println(empIdSort);

        Mono<List<Integer>> empIdSorted = employeeFlux.map(Employee::getId).sort().collectList();
        empIdSorted.subscribe(System.out::println);

        //minimum salary employee details
        Employee minEmpSalary = employees.stream().min((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .get();
        System.out.println(minEmpSalary);

        //maximum salary employee details
        Employee maxSalary = employees.stream().max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .get();
        System.out.println(maxSalary);

        //average salary
        //doublestream : stream contains always double values

        double avgSalary = employees.stream().mapToDouble(emp -> emp.getSalary())
                .average().getAsDouble();
        System.out.println(avgSalary);

        //unique department name

        List<String> uniqueDept = employees.stream().map(Employee::getDepartment).distinct().toList();
        System.out.println(uniqueDept);

        Mono<List<String>> deptUnique = employeeFlux.map(Employee::getDepartment).distinct().collectList();
        deptUnique.subscribe(System.out::println);

        //collect employee id's and their salaries as a map

        Map<Integer, Double> idSal = employees.stream()
                .collect(Collectors.toMap(Employee::getId, Employee::getSalary));
        System.out.println(idSal);

        Mono<Map<Integer, Double>> salId = employeeFlux
                .collect(Collectors.toMap(Employee::getId, Employee::getSalary));
        salId.subscribe(System.out::println);

        //collect emp name and emp salary

        Map<String, Double> nameSalary = employees.stream()
                .collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        System.out.println(nameSalary);

        Mono<Map<String, Double>> salName = employeeFlux
                .collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        salName.subscribe(System.out::println);

        //groupBy
        //get average salary of each department

        Map<String, Map<Double, List<Employee>>> salDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.groupingBy(Employee::getSalary)
                ));
        System.out.println(salDept);

        Mono<Map<String, Map<Double, List<Employee>>>> deptEmp = employeeFlux.collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.groupingBy(Employee::getSalary)
        ));
        deptEmp.subscribe(System.out::println);

        //count() inside collectors class
        //get count of employees gender wise

        Map<String, Long> countGender = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.counting()
        ));
        System.out.println(countGender);

        Mono<Map<String, Long>> genderCount = employeeFlux.collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.counting()
        ));
        genderCount.subscribe(System.out::println);

        //summarizing......
        System.out.println(employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary)));

        //summing
        System.out.println(employees.stream().collect(Collectors.summingDouble(Employee::getSalary)));

        //group the employees by city
        Map<Double, List<Employee>> empSalary = employees.stream().collect(Collectors.groupingBy(Employee::getSalary));
        System.out.println(empSalary);

        Mono<Map<Double, List<Employee>>> salaryEmp = employeeFlux.collect(Collectors.groupingBy(Employee::getSalary));
        salaryEmp.subscribe(System.out::println);

        //group the employee by age
        Map<Integer, List<Employee>> empAge = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(empAge);

        Mono<Map<Integer, List<Employee>>> ageOfEmp = employeeFlux.collect(Collectors.groupingBy(Employee::getAge));
        ageOfEmp.subscribe(System.out::println);

        //count of male and female employees present in the organisation
        Map<String, Long> genderWiseCount = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.counting()
        ));
        System.out.println(genderWiseCount);

        Mono<Map<String, Long>> countGenderWise = employeeFlux.collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.counting()
        ));
        countGenderWise.subscribe(System.out::println);

        //print the names of all department in the organisation
        List<String> uniqDept = employees.stream().map(Employee::getDepartment).distinct().toList();
        System.out.println(uniqDept);

        Mono<List<String>> uniqueDepartment = employeeFlux.map(Employee::getDepartment)
                .distinct().collectList();
        uniqueDepartment.subscribe(System.out::println);

        //print employee details whose age is greater than 28
        employees.stream().filter(e -> e.getAge() > 28).forEach(System.out::println);

        //max age of employee
        OptionalInt maxAge = employees.stream().mapToInt(Employee::getAge).max();
        System.out.println(maxAge);

        //print average age of male and female employees
        Map<String, Double> avgAgeByGender = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.averagingInt(Employee::getAge)
        ));
        System.out.println(avgAgeByGender);

        Mono<Map<String, Double>> avgAGender = employeeFlux.collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.averagingInt(Employee::getAge)
        ));
        avgAGender.subscribe(System.out::println);

        //print the no of employees in each department
        Map<String, Long> empDeptCount = employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
        ));
        System.out.println(empDeptCount);

        Mono<Map<String, Long>> empCountDep = employeeFlux.collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
        ));
        empCountDep.subscribe(System.out::println);

        //find employees from Security Dept
        Optional<Employee> empSecurity = employees.stream().filter(e -> e.getDepartment().equalsIgnoreCase("Security"))
                .findAny();
        System.out.println(empSecurity);

        Mono<Employee> secEmployee = employeeFlux.filter(e -> e.getDepartment().equalsIgnoreCase("Security"))
                .next();
        secEmployee.subscribe(System.out::println);

        //distinct department names that employee work for
        List<Employee> emplDistinct = employees.stream().filter(e -> Boolean.parseBoolean(e.getDepartment()))
                .distinct().toList();
        System.out.println(emplDistinct);

        Mono<List<Employee>> distinctEmpl = employeeFlux.filter(e -> Boolean.parseBoolean(e.getDepartment())).distinct().collectList();
        distinctEmpl.subscribe(System.out::println);

        //find all employees who lives in "Gurgaon,sort them by their name and print name of employees
        employees.stream().filter(e -> e.getCity().equalsIgnoreCase("Gurgaon"))
                .sorted(Comparator.comparing(Employee::getName)).forEach(System.out::println);

        Mono<List<Employee>> empGurg = employeeFlux.filter(e -> e.getCity().equalsIgnoreCase("Gurgaon"))
                .sort(Comparator.comparing(Employee::getName)).collectList();
        empGurg.subscribe(System.out::println);

        //no of employees in the organisation
        long countEmp = employees.stream().count();
        System.out.println(countEmp);

        Mono<Long> empCount = employeeFlux.count();
        empCount.subscribe(System.out::println);

        //employee count in every department
        Map<String, Long> empDeptWise = employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
        ));
        System.out.println(empDeptWise);

        Mono<Map<String, Long>> empDepWise = employeeFlux.collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
        ));
        empDepWise.subscribe(System.out::println);

        //average salary of each department
        Map<String, Double> avgsalDepWise = employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
        ));
        System.out.println(avgsalDepWise);

        Mono<Map<String, Double>> avgSalDeptWise = employeeFlux.collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
        ));
        avgSalDeptWise.subscribe(System.out::println);

        //print average salary by gender in each department
        Map<String, Double> avgSalGenWise = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.averagingDouble(Employee::getSalary)
        ));
        System.out.println(avgSalGenWise);

        Mono<Map<String, Double>> avgSalGender = employeeFlux.collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.averagingDouble(Employee::getSalary)
        ));
        avgSalGender.subscribe(System.out::println);

        //highest salary in the organisation
        Optional<Employee> highSlary = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).findFirst();
        System.out.println(highSlary);

        //second highest salary in the organisation
        Employee secondHigh = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1).findFirst().get();
        System.out.println(secondHigh);

        Mono<Employee> secondHighSal = employeeFlux.sort(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1).next();
        secondHighSal.subscribe(System.out::println);


    }
}
