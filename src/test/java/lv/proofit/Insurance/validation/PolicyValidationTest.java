package lv.proofit.Insurance.validation;

import lv.proofit.Insurance.model.Policy;
import lv.proofit.Insurance.model.PolicyObject;
import lv.proofit.Insurance.model.SubObject;
import lv.proofit.Insurance.risktype.FireRiskType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static lv.proofit.Insurance.validation.PolicyValidator.POLICY_NULL_VALIDATION;
import static lv.proofit.Insurance.validation.PolicyValidator.POLICY_OBJECT_LIST_ELEM_NULL_VALIDATION;
import static lv.proofit.Insurance.validation.PolicyValidator.POLICY_OBJECT_LIST_NULL_VALIDATION;
import static lv.proofit.Insurance.validation.PolicyValidator.POLICY_SUB_OBJECT_ELEM_LIST_NULL_VALIDATION;
import static lv.proofit.Insurance.validation.PolicyValidator.POLICY_SUB_OBJECT_LIST_NULL_VALIDATION;
import static lv.proofit.Insurance.validation.PolicyValidator.POLICY_SUB_OBJECT_RISK_TYPE_NULL_VALIDATION;
import static lv.proofit.Insurance.validation.PolicyValidator.POLICY_SUB_OBJECT_SUM_INSURED_NEGATIVE_VALIDATION;
import static lv.proofit.Insurance.validation.PolicyValidator.POLICY_SUB_OBJECT_SUM_INSURED_NULL_VALIDATION;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PolicyValidationTest {

    Validator sut = new PolicyValidator();


    @Test
    public void validate_whenPolicyIsNull_shouldThrowIllegalArgumentException() {

        // given
        Policy policy = null;

        // when
        assertThatThrownBy( () -> sut.validate(policy) )

                // then
                .isInstanceOf(NullPointerException.class)
                .hasMessage(POLICY_NULL_VALIDATION);
    }

    @Test
    public void validate_whenPolicyObjectIsNull_shouldThrowIllegalArgumentException() {

        // given
        var policy = new Policy();

        // when
        assertThatThrownBy( () -> sut.validate(policy) )

                // then
                .isInstanceOf(NullPointerException.class)
                .hasMessage(POLICY_OBJECT_LIST_NULL_VALIDATION);
    }

    @Test
    public void validate_whenPolicyObjectElemIsNull_shouldThrowIllegalArgumentException() {

        // given
        var listWithNullElem = new ArrayList<PolicyObject>();
        listWithNullElem.add(null);
        var policy = new Policy();
        policy.setPolicyObjectList(listWithNullElem);

        // when
        assertThatThrownBy( () -> sut.validate(policy) )

                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(POLICY_OBJECT_LIST_ELEM_NULL_VALIDATION);
    }

    @Test
    public void validate_whenPolicySubObjectIsNull_shouldThrowIllegalArgumentException() {

        // given
        var policy = new Policy();
        var policyObject = new PolicyObject();
        policy.setPolicyObjectList(List.of(policyObject));

        // when
        assertThatThrownBy( () -> sut.validate(policy) )

                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(POLICY_SUB_OBJECT_LIST_NULL_VALIDATION);
    }

    @Test
    public void validate_whenPolicySubObjectElemIsNull_shouldThrowIllegalArgumentException() {

        // given
        var listWithNullElem = new ArrayList<SubObject>();
        listWithNullElem.add(null);
        var policy = new Policy();
        var policyObject = new PolicyObject();
        policy.setPolicyObjectList(List.of(policyObject));
        policyObject.setSubObjectList(listWithNullElem);

        // when
        assertThatThrownBy( () -> sut.validate(policy) )

                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(POLICY_SUB_OBJECT_ELEM_LIST_NULL_VALIDATION);
    }

    @Test
    public void validate_whenPolicySubObjectRiskTypeIsNull_shouldThrowIllegalArgumentException() {

        // given
        var listWithNullElem = new ArrayList<SubObject>();
        listWithNullElem.add(null);
        var policy = new Policy();
        var policyObject = new PolicyObject();
        var subObject = new SubObject();
        policy.setPolicyObjectList(List.of(policyObject));
        policyObject.setSubObjectList(List.of(subObject));
        subObject.setInsuredSum(BigDecimal.TEN);
        // when
        assertThatThrownBy( () -> sut.validate(policy) )

                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(POLICY_SUB_OBJECT_RISK_TYPE_NULL_VALIDATION);
    }

    @Test
    public void validate_whenPolicySubObjectInsuredSumIsNull_shouldThrowIllegalArgumentException() {

        // given
        var listWithNullElem = new ArrayList<SubObject>();
        listWithNullElem.add(null);
        var policy = new Policy();
        var policyObject = new PolicyObject();
        var subObject = new SubObject();
        policy.setPolicyObjectList(List.of(policyObject));
        policyObject.setSubObjectList(List.of(subObject));
        subObject.setRiskType(FireRiskType.INSTANCE);
        // when
        assertThatThrownBy( () -> sut.validate(policy) )

                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(POLICY_SUB_OBJECT_SUM_INSURED_NULL_VALIDATION);
    }

    @Test
    public void validate_whenPolicySubObjectInsuredSumIsNegative_shouldThrowIllegalArgumentException() {

        // given
        var listWithNullElem = new ArrayList<SubObject>();
        listWithNullElem.add(null);
        var policy = new Policy();
        var policyObject = new PolicyObject();
        var subObject = new SubObject();
        policy.setPolicyObjectList(List.of(policyObject));
        policyObject.setSubObjectList(List.of(subObject));
        subObject.setRiskType(FireRiskType.INSTANCE);
        subObject.setInsuredSum(BigDecimal.valueOf(-1L));
        // when
        assertThatThrownBy( () -> sut.validate(policy) )

                // then
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(POLICY_SUB_OBJECT_SUM_INSURED_NEGATIVE_VALIDATION);
    }
}
