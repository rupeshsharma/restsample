package com.my.sample.converter;

import java.util.HashSet;
import java.util.Set;

import com.my.sample.data.CategoryData;
import com.my.sample.data.ItemData;
import com.my.sample.domain.Category;
import com.my.sample.domain.Item;

public class CategoryConverter {

	public static void convert(Category source, CategoryData target, Boolean isFull) {
		target.setId(source.getId());
		target.setTitle(source.getTitle());
		target.setCreatedDate(source.getCreatedDate());
		target.setModifiedDate(source.getModifiedDate());
		if (isFull) {
			Set<ItemData> itemDataList = new HashSet<ItemData>();
			ItemData itemData = null;
			for (Item item : source.getItems()) {
				itemData = new ItemData();
				ItemConverter.convert(item, itemData);
				itemDataList.add(itemData);
			}
			target.setItems(itemDataList);
		}
	}
	
	public static void reverse(CategoryData source, Category target) {
		target.setTitle(source.getTitle());
	}

}
