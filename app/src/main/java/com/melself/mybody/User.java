package com.melself.mybody;

public class User {
    private String name, email, password, sex, image_id;
    private int weight, growth;

    public User() {

    }


    public User(String name, String email, String password, String sex, int weight, int growth, String image_id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.weight = weight;
        this.growth = growth;
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getGrowth() {
        return growth;
    }

    public void setGrowth(int growth) {
        this.growth = growth;
    }

    public String getImage_id(){
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }
}
