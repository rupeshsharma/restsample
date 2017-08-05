package com.my.sample.converter;

import com.my.sample.data.ItemData;
import com.my.sample.domain.Item;

public class ItemConverter {

	public static void convert(Item source, ItemData target){
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		target.setCategory(source.getId());
		target.setTitle(source.getTitle());
		target.setPrice(source.getPrice());
	}
	
	public static void reverse(ItemData source, Item target){
		target.setDescription(source.getDescription());
		target.setCategory(source.getId());
		target.setTitle(source.getTitle());
		target.setPrice(source.getPrice());
	}
	
}