package cn.edu.tiangong.bigdata.java.lab1;


public class Employee {
    private int id;             // 员工编号
    private String name;        // 员工姓名
    private double baseSalary;  // 基本薪水
    private double growthRate;  // 薪水增长率（小数形式，如1%=0.01）

    //无参
    public Employee() {
        this.id = 1000;
        this.name = "无名氏";
        this.baseSalary = 0.0;
        this.growthRate = 0.0;
    }

    // 单参
    public Employee(int id) {
        this.id = id;
        this.name = "新员工";
        this.baseSalary = 3000.0;
        this.growthRate = 0.01;  // 1%转换为小数
    }

    // 三参
    public Employee(int id, String name, double baseSalary) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.growthRate = 0.05;  // 5%转换为小数
    }

    // 四参
    public Employee(int id, String name, double baseSalary, double growthRate) {
        this.id = id;
        this.name = name;
        this.baseSalary = baseSalary;
        this.growthRate = growthRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getGrowthRate() {
        return growthRate;
    }

    public void setGrowthRate(double growthRate) {
        this.growthRate = growthRate;
    }

    //功能1薪水增长
    public double calculateSalaryIncrease() {
        return this.baseSalary * this.growthRate;
    }

    //功能2增长后工资
    public double calculateTotalSalary() {
        return this.baseSalary + calculateSalaryIncrease();
    }
}