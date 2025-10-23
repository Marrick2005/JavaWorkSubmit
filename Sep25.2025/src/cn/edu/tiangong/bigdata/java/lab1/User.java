package cn.edu.tiangong.bigdata.java.lab1;


public class User {
    private String username;  // 用户名
    private String password;  // 口令（密码）

    //无参
    public User() {
        this.username = "";
        this.password = "";
    }

    //单参
    public User(String username) {
        this.username = username;
        this.password = "";
    }

    //双参
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // 口令获取
    public String getPassword() {
        return password;
    }

    // 设置口令
    public void setPassword(String password) {
        this.password = password;
    }

    //功能1返回类信息
    @Override
    public String toString() {
        return "User{用户名：'" + username + "', 口令：'" + password + "'}";
    }

    //功能2登录验证
    public boolean login(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }
}