package lv.proofit.Insurance.calculator;

import lv.proofit.Insurance.model.Policy;

import java.math.BigDecimal;
/**
 * An interface that specifies available operations for calculator.
 */
public interface Calculator {

    /**
     * Calculate based on {@link Policy} object
     * @param policy {@link Policy} object
     * @return calculated amount
     */
    BigDecimal calculate(Policy policy);
}
