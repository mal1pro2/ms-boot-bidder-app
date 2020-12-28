package com.bids.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bids.api.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
