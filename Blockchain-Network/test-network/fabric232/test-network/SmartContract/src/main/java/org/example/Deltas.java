package org.example;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class Deltas {

    private final static Genson genson = new Genson();

    @Property()
    private String providerID;
    @Property()
    private String cc;
    @Property()
    private String vc;
    

    @Property()
    private String c;
    @Property()
    private String p;


    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Deltas fromJSONString(String json) {
        Deltas deltas = genson.deserialize(json, Deltas.class);
        return deltas;
    }

    public String getProviderID() {
        return providerID;
    }

    public void setProviderID(String providerID) {
        this.providerID = providerID;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
    
}
