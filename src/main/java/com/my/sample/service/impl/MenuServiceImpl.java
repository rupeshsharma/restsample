package com.my.sample.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.my.sample.data.YGraphData;
import com.my.sample.domain.Category;
import com.my.sample.domain.Item;
import com.my.sample.repository.CategoryRepository;
import com.my.sample.repository.ItemRepository;
import com.my.sample.service.MenuService;
import com.my.sample.service.OrderService;

@Service("menuService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class MenuServiceImpl implements MenuService {
	private final ItemRepository itemRepository;
	private final CategoryRepository categoryRepository;
	private final OrderService orderService;

	@Autowired
	public MenuServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository,
			OrderService orderService) {
		super();

		if (itemRepository == null) {
			throw new IllegalArgumentException("itemRepository cannot be null");
		}
		if (categoryRepository == null) {
			throw new IllegalArgumentException("categoryRepository cannot be null");
		}
		if (orderService == null) {
			throw new IllegalArgumentException("orderService cannot be null");
		}

		this.itemRepository = itemRepository;
		this.categoryRepository = categoryRepository;
		this.orderService = orderService;
	}

	@Override
	public MenuData getMenu() {
		MenuData menu = new MenuData();
		List<Category> category = categoryRepository.findAll();
		MenuConverter.convert(category, menu);
		return menu;
	}

	@Override
	public CategoryData addCategory(CategoryData categoryData) {
		Category category = new Category();
		CategoryConverter.reverse(categoryData, category);
		category.setCreatedDate(new Date());
		category.setStatus('y');
		category = categoryRepository.save(category);
		CategoryConverter.convert(category, categoryData, Boolean.FALSE);
		return categoryData;
	}

	@Override
	public ItemData addItem(ItemData itemData) {
		Item item = new Item();
		ItemConverter.reverse(itemData, item);
		item.setCreatedDate(new Date());
		item.setStatus('y');
		item = itemRepository.save(item);
		Category category = categoryRepository.findOne(itemData.getCategory());
		category.getItems().add(item);
		category = categoryRepository.save(category);
		ItemConverter.convert(item, itemData);
		return itemData;
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
		List<Item> items = itemRepository.findItemsByCategory(id);
		ItemData itemData = null;
		for (Item item : items) {
			itemData = new ItemData();
			ItemConverter.convert(item, itemData);
			itemDataList.add(itemData);
		}
		return itemDataList;
	}

	@Override
	public Boolean removeItem(Long id) {
		Item item = itemRepository.findOne(id);
		item.setStatus('n');
		itemRepository.save(item);
		return Boolean.TRUE;
	}

	@Override
	public ItemData updateItem(ItemData itemData) {
		Item item = itemRepository.findOne(itemData.getId());
		ItemConverter.reverse(itemData, item);
		item.setModifiedDate(new Date());
		item = itemRepository.save(item);
		ItemConverter.convert(item, itemData);
		return itemData;
	}

	@Override
	public Boolean deleteCategory(Long id) {
		Category category = categoryRepository.findOne(id);
		category.setStatus('n');
		categoryRepository.save(category);
		return Boolean.TRUE;
	}

	@Override
	public CategoryData updateCategory(CategoryData categoryData) {
		Category category = categoryRepository.findOne(categoryData.getId());
		CategoryConverter.reverse(categoryData, category);
		category.setModifiedDate(new Date());
		category = categoryRepository.save(category);
		CategoryConverter.convert(category, categoryData, Boolean.FALSE);
		return categoryData;
	}

	@Override
	public List<YGraphData> getWholeItemGraphData() {
		List<YGraphData> yGraphDataList = new ArrayList<YGraphData>();

		List<Item> itemList = itemRepository.getAllItemDataMin();
		YGraphData yGraphData = null;
		for (Item item : itemList) {
			yGraphData = new YGraphData();
			yGraphData.setLabel(item.getTitle());
			yGraphData.setY(orderService.getTotalItemSoldById(item.getId()));
			yGraphDataList.add(yGraphData);
		}
		return yGraphDataList;
	}

	@Override
	public List<YGraphData> getWholeItemGraphDataInRange(Date fromDate, Date toDate) {
		List<YGraphData> yGraphDataList = new ArrayList<YGraphData>();

		List<Item> itemList = itemRepository.getAllItemDataMin();
		YGraphData yGraphData = null;
		for (Item item : itemList) {
			yGraphData = new YGraphData();
			yGraphData.setLabel(item.getTitle());
			yGraphData.setY(orderService.getTotalItemSoldByIdInRange(item.getId(),fromDate, toDate));
			yGraphDataList.add(yGraphData);
		}
		return yGraphDataList;
	}

}
