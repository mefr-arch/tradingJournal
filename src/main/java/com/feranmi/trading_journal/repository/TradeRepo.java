package com.feranmi.trading_journal.repository;
// This file is the repository layer responsible for communicating with the database.

import com.feranmi.trading_journal.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeRepo extends JpaRepository<Trade, Long> {
}
