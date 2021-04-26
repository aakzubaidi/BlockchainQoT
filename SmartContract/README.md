# BlockchainQoT
 
## add provider
```json
{
  "ProviderID": "0001",
  "name": "IoTSP",
  "deposit": "1000"
}
```

## add Violation category

```json
{
  "violationKey": "0002",
  "providerKey": "0001",
  "categoryName": "c1",
  "penality": "0.05",
  "maxTolerance": "10"
}
```

————
## evaluate performance example

```json
{
  "deltaID": "0003",
  "ProviderID": "0001",
  "fire": "false",
  "processingTime": "false",
  "edgeAvailability": "false",
  "serverAvailability": "true"
}
```