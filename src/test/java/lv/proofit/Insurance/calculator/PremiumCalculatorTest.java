package lv.proofit.Insurance.calculator;

import lv.proofit.Insurance.model.Policy;
import lv.proofit.Insurance.model.PolicyObject;
import lv.proofit.Insurance.model.SubObject;
import lv.proofit.Insurance.risktype.FireRiskType;
import lv.proofit.Insurance.risktype.RiskType;
import lv.proofit.Insurance.risktype.TheftRiskType;
import lv.proofit.Insurance.validation.Validator;
import org.apache.commons.lang3.tuple.Pair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PremiumCalculatorTest {

    @Mock
    Validator validator;

    PremiumCalculator sut;

    @Before
    public void init() {
        sut = new PremiumCalculator(validator);
    }

    @Test
    public void calculate_whenSubObjectsWithDifferentRiskType_shouldUseDifferentCalculationStrategies() {

        // given
        var policy = buildPolicyForSubObjects(List.of(Pair.of(FireRiskType.INSTANCE, new BigDecimal("100")),
                                                             Pair.of(TheftRiskType.INSTANCE, new BigDecimal("8"))));

        // when
        BigDecimal result = sut.calculate(policy);

        // then
        assertThat(result).isEqualTo(new BigDecimal("2.28"));

    }

    @Test
    public void calculate_whenSubObjectsWithDifferentRiskType_shouldUseDifferentNonDefaultCalculationStrategies() {

        // given
        var policy = buildPolicyForSubObjects(List.of(Pair.of(FireRiskType.INSTANCE, new BigDecimal("500")),
                                                             Pair.of(TheftRiskType.INSTANCE, new BigDecimal("102.51"))));

        // when
        BigDecimal result = sut.calculate(policy);

        // then
        assertThat(result).isEqualTo(new BigDecimal("17.13"));
    }

    @Test
    public void calculate_whenSubObjects4WithDifferentRiskType_shouldUseDifferentCalculationStrategies() {

        // given
        var policy = buildPolicyForSubObjects(List.of(Pair.of(FireRiskType.INSTANCE, new BigDecimal("100")),
                                                             Pair.of(FireRiskType.INSTANCE, new BigDecimal("99")),
                                                             Pair.of(TheftRiskType.INSTANCE, new BigDecimal("40")),
                                                             Pair.of(TheftRiskType.INSTANCE, new BigDecimal("102.51"))));

        // when
        BigDecimal result = sut.calculate(policy);

        // then
        assertThat(result).isEqualTo(new BigDecimal("11.91"));
    }

    @Test
    public void calculate_whenPolicyIsProvided_shouldBeValidated() {

        // given
        var policy = buildPolicyForSubObjects(List.of(Pair.of(FireRiskType.INSTANCE, new BigDecimal("100"))));

        // when
        BigDecimal result = sut.calculate(policy);

        // then
        verify(validator).validate(eq(policy));

    }

    protected Policy buildPolicyForSubObjects(List<Pair<RiskType, BigDecimal>> subObjectMap) {
        var policy = new Policy();
        var policyObject = new PolicyObject();
        policy.setPolicyObjectList(List.of(policyObject));
        policyObject.setSubObjectList(new ArrayList<>());

        subObjectMap.forEach(p -> policyObject.getSubObjectList().add(new SubObject("test", p.getRight(), p.getLeft())));

        return policy;
    }
}
