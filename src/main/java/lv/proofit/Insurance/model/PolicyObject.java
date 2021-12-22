package lv.proofit.Insurance.model;

import lombok.Data;

import java.util.List;

@Data
public class PolicyObject {
    String name;
    List<SubObject> subObjectList;
}
