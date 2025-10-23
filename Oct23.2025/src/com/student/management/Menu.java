package com.student.management;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 命令行菜单类：实现用户交互，调用核心功能
public class Menu {
    private Scanner scanner;                // 接收用户输入
    private StudentManagementSystem system; // 核心功能实例
    private boolean isRunning;              // 控制菜单循环

    // 构造方法：初始化并启动菜单
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.system = new StudentManagementSystem();
        this.isRunning = true;
        showMainMenu(); // 显示主菜单
    }

    // 主菜单：匹配文档中的菜单结构（增加学生、列出全部等6+功能）
    private void showMainMenu() {
        while (isRunning) {
            System.out.println("\n==================== 学生信息管理系统 ====================");
            System.out.println("1. 增加学生信息");
            System.out.println("2. 列出全部学生信息");
            System.out.println("3. 查询学生信息（按学号）");
            System.out.println("4. 删除学生信息（按学号）");
            System.out.println("5. 修改学生信息（按学号）");
            System.out.println("6. 统计功能（绩点排序/班级平均等）");
            System.out.println("7. 从CSV导入数据");
            System.out.println("8. 导出数据到CSV");
            System.out.println("9. 退出系统");
            System.out.print("请选择操作（1-9）：");

            // 处理用户输入（避免非数字输入异常）
            if (!scanner.hasNextInt()) {
                System.out.println("输入错误！请输入1-9的数字！");
                scanner.next(); // 清空无效输入
                continue;
            }
            int choice = scanner.nextInt();
            scanner.nextLine(); // 吸收换行符，避免后续读取空值
            handleChoice(choice);
        }
        scanner.close(); // 退出时关闭扫描器
        System.out.println("系统已关闭！");
    }

    // 处理用户选择：调用对应功能
    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                listAllStudents();
                break;
            case 3:
                findStudentById();
                break;
            case 4:
                deleteStudentById();
                break;
            case 5:
                updateStudent();
                break;
            case 6:
                showStatMenu();
                break;
            case 7:
                loadFromCsv();
                break;
            case 8:
                exportToCsv();
                break;
            case 9:
                isRunning = false;
                break;
            default:
                System.out.println("选择错误！请输入1-9的数字！");
        }
    }

    // -------------------------- 各功能的交互实现 --------------------------
    // 1. 增加学生
    private void addStudent() {
        System.out.println("\n----- 增加学生信息 -----");
        System.out.print("请输入学号：");
        String id = scanner.nextLine();
        System.out.print("请输入姓名：");
        String name = scanner.nextLine();
        System.out.print("请输入性别（男/女）：");
        String gender = scanner.nextLine();
        System.out.print("请输入年龄：");
        // 处理年龄输入异常
        if (!scanner.hasNextInt()) {
            System.out.println("年龄需为整数！添加失败！");
            scanner.next();
            return;
        }
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("请输入班级：");
        String className = scanner.nextLine();
        System.out.print("请输入绩点：");
        // 处理绩点输入异常
        if (!scanner.hasNextDouble()) {
            System.out.println("绩点需为数字！添加失败！");
            scanner.next();
            return;
        }
        double gpa = scanner.nextDouble();
        scanner.nextLine();

        // 调用核心方法添加学生
        system.addStudent(new Student(id, name, gender, age, className, gpa));
    }

    // 2. 列出全部学生
    private void listAllStudents() {
        System.out.println("\n----- 全部学生信息 -----");
        List<Student> students = system.listAllStudents();
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // 3. 按学号查询学生
    private void findStudentById() {
        System.out.println("\n----- 按学号查询学生 -----");
        System.out.print("请输入要查询的学号：");
        String id = scanner.nextLine();
        Student student = system.findStudentById(id);
        if (student != null) {
            System.out.println("查询结果：" + student);
        } else {
            System.out.println("学号【" + id + "】不存在！");
        }
    }

    // 4. 按学号删除学生
    private void deleteStudentById() {
        System.out.println("\n----- 按学号删除学生 -----");
        System.out.print("请输入要删除的学号：");
        String id = scanner.nextLine();
        system.deleteStudentById(id);
    }

    // 5. 按学号修改学生
    private void updateStudent() {
        System.out.println("\n----- 按学号修改学生 -----");
        System.out.print("请输入要修改的学号：");
        String id = scanner.nextLine();
        // 先校验学号是否存在
        if (system.findStudentById(id) == null) {
            System.out.println("学号【" + id + "】不存在！修改失败！");
            return;
        }
        // 输入新信息
        System.out.print("请输入新姓名：");
        String newName = scanner.nextLine();
        System.out.print("请输入新性别（男/女）：");
        String newGender = scanner.nextLine();
        System.out.print("请输入新年龄：");
        if (!scanner.hasNextInt()) {
            System.out.println("年龄需为整数！修改失败！");
            scanner.next();
            return;
        }
        int newAge = scanner.nextInt();
        scanner.nextLine();
        System.out.print("请输入新班级：");
        String newClassName = scanner.nextLine();
        System.out.print("请输入新绩点：");
        if (!scanner.hasNextDouble()) {
            System.out.println("绩点需为数字！修改失败！");
            scanner.next();
            return;
        }
        double newGpa = scanner.nextDouble();
        scanner.nextLine();

        // 调用核心方法修改学生
        system.updateStudent(id, newName, newGender, newAge, newClassName, newGpa);
    }

    // 6. 统计功能子菜单
    private void showStatMenu() {
        while (true) {
            System.out.println("\n----- 统计功能 -----");
            System.out.println("1. 按绩点降序排序");
            System.out.println("2. 查看所有姓王的学生");
            System.out.println("3. 查看每个班级的平均绩点");
            System.out.println("4. 查看绩点低于2.0的学生");
            System.out.println("5. 查看每个班级绩点第二名的学生");
            System.out.println("6. 查看每个班级绩点最高的学生");
            System.out.println("7. 返回主菜单");
            System.out.print("请选择统计操作（1-7）：");

            if (!scanner.hasNextInt()) {
                System.out.println("输入错误！请输入1-7的数字！");
                scanner.next();
                continue;
            }
            int statChoice = scanner.nextInt();
            scanner.nextLine();
            if (statChoice == 7) break; // 返回主菜单

            // 处理统计选择
            switch (statChoice) {
                case 1:
                    List<Student> sortedByGpa = system.sortByGpaDesc();
                    System.out.println("按绩点降序排序结果：");
                    sortedByGpa.forEach(s -> System.out.println(s));
                    break;
                case 2:
                    List<Student> wangStudents = system.findStudentsSurnameWang();
                    System.out.println("姓王的学生列表：");
                    wangStudents.forEach(s -> System.out.println(s));
                    break;
                case 3:
                    Map<String, Double> avgGpaMap = system.getAvgGpaByClass();
                    System.out.println("每个班级的平均绩点：");
                    avgGpaMap.forEach((cls, avg) ->
                            System.out.println("班级【" + cls + "】平均绩点：" + String.format("%.2f", avg)));
                    break;
                case 4:
                    List<Student> lowGpaStudents = system.findStudentsGpaBelow2();
                    System.out.println("绩点低于2.0的学生列表：");
                    lowGpaStudents.forEach(s -> System.out.println(s));
                    break;
                case 5:
                    Map<String, Student> secondTopMap = system.getSecondTopGpaByClass();
                    System.out.println("每个班级绩点第二名的学生：");
                    secondTopMap.forEach((cls, student) -> {
                        if (student != null) {
                            System.out.println("班级【" + cls + "】绩点第二名：" + student);
                        } else {
                            System.out.println("班级【" + cls + "】学生数量不足2人，无第二名！");
                        }
                    });
                    break;
                case 6:
                    Map<String, Student> topGpaMap = system.getTopGpaByClass();
                    System.out.println("每个班级绩点最高的学生：");
                    topGpaMap.forEach((cls, student) ->
                            System.out.println("班级【" + cls + "】绩点最高：" + student));
                    break;
                default:
                    System.out.println("选择错误！请输入1-7的数字！");
            }
        }
    }

    // 7. 从CSV导入数据
    private void loadFromCsv() {
        System.out.println("\n----- 从CSV导入数据 -----");
        System.out.print("请输入CSV文件路径（如D:/students.csv）：");
        String filePath = scanner.nextLine();
        try {
            system.loadFromCsv(filePath);
        } catch (Exception e) {
            System.out.println("导入失败！原因：" + e.getMessage());
        }
    }

    // 8. 导出数据到CSV
    private void exportToCsv() {
        System.out.println("\n----- 导出数据到CSV -----");
        System.out.print("请输入导出路径（如D:/export_students.csv）：");
        String filePath = scanner.nextLine();
        try {
            system.exportToCsv(filePath);
        } catch (Exception e) {
            System.out.println("导出失败！原因：" + e.getMessage());
        }
    }
}