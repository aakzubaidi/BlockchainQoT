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
        const addProvider = {
            contractId: this.roundArguments.contractId,
            contractFunction: 'addProvider',
            invokerIdentity: 'User1@org1.example.com',
            contractArguments: ['0001', 'IoTSP', '1000'],
            readOnly: false
        };

        //await this.sutAdapter.sendRequests(addProvider);

        const addViolatonCategory1 = {
            contractId: this.roundArguments.contractId,
            contractFunction: 'addViolatonCategory',
            invokerIdentity: 'User1@org1.example.com',
            contractArguments: ['0002', '0001', 'c1', '0.05', '10'],
            readOnly: false
        };

        //await this.sutAdapter.sendRequests(addViolatonCategory1);

    }

    async submitTransaction() {
        txIndex++;
        //let breaches = txIndex.toString();
        //let compliantLogs = (txIndex*1000).toString();
        let deltaID = padLeadingZeros(txIndex, 4).toString(); //"0057"
        let ProviderID = '0001';
        let fire = 'true';
        let processingTime = '2';
        let edgeAvailability = 'true';
        let serverAvailability;

        //violation category 2
        if(Math.random() < 0.5)
        {
            fire = 'false';
            processingTime = '0';
            edgeAvailability = 'false';
            serverAvailability = 'true'; 
          
            // compliance case
        } else
        {
            fire = 'true';
            processingTime = '2';
            edgeAvailability = 'true';
            serverAvailability = 'true'; 

        }
        
            const myArgs = {
                contractId: this.roundArguments.contractId,
                contractFunction: 'evaluateComplianceE',
                invokerIdentity: 'User1@org1.example.com',
                contractArguments: [deltaID, ProviderID, fire, processingTime, edgeAvailability, serverAvailability],
                readOnly: false
            };
            await this.sutAdapter.sendRequests(myArgs);
        
    }

    async cleanupWorkloadModule() {

    }
}

function padLeadingZeros(num, size) {
    var s = num+"";
    while (s.length < size) s = "0" + s;
    return s;
}

function createWorkloadModule() {
    return new MyWorkload();
}

module.exports.createWorkloadModule = createWorkloadModule;