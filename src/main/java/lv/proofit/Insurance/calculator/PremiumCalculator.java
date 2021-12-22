package lv.proofit.Insurance.calculator;

import lv.proofit.Insurance.model.Policy;
import lv.proofit.Insurance.model.SubObject;
import lv.proofit.Insurance.risktype.RiskType;
import lv.proofit.Insurance.validation.Validator;

import java.math.BigDecimal;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Premium calculator
 */
public class PremiumCalculator implements Calculator {

    private Validator validator;

    /**
     * Main constructor
     * @param validator validator which is used before calculations.
     */
    public PremiumCalculator(Validator validator){
        this.validator = validator;
    }

    /**
     * Calculate premium based on {@link Policy} object.
     * Details: Sum of all {@link SubObject#getInsuredSum()} grouped by {@link RiskType} and
     * applied {@link RiskType#calculatePremium(BigDecimal)}.
     * @param policy {@link Policy} object
     * @return calculated premium
     */
    @Override
    public BigDecimal calculate(Policy policy) {

        validator.validate(policy);

        Map<RiskType, BigDecimal> insuredSumByRiskTypeMap = policy.getPolicyObjectList().stream()
                .flatMap( policyObject -> policyObject.getSubObjectList().stream())
                .collect(Collectors.toMap(SubObject::getRiskType, SubObject::getInsuredSum, BigDecimal::add));

        return insuredSumByRiskTypeMap.entrySet().stream()
                .map(entry -> entry.getKey().calculatePremium(entry.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }



}
