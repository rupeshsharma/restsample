package com.my.sample.REST.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.my.sample.config.security.Authorities;
import com.my.sample.config.security.RestSecurity;
import com.my.sample.data.CategoryData;
import com.my.sample.data.ItemData;
import com.my.sample.service.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

	private final MenuService menuService;

	@Autowired
	public MenuController(MenuService menuService) {
		super();

		if (menuService == null) {
			throw new IllegalArgumentException("menuService cannot be null");
		}

		this.menuService = menuService;
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN, Authorities.ROLE_USER })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getMenu() throws Exception {
		return new ResponseEntity<>(menuService.getMenu(), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory() throws Exception {
		return new ResponseEntity<>(menuService.getCategory(), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ResponseEntity<?> addCategory(@RequestBody CategoryData categoryData) throws Exception {
		return new ResponseEntity<>(menuService.addCategory(categoryData), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) throws Exception {
		return new ResponseEntity<>(menuService.deleteCategory(id), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/category", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@RequestBody CategoryData categoryData) throws Exception {
		return new ResponseEntity<>(menuService.updateCategory(categoryData), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/{id}/item", method = RequestMethod.GET)
	public ResponseEntity<?> getItemsForCategory(@PathVariable Long id) throws Exception {
		return new ResponseEntity<>(menuService.getItemByCategory(id), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/item", method = RequestMethod.POST)
	public ResponseEntity<?> addItemForCategory(@RequestBody ItemData itemData) throws Exception {
		return new ResponseEntity<>(menuService.addItem(itemData), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeItemFromCategory(@PathVariable Long id) throws Exception {
		return new ResponseEntity<>(menuService.removeItem(id), HttpStatus.OK);
	}

	@RestSecurity(authority = { Authorities.ROLE_ADMIN })
	@RequestMapping(value = "/item", method = RequestMethod.PUT)
	public ResponseEntity<?> updateItem(@RequestBody ItemData itemData) throws Exception {
		return new ResponseEntity<>(menuService.updateItem(itemData), HttpStatus.OK);
	}

	

}
