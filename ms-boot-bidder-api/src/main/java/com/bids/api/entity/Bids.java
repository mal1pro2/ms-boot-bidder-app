
package com.bids.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name = "bids")
public class Bids {

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long bidId;

	@Column(name = "max_auto_bid_amount")
	private float maxAutoBidAmount;

	@Column(name = "isactive")
	private boolean isActive;
	
	@Column(name = "ismeet")
	private boolean isMeet;
	
	@Column(name = "bidder_name", nullable = true)
	private String bidderName;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "action_item_id", referencedColumnName = "id", nullable = false) 
	private ActionItem actionItem;

	/**
	 * 
	 */
	public Bids() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the bidId
	 */
	public long getBidId() {
		return bidId;
	}

	/**
	 * @param bidId the bidId to set
	 */
	public void setBidId(long bidId) {
		this.bidId = bidId;
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

	/**
	 * @return the actionItem
	 */
	public ActionItem getActionItem() {
		return actionItem;
	}

	/**
	 * @param actionItem the actionItem to set
	 */
	public void setActionItem(ActionItem actionItem) {
		this.actionItem = actionItem;
	}

	 

	/**
	 * @return the isMeet
	 */
	public boolean isMeet() {
		return isMeet;
	}

	/**
	 * @param isMeet the isMeet to set
	 */
	public void setMeet(boolean isMeet) {
		this.isMeet = isMeet;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Bids [bidId=" + bidId + ", maxAutoBidAmount=" + maxAutoBidAmount + ", bidderName=" + bidderName
				+ ", actionItem=" + actionItem + "]";
	}
	
	

}
