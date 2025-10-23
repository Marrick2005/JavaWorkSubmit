package com.student.management;

public abstract class Person {
    private String id; // 学号
    private String name;
    private String gender; // 性别
    private int age;

    // 构造方法
    public Person(String id, String name, String gender, int age) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    // Getter和Setter方法（ID不提供Setter，避免修改）
    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}