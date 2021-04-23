# IBM Blockchain



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
