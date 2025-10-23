package com.student.management;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

// 学生信息管理核心类：实现增删改查、Stream统计、CSV导入导出
public class StudentManagementSystem {
    private List<Student> studentList;  // 存储所有学生的集合

    // 构造方法：初始化学生集合
    public StudentManagementSystem() {
        this.studentList = new ArrayList<>();
    }

    // -------------------------- 1. 增删改查功能 --------------------------
    // 1.1 增加学生（校验ID唯一，避免重复）
    public boolean addStudent(Student student) {
        // 用Stream判断ID是否已存在
        boolean isIdExist = studentList.stream()
                .anyMatch(s -> s.getId().equals(student.getId()));
        if (isIdExist) {
            System.out.println("学号【" + student.getId() + "】已存在，添加失败！");
            return false;
        }
        studentList.add(student);
        System.out.println("学号【" + student.getId() + "】添加成功！");
        return true;
    }

    // 1.2 删除学生（根据ID精确删除）
    public boolean deleteStudentById(String id) {
        // 用removeIf批量删除符合条件的学生（仅1个）
        boolean isDeleted = studentList.removeIf(s -> s.getId().equals(id));
        if (isDeleted) {
            System.out.println("学号【" + id + "】删除成功！");
        } else {
            System.out.println("学号【" + id + "】不存在，删除失败！");
        }
        return isDeleted;
    }

    // 1.3 修改学生信息（不允许修改ID，其他属性可改）
    public boolean updateStudent(String id, String newName, String newGender,
                                 int newAge, String newClassName, double newGpa) {
        // 用Stream查找ID对应的学生
        Optional<Student> optionalStudent = studentList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();
        // 存在则更新属性
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(newName);
            student.setGender(newGender);
            student.setAge(newAge);
            student.setClassName(newClassName);
            student.setGpa(newGpa);
            System.out.println("学号【" + id + "】修改成功！");
            return true;
        } else {
            System.out.println("学号【" + id + "】不存在，修改失败！");
            return false;
        }
    }

    // 1.4 按ID查询学生（精确查询）
    public Student findStudentById(String id) {
        return studentList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null); // 不存在返回null
    }

    // 1.5 列出全部学生
    public List<Student> listAllStudents() {
        if (studentList.isEmpty()) {
            System.out.println("当前无学生数据！");
        }
        return studentList;
    }

    // -------------------------- 2. Stream统计功能 --------------------------
    // 2.1 根据绩点排序（从高到低）
    public List<Student> sortByGpaDesc() {
        return studentList.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .collect(Collectors.toList());
    }

    // 2.2 所有姓王的学生列表
    public List<Student> findStudentsSurnameWang() {
        List<Student> result = studentList.stream()
                .filter(s -> s.getName().startsWith("王"))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            System.out.println("无姓王的学生！");
        }
        return result;
    }

    // 2.3 每个班级的平均绩点
    public Map<String, Double> getAvgGpaByClass() {
        return studentList.stream()
                .collect(Collectors.groupingBy(
                        Student::getClassName, // 按班级分组
                        Collectors.averagingDouble(Student::getGpa) // 计算平均绩点
                ));
    }

    // 2.4 绩点低于2.0的学生列表
    public List<Student> findStudentsGpaBelow2() {
        List<Student> result = studentList.stream()
                .filter(s -> s.getGpa() < 2.0)
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            System.out.println("无绩点低于2.0的学生！");
        }
        return result;
    }

    // 2.5 每个班级绩点第二名的学生（修正后）
    public Map<String, Student> getSecondTopGpaByClass() {
        return studentList.stream()
                .collect(Collectors.groupingBy(
                        Student::getClassName,
                        Collectors.collectingAndThen(
                                // 第一步：先按绩点降序排序（替换 sortedList 方法）
                                Collectors.toList(),
                                list -> {
                                    // 对分组后的列表进行降序排序
                                    list.sort(Comparator.comparingDouble(Student::getGpa).reversed());
                                    // 第二步：取排序后的第2个元素（索引1），不足2人则返回null
                                    return list.size() >= 2 ? list.get(1) : null;
                                }
                        )
                ));
    }

    // 2.6 每个班级绩点最高的学生
    public Map<String, Student> getTopGpaByClass() {
        return studentList.stream()
                .collect(Collectors.groupingBy(
                        Student::getClassName,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingDouble(Student::getGpa)),
                                Optional::get // 提取Optional中的学生对象
                        )
                ));
    }

    // -------------------------- 3. CSV导入导出功能 --------------------------
    // 3.1 从CSV导入学生数据（格式：学号,姓名,性别,年龄,班级,绩点）
    public void loadFromCsv(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("文件【" + filePath + "】不存在，创建空文件！");
            file.createNewFile();
            return;
        }
        // 读取CSV文件内容
        try (Scanner scanner = new Scanner(file, "UTF-8")) {
            int count = 0; // 统计导入数量
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue; // 跳过空行
                String[] data = line.split(",");
                // 校验数据格式（需6个字段，匹配文档示例格式）
                if (data.length != 6) {
                    System.out.println("无效数据行【" + line + "】，跳过！");
                    continue;
                }
                // 解析数据（处理数字格式异常）
                try {
                    String id = data[0];
                    String name = data[1];
                    String gender = data[2];
                    int age = Integer.parseInt(data[3]);
                    String className = data[4];
                    double gpa = Double.parseDouble(data[5]);
                    // 添加学生（自动校验ID唯一性）
                    if (addStudent(new Student(id, name, gender, age, className, gpa))) {
                        count++;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("数据格式错误【" + line + "】（年龄/绩点需为数字），跳过！");
                }
            }
            System.out.println("从【" + filePath + "】导入完成，共导入" + count + "条有效数据！");
        }
    }

    // 3.2 导出学生数据到CSV
    public void exportToCsv(String filePath) throws IOException {
        if (studentList.isEmpty()) {
            System.out.println("无学生数据，无需导出！");
            return;
        }
        // 写入CSV文件
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"))) {  //使用UTF8
            for (Student student : studentList) {
                // 按格式拼接数据
                String line = student.getId() + "," +
                        student.getName() + "," +
                        student.getGender() + "," +
                        student.getAge() + "," +
                        student.getClassName() + "," +
                        student.getGpa();
                writer.write(line);
                writer.newLine();
            }
        }
        System.out.println("已导出【" + studentList.size() + "】条数据到【" + filePath + "】！");
    }
}