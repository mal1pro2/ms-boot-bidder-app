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
@Table
public class ActionItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long actionItemId;
	@Column(name = "current_bid")
	private float currentBid;
	@Column(name = "reserve_price")
	private float reservePrice;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
	private Item item;
 
	/**
	 * 
	 */
	public ActionItem() {
		// TODO Auto-generated constructor stub
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
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}
 
}
