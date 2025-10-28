package com.example.saga;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperationsReactive26Oct {

    public static List<Employee> employeeList () {

        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "Yanksha", 28, 128993, "F", "HR", "Blore", 2020));
        empList.add(new Employee(2, "Francesca", 29, 120000, "F", "Admin", "Hyderabad", 2015));
        empList.add(new Employee(3, "Ramesh", 30, 146615, "M", "HR", "Chennai", 2014));
        empList.add(new Employee(4, "Melanie", 32, 12675, "F", "Admin", "Chennai", 2013));
        empList.add(new Employee(5, "Padma", 22, 15450, "F", "IT", "Noida", 2013));
        empList.add(new Employee(6, "Milad", 27, 1489890, "M", "Finance", "Gurugram", 2017));
        empList.add(new Employee(7, "Uzma", 26, 14530, "F", "IT", "Pune", 2016));
        empList.add(new Employee(8, "Ali", 23, 1493445, "M", "Finance", "Trivandam", 2015));
        empList.add(new Employee(9, "Ram", 25, 160, "M", "IT", "Blore", 2010));

        return empList;
    }

    public static void main(String[] args) {

        List<Employee> employees= employeeList();

        //get all employees names list

        List<String> empName = employees.stream().map(Employee::getName).toList();
        System.out.println(empName);

        List<String> employeename = employees.stream().map(e -> e.getName()).toList();
        System.out.println(employeename);

        Flux<String> ename = Flux.fromIterable(employees).map(Employee::getName);
        ename.subscribe(System.out::println);

        //filter : all emp name whose age is more than 25

        List<String> agem25 = employees.stream().filter(emp -> emp.getAge() > 25)
                .map(e->e.getName()).toList();
        System.out.println(agem25);

        Flux<String> age25m = Flux.fromIterable(employees).filter(e -> e.getAge() > 25)
                .map(e->e.getName());
        age25m.subscribe(System.out::println);

        //print all city names of employees

        List<String> empcity = employees.stream().map(emp -> emp.getCity()).distinct().toList();
        System.out.println(empcity);

        Flux<String> ecity = Flux.fromIterable(employees).map(e -> e.getCity()).distinct();
        ecity.subscribe(System.out::println);

        //get count of employees whose salary greater than 20k

        long count = employees.stream().filter(e -> e.getSalary() > 20000).count();
        System.out.println(count);

        Mono<Long> salg2ok = Flux.fromIterable(employees).filter(e -> e.getSalary() > 20000).count();
        salg2ok.subscribe(System.out::println);

        //get name of employees whose salary is greater than 25k

        List<String> salg25k = employees.stream().filter(e -> e.getSalary() > 25000)
                .map(e -> e.getName()).toList();
        System.out.println(salg25k);

        Flux<String> sal25kname = Flux.fromIterable(employees).filter(e -> e.getSalary() > 25000).map(e -> e.getName());
        sal25kname.subscribe(System.out::println);

        //get first 3 employee object as a list

        List<Employee> first3 = employees.stream().limit(3).toList();
        System.out.println(first3);

        Flux<Employee> firstthree = Flux.fromIterable(employees).take(3);
        firstthree.subscribe(System.out::println);

        //skip first 3 employees and give the remaining employees

        List<Employee> skip3 = employees.stream().skip(3).toList();
        System.out.println(skip3);

        Flux<Employee> skipthree = Flux.fromIterable(employees).skip(3);
        skipthree.subscribe(System.out::println);

        //findFirst - always return the first employee from the list

        Employee employee = employees.stream().findFirst().get();
        System.out.println(employee);

        Mono<Employee> findfirst = Flux.fromIterable(employees).next();
        findfirst.subscribe(System.out::println);

        //sorted
        //get emp id in sorted order

        List<Integer> sortedid = employees.stream().map(e -> e.getId()).sorted().toList();
        System.out.println(sortedid);

        Flux<Integer> sortid = Flux.fromIterable(employees).map(e -> e.getId()).sort();
        sortid.subscribe(System.out::println);

        //minimum salary employee details

        Employee minsal = employees.stream().min((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())).get();
        System.out.println(minsal);

        Employee minsalary = employees.stream().min(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(minsalary);

//        Employee minSalaryEmp = Flux.fromIterable(employees)
//                .min(Comparator.comparingDouble(Employee::getSalary))
//                .block();
//
//        System.out.println(minSalaryEmp);

        //maximum salary employee details

        Employee maxsal = employees.stream().max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())).get();
        System.out.println(maxsal);

        Employee maxsalary = employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(maxsalary);


        //average salary
        //doublestream : stream contains always double values

        double asDouble = employees.stream().mapToDouble(Employee::getSalary).average().getAsDouble();
        System.out.println(asDouble);

        Mono<Double> salaryavg = Flux.fromIterable(employees).map(Employee::getSalary)
                .collect(Collectors.averagingDouble(Double::doubleValue));
        salaryavg.subscribe(System.out::println);


        //collect employee id's and their salaries as a map

        Map<Integer, Double> empidsal = employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getSalary));
        System.out.println(empidsal);

        Mono<Map<Integer, Double>> idsal = Flux.fromIterable(employees).
        collect(Collectors.toMap(Employee::getId, Employee::getSalary));

        idsal.subscribe(System.out::println);

        //collect emp name and emp salary

        Map<String, Double> namsal = employees.stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        System.out.println(namsal);

        Mono<Map<String, Double>> namsalary = Flux.fromIterable(employees)
                .collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        namsalary.subscribe(System.out::println);

        //groupBy
        //get average salary of each department

        Map<String, Double> avgsaldep = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgsaldep);

        Mono<Map<String, Double>> avgsaldepwise = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getDeptName
                , Collectors.averagingDouble(Employee::getSalary)));
        avgsaldepwise.subscribe(System.out::println);

        //count() inside collectors class
        //get count of employees gender wise

        Map<String, Long> countemp = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(countemp);

        Mono<Map<String, Long>> empcount = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getGender,
                Collectors.counting()));
        empcount.subscribe(System.out::println);

        //group the employees by city

        Map<String, List<Employee>> empbycity = employees.stream().collect(Collectors.groupingBy(Employee::getCity));
        System.out.println(empbycity);

        Mono<Map<String, List<Employee>>> cityemp = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getCity));
        System.out.println(cityemp);

        //group the employee by age

        Map<Integer, List<Employee>> empage = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(empage);

        Mono<Map<Integer, List<Employee>>> agewiseemp = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getAge));
        agewiseemp.subscribe(System.out::println);

        //count of male and female employees present in the organisation

        Map<String, Long> genwisecou = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(genwisecou);

        Mono<Map<String, Long>> gencount = Flux.fromIterable(employees)
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        gencount.subscribe(System.out::println);

        //count of male and female employees present in the dept wise

        Map<String, Map<String, Long>> gencoudep = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.groupingBy(Employee::getGender, Collectors.counting())));
        System.out.println(gencoudep);

        Mono<Map<String, Map<String, Long>>> gencountdepwise = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.groupingBy(Employee::getGender, Collectors.counting())));
        gencountdepwise.subscribe(System.out::println);

        //print average age of male and female employees

        Map<String, Double> avgage = employees.stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(avgage);

        Mono<Map<String, Double>> avgageemp = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getGender
                , Collectors.averagingInt(Employee::getAge)));
        avgageemp.subscribe(System.out::println);

        //print the no of employees in each department

        Map<String, Long> empdepwise = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(empdepwise);

        Mono<Map<String, Long>> depemp = Flux.fromIterable(employees)
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        depemp.subscribe(System.out::println);

        //distinct department names that employee work for

        List<String> distdep = employees.stream().map(Employee::getDeptName).distinct().toList();
        System.out.println(distdep);

        Flux<String> depdistinct = Flux.fromIterable(employees).map(Employee::getDeptName).distinct();
        depdistinct.subscribe(System.out::println);

        //employee count in every department

        Map<String, Long> countdep = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(countdep);

        Mono<Map<String, Long>> depcount = Flux.fromIterable(employees)
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        depcount.subscribe(System.out::println);

        //average salary of each department

        Map<String, Double> depavgsal = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(depavgsal);

        Mono<Map<String, Double>> depavgsalary = Flux.fromIterable(employees)
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));
        depavgsalary.subscribe(System.out::println);

        //Find maximum age/oldest of employee in the organisation.

        Employee maxage = employees.stream().max(Comparator.comparingInt(Employee::getAge)).get();
        System.out.println(maxage);

        Mono<Optional<Employee>> maxageemp = Flux.fromIterable(employees)
                .collect(Collectors.maxBy(Comparator.comparingInt(Employee::getAge)));
        System.out.println(maxageemp);

        //Print Average age of Male and Female Employees in the organisation

        Map<String, Double> avgagegender = employees.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.averagingDouble(Employee::getAge)));
        System.out.println(avgagegender);

        Mono<Map<String, Double>> avgagegenwise = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getGender,
                Collectors.averagingDouble(Employee::getAge)));
        System.out.println(avgagegenwise);

        //Print Average age of Male and Female Employees in each department.

        Map<String, Double> avgagedep = employees.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.averagingDouble(Employee::getAge)));
        System.out.println(avgagedep);

        Mono<Map<String, Double>> avgagedepwise = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getGender,
                Collectors.averagingDouble(Employee::getAge)));
        avgagedepwise.subscribe(System.out::println);

        //Print the number of employees in each department.

        Map<String, Long> depcountwise = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(depcountwise);

        Mono<Map<String, Long>> depwiseemp = Flux.fromIterable(employees)
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        depwiseemp.subscribe(System.out::println);

        //Find longest serving employee in each department

        Map<String, Optional<Employee>> longserv = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingInt(Employee::getYearOfJoining))));
        System.out.println(longserv);

        Mono<Map<String, Optional<Employee>>> longse = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingInt(Employee::getYearOfJoining))));
        System.out.println(longse);

        //Find average age of gender in each department.

        Map<String, Double> avgagedepa = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.averagingDouble(Employee::getAge)));
        System.out.println(avgagedepa);

        Mono<Map<String, Double>> avgagedepart = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.averagingDouble(Employee::getAge)));
        avgagedepart.subscribe(System.out::println);

        //Find youngest female employee in the organisation.

        Optional<Employee> youngfemp = employees.stream().filter(e -> e.getGender() == "F").min(Comparator.comparingInt(
                Employee::getAge));
        System.out.println(youngfemp);

        //Find the youngest employee in each department

        Map<String, Optional<Employee>> youngemp = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingInt(Employee::getAge))));
        System.out.println(youngemp);

        Mono<Map<String, Optional<Employee>>> youngempdep = Flux.fromIterable(employees)
                .collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingInt(Employee::getAge))));
        youngempdep.subscribe(System.out::println);

        //Find the department name which has the highest number of employees.

        Map.Entry<String, Long> maxNoOfEmployeesInDept = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).
                entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(maxNoOfEmployeesInDept);

        //Find if there any employees from HR Department.

        Optional<Employee> hr = employees.stream().filter(e -> e.getDeptName().equalsIgnoreCase("HR")).findAny();
        System.out.println(hr);

        //Find all employees who lives in ‘Blore’ city,
        // sort them by their name and print the names of employees.

        List<Employee> blore = employees.stream().filter(e -> e.getCity().equalsIgnoreCase("Blore"))
                .sorted(Comparator.comparing(Employee::getName)).toList();
        System.out.println(blore);

        Flux<Employee> blore1 = Flux.fromIterable(employees).filter(e -> e.getCity().equalsIgnoreCase("Blore"))
                .sort(Comparator.comparing(Employee::getName));
        blore1.subscribe(System.out::println);

        //Print Average salary by gender in each department

        Map<String, Map<String, Double>> avgsaldepaw = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));
        System.out.println(avgsaldepaw);

        Mono<Map<String, Map<String, Double>>> avgsaldepawise = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));
        avgsaldepawise.subscribe(System.out::println);

        //Find Highest salary in the organisation.

        Employee highsal = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .findFirst().get();
        System.out.println(highsal);

        Mono<Employee> highestsal = Flux.fromIterable(employees)
                .sort(Comparator.comparingDouble(Employee::getSalary).reversed()).next();
        highestsal.subscribe(System.out::println);

        //Find Second Highest salary in the organisation.

        Optional<Employee> sechigh = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary)
                .reversed()).skip(1).findFirst();
        System.out.println(sechigh);

        Mono<Employee> sechighsal = Flux.fromIterable(employees).sort(Comparator.comparingDouble(Employee::getSalary)
                .reversed()).skip(1).next();
        sechighsal.subscribe(System.out::println);

        //Print the top 3 highest salary earned employees in the organisation

        Optional<Employee> high3 = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3).findFirst();
        System.out.println(high3);

        Flux<Employee> taketop3 = Flux.fromIterable(employees).sort(Comparator.comparingDouble(Employee::getSalary).reversed()).take(3);
        taketop3.subscribe(System.out::println);

        //Print the top 2 highest salary earned employees in each department

        employees.stream().collect(Collectors.groupingBy(Employee::getDeptName)).
                forEach((dept, employees1) -> {
                    employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).limit(2).forEach(System.out::println);
                });

        //Find highest paid salary in the organisation based on gender.

        Map<String, Optional<Employee>> highsaldep = employees.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(highsaldep);

        Mono<Map<String, Optional<Employee>>> highsaldepwise = Flux.fromIterable(employees)
                .collect(Collectors.groupingBy(Employee::getGender,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        highsaldepwise.subscribe(System.out::println);

        //Find lowest paid salary in the organisation

        Optional<Employee> minsala = employees.stream().min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(minsala);

        //Find lowest paid salary in each department based on the gender.

        Map<String, Map<String, Optional<Employee>>> lowsalgendep = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName, Collectors.groupingBy(Employee::getGender,
                        Collectors.minBy(Comparator.comparingDouble(Employee::getSalary)))));
        System.out.println(lowsalgendep);

        Mono<Map<String, Map<String, Optional<Employee>>>> lowsaldepgenwise = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getDeptName, Collectors.groupingBy(Employee::getGender,
                Collectors.minBy(Comparator.comparingDouble(Employee::getSalary)))));
        lowsaldepgenwise.subscribe(System.out::println);

        //Sort the employees salary in the organisation in ascending order

        Stream<Employee> sortinasc = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(sortinasc);

        Flux<Employee> sortasc = Flux.fromIterable(employees).sort(Comparator.comparingDouble(Employee::getSalary));
        sortasc.subscribe(System.out::println);

        //Sort the employees salary in the organisation in descending order.

        List<Employee> sortindes = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary)
                .reversed()).toList();
        System.out.println(sortindes);

        Mono<List<Employee>> sortindescend = Flux.fromIterable(employees)
                .sort(Comparator.comparingDouble(Employee::getSalary).reversed()).collectList();
        sortindescend.subscribe(System.out::println);

        //Highest salary based on department.

        Map<String, Optional<Employee>> highsaldepw = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(highsaldepw);

        Mono<Map<String, Optional<Employee>>> highsalarydep = Flux.fromIterable(employees)
                .collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        highsalarydep.subscribe(System.out::println);

        //Lowest paid based in each department

        Map<String, Optional<Employee>> lowsaldep = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(lowsaldep);

        Mono<Map<String, Optional<Employee>>> lowsal = Flux.fromIterable(employees).collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingDouble(Employee::getSalary))));
        lowsal.subscribe(System.out::println);

        //Find the employees whose name start with J.

         employees.stream().filter(emp -> emp.getName().startsWith("J")).map(Employee::getName)
                        .forEach(i-> System.out.println("Name starts with j"));

        Flux<String> j = Flux.fromIterable(employees).filter(e -> e.getName().startsWith("J")).map(Employee::getName);
        j.subscribe(System.out::println);

        //Find list of employees whose age is less than 30 in Department HR

        Stream<Employee> hr1 = employees.stream().filter(e -> e.getAge() < 30 && e.getDeptName().equalsIgnoreCase("HR"));
        System.out.println(hr1);

        Flux<Employee> hr2 = Flux.fromIterable(employees).filter(e -> e.getAge() < 30 && e.getDeptName()
                .equalsIgnoreCase("HR"));
        hr2.subscribe(System.out::println);


    }
}
