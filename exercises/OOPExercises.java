/**
 * BÀI TẬP THỰC HÀNH - OOP (Lập trình Hướng Đối Tượng)
 *
 * Hướng dẫn:
 * 1. Đọc kỹ yêu cầu từng bài
 * 2. Implement theo hướng dẫn
 * 3. Chạy test để kiểm tra kết quả
 */

// ===== BÀI 1: ENCAPSULATION =====

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Yêu cầu: Tạo class BankAccount với:
 * - Private fields: accountNumber, balance, owner
 * - Constructor khởi tạo tất cả fields
 * - Getter cho tất cả fields
 * - deposit(amount): Nạp tiền (chỉ chấp nhận số dương)
 * - withdraw(amount): Rút tiền (kiểm tra số dư, không được âm)
 * - getBalance(): Trả về số dư
 */
class BankAccount {
    // TODO: Implement here
    private String accountNumber;
    private double balance;
    private String owner;

    public BankAccount(String accountNumber, double balance, String owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public String getOwner() {
        return this.owner;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            return;
        } else {
            throw new RuntimeException("amount must positive");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return;
        } else {
            throw new RuntimeException("amount must positive or amount must less then balance");
        }
    }
}

// ===== BÀI 2: INHERITANCE =====

/**
 * Yêu cầu: Tạo hierarchy cho Vehicle
 *
 * Vehicle (abstract class):
 * - Fields: brand, model, year
 * - Abstract method: void start()
 * - Concrete method: void displayInfo()
 *
 * Car extends Vehicle:
 * - Additional field: numberOfDoors
 * - Implement start(): "Car is starting..."
 *
 * Motorcycle extends Vehicle:
 * - Additional field: hasSidecar
 * - Implement start(): "Motorcycle is starting..."
 **/
abstract class Vehicle {
    // TODO: Implement here
    protected String brand;
    protected String model;
    protected short year;

    public Vehicle(String brand, String model, short year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    protected abstract void start();

    protected void displayInfo() {
        System.out.printf("""
                +++++++++++++++++++++++++
                    - brand: %s
                    - model: %s
                    - year: %s
                +++++++++++++++++++++++++\n
                    """, this.brand, this.model, this.year);
    }
}

class Car extends Vehicle {
    // TODO: Implement here
    protected short numberOfDoors;

    public Car(String brand, String model, short year, short numberOfDoors) {
        super(brand, model, year);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void start() {
        System.out.println("Car is starting...");
    }
}

class Motorcycle extends Vehicle {
    // TODO: Implement here
    protected boolean hasSidecar;

    public Motorcycle(String brand, String model, short year, boolean hasSidecar) {
        super(brand, model, year);
        this.hasSidecar = hasSidecar;
    }

    @Override
    public void start() {
        System.out.println("Motorcycle is starting...");
    }
}

// ===== BÀI 3: POLYMORPHISM - METHOD OVERLOADING =====

/**
 * Yêu cầu: Tạo class Calculator với các method add() overloaded:
 * - add(int a, int b): Cộng 2 số nguyên
 * - add(double a, double b): Cộng 2 số thực
 * - add(int a, int b, int c): Cộng 3 số nguyên
 * - add(String a, String b): Nối 2 chuỗi
 */
class Calculator {
    // TODO: Implement here

    protected int add(int a, int b) {
        return a + b;
    }

    protected double add(double a, double b) {
        return a + b;
    }

    protected int add(int a, int b, int c) {
        return a + b + c;
    }

    protected String add(String a, String b) {
        return a + b;
    }
}

// ===== BÀI 4: POLYMORPHISM - METHOD OVERRIDING =====

/**
 * Yêu cầu: Tạo hierarchy cho Shape
 *
 * Shape (abstract class):
 * - Abstract method: double calculateArea()
 * - Abstract method: double calculatePerimeter()
 *
 * Rectangle extends Shape:
 * - Fields: width, height
 * - Implement calculateArea() và calculatePerimeter()
 *
 * Circle extends Shape:
 * - Field: radius
 * - Implement calculateArea() và calculatePerimeter()
 * - Sử dụng Math.PI
 */
abstract class Shape {
    // TODO: Implement here
    protected abstract double calculateArea();

