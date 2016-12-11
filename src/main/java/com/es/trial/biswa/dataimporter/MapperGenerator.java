package com.es.trial.biswa.dataimporter;

import java.io.IOException;

import org.elasticsearch.common.settings.Settings;

import com.es.trial.biswa.JestSingletonConnection;

import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.mapping.PutMapping;

public class MapperGenerator {
	
	//public final static String mapping = "{ \r\n   \"settings\" : { \r\n     \"index\" : { \r\n       \"analysis\" : { \r\n         \"analyzer\" : { \r\n           \"cust_cache_analyzer\" : { \r\n             \"type\" : \"custom\", \r\n             \"tokenizer\" : \"lowercase\", \r\n             \"filter\"    : [\"asciifolding\", \"title_ngram\"] \r\n           } \r\n         }, \r\n         \"filter\" : { \r\n           \"title_ngram\" : { \r\n             \"type\" : \"nGram\", \r\n             \"min_gram\" : 3, \r\n             \"max_gram\" : 5 \r\n           } \r\n         } \r\n       } \r\n     } \r\n   }, \r\n   \r\n   \"mappings\": { \r\n     \"city\": { \r\n       \"properties\": { \r\n         \"city\": { \r\n           \"type\": \"string\", \r\n           \"analyzer\": \"cust_cache_analyzer\", \r\n           \"boost\": 10 \r\n         },\r\n\t\t\"office\": { \r\n           \"type\": \"string\", \r\n           \"analyzer\": \"cust_cache_analyzer\", \r\n           \"boost\": 10 \r\n         }\t\t \r\n       } \r\n     } \r\n   } \r\n } ";
	public final static String settings = "{\r\n\"settings\" : { \r\n\r\n     \"index\" : { \r\n\r\n       \"analysis\" : { \r\n\r\n         \"analyzer\" : { \r\n\r\n           \"cust_cache_analyzer\" : { \r\n\r\n             \"type\" : \"custom\", \r\n\r\n             \"tokenizer\" : \"lowercase\", \r\n\r\n             \"filter\"    : [\"asciifolding\", \"title_ngram\"] \r\n\r\n           } \r\n\r\n         }, \r\n\r\n         \"filter\" : { \r\n\r\n           \"title_ngram\" : { \r\n\r\n             \"type\" : \"nGram\", \r\n\r\n             \"min_gram\" : 3, \r\n\r\n             \"max_gram\" : 5 \r\n\r\n           } \r\n\r\n         } \r\n\r\n       } \r\n\r\n     } \r\n\r\n   }\r\n}";
	public final static String mapping = "{\r\n\"mappings\": { \r\n\r\n     \"location\": { \r\n\r\n       \"properties\": { \r\n\r\n         \"location\": { \r\n\r\n           \"type\": \"string\", \r\n\r\n           \"analyzer\": \"cust_cache_analyzer\", \r\n\r\n           \"boost\": 10 \r\n\r\n         },\r\n\r\n\t\t\"office\": { \r\n\r\n           \"type\": \"string\", \r\n\r\n           \"analyzer\": \"cust_cache_analyzer\", \r\n\r\n           \"boost\": 10 \r\n\r\n         }\t\t \r\n\r\n       } \r\n\r\n     } \r\n\r\n   }\r\n}";
	
	public static void createIndex(String indexName) throws IOException {
		JestSingletonConnection.getInstance().getJestClient().execute(new CreateIndex.Builder(indexName).settings(Settings.builder().loadFromSource(settings).build().getAsMap()).build());
	}
	
	public static void createMapping(String indexName, String typeName, String mappingEntries) throws IOException {
		//Create mapping
		PutMapping putMapping = new PutMapping.Builder(indexName,typeName,mappingEntries).build();
		JestSingletonConnection.getInstance().getJestClient().execute(putMapping);
	}
	
	public static void main(String[] args) throws IOException {
		String indexName = "biswa_index";
		String typeName = "cacheType"; 
		
		//Create index
		createIndex(indexName);

		//Create mapping
		createMapping(indexName, typeName, mapping);
				
	}
}
