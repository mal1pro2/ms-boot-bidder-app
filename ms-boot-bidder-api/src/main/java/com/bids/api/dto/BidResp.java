package com.bids.api.dto;

public class BidResp {

	private String bidderName;
	private String message;
	private float maxAutoBidAmount;

	/**
	 * @param bidderName
	 * @param message
	 * @param maxAutoBidAmount
	 */
	public BidResp(String bidderName, String message, float maxAutoBidAmount) {
		this.bidderName = bidderName;
		this.message = message;
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

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
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

}
