package com.bids.api.service;

import java.util.List;
import java.util.Optional;

import com.bids.api.dto.ItemDto;
import com.bids.api.entity.Item;

public interface ItemService {
	
	List<Item> findAll();
	
	Item save(ItemDto dto);
	
	Optional<Item> find(Long id);

}
