package com.bids.api.dto;

public class ActionDto {
	
	
	private long auctionItemId; 
	public ActionDto() {
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * @param actionItemId
	 */
	public ActionDto(long auctionItemId) {
		this.auctionItemId = auctionItemId;
	}


	/**
	 * @return the actionItemId
	 */
	public long getAuctionItemId() {
		return auctionItemId;
	}
	/**
	 * @param actionItemId the actionItemId to set
	 */
	public void setAuctionItemId(long auctionItemId) {
		this.auctionItemId = auctionItemId;
	}
	 
	

}
