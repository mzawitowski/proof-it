package lv.proofit.Insurance.risktype;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class FireRiskTypeTest {

    private RiskType sut = FireRiskType.INSTANCE;

    @Test
    public void calculatePremium_whenSumInsuredIsBelowOrEqualDefault_shouldUseDefaultCoefficient() {
        //given
        var sumInsured = new BigDecimal("99.99");

        //when
        BigDecimal result = sut.calculatePremium(sumInsured);

        //then
        assertThat(result).isEqualTo(new BigDecimal("1.40"));
    }

    @Test
    public void calculatePremium_whenSumInsuredIsGreaterThanDefault_shouldNotUseDefaultCoefficient() {
        //given
        var sumInsured = new BigDecimal("100.01");

        //when
        BigDecimal result = sut.calculatePremium(sumInsured);

        //then
        assertThat(result).isEqualTo(new BigDecimal("2.40"));
    }
}
