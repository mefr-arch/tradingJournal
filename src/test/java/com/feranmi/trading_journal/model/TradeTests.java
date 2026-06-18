package com.feranmi.trading_journal.model;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.junit.jupiter.api.Test;

class TradeTests {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    void calculatesLongPnl() {
        Trade trade = new Trade();
        trade.setDirection(Direction.LONG);
        trade.setEntryPrice(100.0);
        trade.setExitPrice(125.0);
        trade.setQuantity(2.0);

        assertThat(trade.calculatePnl()).isEqualTo(50.0);
    }

    @Test
    void calculatesShortPnl() {
        Trade trade = new Trade();
        trade.setDirection(Direction.SHORT);
        trade.setEntryPrice(125.0);
        trade.setExitPrice(100.0);
        trade.setQuantity(2.0);

        assertThat(trade.calculatePnl()).isEqualTo(50.0);
    }

    @Test
    void requiresSymbolDirectionAndPositivePricesAndQuantity() {
        Trade trade = new Trade();
        trade.setSymbol("");
        trade.setEntryPrice(0.0);
        trade.setExitPrice(-1.0);
        trade.setQuantity(0.0);

        var violations = validator.validate(trade);

        assertThat(violations)
                .extracting(violation -> violation.getPropertyPath().toString())
                .containsExactlyInAnyOrder("symbol", "direction", "entryPrice", "exitPrice", "quantity");
    }
}