    protected abstract double calculatePerimeter();
}

class Rectangle extends Shape {
    // TODO: Implement here
    private double width;
    private double height;

    public Rectangle(int width, int height) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}

class Circle extends Shape {
    // TODO: Implement here
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return radius * radius * Math.PI;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * radius * Math.PI;
    }
}

// ===== BÀI 5: INTERFACE =====

/**
 * Yêu cầu: Tạo interface Drawable và Resizable
 *
 * Drawable:
 * - void draw()
 *
 * Resizable:
 * - void resize(double factor)
 *
 * Image implements Drawable, Resizable:
 * - Fields: width, height, name
 * - draw(): In ra "Drawing image: [name]"
 * - resize(factor): Nhân width và height với factor
 */
interface Drawable {
    // TODO: Implement here
    public abstract void draw();
}

interface Resizable {
    // TODO: Implement here
    public abstract void resize(double factor);
}

class Image implements Drawable, Resizable {
    // TODO: Implement here
    protected double width;
    protected double height;
    protected String name;

    public Image(double width, double height, String name) {
        this.width = width;
        this.height = height;
        this.name = name;
    }

    @Override
    public void draw() {
        System.out.println("Drawing image: " + name);
    }

    @Override
    public void resize(double factor) {
        this.height *= factor;
        this.width *= factor;
    }
}

// ===== BÀI 6: ABSTRACT CLASS VS INTERFACE =====

/**
 * Yêu cầu: Tạo hệ thống Employee
 *
 * Employee (abstract class):
 * - Fields: name, id, baseSalary
 * - Abstract method: double calculateSalary()
 * - Concrete method: void displayInfo()
 *
 * Bonus (interface):
 * - double calculateBonus()
 *
 * FullTimeEmployee extends Employee implements Bonus:
 * - calculateSalary(): baseSalary + calculateBonus()
 * - calculateBonus(): baseSalary * 0.2
 *
 * PartTimeEmployee extends Employee:
 * - Field: hoursWorked
 * - calculateSalary(): hoursWorked * hourlyRate
 */
abstract class Employee {
    // TODO: Implement here
    protected String name;
    protected String id;
    protected int baseSalary;

    public Employee(String name, String id, int baseSalary) {
        this.name = name;
        this.id = id;
        this.baseSalary = baseSalary;
    }

    protected abstract double calculateSalary();

    protected void displayInfo() {
        System.out.printf("%s - %s - %d", this.name, this.id, this.baseSalary);
    };
}

interface Bonus {
    // TODO: Implement here
    public abstract double calculateBonus();
}

class FullTimeEmployee extends Employee implements Bonus {
    // TODO: Implement here
    public FullTimeEmployee(String name, String id, int baseSalary) {
        super(name, id, baseSalary);
    }

    @Override
    public double calculateBonus() {
        return super.baseSalary * 0.2;
    }

    @Override
    protected double calculateSalary() {
        return super.baseSalary + this.calculateBonus();
    }
}

class PartTimeEmployee extends Employee {
    // TODO: Implement here
    public float hoursWorked;

    public PartTimeEmployee(String name, String id, int baseSalary, float hoursWorked) {
        super(name, id, baseSalary);
        this.hoursWorked = hoursWorked;
    }

    @Override
    protected double calculateSalary() {
        return this.hoursWorked * 20;
    }
}

// ===== BÀI 7: COMPOSITION =====

/**
 * Yêu cầu: Tạo class Computer sử dụng Composition
 *
 * CPU:
 * - Fields: brand, cores, speed
 * - Method: void displaySpecs()
 *
 * RAM:
 * - Fields: size, type
 * - Method: void displaySpecs()
 *
 * Computer:
 * - Fields: CPU cpu, RAM ram, String model
 * - Method: void displayFullSpecs() - Hiển thị thông tin computer + cpu + ram
 * - Method: void upgradeCPU(CPU newCpu)
 * - Method: void upgradeRAM(RAM newRam)
 */
class CPU {
    // TODO: Implement here
    protected String brand;
    protected short cores;
    protected double speed;

