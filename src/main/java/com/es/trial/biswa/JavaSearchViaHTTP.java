package com.es.trial.biswa;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class JavaSearchViaHTTP {
public static void main(String[] args) throws ClientProtocolException, IOException {
	CloseableHttpClient httpclient = HttpClients.createDefault();
	HttpGet httpGet = new HttpGet("http://localhost:9200/autocomplete_test/_search?q=city:dia&pretty=True");
	CloseableHttpResponse response1 = httpclient.execute(httpGet);
	try {
	    System.out.println(response1.getStatusLine());
	    HttpEntity entity1 = response1.getEntity();
	    System.out.println(EntityUtils.toString(entity1));
	    EntityUtils.consume(entity1);
	} finally {
	    response1.close();
	}
	
	HttpPost httpPost = new HttpPost("http://localhost:9200/autocomplete_test/_search?q=city:dia&pretty=True");
	List <NameValuePair> nvps = new ArrayList <NameValuePair>();
	httpPost.setEntity(new UrlEncodedFormEntity(nvps));
	CloseableHttpResponse response2 = httpclient.execute(httpPost);

	try {
	    System.out.println(response2.getStatusLine());
	    HttpEntity entity2 = response2.getEntity();
	    EntityUtils.consume(entity2);
	} finally {
	    response2.close();
	}
}
}
