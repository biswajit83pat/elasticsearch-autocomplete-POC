package com.es.trial.biswa;

public class SearchModel {
	private final String searchField;
	private final String searchQuery;

	public SearchModel(String searchField, String searchQuery) {
		this.searchField = searchField;
		this.searchQuery = searchQuery;
	}
	
	public String getSearchField() {
		return searchField;
	}

	public String getSearchQuery() {
		return searchQuery;
	}
}
