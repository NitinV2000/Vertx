package io.vertx.ebservice;

import io.vertx.core.Vertx;

public class CalculatorApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new CalculatorVerticle(), ar -> {
          System.out.println("Ready to accept request from Postman");
        });
      }
}
