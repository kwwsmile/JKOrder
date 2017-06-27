package com.example.ayou.jk_takeout.mine.model;

/**
 * Created by kongweiwei on 2017/4/27.
 */

//用户地址详情的实体类
public class AccountAddress {
   private String name;//姓名
   private String sex;//性别
   private String phoneNumber;//电话
   private String address;//定位的地址
   private String addressDetail;//详细地址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    @Override
    public String toString() {
        return "AccountAddress{" +
                "name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                '}';
    }

    public AccountAddress(String name, String sex, String phoneNumber, String address, String addressDetail) {
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.addressDetail = addressDetail;
    }
}
