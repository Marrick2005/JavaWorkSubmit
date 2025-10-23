package cn.edu.tiangong.bigdata.java.test;

import cn.edu.tiangong.bigdata.java.lab1.Address;
import cn.edu.tiangong.bigdata.java.lab1.Employee;
import cn.edu.tiangong.bigdata.java.lab1.User;

public class TestAll {
    public static void main(String[] args) {
        // Address类
        testAddress();
        // Employee类
        testEmployee();
        // 测试User类
        testUser();
    }

    // Address类
    private static void testAddress() {
        System.out.println("=================== 测试Address类 ===================");
        Address addr = new Address("中国", "天津市", "西青区", "宾水西道", "300387");
        System.out.println("完整地址：" + addr.getFullAddress());
        addr.setCity("南开区");
        addr.setZipCode("300111");
        System.out.println("修改后完整地址：" + addr.getFullAddress());
        System.out.println();  // 换行，区分不同测试模块
    }

    // Employee类
    private static void testEmployee() {
        System.out.println("=================== 测试Employee类 ===================");
        // 测试无参构造
        Employee emp1 = new Employee();
        System.out.println("无参构造员工：" + emp1.getId() + "，" + emp1.getName() +
                "，基本薪水：" + emp1.getBaseSalary() + "，增长后总额：" + emp1.calculateTotalSalary());

        // 测试单参构造
        Employee emp2 = new Employee(1001);
        System.out.println("单参构造员工：" + emp2.getId() + "，" + emp2.getName() +
                "，基本薪水：" + emp2.getBaseSalary() + "，增长额：" + emp2.calculateSalaryIncrease() +
                "，增长后总额：" + emp2.calculateTotalSalary());

        // 测试三参构造
        Employee emp3 = new Employee(1002, "李四", 6000.0);
        System.out.println("三参构造员工：" + emp3.getId() + "，" + emp3.getName() +
                "，基本薪水：" + emp3.getBaseSalary() + "，增长率：" + (emp3.getGrowthRate()*100) + "%" +
                "，增长后总额：" + emp3.calculateTotalSalary());

        // 测试四参构造
        Employee emp4 = new Employee(1003, "王五", 8000.0, 0.08);
        System.out.println("四参构造员工：" + emp4.getId() + "，" + emp4.getName() +
                "，基本薪水：" + emp4.getBaseSalary() + "，增长率：" + (emp4.getGrowthRate()*100) + "%" +
                "，增长额：" + emp4.calculateSalaryIncrease() + "，增长后总额：" + emp4.calculateTotalSalary());
        System.out.println();
    }

    // User类
    private static void testUser() {
        System.out.println("=================== 测试User类 ===================");
        // 双参
        User user = new User("马如君MaRujun", "2410160121");
        // 返回
        System.out.println("用户信息：" + user.toString());

        // 获取
        user.setPassword("20050928");  // 改
        System.out.println("修改后口令：" + user.getPassword());

        // 测试登录验证
        boolean login1 = user.login("马如君MaRujun", "20050928");  // TUTP
        boolean login2 = user.login("马如君MaRujun", "123456");  // TUFP
        boolean login3 = user.login("马如君marujun", "20050928");   // FUgit initTP
        System.out.println("登录验证1（正确信息）：" + login1);  // T
        System.out.println("登录验证2（错误口令）：" + login2);  // F
        System.out.println("登录验证3（错误用户名）：" + login3); // F
    }
}