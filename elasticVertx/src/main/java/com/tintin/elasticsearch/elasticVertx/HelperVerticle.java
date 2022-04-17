package com.tintin.elasticsearch.elasticVertx;

import io.vertx.core.AbstractVerticle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;


import io.vertx.core.eventbus.Message;

public class HelperVerticle extends AbstractVerticle{
    @Override
    public void start() throws Exception {
        vertx.eventBus().consumer("allDetailsInFile", msg -> {
            try {
                allDetailsInFile(msg);
            } catch (IOException e) {
                msg.reply("Exception has occured");
            }
        });
    }

    private void allDetailsInFile(Message<Object> msg) throws IOException {
        String filepath = "employees.txt";
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
        Path path = Paths.get(filepath);
		File fout = new File(path.toString());
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
		Map<String, Object> map=null;
		SearchRequest searchRequest = new SearchRequest("employee_index");
		searchRequest.scroll(scroll);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchSourceBuilder.size(1);
		searchRequest.source(searchSourceBuilder);

		SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT); 
		String scrollId = searchResponse.getScrollId();
		SearchHit[] searchHits = searchResponse.getHits().getHits();
		for (SearchHit hit : searchHits) {
             map = hit.getSourceAsMap();
             String str = Arrays.toString(map.entrySet().toArray());
             System.out.println(str);
             bw.write(str);
     		 bw.newLine();
             
       }

		while (searchHits != null && searchHits.length > 0) { 
		    bw.write("New set of records");
            bw.newLine();
		    SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId); 
		    scrollRequest.scroll(scroll);
		    searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
		    scrollId = searchResponse.getScrollId();
		    searchHits = searchResponse.getHits().getHits();
		    for (SearchHit hit : searchHits) {
	             map = hit.getSourceAsMap();
	             String str = Arrays.toString(map.entrySet().toArray());
	             System.out.println(str);
	             bw.write(str);
	     		 bw.newLine();
	       }
		}

		ClearScrollRequest clearScrollRequest = new ClearScrollRequest(); 
		clearScrollRequest.addScrollId(scrollId);
		ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest, RequestOptions.DEFAULT);
		boolean succeeded = clearScrollResponse.isSucceeded();
		bw.close();
		System.out.println(succeeded);
        msg.reply(String.valueOf(succeeded));
    }
}
