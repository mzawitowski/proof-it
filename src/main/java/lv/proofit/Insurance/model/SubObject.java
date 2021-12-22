package lv.proofit.Insurance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.proofit.Insurance.risktype.RiskType;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubObject {
    String name;
    BigDecimal insuredSum;
    RiskType riskType;
}
