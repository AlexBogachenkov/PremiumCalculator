import java.util.ArrayList;
import java.util.List;

class Policy {

    private String policyNumber;
    private String policyStatus;
    private List<Object> policyObjects = new ArrayList<Object>(1);

    public Policy(String policyNumber, String policyStatus) {
        this.policyNumber = policyNumber;
        this.policyStatus = policyStatus;
    }

    public void addObjectToPolicy(Object object) {
        this.policyObjects.add(object);
    }

    public List<Object> getPolicyObjects() {
        return policyObjects;
    }
}
