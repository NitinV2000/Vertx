package io.vertx.ebservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ebservice.calculator.CalculatorService;
import io.vertx.ebservice.calculator.impl.CalculatorServiceImpl;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.serviceproxy.ServiceBinder;

public class CalculatorVerticle extends AbstractVerticle{
    
  private CalculatorService service;

  @Override
  public void start() {
    service = new CalculatorServiceImpl(); // <1>

    new ServiceBinder(vertx) // <2>
      .setAddress("calculator.services.myapplication") // <3>
      .register(CalculatorService.class, service); // <4>

      Router router = Router.router(vertx);
      vertx.createHttpServer().requestHandler(router).listen(8080);
      router.get("/api/add").handler(BodyHandler.create()).handler(this::add);
  }

  public void add(RoutingContext routingContext) {
		System.out.println("Add called");
		HttpServerResponse response = routingContext.response();
		JsonObject json = routingContext.getBodyAsJson();
        System.out.println(json.toString());
		service.add(Integer.parseInt(json.getString("a")),
				Integer.parseInt(json.getString("b")), handler -> {
					if (handler.succeeded()) {
						System.out.println("Successfully called");
						response.end(handler.result().toString());
					}
					else {
                        System.out.println(handler.cause());
					}
				});
	}
}
