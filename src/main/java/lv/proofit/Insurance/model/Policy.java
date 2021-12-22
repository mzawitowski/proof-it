package lv.proofit.Insurance.model;

import lombok.Data;

import java.util.List;

@Data
public class Policy {
    String number;
    PolicyStatus status;
    List<PolicyObject> policyObjectList;
}
