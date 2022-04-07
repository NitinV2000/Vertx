package elk_practice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

import org.apache.http.HttpHost;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.ClearScrollRequest;
import org.elasticsearch.action.search.ClearScrollResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.elasticsearch.client.indices.CreateIndexRequest;
public class Api {
	
	public static void main(String[] args) throws IOException {
		
		String filepath = "C:\\STS5\\elasticsearch\\employees.txt";
		
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(new HttpHost("localhost", 9200, "http")));
		
		Path path = Paths.get(filepath);
		File fout = new File(path.toString());
		FileOutputStream fos = new FileOutputStream(fout);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
		
		// scroll api
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
//             System.out.println("map:"+Arrays.toString(map.entrySet().toArray()));
             String str = Arrays.toString(map.entrySet().toArray());
             System.out.println(str);
//             byte[] strToBytes = str.getBytes(); 
//             Files.write(path, strToBytes);
             bw.write(str);
     		 bw.newLine();
             
       }

		while (searchHits != null && searchHits.length > 0) { 
		    
		    SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId); 
		    scrollRequest.scroll(scroll);
		    searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
		    scrollId = searchResponse.getScrollId();
		    searchHits = searchResponse.getHits().getHits();
		    for (SearchHit hit : searchHits) {
	             map = hit.getSourceAsMap();
//	             System.out.println("map:"+Arrays.toString(map.entrySet().toArray()));
	             String str = Arrays.toString(map.entrySet().toArray());
	             System.out.println(str);
//	             byte[] strToBytes = str.getBytes(); 
//	             Files.write(path, strToBytes);
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
		
		
		// Creating Index
//		CreateIndexRequest request = new CreateIndexRequest("employee_index");
//	    request.settings(Settings.builder()
//	            .put("index.max_inner_result_window", 250)
//	            .put("index.write.wait_for_active_shards", 1)
//	            .put("index.query.default_field", "paragraph")
//	            .put("index.number_of_shards", 3)
//	            .put("index.number_of_replicas", 2)
//	    );
//	    CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);
//	    System.out.println("Index : " + createIndexResponse.index() + " Created");
		
		// Inserting a class object
//		Employee emp = new Employee("Tintin","SDE");
//		 
//		IndexRequest indexRequest = new IndexRequest("employee_index");
//		indexRequest.id("1");
//		indexRequest.source(new ObjectMapper().writeValueAsString(emp), XContentType.JSON);
//		IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
//		System.out.println("response id: "+indexResponse.getId());
//		System.out.println("response name: "+indexResponse.getResult().name());
        

		
		// Get all documents
//		SearchRequest searchRequest = new SearchRequest();
//	    searchRequest.indices("sales-records");
//	    SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//	    searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//	    searchRequest.source(searchSourceBuilder);
//	    Map<String, Object> map=null;
//	     
//	    try {
//	        SearchResponse searchResponse = null;
//	        searchResponse =client.search(searchRequest, RequestOptions.DEFAULT);
//	        if (searchResponse.getHits().getTotalHits().value > 0) {
//	            SearchHit[] searchHit = searchResponse.getHits().getHits();
//	            for (SearchHit hit : searchHit) {
//	                map = hit.getSourceAsMap();
//	                  System.out.println("map:"+Arrays.toString(map.entrySet().toArray()));
//	                    
//	                
//	            }
//	        }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
	}
}
