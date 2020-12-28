package com.bids.api.service;

import java.util.List;
import java.util.Optional;

import com.bids.api.dto.ActionItemDto;
import com.bids.api.entity.ActionItem;

public interface ActionItemService {
	
	List<ActionItem> findAll();
	
	ActionItem save(ActionItemDto dto);
	
	Optional<ActionItem> find(Long id);

}
