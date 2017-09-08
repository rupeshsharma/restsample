package com.my.sample.service;

import java.util.List;

import com.my.sample.data.CategoryData;
import com.my.sample.data.ItemData;
import com.my.sample.data.MenuData;

public interface MenuService {

	MenuData getMenu();
	
	CategoryData addCategory(CategoryData categoryData);
	
	Long addItem(ItemData itemData);

	List<CategoryData> getCategory();

	List<ItemData> getItemByCategory(Long id);

	Boolean removeItemFromCategory(Long id, Long itemId);

	Boolean updateItem(ItemData itemData);

	Boolean deleteCategory(Long id);

	CategoryData updateCategory(CategoryData categoryData);
	
}
