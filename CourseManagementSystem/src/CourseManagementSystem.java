
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int id;
    private ArrayList<Double> grades;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.grades = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Double> getGrades() {
        return grades;
    }
}

class Course {
    private String courseCode;
    private String name;
    private int maxCapacity;
    private ArrayList<Student> enrolledStudents;

    public Course(String courseCode, String name, int maxCapacity) {
        this.courseCode = courseCode;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public ArrayList<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }
}

class CourseManagement {
    private static ArrayList<Course> courses = new ArrayList<>();
    private static ArrayList<Student> students = new ArrayList<>();

    public static void addCourse(String courseCode, String name, int maxCapacity) {
        Course course = new Course(courseCode, name, maxCapacity);
        courses.add(course);
    }

    public static void enrollStudent(Student student, Course course) {
        if (course.getMaxCapacity() > course.getEnrolledStudents().size()) {
            course.enrollStudent(student);
            students.add(student);
            System.out.println("Student " + student.getName() + " enrolled in course " + course.getName());
        } else {
            System.out.println("Course " + course.getName() + " has reached its maximum capacity.");
        }
    }

    public static void assignGrade(int studentID, String courseCode, double grade) {
        Student student = findStudentByID(studentID);
        Course course = findCourseByCode(courseCode);

        if (student != null && course != null) {
            student.getGrades().add(grade);
            System.out.println("Grade " + grade + " assigned to student " + student.getName() + " for course " + course.getName());
        } else {
            System.out.println("Student or course not found.");
        }
    }

    public static void calculateOverallGrades() {
        for (Student student : students) {
            ArrayList<Double> grades = student.getGrades();
            if (!grades.isEmpty()) {
                double sum = 0;
                for (double grade : grades) {
                    sum += grade;
                }
                double average = sum / grades.size();
                System.out.println("Overall grade for student " + student.getName() + ": " + average);
            } else {
                System.out.println("No grades assigned for student " + student.getName());
            }
        }
    }

    private static Student findStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getId() == studentID) {
                return student;
            }
        }
        return null;
    }

    static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

public class CourseManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Administrator Interface");
            System.out.println("1. Add a new course");
            System.out.println("2. Enroll a student");
            System.out.println("3. Assign a grade to a student");
            System.out.println("4. Calculate overall course grades for each student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addNewCourse(scanner);
                    break;
                case 2:
                    enrollStudent(scanner);
                    break;
                case 3:
                    assignGrade(scanner);
                    break;
                case 4:
                    calculateOverallGrades();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting Administrator Interface. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
        scanner.close();
    }

    private static void addNewCourse(Scanner scanner) {
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        System.out.print("Enter maximum capacity: ");
        int maxCapacity = scanner.nextInt();

        CourseManagement.addCourse(courseCode, name, maxCapacity);
        System.out.println("Course added successfully.");
    }

    private static void enrollStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter course code to enroll in: ");
        String courseCode = scanner.nextLine();

        Student student = new Student(studentName, studentID);
                    Course course = CourseManagement.findCourseByCode(courseCode);
        if (course != null) {
            CourseManagement.enrollStudent(student, course);
        } else {
            System.out.println("Course with code " + courseCode + " does not exist.");
        }
    }

    private static void assignGrade(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        
        System.out.print("Enter grade: ");
        if (scanner.hasNextDouble()) {
            double grade = scanner.nextDouble();
            CourseManagement.assignGrade(studentID, courseCode, grade);
        } else {
            System.out.println("Invalid grade format. Grade must be a number.");
            scanner.nextLine(); // Consume invalid input from scanner buffer
        }
    }

   public static void calculateOverallGrades() {
        Iterable<Student> students = null;
    for (Student student : students) {
        ArrayList<Double> grades = student.getGrades();
        if (!grades.isEmpty()) {
            double sum = 0;
            for (double grade : grades) {
                sum += grade;
            }
            double average = sum / grades.size();
            System.out.println("Overall grade for student " + student.getName() + ": " + average);
        } else {
            System.out.println("No grades assigned for student " + student.getName());
        }
    }
}

}