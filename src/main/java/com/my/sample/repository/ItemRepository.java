package com.my.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.my.sample.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	@Query("Select i FROM Item i WHERE i.category.id = :categoryId AND i.status = 'y'")
	List<Item> findItemsByCategory(@Param("categoryId")Long categoryId);

	@Query("Select New Item(i.id, i.title) FROM Item i WHERE i.status = 'y'")
	List<Item> getAllItemDataMin();

}
