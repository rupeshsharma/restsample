package com.my.sample.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.my.sample.domain.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query("Select NEW Category(c.id,c.title,c.createdDate,c.modifiedDate) FROM Category c WHERE c.status = 'y'")
	List<Category> findCategories();

}
