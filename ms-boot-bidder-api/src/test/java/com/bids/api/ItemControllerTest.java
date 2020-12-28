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

import com.bids.api.dto.ItemDto;
import com.bids.api.entity.Item;
import com.bids.api.repo.ItemRepository;
import com.bids.api.service.ItemService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@ActiveProfiles("dev")
public class ItemControllerTest {

	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemRepository itemRepository;

	@Autowired
	private TestRestTemplate restTemplate;
 
	 
	@Test 
	public void testGetAllItems() {
		ItemDto dto = new ItemDto("item desc 1");
		itemService.save(dto);
		String body = restTemplate.getForObject("/items", String.class); 
		assertEquals(body != null, true);
	}

	@Test  
	public void testSaveItem() {
		ItemDto dto = new ItemDto(2, "item desc 2");
		ResponseEntity<Item> reponse = restTemplate.postForEntity("/items", dto, Item.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.CREATED);
	}

	@Test   
	public void testBadRequest() {
		ResponseEntity<Item> reponse = restTemplate.postForEntity("/items", null, Item.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	
	@Test   
	public void testBadArgument() {
		ItemDto dto = new ItemDto();
		dto.setDescription(null);
		ResponseEntity<Item> reponse = restTemplate.postForEntity("/items", dto, Item.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.CONFLICT);
	}

	@Test   
	public void testGetItem() {
		ItemDto dto = new ItemDto("item desc 3");
		Item item = itemService.save(dto);
		ResponseEntity<Item> reponse = restTemplate.getForEntity("/items/"+item.getItemId(), Item.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.OK);
	}

	@Test  
	public void testInvalidItemId() {
		ItemDto dto = new ItemDto("item desc 4");
		itemService.save(dto);
		ResponseEntity<Item> reponse = restTemplate.getForEntity("/items/101", Item.class);
		assertEquals(reponse.getStatusCode(), HttpStatus.NOT_FOUND);
	}

}
