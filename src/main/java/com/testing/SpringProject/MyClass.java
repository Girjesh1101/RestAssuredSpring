package com.testing.SpringProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyClass {

    //IoC container --> inversation of control
    // Application content  --
    // Both are same

    // @Component --> provide the information and give details to IoC
    // @Bean  --> is basically called Object --> always use in function

//    @Autowired --> Dependancy injection  --> it is depends on anything


    @Autowired
    private  Car car;

    @GetMapping("abc")
    public  String sayHello(){
    	
    	System.out.println("Hello");
        return  "Hello";
    }

    @GetMapping("start")
    public  String start(){
        return car.carStart();
    }
}
