package com.trailfinder.app.ws.service;

import java.util.List;


public interface ProfileHelperService<T> {
	
	List<T> getItems(String userId);
	
	T getItem(String itemId);
	
	void deleteItem(String itemId);
	
}