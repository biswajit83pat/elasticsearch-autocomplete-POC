package com.es.trial.biswa;

import java.util.Arrays;

public class SearchFacadeRunner {
	public static void main(String[] args) throws InterruptedException {
		SearchFacade searchFacade = new SearchFacade(
				Arrays.asList(new SearchModel("location", "dia"), new SearchModel("office", "dia")));
		System.out.println(searchFacade.getSearchResults());
	}
}
