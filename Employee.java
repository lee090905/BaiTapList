package day10.BaiTapList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Employee.java
public class Employee {
    private String employeeId;
    private String name;
    private String department;
    private double salary;

    public Employee(String employeeId, String name, String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", name=" + name + ", department=" + department + ", salary=" + salary + "]";
    }
}

// EmployeeManager.java


class EmployeeManager {
    private List<Employee> employees;

    public EmployeeManager() {
        this.employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee findEmployeeById(String employeeId) {
        Optional<Employee> employee = employees.stream()
                .filter(e -> e.getEmployeeId().equals(employeeId))
                .findFirst();
        return employee.orElse(null);
    }

    public void updateEmployee(Employee updatedEmployee) {
        Employee employee = findEmployeeById(updatedEmployee.getEmployeeId());
        if (employee != null) {
            employee.setName(updatedEmployee.getName());
            employee.setDepartment(updatedEmployee.getDepartment());
            employee.setSalary(updatedEmployee.getSalary());
        }
    }

    public void deleteEmployee(String employeeId) {
        employees.removeIf(e -> e.getEmployeeId().equals(employeeId));
    }

    public List<Employee> listEmployeesByDepartment(String department) {
        return employees.stream()
                .filter(e -> e.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public void sortEmployeesBySalaryDescending() {
        employees.sort(Comparator.comparingDouble(Employee::getSalary).reversed());
    }

    public void printAllEmployees() {
        employees.forEach(System.out::println);
    }

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();

        // Thêm nhân viên
        manager.addEmployee(new Employee("E001", "John Doe", "IT", 5000));
        manager.addEmployee(new Employee("E002", "Jane Smith", "HR", 4000));
        manager.addEmployee(new Employee("E003", "Emily Davis", "IT", 6000));

        // Tìm kiếm nhân viên
        System.out.println("Tìm kiếm nhân viên với ID E002:");
        System.out.println(manager.findEmployeeById("E002"));

        // Cập nhật thông tin nhân viên
        System.out.println("\nCập nhật thông tin nhân viên với ID E002:");
        manager.updateEmployee(new Employee("E002", "Jane Smith", "Marketing", 4500));
        System.out.println(manager.findEmployeeById("E002"));

        // Xóa nhân viên
        System.out.println("\nXóa nhân viên với ID E001:");
        manager.deleteEmployee("E001");
        manager.printAllEmployees();

        // Liệt kê nhân viên của phòng ban IT
        System.out.println("\nNhân viên của phòng IT:");
        manager.listEmployeesByDepartment("IT").forEach(System.out::println);

        // Sắp xếp nhân viên theo lương giảm dần
        System.out.println("\nSắp xếp nhân viên theo lương giảm dần:");
        manager.sortEmployeesBySalaryDescending();
        manager.printAllEmployees();
    }
}
