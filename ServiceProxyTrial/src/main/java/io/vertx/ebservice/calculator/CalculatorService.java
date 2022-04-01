package io.vertx.ebservice.calculator;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

@VertxGen
@ProxyGen
public interface CalculatorService {
    
void add(int a,int b,Handler<AsyncResult<Integer>> resultHandler);

  static CalculatorService createProxy(Vertx vertx, String address) { 
    return new CalculatorServiceVertxEBProxy(vertx, address);
  }
}
