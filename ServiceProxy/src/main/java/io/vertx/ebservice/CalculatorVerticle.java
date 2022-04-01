package io.vertx.ebservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.ebservice.calculator.CalculatorService;
import io.vertx.ebservice.calculator.impl.CalculatorServiceImpl;
import io.vertx.serviceproxy.ServiceBinder;

public class CalculatorVerticle extends AbstractVerticle{
    
  private CalculatorService service;

  @Override
  public void start() {
    service = new CalculatorServiceImpl();

    new ServiceBinder(vertx)
      .setAddress("calculator.services.myapplication")
      .register(CalculatorService.class, service);
  }
}
