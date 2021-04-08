from prometheus_client import start_http_server, Gauge
import requests,time,random


sensorStatus = Gauge('TEST1', 'send data every 1 sec')

if __name__ == '__main__':
    start_http_server(9321)
    x = 0
    while x < 10:
        #sensorStatus.set(random.randint(1,30))
        sensorStatus.set_to_current_time()
        print (time.time())
        time.sleep(1)
        x += 1

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "9321"]
