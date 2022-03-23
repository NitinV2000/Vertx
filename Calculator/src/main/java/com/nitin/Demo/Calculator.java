package com.nitin.Demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

public class Calculator extends AbstractVerticle{

    @Override
    public void start() throws Exception {
        super.start();
        Router router = Router.router(vertx);
        HttpServer httpServer = vertx.createHttpServer();
        router.get("/add/:num1/:num2").handler(routingContext -> {
            String n1 = routingContext.request().getParam("num1");
            String n2 = routingContext.request().getParam("num2");
            Double d1 = Double.valueOf(n1);
            Double d2 = Double.valueOf(n2);
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.write(String.valueOf(d1+d2));
            response.end();
        });

        router.get("/sub/:num1/:num2").handler(routingContext -> {
            String n1 = routingContext.request().getParam("num1");
            String n2 = routingContext.request().getParam("num2");
            Double d1 = Double.valueOf(n1);
            Double d2 = Double.valueOf(n2);
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.write(String.valueOf(d1-d2));
            response.end();
        });

        router.get("/mul/:num1/:num2").handler(routingContext -> {
            String n1 = routingContext.request().getParam("num1");
            String n2 = routingContext.request().getParam("num2");
            Double d1 = Double.valueOf(n1);
            Double d2 = Double.valueOf(n2);
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.write(String.valueOf(d1*d2));
            response.end();
        });

        router.get("/div/:num1/:num2").handler(routingContext -> {
            String n1 = routingContext.request().getParam("num1");
            String n2 = routingContext.request().getParam("num2");
            Double d1 = Double.valueOf(n1);
            Double d2 = Double.valueOf(n2);
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.write(String.valueOf(d1/d2));
            response.end();
        });

        router.get("/rem/:num1/:num2").handler(routingContext -> {
            String n1 = routingContext.request().getParam("num1");
            String n2 = routingContext.request().getParam("num2");
            Double d1 = Double.valueOf(n1);
            Double d2 = Double.valueOf(n2);
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.write(String.valueOf(d1%d2));
            response.end();
        });

        router.get("/pow/:num1/:num2").handler(routingContext -> {
            String n1 = routingContext.request().getParam("num1");
            String n2 = routingContext.request().getParam("num2");
            Double d1 = Double.valueOf(n1);
            Double d2 = Double.valueOf(n2);
            HttpServerResponse response = routingContext.response();
            response.setChunked(true);
            response.write(String.valueOf(Math.pow(d1, d2)));
            response.end();
        });

        httpServer.requestHandler(router).listen(8091);
    }
    
}
