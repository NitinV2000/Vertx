package io.vertx.ebservice;

import io.vertx.core.Vertx;

public class CalculatorApplication {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new CalculatorVerticle(), ar -> {
          System.out.println("Calculator Verticle deployed Successfully");
        });
        vertx.deployVerticle(new ClientVerticle(), ar -> {
          System.out.println("Client Verticle deployed Successfully");
        });
      }
}
