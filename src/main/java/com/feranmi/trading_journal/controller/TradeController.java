package com.feranmi.trading_journal.controller;

import java.util.List;

import jakarta.validation.Valid;

import com.feranmi.trading_journal.dto.TradeStatsResponse;
import com.feranmi.trading_journal.model.Trade;
import com.feranmi.trading_journal.service.TradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trades")
public class TradeController {

    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping
    public Trade createTrade(@Valid @RequestBody Trade trade) {
        return tradeService.createTrade(trade);
    }

    @GetMapping
    public List<Trade> getAllTrades() {
        return tradeService.getAllTrades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trade> getTradeById(@PathVariable Long id) {
        return tradeService.getTradeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trade> updateTrade(@PathVariable Long id, @Valid @RequestBody Trade trade) {
        return tradeService.updateTrade(id, trade)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") //Deleting trades
    public ResponseEntity<Void> deleteTrade(@PathVariable Long id) {
        if (tradeService.getTradeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        tradeService.deleteTrade(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/stats") //gets the trade stats
    public TradeStatsResponse getTradeStats() {
        return tradeService.getTradeStats();
    }
}
