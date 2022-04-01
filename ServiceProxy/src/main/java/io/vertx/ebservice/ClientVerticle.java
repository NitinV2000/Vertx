package io.vertx.ebservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ebservice.calculator.CalculatorService;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class ClientVerticle extends AbstractVerticle {

    private CalculatorService service;

    @Override
    public void start() throws Exception {
        service = CalculatorService.createProxy(vertx, "calculator.services.myapplication");

        Router router = Router.router(vertx);
        vertx.createHttpServer().requestHandler(router).listen(8080);
        router.get("/api/add").handler(BodyHandler.create()).handler(this::add);
        router.get("/api/sub").handler(BodyHandler.create()).handler(this::sub);
        router.get("/api/mul").handler(BodyHandler.create()).handler(this::mul);
        router.get("/api/div").handler(BodyHandler.create()).handler(this::div);
        router.get("/api/rem").handler(BodyHandler.create()).handler(this::rem);

    }

    public void add(RoutingContext routingContext) {
		System.out.println("Add Request has been made");
		HttpServerResponse response = routingContext.response();
		JsonObject json = routingContext.getBodyAsJson();
        int num1 = Integer.parseInt(json.getString("num1"));
        int num2 = Integer.parseInt(json.getString("num2"));
		service.add(num1, num2, handler -> {
			if (handler.succeeded()) {
				System.out.println("Successfully called");
				response.end(handler.result().toString());
			}
			else {
                 System.out.println(handler.cause());
				}
		});
	}

    public void sub(RoutingContext routingContext) {
		System.out.println("Sub Request has been made");
		HttpServerResponse response = routingContext.response();
		JsonObject json = routingContext.getBodyAsJson();
        int num1 = Integer.parseInt(json.getString("num1"));
        int num2 = Integer.parseInt(json.getString("num2"));
		service.sub(num1, num2, handler -> {
			if (handler.succeeded()) {
				System.out.println("Successfully called");
				response.end(handler.result().toString());
			}
			else {
                 System.out.println(handler.cause());
				}
		});
	}

    public void mul(RoutingContext routingContext) {
		System.out.println("Mul Request has been made");
		HttpServerResponse response = routingContext.response();
		JsonObject json = routingContext.getBodyAsJson();
        int num1 = Integer.parseInt(json.getString("num1"));
        int num2 = Integer.parseInt(json.getString("num2"));
		service.mul(num1, num2, handler -> {
			if (handler.succeeded()) {
				System.out.println("Successfully called");
				response.end(handler.result().toString());
			}
			else {
                 System.out.println(handler.cause());
				}
		});
	}

    public void div(RoutingContext routingContext) {
		System.out.println("Div request has been made");
		HttpServerResponse response = routingContext.response();
		JsonObject json = routingContext.getBodyAsJson();
        int num1 = Integer.parseInt(json.getString("num1"));
        int num2 = Integer.parseInt(json.getString("num2"));
		service.div(num1, num2, handler -> {
			if (handler.succeeded()) {
				System.out.println("Successfully called");
				response.end(handler.result().toString());
			}
			else {
                 System.out.println(handler.cause());
				}
		});
	}

    public void rem(RoutingContext routingContext) {
		System.out.println("Rem Request has been made");
		HttpServerResponse response = routingContext.response();
		JsonObject json = routingContext.getBodyAsJson();
        int num1 = Integer.parseInt(json.getString("num1"));
        int num2 = Integer.parseInt(json.getString("num2"));
		service.rem(num1, num2, handler -> {
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
