package com.my.sample.service;

import java.util.Date;
import java.util.List;

import com.my.sample.data.CategoryData;
import com.my.sample.data.ItemData;
import com.my.sample.data.MenuData;
import com.my.sample.data.YGraphData;

public interface MenuService {

	MenuData getMenu();
	
	CategoryData addCategory(CategoryData categoryData);
	
	ItemData addItem(ItemData itemData);

	List<CategoryData> getCategory();

	List<ItemData> getItemByCategory(Long id);

	Boolean removeItem(Long id);

	ItemData updateItem(ItemData itemData);

	Boolean deleteCategory(Long id);

	CategoryData updateCategory(CategoryData categoryData);

	List<YGraphData> getWholeItemGraphData();

	List<YGraphData> getWholeItemGraphDataInRange(Date fromDate, Date toDate);
	
}
