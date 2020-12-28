package com.bids.api.dto;

import com.sun.istack.NotNull;

public class BidDto {

	@NotNull
	private long auctionItemId;
	@NotNull
	private float maxAutoBidAmount;
	@NotNull
	private String bidderName;
	/**
	 * 
	 */
	public BidDto() {
		// TODO Auto-generated constructor stub
	}
	 
	/**
	 * @return the auctionItemId
	 */
	public long getAuctionItemId() {
		return auctionItemId;
	}

	/**
	 * @param auctionItemId the auctionItemId to set
	 */
	public void setAuctionItemId(long auctionItemId) {
		this.auctionItemId = auctionItemId;
	}

	/**
	 * @return the maxAutoBidAmount
	 */
	public float getMaxAutoBidAmount() {
		return maxAutoBidAmount;
	}
	/**
	 * @param maxAutoBidAmount the maxAutoBidAmount to set
	 */
	public void setMaxAutoBidAmount(float maxAutoBidAmount) {
		this.maxAutoBidAmount = maxAutoBidAmount;
	}
	/**
	 * @return the bidderName
	 */
	public String getBidderName() {
		return bidderName;
	}
	/**
	 * @param bidderName the bidderName to set
	 */
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	
	
	
}
