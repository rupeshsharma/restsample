package com.my.sample.converter;

import com.my.sample.data.ItemData;
import com.my.sample.domain.Category;
import com.my.sample.domain.Item;

public class ItemConverter {

	public static void convert(Item source, ItemData target){
		target.setId(source.getId());
		target.setDescription(source.getDescription());
		target.setCategory(source.getCategory().getId());
		target.setTitle(source.getTitle());
		target.setPrice(source.getPrice());
		target.setCreatedDate(source.getCreatedDate());
		target.setModifiedDate(source.getModifiedDate());
		target.setType(source.getType());
	} 
	
	public static void reverse(ItemData source, Item target){
		target.setDescription(source.getDescription());
		target.setCategory(new Category(source.getCategory()));
		target.setTitle(source.getTitle());
		target.setPrice(source.getPrice());
		target.setType(source.getType());
	}
	
}
