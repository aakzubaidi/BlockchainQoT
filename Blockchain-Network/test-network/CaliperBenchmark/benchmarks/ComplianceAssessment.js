'use strict';

let txIndex = 2;
const incidentID = `QoSTESTRecoed`;

const { WorkloadModuleBase } = require('@hyperledger/caliper-core');

class MyWorkload extends WorkloadModuleBase {
    constructor() {
        super();
    }


    async initializeWorkloadModule(workerIndex, totalWorkers, roundIndex, roundArguments, sutAdapter, sutContext) {
        await super.initializeWorkloadModule(workerIndex, totalWorkers, roundIndex, roundArguments, sutAdapter, sutContext);
        console.log(`Worker ${this.workerIndex}: Creating providers and violation categories ${incidentID}`);


    }

    async submitTransaction() {
        txIndex++;
        let ProviderID = '0001';
        let fromKey = '0003';
        let toKey = (this.roundArguments.Txnumber + 4).toString();
        let ViolationCategoryID = '0002';
        
            const myArgs = {
                contractId: this.roundArguments.contractId,
                contractFunction: 'ComplianceAssessment',
                invokerIdentity: 'User1@org1.example.com',
                contractArguments: [ProviderID, fromKey, toKey, ViolationCategoryID],
                readOnly: false
            };
            await this.sutAdapter.sendRequests(myArgs);
        
    }

    async cleanupWorkloadModule() {

    }
}

function createWorkloadModule() {
    return new MyWorkload();
}

module.exports.createWorkloadModule = createWorkloadModule;