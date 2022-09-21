package com.example.groupnine;

import java.util.HashMap;
import java.util.Map;

public class id1 {
    private int Id;
    private String name;
    private String position;
    private String Phone;

    public id1(int id, String name, String position, String phone) {
        Id = id;
        this.name = name;
        this.position = position;
        this.Phone = phone;
    }

    public id1() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "id1{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result  = new HashMap<>();
        result.put("phone", Phone);

        return result;
    }
}

