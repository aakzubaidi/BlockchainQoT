/*
 * SPDX-License-Identifier: Apache-2.0
 */

package org.example;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import com.owlike.genson.Genson;

@DataType()
public class Metric {

    private final static Genson genson = new Genson();

    @Property()
    private String name;
    @Property()
    private String requieredLevel;
    @Property()
    private String value;




    public Metric(){
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toJSONString() {
        return genson.serialize(this).toString();
    }

    public static Metric fromJSONString(String json) {
        Metric asset = genson.deserialize(json, Metric.class);
        return asset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequieredLevel() {
        return requieredLevel;
    }

    public void setReuiredLevel(String reuieredLevel) {
        this.requieredLevel = reuieredLevel;
    }
}
