import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class SubObject {

    private String subObjectName;
    private BigDecimal sumInsured;
    private List<String> riskType = new ArrayList<String>(2);

    public SubObject(String subObjectName, BigDecimal sumInsured, String riskType) {
        this.subObjectName = subObjectName;
        this.sumInsured = sumInsured;
        this.riskType.add(riskType);
    }

    public List<String> getRiskType() {
        return riskType;
    }

    public BigDecimal getSumInsured() {
        return sumInsured;
    }
}
