package com.feranmi.trading_journal.dto;

public class TradeStatsResponse {
    private int totalTrades;
    private int winningTrades;
    private int losingTrades;
    private double totalPnl;
    private double winRate;

    public TradeStatsResponse(int totalTrades, int winningTrades, int losingTrades, double totalPnl, double winRate) {
        this.totalTrades = totalTrades;
        this.winningTrades = winningTrades;
        this.losingTrades = losingTrades;
        this.totalPnl = totalPnl;
        this.winRate = winRate;
    }

    public int getTotalTrades() {
        return totalTrades;
    }

    public int getWinningTrades() {
        return winningTrades;
    }

    public int getLosingTrades() {
        return losingTrades;
    }

    public double getTotalPnl() {
        return totalPnl;
    }

    public double getWinRate() {
        return winRate;
    }
}
