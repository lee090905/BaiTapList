package day10.BaiTapList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private int age;
    private double gpa;

    public Student(int id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gpa=" + gpa +
                '}';
    }
}

class StudentManager {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student searchStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean updateStudentById(int id, String name, int age, double gpa) {
        Student student = searchStudentById(id);
        if (student != null) {
            student.setName(name);
            student.setAge(age);
            student.setGpa(gpa);
            return true;
        }
        return false;
    }

    public boolean deleteStudentById(int id) {
        Student student = searchStudentById(id);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }

    public void sortStudentsByName() {
        students.sort(Comparator.comparing(Student::getName));
    }

    public void displayStudentsWithGpaGreaterThan(double gpa) {
        for (Student student : students) {
            if (student.getGpa() > gpa) {
                System.out.println(student);
            }
        }
    }

    public void displayAllStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Thêm sinh viên");
            System.out.println("2. Tìm kiếm sinh viên theo ID");
            System.out.println("3. Cập nhật sinh viên theo ID");
            System.out.println("4. Xóa sinh viên theo ID");
            System.out.println("5. Sắp xếp sinh viên theo tên");
            System.out.println("6. Hiển thị sinh viên có GPA lớn hơn giá trị cho trước");
            System.out.println("7. Hiển thị tất cả sinh viên");
            System.out.println("8. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer
                    System.out.print("Nhập tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập tuổi: ");
                    int age = scanner.nextInt();
                    System.out.print("Nhập GPA: ");
                    double gpa = scanner.nextDouble();
                    manager.addStudent(new Student(id, name, age, gpa));
                    break;
                case 2:
                    System.out.print("Nhập ID để tìm kiếm: ");
                    id = scanner.nextInt();
                    Student student = manager.searchStudentById(id);
                    if (student != null) {
                        System.out.println(student);
                    } else {
                        System.out.println("Không tìm thấy sinh viên với ID: " + id);
                    }
                    break;
                case 3:
                    System.out.print("Nhập ID để cập nhật: ");
                    id = scanner.nextInt();
                    scanner.nextLine(); // Clear the buffer
                    System.out.print("Nhập tên mới: ");
                    name = scanner.nextLine();
                    System.out.print("Nhập tuổi mới: ");
                    age = scanner.nextInt();
                    System.out.print("Nhập GPA mới: ");
                    gpa = scanner.nextDouble();
                    if (manager.updateStudentById(id, name, age, gpa)) {
                        System.out.println("Cập nhật thành công.");
                    } else {
                        System.out.println("Không tìm thấy sinh viên với ID: " + id);
                    }
                    break;
                case 4:
                    System.out.print("Nhập ID để xóa: ");
                    id = scanner.nextInt();
                    if (manager.deleteStudentById(id)) {
                        System.out.println("Xóa thành công.");
                    } else {
                        System.out.println("Không tìm thấy sinh viên với ID: " + id);
                    }
                    break;
                case 5:
                    manager.sortStudentsByName();
                    System.out.println("Danh sách sinh viên sau khi sắp xếp:");
                    manager.displayAllStudents();
                    break;
                case 6:
                    System.out.print("Nhập giá trị GPA: ");
                    gpa = scanner.nextDouble();
                    System.out.println("Các sinh viên có GPA lớn hơn " + gpa + ":");
                    manager.displayStudentsWithGpaGreaterThan(gpa);
                    break;
                case 7:
                    System.out.println("Danh sách tất cả sinh viên:");
                    manager.displayAllStudents();
                    break;
                case 8:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }
}
