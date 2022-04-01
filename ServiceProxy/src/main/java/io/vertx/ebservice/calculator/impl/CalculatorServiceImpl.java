package io.vertx.ebservice.calculator.impl;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.ebservice.calculator.CalculatorService;

public class CalculatorServiceImpl implements CalculatorService{

    @Override
    public void add(int a, int b, Handler<AsyncResult<Integer>> handler) {
        System.out.println("Add called");
        System.out.println("Values: num1->"+a+" "+"num2->"+b);
		handler.handle(Future.succeededFuture(a+b));

    }

    @Override
    public void sub(int a, int b, Handler<AsyncResult<Integer>> handler) {
        System.out.println("Sub called");
        System.out.println("Values: a->"+a+" "+"b->"+b);
		handler.handle(Future.succeededFuture(a-b));
        
    }

    @Override
    public void mul(int a, int b, Handler<AsyncResult<Integer>> handler) {
        System.out.println("Mul called");
        System.out.println("Values: a->"+a+" "+"b->"+b);
		handler.handle(Future.succeededFuture(a*b));
        
    }

    @Override
    public void div(int a, int b, Handler<AsyncResult<Integer>> handler) {
        System.out.println("Div called");
        System.out.println("Values: a->"+a+" "+"b->"+b);
		handler.handle(Future.succeededFuture(a/b));
        
    }

    @Override
    public void rem(int a, int b, Handler<AsyncResult<Integer>> handler) {
        System.out.println("Rem called");
        System.out.println("Values: a->"+a+" "+"b->"+b);
		handler.handle(Future.succeededFuture(a%b));
        
    }
    
  
}
