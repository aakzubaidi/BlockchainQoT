'use strict';

let txIndex = 0;
const incidentID = `QoSTESTRecoed`;

const { WorkloadModuleBase } = require('@hyperledger/caliper-core');

class MyWorkload extends WorkloadModuleBase {
    constructor() {
        super();
    }

    
    async initializeWorkloadModule(workerIndex, totalWorkers, roundIndex, roundArguments, sutAdapter, sutContext) {
        await super.initializeWorkloadModule(workerIndex, totalWorkers, roundIndex, roundArguments, sutAdapter, sutContext);
            console.log(`Worker ${this.workerIndex}: Creating incident QoS metric ${incidentID}`);
            const request = {
                contractId: this.roundArguments.contractId,
                contractFunction: 'createMonitoringReport',
                invokerIdentity: 'Org1Admin',
                contractArguments: [incidentID,'0','0'],
                readOnly: false
            };

            await this.sutAdapter.sendRequests(request);
    }
    
    async submitTransaction() {
        txIndex++;
        let breaches = txIndex.toString();
        let compliantLogs = (txIndex*1000).toString();
        const myArgs = {
            contractId: this.roundArguments.contractId,
            contractFunction: 'reportViolation',
            invokerIdentity: 'Org1Admin',
            contractArguments: [incidentID, breaches , compliantLogs ],
            readOnly: false
        };

        await this.sutAdapter.sendRequests(myArgs);
    }
    
    async cleanupWorkloadModule() {
        // NOOP
    }
}

function createWorkloadModule() {
    return new MyWorkload();
}

module.exports.createWorkloadModule = createWorkloadModule;