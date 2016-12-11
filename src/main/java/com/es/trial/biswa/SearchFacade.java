package com.es.trial.biswa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SearchFacade {
	
	private final List<SearchModel> searchModelList;
	
	public SearchFacade(final List<SearchModel> searchModelList) {
		this.searchModelList = searchModelList;
	}
	
	final ExecutorService pool = Executors.newFixedThreadPool(3);//based on number of keys we are trying to search upon
	final ExecutorCompletionService<String> completionService = new ExecutorCompletionService<String>(pool);
	
	public List<String> getSearchResults() throws InterruptedException{
		List<String> resultsList = new ArrayList<String>();
		for (final SearchModel searchModel : searchModelList) {
		    completionService.submit(new CallableSearcher(searchModel.getSearchField(),searchModel.getSearchQuery()));
	}
		
		for(int i = 0; i < searchModelList.size(); ++i) {
		    final Future<String> future = completionService.take();
		    try {
		        final String content = future.get();
		        resultsList.add(content);
		    } catch (ExecutionException e) {
		        System.out.println("Error while searching.."  +  e.getCause());
		    }
		}
		
		//shutdown once everything is finished
		pool.shutdown();
		
		return resultsList;
	}
}
