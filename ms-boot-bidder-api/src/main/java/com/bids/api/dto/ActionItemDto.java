package com.bids.api.dto;

import com.sun.istack.NotNull;

public class ActionItemDto {
	
	
	private long actionItemId;
	private float currentBid;
	@NotNull
	private float reservePrice;
	private ItemDto item;
	/**
	 * 
	 */
	public ActionItemDto() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param actionItemId
	 * @param currentBid
	 * @param reservePrice
	 * @param item
	 */
	public ActionItemDto(long actionItemId, float currentBid, float reservePrice, ItemDto item) {
		this.actionItemId = actionItemId;
		this.currentBid = currentBid;
		this.reservePrice = reservePrice; 
		this.item = item;
	}
	
	public ActionItemDto(float currentBid, float reservePrice, ItemDto item) { 
		this.currentBid = currentBid;
		this.reservePrice = reservePrice; 
		this.item = item;
	}
	
	
	/**
	 * @return the actionItemId
	 */
	public long getActionItemId() {
		return actionItemId;
	}
	/**
	 * @param actionItemId the actionItemId to set
	 */
	public void setActionItemId(long actionItemId) {
		this.actionItemId = actionItemId;
	}
	/**
	 * @return the currentBid
	 */
	public float getCurrentBid() {
		return currentBid;
	}
	/**
	 * @param currentBid the currentBid to set
	 */
	public void setCurrentBid(float currentBid) {
		this.currentBid = currentBid;
	}
	/**
	 * @return the reservePrice
	 */
	public float getReservePrice() {
		return reservePrice;
	}
	/**
	 * @param reservePrice the reservePrice to set
	 */
	public void setReservePrice(float reservePrice) {
		this.reservePrice = reservePrice;
	}
	/**
	 * @return the item
	 */
	public ItemDto getItem() {
		return item;
	}
	/**
	 * @param item the item to set
	 */
	public void setItem(ItemDto item) {
		this.item = item;
	}
	
	
	

}
