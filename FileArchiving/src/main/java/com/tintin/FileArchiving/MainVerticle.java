package com.tintin.FileArchiving;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainVerticle extends AbstractVerticle {
  private String filename = "config.json";

  @Override
  public void start() throws Exception {
    Router router = Router.router(vertx);
    vertx.createHttpServer().requestHandler(router).listen(8080);
    router.get("/api/read").handler(event -> {
      try {
        initRead(event);
      } catch (Exception e) {
        e.printStackTrace();
    }
  }
    );
  }

  public void initRead(RoutingContext rtx){
    final FileSystem fs = vertx.fileSystem();
    fs.readFile(filename, readAttempt -> {
      if (readAttempt.succeeded()) {
          JsonArray allObjects = new JsonArray(new String(readAttempt.result().getBytes()));
          for (Object obj : allObjects) {
              JsonObject record = (JsonObject) obj;
              String id = record.getString("id");
              int interval = record.getInteger("archieval-Interval");
              String storage = record.getString("storage-type");
              Record r = new Record(id,interval,storage);
              System.out.println(r.toString());
          }
       }
     });
   }

}
