package com.feranmi.trading_journal.model;
//The purpose of this file is to define what a trade is.
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDateTime; 


@Entity //makes this to be a data table
public class Trade {
    


    @Id //marks a unique id for each trade.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // generates the ID, so I don't have to manually have a trade 1, trade 2, etc...
    
    private Long id;

    @NotBlank(message = "Symbol is required")
    private String symbol;


    @Enumerated(EnumType.STRING) // again, makes i so I can have a fixed choice for the direction.
    @NotNull(message = "Direction is required")
    private Direction direction;

    @Positive(message = "Entry price must be above zero")
    private double entryPrice;

    @Positive(message = "Exit price must be above zero")
    private double exitPrice;

    @Positive(message = "Quantity must be above zero")
    private double quantity; //contracts, lots, etc

    private String strategyTag;

    private LocalDateTime entryTime;

    private LocalDateTime exitTime;

    private String notes;

    private double pnl;




    //All variables that define a trade.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public double getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(double entryPrice) {
        this.entryPrice = entryPrice;
    }

    public double getExitPrice() {
        return exitPrice;
    }

    public void setExitPrice(double exitPrice) {
        this.exitPrice = exitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getStrategyTag() {
        return strategyTag;
    }

    public void setStrategyTag(String strategyTag) {
        this.strategyTag = strategyTag;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPnl(double pnl){
        this.pnl = pnl;
    }

    

    public double getPnl() {
        return pnl;
    }

   
    public double calculatePnl() {
        if (direction == Direction.SHORT) {
            return (entryPrice - exitPrice) * quantity;
        }

        return (exitPrice - entryPrice) * quantity;
    }

    
}
