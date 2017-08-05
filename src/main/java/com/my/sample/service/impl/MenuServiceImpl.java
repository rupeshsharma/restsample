package com.my.sample.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.my.sample.converter.CategoryConverter;
import com.my.sample.converter.ItemConverter;
import com.my.sample.converter.MenuConverter;
import com.my.sample.data.CategoryData;
import com.my.sample.data.ItemData;
import com.my.sample.data.MenuData;
import com.my.sample.domain.Category;
import com.my.sample.domain.Item;
import com.my.sample.repository.CategoryRepository;
import com.my.sample.repository.ItemRepository;
import com.my.sample.service.MenuService;

@Service("menuService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class MenuServiceImpl implements MenuService {
	private final ItemRepository itemRepository;
	private final CategoryRepository categoryRepository;

	@Autowired
	public MenuServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
		super();

		if (itemRepository == null) {
			throw new IllegalArgumentException("itemRepository cannot be null");
		}
		if (categoryRepository == null) {
			throw new IllegalArgumentException("categoryRepository cannot be null");
		}

		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
	}

	@Override
	public MenuData getMenu() {
		MenuData menu = new MenuData();
		List<Category> category = categoryRepository.findAll();
		MenuConverter.convert(category, menu);
		return menu;
	}

	@Override
	public Long addCategory(CategoryData categoryData) {
		Category category = new Category();
		category.setTitle(categoryData.getTitle());
		category = categoryRepository.save(category);
		return category.getId();
	}

	@Override
	public Long addItem(ItemData itemData) {
		Item item = new Item();
		item.setTitle(itemData.getTitle());
		item.setPrice(itemData.getPrice());
		item.setDescription(itemData.getDescription());
		item.setCategory(itemData.getCategory());
		item = itemRepository.save(item);
		Category category = categoryRepository.findOne(itemData.getCategory());
		category.getItems().add(item);
		categoryRepository.save(category);
		return item.getId();
	}

	@Override
	public List<CategoryData> getCategory() {
		List<CategoryData> categoryDataList = new ArrayList<CategoryData>();
		List<Category> categories = categoryRepository.findCategories();
		CategoryData categoryData = null;
		for (Category category : categories) {
			categoryData = new CategoryData();
			CategoryConverter.convert(category, categoryData, Boolean.FALSE);
			categoryDataList.add(categoryData);
		}
		return categoryDataList;
	}

	@Override
	public List<ItemData> getItemByCategory(Long id) {
		List<ItemData> itemDataList = new ArrayList<ItemData>();
		Category category = categoryRepository.findOne(id);
		ItemData itemData = null;
		for (Item item : category.getItems()) {
			itemData = new ItemData();
			ItemConverter.convert(item, itemData);
			itemDataList.add(itemData);
		}
		return itemDataList;
	}

	@Override
	public Boolean removeItemFromCategory(Long id, Long itemId) {
		Category category = categoryRepository.findOne(id);
		category.getItems().remove(new Item(itemId));
		categoryRepository.save(category);
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateItem(ItemData itemData) {
		Item item = itemRepository.findOne(itemData.getId());
		ItemConverter.reverse(itemData, item);
		itemRepository.save(item);
		return Boolean.TRUE;
	}

	@Override
	public Boolean deleteCategory(Long id) {
		categoryRepository.delete(id);
		return Boolean.TRUE;
	}

	@Override
	public Boolean updateCategory(CategoryData categoryData) {
		Category category = categoryRepository.findOne(categoryData.getId());
		CategoryConverter.reverse(categoryData, category);
		categoryRepository.save(category);
		return Boolean.TRUE;
	}

}
