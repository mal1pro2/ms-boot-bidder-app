package com.bids.api.service;

import java.util.List;

import com.bids.api.dto.BidDto;
import com.bids.api.entity.Bids;

public interface BidsService {
	
	List<Bids> findAll();
	
	List<Bids> outBid(long id);
	
	Bids save(BidDto dto); 
}
