package lv.proofit.Insurance.validation;

import lv.proofit.Insurance.model.Policy;
import lv.proofit.Insurance.model.PolicyObject;
import lv.proofit.Insurance.model.SubObject;
import org.apache.commons.lang3.Validate;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class PolicyValidator implements Validator {

    /*
    This can be localized if API will return message witch indicates what is wrong with input.
     */
    public static String POLICY_NULL_VALIDATION = "The policy must not be null";
    public static String POLICY_OBJECT_LIST_NULL_VALIDATION = "The policy object list must not be null";
    public static String POLICY_OBJECT_LIST_ELEM_NULL_VALIDATION = "The policy object list contains null element";
    public static String POLICY_SUB_OBJECT_LIST_NULL_VALIDATION = "The policy sub object list must not be null";
    public static String POLICY_SUB_OBJECT_ELEM_LIST_NULL_VALIDATION = "The policy sub object contains null element";
    public static String POLICY_SUB_OBJECT_RISK_TYPE_NULL_VALIDATION = "The policy sub object risk type must not be null";
    public static String POLICY_SUB_OBJECT_SUM_INSURED_NULL_VALIDATION = "The policy sub object sum insured must not be null";
    public static String POLICY_SUB_OBJECT_SUM_INSURED_NEGATIVE_VALIDATION = "The policy sub object sum insured must not be negative";

    @Override
    public void validate(Policy policy) {
        Validate.notNull(policy, POLICY_NULL_VALIDATION);
        validatePolicyObject(policy.getPolicyObjectList());
        validatePolicySubObject(policy.getPolicyObjectList().stream()
                .flatMap( obj -> obj.getSubObjectList().stream()).collect(Collectors.toList()));
    }

    private void validatePolicyObject(List<PolicyObject> policyObjectList) {
        Validate.notNull(policyObjectList, POLICY_OBJECT_LIST_NULL_VALIDATION);
        Validate.noNullElements(policyObjectList, POLICY_OBJECT_LIST_ELEM_NULL_VALIDATION);
        Validate.noNullElements(policyObjectList.stream()
                .map(PolicyObject::getSubObjectList).collect(Collectors.toList()), POLICY_SUB_OBJECT_LIST_NULL_VALIDATION);

    }

    private void validatePolicySubObject(List<SubObject> subObjectList) {
        Validate.noNullElements(subObjectList, POLICY_SUB_OBJECT_ELEM_LIST_NULL_VALIDATION);
        Validate.noNullElements(subObjectList.stream().map(SubObject::getRiskType)
                .collect(Collectors.toList()), POLICY_SUB_OBJECT_RISK_TYPE_NULL_VALIDATION);
        Validate.noNullElements(subObjectList.stream().map(SubObject::getInsuredSum)
                .collect(Collectors.toList()), POLICY_SUB_OBJECT_SUM_INSURED_NULL_VALIDATION);
        Validate.isTrue(subObjectList.stream().map(SubObject::getInsuredSum)
                .anyMatch(insuredSum -> BigDecimal.ZERO.compareTo(insuredSum) < 0 ), POLICY_SUB_OBJECT_SUM_INSURED_NEGATIVE_VALIDATION);
    }
}
