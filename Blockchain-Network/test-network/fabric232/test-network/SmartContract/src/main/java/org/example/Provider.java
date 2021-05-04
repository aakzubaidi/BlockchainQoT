package org.example;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class Provider {

    private final static Genson genson = new Genson();

    @Property()
    private String name;
    @Property()
    private String deposit;
    @Property()
    private String compliantCases;
    @Property()
    private String valdityOfSLA;



    public String getValdityOfSLA() {
        return valdityOfSLA;
    }

    public void setValdityOfSLA(String valdityOfSLA) {
        this.valdityOfSLA = valdityOfSLA;
    }

    public static Genson getGenson() {
        return genson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getCompliantCases() {
        return compliantCases;
    }

    public void setCompliantCases(String compliantCases) {
        this.compliantCases = compliantCases;
    }

    public Provider(){
    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Provider fromJSONString(String json) {
        Provider provider = genson.deserialize(json, Provider.class);
        return provider;
    }
    
}
