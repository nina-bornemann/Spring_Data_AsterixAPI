package com.ninabornemann.spring_data_asterixapi.model;

import java.util.Objects;

public class Characters {
    private String id;
    private String name;
    private int age;
    private String profession;

    public Characters(String id, String name, int age, String profession) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Characters that = (Characters) o;
        return age == that.age && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(profession, that.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, profession);
    }

    @Override
    public String toString() {
        return "Characters{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
