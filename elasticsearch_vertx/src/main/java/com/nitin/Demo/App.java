package com.nitin.Demo;


import io.vertx.core.Vertx;

public class App{
    public static void main(String[] args){
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(EmployeeVerticle.class.getName(), event -> {
            if(event.succeeded()){
                System.out.println("Employee Verticle Deployed");
            }
            else{
                System.out.println("Employee Verticle couldnt be Deployed");
            }
        });
        vertx.deployVerticle(HelperVerticle.class.getName(), event -> {
            if(event.succeeded()){
                System.out.println("Helper Verticle Deployed");
            }
            else{
                System.out.println("Helper Verticle couldnt be Deployed");
            }
        });
    }
}