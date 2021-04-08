/*
 * SPDX-License-Identifier: Apache-2.0
 */
package org.example;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.contract.annotation.Contact;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.License;
import static java.nio.charset.StandardCharsets.UTF_8;

@Contract(name = "MetricContract",
    info = @Info(title = "Metric contract",
                description = "My Smart Contract",
                version = "0.0.1",
                license =
                        @License(name = "Apache-2.0",
                                url = ""),
                                contact =  @Contact(email = "BlockchainQoT@example.com",
                                                name = "BlockchainQoT",
                                                url = "http://BlockchainQoT.me")))
@Default
public class MetricContract implements ContractInterface {
    public  MetricContract() {

    }
    @Transaction()
    public boolean Exists(Context ctx, String metricId) {
        byte[] buffer = ctx.getStub().getState(metricId);
        return (buffer != null && buffer.length > 0);
    }

    @Transaction()
    public void createMetric(Context ctx, String metricId, String name, String requieredLevel, String value) {
        boolean exists = Exists(ctx,metricId);
        if (exists) {
            throw new RuntimeException("The asset "+metricId+" already exists");
        }
        Metric asset = new Metric();
        asset.setName(name);
        asset.setReuiredLevel(requieredLevel);
        asset.setValue(value);
        ctx.getStub().putState(metricId, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public Metric readMetric(Context ctx, String metricId) {
        boolean exists = Exists(ctx,metricId);
        if (!exists) {
            throw new RuntimeException("The asset "+metricId+" does not exist");
        }

        Metric newAsset = Metric.fromJSONString(new String(ctx.getStub().getState(metricId),UTF_8));
        return newAsset;
    }

    @Transaction()
    public void updateMetric(Context ctx, String metricId,  String newValue) {
        boolean exists = Exists(ctx,metricId);
        if (!exists) {
            throw new RuntimeException("The asset "+metricId+" does not exist");
        }
        Metric asset = new Metric();
        asset.setValue(newValue);

        ctx.getStub().putState(metricId, asset.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public void deleteMetric(Context ctx, String metricId) {
        boolean exists = Exists(ctx,metricId);
        if (!exists) {
            throw new RuntimeException("The asset "+metricId+" does not exist");
        }
        ctx.getStub().delState(metricId);
    }



    @Transaction()
    public void evaluateCompliance(Context ctx, String ProviderID, String f, String ts, String ae, String ac) {
        boolean exists = Exists(ctx,ProviderID);
        if (exists) {
            throw new RuntimeException("The asset "+ProviderID+" already exists");
        }
        PerformanceRecords asset = new PerformanceRecords();
        
        ctx.getStub().putState(ProviderID, asset.toJSONString().getBytes(UTF_8));
    }



}
