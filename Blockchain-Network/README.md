# Blockchain Network

```sh

```
## access containers
http://d3me.net:9000/#!/1/docker/containers/5623237399f682ae0629fd39ab63fac1e10b435dd74e69b99e9ce65b30464bfe
username: admin
password: 12345678
## access Blockchain (Hyperledger fabric dashboard)
http://d3me.net:3000/d/monitor/hyperledger-fabric-monitoring?orgId=1&refresh=5s
## access Benchmarking Dashboard (Hyperledger caliper)
http://d3me.net:3000/d/CV8xBWTMz/hyperledger-caliper?orgId=1&refresh=5s
## access couch DB peer0.org1
http://d3me.net:5984/_utils/#
username: admin
password: adminpw
## access couch DB peer0.org1
http://d3me.net:7984/_utils/#
username: admin
password: adminpw

# Benchmark Commands

```sh
npm init -y
```


```sh
npm install --only=prod \
    @hyperledger/caliper-cli
```



```sh
npx caliper bind --caliper-bind-sut fabric:2.2
```

```sh
npx caliper launch manager \
--caliper-workspace . \
--caliper-benchconfig benchmarks/config.yaml \
--caliper-networkconfig networks/networkConfig.yaml --caliper-flow-only-test --caliper-fabric-gateway-usegateway
```

### Helpful Resources
- https://hyperledger-fabric.readthedocs.io/en/latest/getting_started.html
- https://hyperledger.github.io/caliper/v0.4.2/fabric-tutorial/tutorials-fabric-existing/
- https://medium.com/@jushuspace/hyperledger-fabric-monitoring-with-prometheus-and-statsd-f43ef0ab110e
- https://hyperledger.github.io/caliper/v0.2/caliper-monitors/
- https://github.com/hyperledger/caliper-benchmarks/blob/master/benchmarks/samples/fabric/marbles/config-prometheus.yaml
- https://grafana.com/grafana/dashboards/13405
