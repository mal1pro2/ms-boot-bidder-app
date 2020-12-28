package com.bids.api.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bids.api.dto.ItemDto;
import com.bids.api.entity.Item;
import com.bids.api.exception.NotFoundException;
import com.bids.api.service.ItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/items")
@EnableSwagger2
@Api(value = "Items API", description = "Item details created")
public class ItemController {

	Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService temService;

	@ApiOperation(value = "Get All Items", notes = "Return Items List", response = List.class)
	@GetMapping
	public ResponseEntity<?> getAllItems() {
		List<Item> list = temService.findAll();
		if (list == null || list.isEmpty()) {
			throw new NotFoundException("No Item found!");
		}
		logger.info("Total items : "+list.size());
		return new ResponseEntity<List<Item>>(list, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getItems(@PathVariable(name = "id") String id) {
		Optional<Item> object = temService.find(Long.parseLong(id));
		if (!object.isPresent()) {
			throw new NotFoundException("Invalid item id!");
		}
		return new ResponseEntity<Item>(object.get(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createItem(@Valid @RequestBody ItemDto dto) {
		return new ResponseEntity<Item>(temService.save(dto), HttpStatus.CREATED);
	}

}
