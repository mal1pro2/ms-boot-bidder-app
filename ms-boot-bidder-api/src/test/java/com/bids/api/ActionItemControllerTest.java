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
import com.bids.api.dto.ItemDto;
import com.bids.api.entity.ActionItem;
import com.bids.api.entity.Item;
import com.bids.api.repo.ActionItemRepository;
import com.bids.api.service.ActionItemService;
import com.bids.api.service.ItemService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("dev")
public class ActionItemControllerTest {

	@Autowired
	ItemService itemService;
	
	@Autowired
	ActionItemService actionItemService;
	
	@Autowired
	ActionItemRepository  actionItemRepository;
	 
	@Autowired
	private TestRestTemplate restTemplate;
 
	@Test 
	public void testActiontemsNotFound() {
		actionItemRepository.deleteAll();
		String body = restTemplate.getForObject("/auctionItems", String.class); 
		assertEquals(body.contains("No Action Item found!"), true);
	}
	
	
	@Test 
	public void testGetAllIActiontems() {
		ItemDto itemDto = new ItemDto("item 31");
		Item item= itemService.save(itemDto);
		actionItemService.save(new ActionItemDto(0, 1320, new ItemDto(item.getItemId(),item.getDescription())));
		String body = restTemplate.getForObject("/auctionItems", String.class); 
		assertEquals(body != null, true);
	}

	@Test  
	public void testSaveActionItem() {
		ItemDto itemDto = new ItemDto("item21");
		Item item= itemService.save(itemDto);
		ActionItemDto actionItemDto = new ActionItemDto(0, 1320, new ItemDto(item.getItemId(),item.getDescription()));
		ResponseEntity<ActionItem> reponse = restTemplate.postForEntity("/auctionItems", actionItemDto, ActionItem.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.CREATED);
	}

	@Test   
	public void testActionItemBadRequest() {
		ResponseEntity<ActionItem> reponse = restTemplate.postForEntity("/auctionItems", null, ActionItem.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	
	@Test   
	public void testBadArgument() { 
		ActionItemDto actionItemDto = new ActionItemDto(0, 1320, null);
		ResponseEntity<ActionItem> reponse = restTemplate.postForEntity("/auctionItems", actionItemDto, ActionItem.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Test   
	public void testGetItem() {
		ItemDto itemDto = new ItemDto("item22");
		Item item= itemService.save(itemDto);
		ActionItem actionItem = actionItemService.save(new ActionItemDto(0, 1320, new ItemDto(item.getItemId(),item.getDescription())));
		ResponseEntity<ActionItem> reponse = restTemplate.getForEntity("/auctionItems/"+actionItem.getActionItemId(), ActionItem.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.OK);
	}

	@Test 
	public void testInvalidItemId() {
		ItemDto itemDto = new ItemDto("item23");
		Item item= itemService.save(itemDto);
		actionItemService.save(new ActionItemDto(0, 1320, new ItemDto(item.getItemId(),item.getDescription())));
		ResponseEntity<ActionItem> reponse = restTemplate.getForEntity("/auctionItems/12", ActionItem.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.NOT_FOUND);
	}

}
