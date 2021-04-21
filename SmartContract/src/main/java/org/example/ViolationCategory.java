package org.example;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class ViolationCategory {

    private final static Genson genson = new Genson();

    @Property()
    private String categoryName;
    @Property()
    private String providerKey;
    @Property()
    private String penality;
    @Property()
    private String maxTolerance;
    @Property()
    private String violationRate;
    
    @Property()
    private String violationsCounter;

    public ViolationCategory(){
    }

    

    public String getProviderKey() {
        return providerKey;
    }

    public void setProviderKey(String providerKey) {
        this.providerKey = providerKey;
    }

    public String getPenality() {
        return penality;
    }

    public void setPenality(String penality) {
        this.penality = penality;
    }

    public String getMaxTolerance() {
        return maxTolerance;
    }

    public void setMaxTolerance(String maxTolerance) {
        this.maxTolerance = maxTolerance;
    }

    public String getViolationRate() {
        return violationRate;
    }

    public void setViolationRate(String violationRate) {
        this.violationRate = violationRate;
    }




    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static ViolationCategory fromJSONString(String json) {
        ViolationCategory violationCategory = genson.deserialize(json, ViolationCategory.class);
        return violationCategory;
    }


    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getViolationsCounter() {
        return violationsCounter;
    }

    public void setViolationsCounter(String violationsCounter) {
        this.violationsCounter = violationsCounter;
    }


    
}
