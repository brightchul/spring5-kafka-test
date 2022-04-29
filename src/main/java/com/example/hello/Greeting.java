package com.example.hello;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Greeting {

    private String msg;
    private String name;
}