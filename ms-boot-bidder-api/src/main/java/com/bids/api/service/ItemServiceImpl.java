/**
 * 
 */
package com.bids.api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bids.api.dto.ItemDto;
import com.bids.api.entity.Item;
import com.bids.api.repo.ItemRepository;
import com.bids.api.utils.Utils;

/**
 * @author ompal singh
 *
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {
	
	Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	@Autowired
	private ItemRepository  itemRepository;

	@Override
	public List<Item> findAll() { 
		return itemRepository.findAll();
	}

	@Override
	public Item save(ItemDto dto) {	

		logger.info("Item Request: "+Utils.ObjectToJson(dto));
		Item item = new Item();
		item.setDescription(dto.getDescription());
		logger.info("Item data before save: "+item.toString());
		return itemRepository.save(item);
	}

	@Override
	public Optional<Item> find(Long id) {	
		logger.info("Item id "+id);
		return itemRepository.findById(id);
	}

}
