package com.greenshake;

import lombok.*;

@Getter
@Setter
public class Client {
    private String name;
    private long id;

    @Override
    public String toString() {
        return
                "name= " + name +
                ", id=" + id ;
    }
}
