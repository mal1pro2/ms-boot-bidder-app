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

import com.bids.api.dto.ActionDto;
import com.bids.api.dto.ActionItemDto;
import com.bids.api.entity.ActionItem;
import com.bids.api.exception.NotFoundException;
import com.bids.api.service.ActionItemService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auctionItems")
public class ActionItemController {
	
	Logger logger = LoggerFactory.getLogger(ActionItemController.class);
	
	@Autowired
	private ActionItemService actionItemService;
	
	
	@GetMapping
	public ResponseEntity<?> getAllActionItems(){
		List<ActionItem> list = actionItemService.findAll(); 
		if(list == null || list.isEmpty()) {
			throw new NotFoundException("No Action Item found!");
		}
		logger.info("Total AuctionItems : "+list.size());
		return new ResponseEntity<List<ActionItem>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getActionItems(@PathVariable(name = "id") String id){
		Optional<ActionItem> object = actionItemService.find(Long.parseLong(id));
		if(!object.isPresent()) {
			throw new NotFoundException("Invalid action id!");
		}
		return new ResponseEntity<ActionItem>(object.get(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> createActionItem(@Valid @RequestBody ActionItemDto dto){ 
		ActionItem item = actionItemService.save(dto);
		return new ResponseEntity<ActionDto>(new ActionDto(item.getActionItemId()), HttpStatus.CREATED) ;
	}

}
