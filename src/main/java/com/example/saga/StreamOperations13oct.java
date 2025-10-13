package streamsquestions;

import java.util.ArrayList;
import java.util.*;
import java.util.stream.Collectors;

public class StreamOperations13oct {

    public static List<Employee> employeeList () {

        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee(1, "Yanksha", 28, 128993, "F", "HR", "Blore", 2020));
        empList.add(new Employee(2, "Francesca", 29, 120000, "F", "HR", "Hyderabad", 2015));
        empList.add(new Employee(3, "Ramesh", 30, 146615, "M", "HR", "Chennai", 2014));
        empList.add(new Employee(4, "Melanie", 32, 12675, "F", "HR", "Chennai", 2013));
        empList.add(new Employee(5, "Padma", 22, 15450, "F", "IT", "Noida", 2013));
        empList.add(new Employee(6, "Milad", 27, 1489890, "M", "IT", "Gurugram", 2017));
        empList.add(new Employee(7, "Uzma", 26, 14530, "F", "IT", "Pune", 2016));
        empList.add(new Employee(8, "Ali", 23, 1493445, "M", "IT", "Trivandam", 2015));
        empList.add(new Employee(9, "Ram", 25, 160, "M", "IT", "Blore", 2010));

        return empList;
    }
    public static void main(String[] args) {

        List<Employee> employees = employeeList();

        //get all employees names list

        List<String> empName = employees.stream().map(Employee::getName).toList();
        System.out.println(empName);

        List<String> name = employees.stream().map(emp -> emp.getName()).toList();
        System.out.println(name);

        //filter : all emp name whose age is more than 25

        List<String> agemorethan25 = employees.stream().filter(emp -> emp.getAge() > 25).map(Employee::getName).toList();
        System.out.println(agemorethan25);

        //print all city names of employees

        List<String> city = employees.stream().map(Employee::getCity).distinct().toList();
        System.out.println(city);

        //get count of employees whose salary greater than 20k

        long count = employees.stream().filter(emp -> emp.getSalary() > 20000).count();
        System.out.println(count);

        //get name of employees whose salary is greater than 25k

        List<String> nameEmployee = employees.stream().filter(emp -> emp.getSalary() > 25000).map(Employee::getName).toList();
        System.out.println(nameEmployee);

        //get first 3 employee object as a list

        List<Employee> first3 = employees.stream().limit(3).toList();
        System.out.println(first3);

        //skip first 3 employees and give the remaining employees

        List<Employee> skipfirst3 = employees.stream().skip(3).toList();
        System.out.println(skipfirst3);

        //findFirst - always return the first employee from the list

        Employee employee = employees.stream().findFirst().get();
        System.out.println(employee);

        //sorted
        //get emp id in sorted order
        List<Integer> empidsort = employees.stream().map(emp -> emp.getId()).sorted().toList();
        System.out.println(empidsort);

        //minimum salary employee details

        Employee minSalary = employees.stream().min((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())).get();
        System.out.println(minSalary);

        //max salary

        Employee maxsalary = employees.stream().max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())).get();
        System.out.println(maxsalary);

        //average salary
        //doublestream : stream contains always double values

        double asDouble = employees.stream().mapToDouble(emp -> emp.getSalary()).average().getAsDouble();
        System.out.println(asDouble);

        //unique department name

        List<String> uniquedept = employees.stream().map(Employee::getDeptName).distinct().toList();
        System.out.println(uniquedept);

        //collect employee id's and their salaries as a map

        Map<Integer, Long> idandsalary = employees.stream().collect(Collectors.toMap(Employee::getId, Employee::getSalary));
        System.out.println(idandsalary);

        //collect emp name and emp salary

        Map<String, Long> nameSalary = employees.stream().collect(Collectors.toMap(Employee::getName, Employee::getSalary));
        System.out.println(nameSalary);

        //groupBy
        //get average salary of each department

        Map<String, Double> avgSalDepWise = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalDepWise);

        //count() inside collectors class
        //get count of employees gender wise

        Map<String, Long> countgender = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(countgender);

        //group the employees by city

        Map<String, List<Employee>> empCity = employees.stream().collect(Collectors.groupingBy(Employee::getCity));
        System.out.println(empCity);

        //group the employee by age

        Map<Integer, List<Employee>> byage = employees.stream().collect(Collectors.groupingBy(Employee::getAge));
        System.out.println(byage);

        //count of male and female employees present in the organisation

        Map<String, Long> countbygender = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(countbygender);

        //count of male and female employees present in the dept wise

        Map<String, Map<String, Long>> countgendept = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.groupingBy(Employee::getGender, Collectors.counting())));
        System.out.println(countgendept);

        //print average age of male and female employees

        Map<String, Double> avgage = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));
        System.out.println(avgage);

        //print the no of employees in each department

        Map<String, Long> empcount = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(empcount);

        //distinct department names that employee work for

        employees.stream().map(Employee::getDeptName).distinct().forEach(System.out::println);

        //employee count in every department

        Map<String, Long> empcountDept = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(empcountDept);

        //average salary of each department

        Map<String, Double> avgsalary = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgsalary);

        //group the employees by city

        Map<String, List<Employee>> grpbyCity = employees.stream().collect(Collectors.groupingBy(Employee::getCity));
        System.out.println(grpbyCity);

        //count of male and female present in the organisation

        Map<String, Long> countofmf = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(countofmf);

        //count of male and female present in the department

        Map<String, Map<String, Long>> mfindep = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.groupingBy(Employee::getGender, Collectors.counting())));
        System.out.println(mfindep);

        //name of all distinct dep
        List<String> disdep = employees.stream().map(Employee::getDeptName).distinct().toList();
        System.out.println(disdep);

        //Print employee details whose age is greater than 28 in the organisation.

        List<Employee> agegr28 = employees.stream().filter(emp -> emp.getAge() > 28).toList();
        System.out.println(agegr28);

        //Find maximum age/oldest of employee in the organisation.

        Employee maxage = employees.stream().max(Comparator.comparingInt(Employee::getAge)).get();
        System.out.println(maxage);

        //Print Average age of Male and Female Employees in the organisation

        Map<String, Double> avgagemf = employees.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge)));
        System.out.println(avgagemf);

        //Print Average age of Male and Female Employees in each department.
        Map<String, Map<String, Double>> avgagemfdep = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName
                , Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge))));
        System.out.println(avgagemfdep);

        //Print the number of employees in each department.

        Map<String, Long> empeachdep = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(empeachdep);

        //Find longest serving employees in the organization.

        Employee longservEmp = employees.stream().min(Comparator.comparingInt(Employee::getYearOfJoining)).get();
        System.out.println(longservEmp);


        //Find longest serving employee in each department

        Map<String, Optional<Employee>> longservdepwise = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingInt(Employee::getYearOfJoining))));
        System.out.println(longservdepwise);


        //Find average age of gender in each department.

        Map<String, Map<String, Double>> avgagedepwise = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName
                , Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge))));
        System.out.println(avgagedepwise);

        //Find youngest female employee in the organisation.

        Employee youngFemEmp = employees.stream().filter(e -> e.getGender() == "F")
                .min(Comparator.comparingInt(Employee::getAge)).get();
        System.out.println(youngFemEmp);

        //Find the youngest employee in each department

        Map<String, Optional<Employee>> youngempdep = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingInt(Employee::getAge))));
        System.out.println(youngempdep);

        //Find the department name which has the highest number of employees.

        Map.Entry<String, Long> maxNoOfEmployeesInDept = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting())).
                entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(maxNoOfEmployeesInDept);

        //Find if there any employees from HR Department.
        Optional<Employee> findanyhr = employees.stream().filter(e -> e.getDeptName().equalsIgnoreCase("HR"))
                .findAny();
        System.out.println(findanyhr);

        //Find all employees who lives in ‘Blore’ city,
        // sort them by their name and print the names of employees.

        Optional<Employee> bannamesort = employees.stream().filter(e -> e.getCity().equalsIgnoreCase("Bangalore"))
                .sorted(Comparator.comparing(Employee::getName)).findFirst();
        System.out.println(bannamesort);

        //No of employees in the organisation.

        long emp = employees.stream().count();
        System.out.println(emp);

        //Find employee count in every department

        Map<String, Long> empcountdep = employees.stream().
                collect(Collectors.groupingBy(Employee::getDeptName, Collectors.counting()));
        System.out.println(empcountdep);

        //Find the department which has the highest number of employees.

        //employees.stream().collect(Collectors.groupingBy())

        //Print Average salary of each department.

        Map<String, Double> avgsaldep = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName
                , Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgsaldep);

        //Print Average salary by gender in each department

        Map<String, Map<String, Double>> avgsalgendep = employees.stream().collect
                (Collectors.groupingBy(Employee::getDeptName,
                Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary))));
        System.out.println(avgsalgendep);

        //Find Highest salary in the organisation.

        Optional<Employee> highsal = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary)
                .reversed()).findFirst();
        System.out.println(highsal);

        Employee emphighsal = employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(emphighsal);

        //Find Second Highest salary in the organisation.

        Optional<Employee> sechighsal = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .skip(1).findFirst();
        System.out.println(sechighsal);

        //Nth Highest salary.

