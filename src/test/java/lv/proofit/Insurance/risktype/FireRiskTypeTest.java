package lv.proofit.Insurance.risktype;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class FireRiskTypeTest {

    private RiskType sut = FireRiskType.INSTANCE;

    @ParameterizedTest
    @CsvSource({"99.00,1.39,When sum insured is below 100 should use default coefficient",
                "100.00,1.40,When sum insured is equal 100 should use default coefficient",
                "100.01,2.40,When sum insured is greater than 100 should not use default coefficient",
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
