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

    public boolean Exists(Context ctx, String metricId) {
        byte[] buffer = ctx.getStub().getState(metricId);
        return (buffer != null && buffer.length > 0);
    }

    @Transaction()
    public void createMetric(Context ctx, String metricId, String name, String requieredLevel, String value) {
        boolean exists = Exists(ctx,metricId);
        if (exists) {
            throw new RuntimeException("The metric "+metricId+" already exists");
        }
        Metric metric = new Metric();
        metric.setName(name);
        metric.setReuiredLevel(requieredLevel);
        metric.setValue(value);
        ctx.getStub().putState(metricId, metric.toJSONString().getBytes(UTF_8));
    }

    @Transaction()
    public Metric readMetric(Context ctx, String metricId) {
        boolean exists = Exists(ctx,metricId);
        if (!exists) {
            throw new RuntimeException("The asset "+metricId+" does not exist");
        }

        Metric metric = Metric.fromJSONString(new String(ctx.getStub().getState(metricId),UTF_8));
        return metric;
    }

    @Transaction()
    public void updateMetric(Context ctx, String metricId,  String newValue) {
        boolean exists = Exists(ctx,metricId);
        if (!exists) {
            throw new RuntimeException("The asset "+metricId+" does not exist");
        }
        Metric metric = new Metric();
        metric.setValue(newValue);

        ctx.getStub().putState(metricId, metric.toJSONString().getBytes(UTF_8));
    }



    //add provider
    @Transaction()
    public void addProvider(Context ctx, String ProviderID, String name, String deposit) {
        boolean exists = Exists(ctx,ProviderID);
        if (exists) {
            throw new RuntimeException("The asset "+ProviderID+" already exists");
        }
        Provider provider = new Provider();
        provider.setName(name);
        provider.setDeposit(deposit);
        provider.setCompliantCases("0");
        provider.setValdityOfSLA("true");
        
        ctx.getStub().putState(ProviderID, provider.toJSONString().getBytes(UTF_8));
    }


    //Add violation category
    @Transaction()
    public void addViolatoncategory(Context ctx, String violationKey, String providerKey, String categoryName, String penality, String maxTolerance) {
        boolean exists = Exists(ctx,violationKey);
        if (exists) {
            throw new RuntimeException("The violation category "+violationKey+" already exists");
        }

        exists = Exists(ctx,providerKey);
        if (!exists) {
            throw new RuntimeException("The provider "+providerKey+" does not exists");
        }
        ViolationCategory violationCategory = new ViolationCategory();
        violationCategory.setCategoryName(categoryName);
        violationCategory.setProviderKey(providerKey);
        Provider provider = Provider.fromJSONString(new String(ctx.getStub().getState(providerKey),UTF_8));
        double deposite = Double.valueOf(provider.getDeposit());
        double p = deposite * Double.valueOf(penality);
        violationCategory.setPenality(String.valueOf(p));
        violationCategory.setMaxTolerance(maxTolerance);
        violationCategory.setViolationRate("0");
        violationCategory.setViolationsCounter("0");
        
        
        ctx.getStub().putState(violationKey, violationCategory.toJSONString().getBytes(UTF_8));
    }


    @Transaction()
    public void evaluateCompliance(Context ctx, String ProviderID, String fire, String processingTime, String edgeAvailability, String serverAvailability ) {
        boolean exists = Exists(ctx,ProviderID);
        if (!exists) {
            throw new RuntimeException("The provider with ID: "+ProviderID+" does not exist");
        }
        boolean f = Boolean.valueOf(fire);
        double ts = Double.valueOf(processingTime);
        boolean ae = Boolean.valueOf(edgeAvailability);
        boolean ac = Boolean.valueOf(serverAvailability);
        String violationKey = "";
        double vr = 0;
        int cc = 0;
        int vc = 0;
        double mt = 0;
        Boolean complianceStatus = true;
        Provider provider = Provider.fromJSONString(new String(ctx.getStub().getState(ProviderID),UTF_8));

        if(!f && ts < 1 && !ae && ac)
        {
            violationKey = "0002";
            complianceStatus = false;
        }

        ViolationCategory violationCategory  = ViolationCategory.fromJSONString(new String(ctx.getStub().getState(violationKey),UTF_8));

        cc = Integer.valueOf(provider.getCompliantCases());
        vc = Integer.valueOf(violationCategory.getViolationsCounter());
        mt = Double.valueOf(violationCategory.getMaxTolerance());

        if (complianceStatus)
        {
            cc++;
            provider.setCompliantCases(String.valueOf(cc));
        }
        else
        {
            vc++;
            violationCategory.setViolationsCounter(String.valueOf(vr));
        }
        
        vr = (vc/(vc+cc)) * 100;
        violationCategory.setViolationRate(String.valueOf(vr));

        
        if (vr > mt)
        {
            provider.setValdityOfSLA("false");
            System.out.println("SLA terminated");
        }
        else
        {
            double penality = Double.valueOf(violationCategory.getPenality());
            double deposite = Double.valueOf(provider.getDeposit()) - penality;
            provider.setDeposit(String.valueOf(deposite));
        }


        if(complianceStatus)
        {
            ctx.getStub().putState(ProviderID, provider.toJSONString().getBytes(UTF_8));
        }
        else
        {
            ctx.getStub().putState(ProviderID, provider.toJSONString().getBytes(UTF_8));
            ctx.getStub().putState(violationKey, violationCategory.toJSONString().getBytes(UTF_8));

        }

    }



}
