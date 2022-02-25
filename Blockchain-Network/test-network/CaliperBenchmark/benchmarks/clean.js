'use strict';

let txIndex = 5002;
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
        let ProviderID = '0001';
        let violationKey = "0002";
        let Deposite = "100000";
        let maxTolerance = "60";
        let Penalty = "0.05";
        const myArgs = {
            contractId: this.roundArguments.contractId,
            contractFunction: 'clean',
            invokerIdentity: 'User1@org1.example.com',
            contractArguments: [ProviderID, violationKey, Deposite, maxTolerance, Penalty],
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