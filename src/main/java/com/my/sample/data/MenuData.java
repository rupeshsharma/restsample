package com.my.sample.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuData implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 734622352934543515L;
	private List<CategoryData> categories = new ArrayList<CategoryData>();

	public List<CategoryData> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryData> categories) {
		this.categories = categories;
	}
}
