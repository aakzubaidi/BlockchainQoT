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
  "ProviderID": "0001",
  "fire": "false",
  "processingTime": "0.5",
  "edgeAvailability": "false",
  "serverAvailability": "true"
}
```