    public CPU(String brand, int cores, double speed) {
        this.brand = brand;
        this.cores = (short) cores;
        this.speed = speed;
    }

    protected void displaySpecs() {
        System.out.printf("%s - %d - %.0f", this.brand, this.cores, this.speed);
    }
}

class RAM {
    // TODO: Implement here
    protected int size;
    protected String type;

    public RAM(int size, String type) {
        this.size = size;
        this.type = type;
    }

    protected void displaySpecs() {
        System.out.printf("%d - %s \n", this.size, this.type);
    }
}

class Computer {
    // TODO: Implement here
    protected CPU cpu;
    protected RAM ram;
    protected String model;

    public Computer(CPU cpu, RAM ram, String model) {
        this.cpu = cpu;
        this.ram = ram;
        this.model = model;
    }

    protected void displayFullSpecs() {
        this.cpu.displaySpecs();
        this.ram.displaySpecs();
    }

    protected void upgradeCPU(CPU newCpu) {
        this.cpu = newCpu;
    }

    protected void upgradeRAM(RAM newRam) {
        this.ram = newRam;
    }
}

// ===== BÀI 8: STATIC MEMBERS =====

/**
 * Yêu cầu: Tạo class Student với static counter
 *
 * Student:
 * - Static field: studentCount (đếm số lượng students)
 * - Fields: id, name, grade
 * - Constructor: Tự động tăng studentCount và gán id
 * - Static method: getStudentCount()
 * - Static method: resetCounter()
 * - Instance method: displayInfo()
 */
class Student {
    // TODO: Implement here
    public static int studentCount;

    protected int id;
    protected String name;
    protected float grade;

    public Student(String name, int id) {
        studentCount++;
        this.name = name;
        this.id = id;
    }

    public static int getStudentCount() {
        return studentCount;
    }

    public static int resetCounter() {
        return studentCount = 0;
    }

    public void displayInfo() {
        System.out.printf("%d - %s - %d", this.id, this.name, this.grade);
    }
}

// ===== BÀI 9: EQUALS & HASHCODE =====

/**
 * Yêu cầu: Tạo class Book với override equals() và hashCode()
 *
 * Book:
 * - Fields: isbn, title, author
 * - Constructor
 * - Override equals(): 2 sách bằng nhau nếu cùng ISBN
 * - Override hashCode(): Dựa trên ISBN
 * - Override toString()
 */
class Book {
    // TODO: Implement here
    protected String isbn;
    protected String title;
    protected String author;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Book other = (Book) obj;
        return Objects.equals(other.isbn, this.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.isbn);
    }

    @Override
    public String toString() {
        return "Book{isbn='" + isbn + "', title='" + title + "', author='" + author + "'}";
    }
}

// ===== BÀI 10: COMPARABLE & COMPARATOR =====

/**
 * Yêu cầu: Tạo class Person với Comparable và Comparator
 *
 * Person implements Comparable<Person>:
 * - Fields: name, age, salary
 * - Constructor
 * - compareTo(): So sánh theo age (natural ordering)
 *
 * NameComparator implements Comparator<Person>:
 * - compare(): So sánh theo name
 *
 * SalaryComparator implements Comparator<Person>:
 * - compare(): So sánh theo salary (giảm dần)
 */
class Person implements Comparable<Person> {
    // TODO: Implement here
    protected String name;
    protected short age;
    protected float salary;

    public Person(String name, int age, float salary) {
        this.name = name;
        this.age = (short) age;
        this.salary = salary;
    }

