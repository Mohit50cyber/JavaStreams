package com.example.saga;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperation {

    public static List<Employee> employeesKaList(){

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee(6,"Vishal","Bathinda",43,"Male","Security",2016,20000.00));
        employeeList.add(new Employee(64,"qwerty","ssvsv",433,"Female","Fire",2019,20080.00));
        employeeList.add(new Employee(2,"nigga","jfjn",23,"Male","IT",2011,23000.00));
        employeeList.add(new Employee(61,"secret","advgsd",3,"Male","Defence",2014,25000.00));
        employeeList.add(new Employee(56,"vertika","ukkgu",5,"Female","Avaiation",2015,20300.00));
        employeeList.add(new Employee(66,"nunu","adgsdg",23,"Male","HR",2012,278000.00));
        employeeList.add(new Employee(16,"luli","dgsfx",56,"Female","Avaiation",2014,234000.00));
        employeeList.add(new Employee(76,"lund","cncbnc",44,"Male","Security",2018,25000.00));
        employeeList.add(new Employee(68,"chut","bmbm",11,"Female","Buisness",2013,278000.00));
        employeeList.add(new Employee(60,"dede","uiyui",22,"Female","Gym Trainer",2015,289000.00));
        employeeList.add(new Employee(90,"Mohit Singh","Gurgaon",29,"Male","IT",2000,2600000.0));

        return employeeList;
    }


    public static void main(String[] args) {
        List<Employee> employees = employeesKaList();

        //Stream methods

        //Get all employee names as List

        //With stream
        Stream<Employee> employeeStream =employees.stream();
        List<String>employees1=employeeStream.map(emp->emp.getName()).toList();
        System.out.println(employees1);

        Stream<Employee> employeeStream1 = employees.stream();
        List<Integer> employees2= employeeStream1.map(emp->emp.getAge()).toList();
        System.out.println(employees2);

        Stream<Employee> employeeStream2 = employees.stream();
        List<String> employees3=employeeStream2.map(emp->emp.getDepartment()).toList();
        System.out.println(employees3);


        //filter
        //emp name whose age <25
        Stream<Employee> employeeStream3 = employees.stream();
        List<Integer> employees4=employeeStream3.filter(emp->emp.getAge() >25)
                .map(emp->emp.getId()).toList();
        System.out.println(employees4);

        Stream<Employee> employeeStream4 = employees.stream();
        List<String> employees5 =employeeStream4.filter(emp->emp.getAge()>25)
                .map(emp->emp.getName()).toList();
        System.out.println(employees5);

        List<String> employeeStream5 = employees.stream()
                .filter(emp->emp.getAge()>30)
                .map(emp->emp.getName()).toList();
        System.out.println(employeeStream5);

        //Print all city names of employees
           employees.stream()
                .map(emp->emp.getCity())
                .distinct()
                .forEach(System.out::println);

        //get count of employees whose salary is > 20k
        Long countOfEmp=employees.stream()
                .filter(emp->emp.getSalary()>200000)
                .count();
        System.out.println(countOfEmp);


        //Get first three employee object as a list
        List<Employee> first3Employees=employees.stream()
                .limit(3).toList();
        System.out.println(first3Employees);


        //skip first 3 employees and collect other employee data
        List<Employee>skipfirst3=employees.stream()
                .skip(3).toList();
        System.out.println(skipfirst3);


        //Count no of employees
        Long count1 = employees.stream().distinct().count();
        System.out.println(count1);

        //anyMatch() --> verify any employee < 50

        boolean isUnderAge=employees.stream().anyMatch(emp->emp.getAge()<50);
        System.out.println(isUnderAge);

        //allMatch()
        //check every employee joined after 2010 or not

        boolean result=employees.stream().allMatch(emp->emp.getYearOfJoining()>2010);
        System.out.println(result);

        //noneMatch()
        //no one matching : true
        //any one Matching : false

        boolean result1= employees.stream().noneMatch(emp->emp.getSalary()<10000);
        System.out.println(result1);

        //findAny()
        //any one value from stream
        Employee e=employees.stream().findAny().get();
        System.out.println(e);

        //findFirst()
        //It will return first element from stream

        Employee emp1 = employees.stream()
                .findFirst().get();
        System.out.println(emp1);

        //sorted()
        //get employee id's in sorted order

        List<Integer> sortedEmpId=employees.stream().map(emp->emp.getId())
                 .sorted().toList();
        System.out.println(sortedEmpId);

        //sorted wrt comparator
        //define sorting based on employee id , sort employee objects
        List<Employee> sortedEmpObject=employees.stream().sorted((e1,e2)->{
            return e1.getId() - e2.getId();
        }).toList();
        System.out.println(sortedEmpObject);

        //min()
        //minimum salary employee detail
        Employee minSalaryGuy=employees.stream()
                .min((e1,e2)-> (int) (e1.getSalary() - e2.getSalary()))
                .get();
        System.out.println(minSalaryGuy);

        //max()
        //Maximum salary employee details
        Employee maxSalary = employees.stream()
                .max((e1,e2)-> (int) (e1.getSalary() - e2.getSalary()))
                .get();
        System.out.println(maxSalary);

        //peek()
        //out of all employees , find whose date of joining > 2015
        //track how many employees are processed



        //collect all employees names whose age is greater than 25
        List<String> names=employees.stream().filter(emp->emp.getAge()>25)
                .map(Employee::getName).collect(Collectors.toList());
        System.out.println(names);

        //unique department names
        Set<String> dep=employees.stream().map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toSet());
        System.out.println(dep);

        //collect employee Ids and their salaries as map
        Map<Integer,Double> empIdandSalary=employees.stream()
                .collect(Collectors.toMap(Employee::getId,Employee::getSalary));
        System.out.println(empIdandSalary);


    }






}
