package com.codewithmosh.store.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

//Using Lombok instead of manually creating a constructor and getter
@AllArgsConstructor
@Getter
public class Message {
    private String text;
}
