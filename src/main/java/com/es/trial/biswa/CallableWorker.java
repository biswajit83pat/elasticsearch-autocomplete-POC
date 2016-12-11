package com.es.trial.biswa;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;

import org.apache.commons.io.IOUtils;

public class CallableWorker implements Callable<String>{

	private final String site;
	
	public CallableWorker(String site) {
		this.site = site;
	}
	
	public String call() throws Exception {
		return IOUtils.toString(new URL("http://" + site), StandardCharsets.UTF_8);
	}

}
