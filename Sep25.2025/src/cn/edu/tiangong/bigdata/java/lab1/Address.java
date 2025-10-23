package cn.edu.tiangong.bigdata.java.lab1;


public class Address {
    private String country;   // 国家
    private String province;  // 省份
    private String city;      // 城市
    private String street;    // 街道
    private String zipCode;   // 邮编

    //无参
    public Address() {
    }

    //全参
    public Address(String country, String province, String city, String street, String zipCode) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    //核心完整地址
    public String getFullAddress() {
        // 拼接格式：国家-省份-城市-街道，邮编：XXX
        return country + "-" + province + "-" + city + "-" + street + "，邮编：" + zipCode;
    }
}