package com.my.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.sample.domain.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
