package lv.proofit.Insurance.risktype;

import java.math.BigDecimal;

import static lv.proofit.Insurance.utils.Const.ROUND_MODE;
import static lv.proofit.Insurance.utils.Const.ROUND_SCALE;

public enum FireRiskType implements RiskType{

    INSTANCE;

    private BigDecimal ONE_HUNDRED = new BigDecimal("100");
    private BigDecimal COEFFICIENT_FIRE_DEFAULT = new BigDecimal("0.014");
    private BigDecimal COEFFICIENT_FIRE = new BigDecimal("0.024");

    @Override
    public BigDecimal calculatePremium(BigDecimal insuredSum) {
        BigDecimal result;

        if(ONE_HUNDRED.compareTo(insuredSum) < 0) {
            result = insuredSum.multiply(COEFFICIENT_FIRE);
        } else {
            result = insuredSum.multiply(COEFFICIENT_FIRE_DEFAULT);
        }

        return result.setScale(ROUND_SCALE, ROUND_MODE);
    }
}
