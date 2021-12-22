package lv.proofit.Insurance.risktype;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TheftRiskTypeTest {

    private RiskType sut = TheftRiskType.INSTANCE;

    @Test
    public void calculatePremium_whenSumInsuredIsBelowOrEqualDefault_shouldUseDefaultCoefficient() {
        //given
        var sumInsured = new BigDecimal("14.99");

        //when
        BigDecimal result = sut.calculatePremium(sumInsured);

        //then
        assertThat(result).isEqualTo(new BigDecimal("1.65"));
    }

    @Test
    public void calculatePremium_whenSumInsuredIsGreaterThanDefault_shouldNotUseDefaultCoefficient() {
        //given
        var sumInsured = new BigDecimal("15.01");

        //when
        BigDecimal result = sut.calculatePremium(sumInsured);

        //then
        assertThat(result).isEqualTo(new BigDecimal("0.75"));
    }
}
