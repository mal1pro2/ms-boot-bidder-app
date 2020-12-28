package com.bids.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bids.api.entity.ActionItem;

@Repository
public interface ActionItemRepository extends JpaRepository<ActionItem, Long> {

}
