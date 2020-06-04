import java.util.ArrayList;
import java.util.List;

class Object {

    private String objectName;
    List<SubObject> subObjects = new ArrayList<SubObject>(1);

    public Object(String objectName) {
        this.objectName = objectName;
    }

    public void addSubObjectToObject(SubObject subObject) {
        this.subObjects.add(subObject);
    }

    public List<SubObject> getSubObjects() {
        return subObjects;
    }
}
