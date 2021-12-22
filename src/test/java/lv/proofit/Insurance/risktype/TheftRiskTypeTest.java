package lv.proofit.Insurance.risktype;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class TheftRiskTypeTest {

    private RiskType sut = TheftRiskType.INSTANCE;

    @ParameterizedTest
    @CsvSource({"14.00,1.54,When sum insured is below 15 should use default coefficient",
                "15.0,0.75,When sum insured is equal 15 should not use default coefficient",
                "15.01,0.75,When sum insured is greater than 15 should not use default coefficient",
                "0.00,0.00,When sum insured is equal 0 than should return 0"})
    public void calculatePremium_whenSumInsuredProvided_shouldCalculatePremium(BigDecimal sumInsured, BigDecimal expected, String testCaseDesc) {
        //given
        // sumInsured

        //when
        BigDecimal result = sut.calculatePremium(sumInsured);

        //then
        assertThat(result).describedAs(testCaseDesc).isEqualTo(expected);
    }
}
