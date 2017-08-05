package com.my.sample.converter;

import java.util.ArrayList;
import java.util.List;

import com.my.sample.data.CategoryData;
import com.my.sample.data.MenuData;
import com.my.sample.domain.Category;

public class MenuConverter {
	
	public static void convert(List<Category> source, MenuData target){
		List<CategoryData> categoryDataList = new ArrayList<CategoryData>();
		CategoryData categoryData = null;
		for(Category category : source){
			categoryData = new CategoryData();
			CategoryConverter.convert(category, categoryData, Boolean.TRUE);
			categoryDataList.add(categoryData);
		}
		target.setCategories(categoryDataList);
	}

}
