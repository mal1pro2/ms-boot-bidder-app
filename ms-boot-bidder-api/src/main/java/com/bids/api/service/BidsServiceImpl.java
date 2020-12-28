package com.bids.api.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bids.api.dto.BidDto;
import com.bids.api.entity.ActionItem;
import com.bids.api.entity.Bids;
import com.bids.api.repo.ActionItemRepository;
import com.bids.api.repo.BidsRepository;

@Service
@Transactional
public class BidsServiceImpl implements BidsService {

	Logger logger = LoggerFactory.getLogger(BidsServiceImpl.class);
	
	@Autowired
	private BidsRepository bidsRepository;

	@Autowired
	private ActionItemRepository aitemRepository;

	@Override
	public List<Bids> findAll() {
		return bidsRepository.findAll();
	}

	@Override
	public Bids save(BidDto dto) {
		return selectBid(dto);
	}
 
	private Bids selectBid(BidDto dto) {
		logger.info("Bid Request: "+dto.toString());
		Optional<ActionItem> actionItem = aitemRepository.findById(dto.getAuctionItemId());
		ActionItem item = null;
		if (actionItem.isPresent()) {
			item = actionItem.get(); 
		}

		Bids bids = new Bids();
		bids.setActive(false);
		if (dto.getMaxAutoBidAmount() < item.getReservePrice()) {
			item.setCurrentBid(dto.getMaxAutoBidAmount());
			bids.setMeet(false);
		} else {
			item.setReservePrice(dto.getMaxAutoBidAmount());
			bids.setMeet(true);
		}

		bids.setMaxAutoBidAmount(dto.getMaxAutoBidAmount());
		bids.setBidderName(dto.getBidderName());
		bids.setActionItem(item);
		logger.info("Bid data before save: "+bids.toString());
		return bidsRepository.save(bids);

	}

	@Override
	public List<Bids> outBid(long id) {
		Optional<Bids> bid = bidsRepository.findById(id);
		Bids entity = bid.get();
		entity.setActive(true);
		bidsRepository.save(entity);
		return bidsRepository.findAll();
	}

}
