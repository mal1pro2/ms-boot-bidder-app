package com.bids.api.resource;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bids.api.dto.BidDto;
import com.bids.api.dto.BidResp;
import com.bids.api.entity.Bids;
import com.bids.api.exception.NotFoundException;
import com.bids.api.service.BidsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/bids")
public class BidsController {
	
	Logger logger = LoggerFactory.getLogger(BidsController.class);
	
	@Autowired
	private BidsService bidsService;
	
	
	@GetMapping
	public ResponseEntity<?> getAllBida(){
		List<Bids> list = bidsService.findAll(); 
		if(list == null || list.isEmpty()) {
			throw new NotFoundException("No Bids found!");
		} 
		logger.info("Total Bids : "+list.size());
		return new ResponseEntity<List<Bids>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/outbid")
	public ResponseEntity<?> outBid(@RequestParam(name = "id",required = true) String id){
		List<Bids> list = bidsService.outBid(Long.parseLong(id)); 
		if(list == null || list.isEmpty()) {
			throw new NotFoundException("No Bids found!");
		} 
		return new ResponseEntity<List<Bids>>(list,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createBid(@Valid @RequestBody BidDto dto){  
		Bids bids = bidsService.save(dto);
		String message = "You have meet the bid!"; 
		if(!bids.isMeet()) {
			message = "You have not meet the bid!";
		}
		BidResp resp = new BidResp(bids.getBidderName(), message, bids.getMaxAutoBidAmount());
		return new ResponseEntity<BidResp>(resp, HttpStatus.CREATED);
	}

}