//        long n=10;
//        Employee nhighsal = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
//                .skip(n - 1).findFirst().get();
//        System.out.println(nhighsal);

        //Print the top 3 highest salary earned employees in the organisation

        Optional<Employee> top3highsal = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3).findFirst();
        System.out.println(top3highsal);

        //Print the top 2 highest salary earned employees in each department

        employees.stream().collect(Collectors.groupingBy(Employee::getDeptName)).
                forEach((dept, employees1) -> {
                    employees.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).limit(2).forEach(System.out::println);
                });

        //Find highest paid salary in the organisation based on gender.

        Map<String, Optional<Employee>> highsalgenderwise = employees.stream().collect(Collectors.groupingBy(Employee::getGender,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(highsalgenderwise);

        //Find lowest paid salary in the organisation

        Employee minSalaryEmp = employees.stream().min(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(minSalaryEmp);

        //Find lowest paid salary in each department based on the gender.

        Map<String, Map<String, Optional<Employee>>> minsalDepWise = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.groupingBy(Employee::getGender,
                Collectors.minBy(Comparator.comparingDouble(Employee::getSalary)))));
        System.out.println(minsalDepWise);

        //Sort the employees salary in the organisation in ascending order

        List<Employee> sortascsal = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary)).toList();
        System.out.println(sortascsal);

        //Sort the employees salary in the organisation in descending order.

        List<Employee> sortdescsal = employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).toList();
        System.out.println(sortdescsal);

        //Highest salary based on department.

        Map<String, Optional<Employee>> highsaldepwise = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(highsaldepwise);

        //Lowest paid based in each department

        Map<String, Optional<Employee>> lowsaldepwise = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDeptName,
                Collectors.minBy(Comparator.comparingDouble(Employee::getSalary))));
        System.out.println(lowsaldepwise);

        //Find the employees whose name start with J.

         employees.stream().filter(e -> e.getName()
                .startsWith("J")).map(Employee::getName).forEach(namej->
                System.out.println("Name starts with J" + namej));

         //Find list of employees whose age is less than 30 in Department HR

        employees.stream().filter(e->e.getAge() < 30 && e.getDeptName()
                .equalsIgnoreCase("HR")).map(Employee::getName)
                .forEach(System.out::println);



    }


}
