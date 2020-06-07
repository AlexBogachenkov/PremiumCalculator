import java.util.ArrayList;
import java.util.List;

class Policy {

    private String policyNumber;
    private String policyStatus;
    private List<PolicyObject> policyObjects = new ArrayList<PolicyObject>(1);

    public Policy(String policyNumber, String policyStatus) {
        this.policyNumber = policyNumber;
        this.policyStatus = policyStatus;
    }

    public void addObjectToPolicy(PolicyObject object) {
        this.policyObjects.add(object);
    }

    public List<PolicyObject> getPolicyObjects() {
        return policyObjects;
    }
}
