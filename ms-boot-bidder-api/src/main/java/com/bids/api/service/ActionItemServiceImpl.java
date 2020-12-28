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

import com.bids.api.dto.ActionItemDto;
import com.bids.api.entity.ActionItem;
import com.bids.api.entity.Item;
import com.bids.api.repo.ActionItemRepository;
import com.bids.api.repo.ItemRepository;

/**
 * @author ompal singh
 *
 */
@Service
@Transactional
public class ActionItemServiceImpl implements ActionItemService {
	
	Logger logger = LoggerFactory.getLogger(ActionItemServiceImpl.class);

	@Autowired
	private ActionItemRepository actionItemRepository;

	@Autowired
	private ItemRepository iRepository;

	@Override
	public List<ActionItem> findAll() {
		return actionItemRepository.findAll();
	}

	@Override
	public ActionItem save(ActionItemDto dto) {
		logger.info("AuctionItem Request: "+dto.toString());
		Optional<Item> item = iRepository.findById(dto.getItem().getItemId());
		ActionItem actionItem = new ActionItem();
		actionItem.setCurrentBid(dto.getCurrentBid());
		actionItem.setReservePrice(dto.getReservePrice());
		actionItem.setItem(item.get());
		logger.info("actionItem data before save: "+actionItem.toString());
		return actionItemRepository.save(actionItem);
	}

	@Override
	public Optional<ActionItem> find(Long id) {
		logger.info("AuctionItem ID: "+id);
		return actionItemRepository.findById(id);
	}

}
