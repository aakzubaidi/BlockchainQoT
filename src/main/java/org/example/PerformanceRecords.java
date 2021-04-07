package org.example;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class PerformanceRecords {

    private final static Genson genson = new Genson();

    @Property()
    private String providerName;
    @Property()
    private String role;
    @Property()
    private String value;




    public PerformanceRecords(){
    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Metric fromJSONString(String json) {
        Metric asset = genson.deserialize(json, Metric.class);
        return asset;
    }
    
}
