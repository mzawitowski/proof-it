package lv.proofit.Insurance.risktype;

import java.math.BigDecimal;
/**
 * An interface that specifies available operations for insured risk types.
 */
public interface RiskType {

    /**
     * Calculates premium for insured sum
     * @param insuredSum insured sum
     * @return calculated premium
     */
    BigDecimal calculatePremium(BigDecimal insuredSum);

}
