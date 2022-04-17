package com.tintin.elasticsearch.elasticVertx;

import java.io.IOException;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class EmployeeVerticle extends AbstractVerticle{
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        super.start();

        Router router = Router.router(vertx);

        vertx.createHttpServer()
             .requestHandler(router)
             .listen(8091)
             .onSuccess(ok -> {
                 System.out.println("Started Properly");
                 startPromise.complete();
             })
             .onFailure(startPromise::fail);

        router.get("/getAllInFile").handler(BodyHandler.create()).handler(event -> {
            try {
                allDetailsInFile(event);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });

    }

    public void allDetailsInFile(RoutingContext routingContext) throws IOException{
        vertx.eventBus().request("allDetailsInFile", null, reply -> {
            routingContext.request().response().end((String)reply.result().body());
        });
    }
}
