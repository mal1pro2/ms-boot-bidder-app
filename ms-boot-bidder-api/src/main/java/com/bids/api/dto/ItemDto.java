package com.bids.api.dto;

import com.sun.istack.NotNull;

public class ItemDto {
	
	private long itemId;
	@NotNull
	private String description;
	/**
	 * 
	 */
	public ItemDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	/**
	 * @param itemId
	 * @param description
	 */
	public ItemDto(long itemId, String description) {
		this.itemId = itemId;
		this.description = description;
	}




	public ItemDto(String description) {
		this.description = description;
	}


	/**
	 * @return the itemId
	 */
	public long getItemId() {
		return itemId;
	}


	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
