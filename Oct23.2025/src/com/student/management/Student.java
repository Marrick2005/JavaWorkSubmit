package com.student.management;

public class Student extends Person {
    private String className; // 班级（如"大数据2401"）
    private double gpa; // GPA

    // 调用父类
    public Student(String id, String name, String gender, int age, String className, double gpa) {
        super(id, name, gender, age);
        this.className = className;
        this.gpa = gpa;
    }

    // Getter和Setter
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public double getGpa() { return gpa; }
    public void setGpa(double gpa) { this.gpa = gpa; }

    // 重写toString()，便于打印
    @Override
    public String toString() {
        return "学号：" + getId() + "，姓名：" + getName() + "，性别：" + getGender() +
                "，年龄：" + getAge() + "，班级：" + className + "，绩点：" + gpa;
    }
}