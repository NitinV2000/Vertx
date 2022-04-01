package io.vertx.ebservice.calculator.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ebservice.calculator.CalculatorService;

public class CalculatorServiceImpl implements CalculatorService{

    @Override
    public void add(int a, int b, Handler<AsyncResult<Integer>> resultHandler) {
        System.out.println("SP:Calculator proxy called");
		System.out.println("a : "+a+" b: "+b);
		int result=a+b;
		resultHandler.handle(Future.succeededFuture(result));
    }
    
  
}