    @Override
    public int compareTo(Person p) {
        if (this.age == p.age) {
            return 0;
        } else if (this.age > p.age) {
            return 1;
        } else {
            return -1;
        }
    }
}

class NameComparator implements java.util.Comparator<Person> {
    // TODO: Implement here
    @Override
    public int compare(Person o1, Person o2) {
        return o1.name.compareTo(o2.name);
    }
}

class SalaryComparator implements java.util.Comparator<Person> {
    // TODO: Implement here
    // decrease
    @Override
    public int compare(Person o1, Person o2) {
        if (o1.salary == o2.salary) {
            return 0;
        } else if (o1.salary > o2.salary) {
            return -1;
        } else {
            return 1;
        }
    }
}

// ===== MAIN CLASS - TEST CODE =====

public class OOPExercises {
    public static void main(String[] args) {
        System.out.println("=== TEST BÀI 1: ENCAPSULATION ===");
        // TODO: Test BankAccount
        BankAccount account = new BankAccount("ACC001", 1000, "John");
        account.deposit(500);
        account.withdraw(200);
        System.out.println("Balance: " + account.getBalance());

        System.out.println("\n=== TEST BÀI 2: INHERITANCE ===");
        // TODO: Test Vehicle hierarchy
        Car car = new Car("Toyota", "Camry", (short) 2024, (short) 4);
        car.displayInfo();
        car.start();

        System.out.println("\n=== TEST BÀI 3: OVERLOADING ===");
        // TODO: Test Calculator
        Calculator calc = new Calculator();
        System.out.println(calc.add(5, 3));
        System.out.println(calc.add(5.5, 3.2));

        System.out.println("\n=== TEST BÀI 4: OVERRIDING ===");
        // TODO: Test Shape
        Shape rect = new Rectangle(5, 10);
        Shape circle = new Circle(7);
        System.out.println("Rectangle Area: " + rect.calculateArea());
        System.out.println("Circle Area: " + circle.calculateArea());

        System.out.println("\n=== TEST BÀI 5: INTERFACE ===");
        // TODO: Test Image
        Image img = new Image(100, 200, "photo.jpg");
        img.draw();
        img.resize(1.5);

        System.out.println("\n=== TEST BÀI 6: ABSTRACT CLASS VS INTERFACE ===");
        // TODO: Test Employee
        Employee ft = new FullTimeEmployee("Alice", "E001", 5000);
        Employee pt = new PartTimeEmployee("Bob", "E002", 20, 160);
        ft.displayInfo();
        System.out.println("Salary: " + ft.calculateSalary());

        System.out.println("\n=== TEST BÀI 7: COMPOSITION ===");
        // TODO: Test Computer
        CPU cpu = new CPU("Intel", 8, 3.5);
        RAM ram = new RAM(16, "DDR4");
        Computer pc = new Computer(cpu, ram, "Gaming PC");
        pc.displayFullSpecs();

        System.out.println("\n=== TEST BÀI 8: STATIC ===");
        // TODO: Test Student
        Student s1 = new Student("Alice", 90);
        Student s2 = new Student("Bob", 85);
        System.out.println("Total students: " + Student.getStudentCount());

        System.out.println("\n=== TEST BÀI 9: EQUALS & HASHCODE ===");
        // TODO: Test Book
        Book b1 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
        Book b2 = new Book("978-0134685991", "Effective Java", "Joshua Bloch");
        System.out.println("b1.equals(b2): " + b1.equals(b2));
        System.out.println("Same hashCode: " + (b1.hashCode() == b2.hashCode()));

        System.out.println("\n=== TEST BÀI 10: COMPARABLE & COMPARATOR ===");
        // TODO: Test Person
        List<Person> people = Arrays.asList(
                new Person("Charlie", 30, 60000),
                new Person("Alice", 25, 70000),
                new Person("Bob", 35, 50000));
        Collections.sort(people); // Natural ordering (by age)
        Collections.sort(people, new NameComparator());
        Collections.sort(people, new SalaryComparator());
    }
}
