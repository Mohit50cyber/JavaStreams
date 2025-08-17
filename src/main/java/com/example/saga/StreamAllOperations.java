package com.example.saga;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAllOperations {

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

        //get all employees names list

        List<String> empName = employees.stream().map(Employee::getName).toList();
        System.out.println(empName);

        List<String> nameEmp = employees.stream().map(emp -> emp.getName()).toList();
        System.out.println(nameEmp);

        //filter : all emp name whose age is more than 25

        List<String> ageemp = employees.stream()
                .filter(emp -> emp.getAge() > 25)
                .map(emp->emp.getName())
                .toList();
        System.out.println(ageemp);

        //print all city names of employees

        List<String> distinctcity = employees.stream().map(employee -> employee.getCity())
                .distinct()
                .toList();
        System.out.println(distinctcity);

        //get count of employees whose salary greater than 20k

        long count = employees.stream().filter(emp -> emp.getSalary() > 20000)
                .count();
        System.out.println(count);

        //get name of employees whose salary is greater than 25k
        List<String> empN = employees.stream().filter(emp -> emp.getSalary() > 25000)
                .map(emp -> emp.getName()).toList();
        System.out.println(empN);

        //get first 3 employee object as a list
        List<Employee> first3 = employees.stream().limit(3).toList();
        System.out.println(first3);

        //skip first 3 employees and give the remaining employees

        List<Employee> afterfirst3 = employees.stream().skip(3).toList();
        System.out.println(afterfirst3);

        //anymatch
        //verify any employee under age 18

        boolean empless18 = employees.stream().anyMatch(emp -> emp.getAge() < 18);
        System.out.println(empless18);

        //allmatch
        //check every employee joined after 2010 or not
        boolean joinedafter2010 = employees.stream().allMatch(emp -> emp.getYearOfJoining() > 2010);
        System.out.println(joinedafter2010);

        //nonematch
        //any one matching then false otherwise none matching true
        //every employee salary under 10k

        boolean salaryunder10k = employees.stream().noneMatch(emp -> emp.getSalary() < 10000);
        System.out.println(salaryunder10k);

        //findAny
        //findFirst - always return the first employee from the list

        Employee firstemployee = employees.stream().findFirst().get();
        System.out.println(firstemployee);

        //sorted
        //get emp id in sorted order
        List<Integer> sorted = employees.stream().map(emp -> emp.getId()).sorted().toList();
        System.out.println(sorted);

        //minimum salary employee details
        Employee minemployeesalary = employees.stream()
                .min((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
                .get();
        System.out.println(minemployeesalary);

        //max salary
        Employee maxsalary = employees.stream().max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())).get();
        System.out.println(maxsalary);

        //average salary
        //doublestream : stream contains always double values
        double averagesalary = employees.stream().mapToDouble(emp -> emp.getSalary()).average().getAsDouble();
        System.out.println(averagesalary);

        //intstream


        //peek()
        //collect
        //Collectors : JDK8 , as part of stream API operations
        //unique department name

        List<String> uniquedept = employees.stream().map(Employee::getDepartment).distinct().toList();
        System.out.println(uniquedept);

        //collect employee id's and their salaries as a map

        Map<Integer, Double> collectIdAndSalary = employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getSalary));
        System.out.println(collectIdAndSalary);

        //collect emp name and emp salary


        Map<String, Double> collectnameandsalary = employees.stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        System.out.println(collectnameandsalary);

        //groupBy
        //get average salary of each department

        Map<String, Double> avgsaldep = employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
        ));
        System.out.println(avgsaldep);

        //count() inside collectors class
        //get count of employees gender wise
        Map<String, Long> countgenderwise = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.counting()
        ));
        System.out.println(countgenderwise);


        //summarizing......
        System.out.println(employees.stream().collect(Collectors.summarizingDouble(Employee::getSalary)));

        //summing.....
        System.out.println(employees.stream().collect(Collectors.summingDouble(Employee::getSalary)));

       //joining
       //all dept name with delimiter
        String alldepName = employees.stream().map(Employee::getDepartment).collect(Collectors.joining(":::"));
        System.out.println(alldepName);


        //group the employees by city
        Map<String, List<Employee>> groupByCity = employees.stream().collect(Collectors.groupingBy(Employee::getCity));
        System.out.println(groupByCity);

        //group the employee by age
        Map<Integer, List<Employee>> groupByAge = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(groupByAge);

        //count of male and female employees present in the organisation
        Map<String,Long> countByGender=employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.counting()));
        System.out.println(countByGender);

        //print the names of all department in the organisation
        List<String> uniqueDpt = employees.stream().map(Employee::getDepartment).distinct().toList();
        System.out.println(uniqueDpt);

        //print employee details whose age is greater than 28
        employees.stream().filter(e->e.getAge() > 28).forEach(System.out::println);

        //max age of employee
        OptionalInt maxAge = employees.stream().mapToInt(Employee::getAge).max();
        System.out.println(maxAge);


        //print average age of male and female employees
        Map<String, Double> avgageMF = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.averagingInt(Employee::getAge)
        ));
        System.out.println(avgageMF);


        //print the no of employees in each department
        Map<String, Long> noEmpCount = employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.counting()
        ));
        System.out.println(noEmpCount);

        //oldest employee by age
        Employee oldEmployee = employees.stream().max(Comparator.comparingInt(Employee::getYearOfJoining)).get();
        System.out.println(oldEmployee);

        //longest serving employee in the organisation
        Optional<Employee> seniorEmp = employees.stream().sorted(Comparator.comparingInt(Employee::getYearOfJoining)).findFirst();
        System.out.println(seniorEmp);

        //find employees from Security Dept
        Optional<Employee> security = employees.stream().filter(e -> e.getDepartment().equalsIgnoreCase("Security")).findAny();
        System.out.println(security);

        //distinct department names that employee work for
        employees.stream().map(e->e.getDepartment()).distinct().forEach(System.out::println);

        //find all employees who lives in "Gurgaon,sort them by their name and print name of employees
        employees.stream().filter(e->e.getCity().equalsIgnoreCase("Gurgaon"))
                .sorted(Comparator.comparing(Employee::getName))
                .forEach(e->System.out.println("name is  : " + e.getName()));

        //no of employees in the organisation
        long countEmp = employees.stream().count();
        System.out.println(countEmp);

        //employee count in every department
        Map<String,Long> empDepCount=employees.stream().collect(Collectors.groupingBy(
           Employee::getDepartment,
           Collectors.counting()
        ));
        System.out.println(empDepCount);

        //average salary of each department
        Map<String,Double> avgSalaryDept=employees.stream().collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.averagingDouble(Employee::getSalary)
        ));
        System.out.println(avgSalaryDept);


        //print average salary by gender in each department
        Map<String,Double> avgSalByGender=employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.averagingDouble(Employee::getSalary)
        ));
        System.out.println(avgSalByGender);


        //highest salary in the organisation
        Optional<Employee> highestSalary = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary)
                .reversed()).findFirst();
        System.out.println(highestSalary);

        //second highest salary in the organisation
        Employee secondHighSal = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1).findFirst().get();
        System.out.println(secondHighSal);

        //nth highest salary
        long n = 5;
        Employee nthHigh = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(n - 1).findFirst().get();
        System.out.println(nthHigh);

        //top 3 highest salary earned employees
        Stream<Employee> top3highSal = employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).limit(3);
        System.out.println(top3highSal);

        //highest paid salary in organisation based gender
        Map<String, Optional<Employee>> highSalGender = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.maxBy((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
        ));
        System.out.println(highSalGender);

        //lowest paid salary in organisation based on gender
        Map<String, Optional<Employee>> lowsalGender = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.minBy((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
        ));
        System.out.println(lowsalGender);

        //lowest paid salary in org.
        employees.stream().min(Comparator.comparingDouble(Employee::getSalary))
                .ifPresent(e->System.out.println("Lowest salary : " + e.getName()));


        //lowest paid salary in org. based on gender
        Map<String, Optional<Employee>> lowSalGender = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.minBy((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()))
        ));
        System.out.println(lowSalGender);


        System.out.println("-------------------------------------------------------------");


        //sort the emp salary in ascending order
        employees.stream().sorted(Comparator.comparing(Employee::getSalary))
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------------");

        //sort the emp salary in descending order
        employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed())
                .forEach(System.out::println);

        System.out.println("-------------------------------------------------------------");


        //no of male and female in each department
        Map<String, Long> countMF = employees.stream().collect(Collectors.groupingBy(
                Employee::getGender,
                Collectors.counting()
        ));
        System.out.println(countMF);

        //list of employees whose age is less than 30 in department Aviation
        employees.stream().filter(e->e.getAge() <30 &&
                e.getDepartment().equalsIgnoreCase("Aviation"))
                .forEach(name->System.out.println("young in dev " + name ));


        //employees whose name start with J
        employees.stream().filter(e->e.getDepartment().toLowerCase()
                .startsWith("J"))
                .map(Employee::getName)
                .forEach(name->System.out.println("Name starts with J: " + name));












    }
}
