package com.feranmi.trading_journal.service;

import java.util.List;
import java.util.Optional;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import com.feranmi.trading_journal.dto.TradeStatsResponse;
import com.feranmi.trading_journal.model.Trade;
import com.feranmi.trading_journal.repository.TradeRepo;
import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private final TradeRepo tradeRepo;
    private final Validator validator;

    public TradeService(TradeRepo tradeRepo, Validator validator) {
        this.tradeRepo = tradeRepo;
        this.validator = validator;
    }

    public Trade createTrade(Trade trade) {
        validateTrade(trade);
        trade.setPnl(trade.calculatePnl());
        return tradeRepo.save(trade);
    }

    public List<Trade> getAllTrades() {
        return tradeRepo.findAll();
    }

    public Optional<Trade> getTradeById(Long id) {
        return tradeRepo.findById(id);
    }

    public Optional<Trade> updateTrade(Long id, Trade updatedTrade) {
        return tradeRepo.findById(id)
                .map(existingTrade -> {
                    existingTrade.setSymbol(updatedTrade.getSymbol());
                    existingTrade.setDirection(updatedTrade.getDirection());
                    existingTrade.setEntryPrice(updatedTrade.getEntryPrice());
                    existingTrade.setExitPrice(updatedTrade.getExitPrice());
                    existingTrade.setQuantity(updatedTrade.getQuantity());
                    existingTrade.setStrategyTag(updatedTrade.getStrategyTag());
                    existingTrade.setEntryTime(updatedTrade.getEntryTime());
                    existingTrade.setExitTime(updatedTrade.getExitTime());
                    existingTrade.setNotes(updatedTrade.getNotes());
                    existingTrade.setPnl(existingTrade.calculatePnl());

                    validateTrade(existingTrade);
                    return tradeRepo.save(existingTrade);
                });
    }

    public void deleteTrade(Long id) {
        tradeRepo.deleteById(id);
    }

    private void validateTrade(Trade trade) {
        var violations = validator.validate(trade);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    public TradeStatsResponse getTradeStats() {
        List<Trade> trades = tradeRepo.findAll(); //gets all the trades available
    
        int totalTrades = trades.size(); // # of trades
    
        double totalPnl = trades.stream()
                .mapToDouble(Trade::getPnl)
                .sum(); //stream makes it a sequence of values. mapToDouble just makes all PnLs a double, and then we take the total
    
        int winningTrades = (int) trades.stream()
                .filter(trade -> trade.getPnl() > 0)
                .count(); //we filter for trades above 0, then count
    
        int losingTrades = (int) trades.stream()
                .filter(trade -> trade.getPnl() < 0)
                .count(); //same thing but below zero
    
        double winRate = totalTrades == 0
                ? 0.0
                : (winningTrades * 100.0) / totalTrades; //iternary operator, basically an if else statement. if the total trades is equal to 0, then we have a wr of 0. else we calc the winning percentage.
    
        return new TradeStatsResponse(
                totalTrades,
                winningTrades,
                losingTrades,
                totalPnl,
                winRate
        );
    }
}
