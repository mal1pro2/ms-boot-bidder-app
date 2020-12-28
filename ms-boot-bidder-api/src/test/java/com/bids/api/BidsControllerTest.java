package com.bids.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.bids.api.dto.ActionItemDto;
import com.bids.api.dto.BidDto;
import com.bids.api.dto.BidResp;
import com.bids.api.dto.ItemDto;
import com.bids.api.entity.ActionItem;
import com.bids.api.entity.Bids;
import com.bids.api.entity.Item;
import com.bids.api.repo.BidsRepository;
import com.bids.api.service.ActionItemService;
import com.bids.api.service.BidsService;
import com.bids.api.service.ItemService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("dev")
public class BidsControllerTest {

	@Autowired
	ItemService itemService;
	
	@Autowired
	ActionItemService actionItemService;
	
	@Autowired
	BidsService bidsService;
	 
	
	@Autowired
	BidsRepository bidsRepository;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test 
	public void testNoBidsPresent() {
		bidsRepository.deleteAll();
		String body = restTemplate.getForObject("/bids", String.class);
		JsonObject jsonObject = new Gson().fromJson(body, JsonObject.class);
		JsonElement jelement = jsonObject.get("errors");
		String error = jelement.getAsString();
		assertEquals(error.equalsIgnoreCase("No Bids found!"),true);
	}

	@Test 
	public void testGetAllBids() {
		ItemDto itemDto = new ItemDto("item11");
		Item item= itemService.save(itemDto);
		ActionItem actionItem = actionItemService.save(new ActionItemDto(0, 1320, new ItemDto(item.getItemId(),item.getDescription())));
		BidDto bidDto = new BidDto();
		bidDto.setAuctionItemId(actionItem.getActionItemId());
		bidDto.setBidderName("Sam");
		bidDto.setMaxAutoBidAmount(200);
		bidsService.save(bidDto);
		String body = restTemplate.getForObject("/bids", String.class); 
		assertEquals(body != null, true);
	}

	@Test  
	public void testBidNotMeet() {
		ItemDto itemDto = new ItemDto("item12");
		Item item= itemService.save(itemDto);
		ActionItem actionItem = actionItemService.save(new ActionItemDto(0, 1320, new ItemDto(item.getItemId(),item.getDescription())));
		BidDto bidDto = new BidDto();
		bidDto.setAuctionItemId(actionItem.getActionItemId());
		bidDto.setBidderName("Sam");
		bidDto.setMaxAutoBidAmount(200); 
		ResponseEntity<BidResp> reponse = restTemplate.postForEntity("/bids", bidDto, BidResp.class);
		assertEquals(reponse.getBody().getMessage(), "You have not meet the bid!");
		assertEquals(reponse.getStatusCode(), HttpStatus.CREATED);
	}
	
	@Test  
	public void testBidMeet() {
		ItemDto itemDto = new ItemDto("item13");
		Item item= itemService.save(itemDto);
		ActionItem actionItem = actionItemService.save(new ActionItemDto(0, 1320, new ItemDto(item.getItemId(),item.getDescription())));
		BidDto bidDto = new BidDto();
		bidDto.setAuctionItemId(actionItem.getActionItemId());
		bidDto.setBidderName("Sam");
		bidDto.setMaxAutoBidAmount(1400); 
		ResponseEntity<BidResp> reponse = restTemplate.postForEntity("/bids", bidDto, BidResp.class);		
		assertEquals(reponse.getBody().getMessage(), "You have meet the bid!");
		assertEquals(reponse.getStatusCode(), HttpStatus.CREATED);
	}

	@Test   
	public void testBidBadRequest() {
		ResponseEntity<Bids> reponse = restTemplate.postForEntity("/bids", null, Bids.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	
	@Test   
	public void testBadArgument() {
		BidDto bidDto = new BidDto(); 
		bidDto.setBidderName(null);
		bidDto.setMaxAutoBidAmount(1400);
		ResponseEntity<Bids> reponse = restTemplate.postForEntity("/bids", bidDto, Bids.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
 
}
