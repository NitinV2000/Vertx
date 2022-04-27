package com.tintin.FileArchiving;

import io.vertx.core.Vertx;

public class App {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MainVerticle(), ar -> {
          System.out.println("MainVerticle deployed Successfully");
        });
      }
}
