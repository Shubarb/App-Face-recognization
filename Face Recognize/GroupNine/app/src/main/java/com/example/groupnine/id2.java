package com.example.groupnine;

import java.util.HashMap;
import java.util.Map;

public class id2 {

    private String name;
    private String time;


    public id2(String name, String time) {
        this.name = name;
        this.time = time;
    }

    public id2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "id2{" +
                "name='" + name + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result  = new HashMap<>();
        result.put("time", time);

        return result;
    }
}

