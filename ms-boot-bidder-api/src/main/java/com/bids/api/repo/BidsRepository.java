package com.bids.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bids.api.entity.Bids;

@Repository
public interface BidsRepository extends JpaRepository<Bids, Long> {

}
