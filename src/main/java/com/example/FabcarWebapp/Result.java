package com.example.FabcarWebapp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result {

    private String key;
    private Car record;

    public Result(){
        this.record = new Car();
    }

}
