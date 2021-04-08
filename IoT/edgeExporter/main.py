#Importing required modules
from prometheus_client import start_http_server, Gauge
from time import sleep
import math, signal, sys, os, requests
import RPi.GPIO as GPIO

# Create a metric to track time spent and requests made.
initialAlert = Gauge('initialAlert', 'Status of the sensor')
ConfirmedAlert = Gauge('ConfirmedAlert', 'Status of the sensor')


#RPi pins settings
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)

# initialise variables
AlertEndpoint = "http://mhz-iot.com:8010/api/Sensors/registerAlert"
RegisteringEndpoint = "http://mhz-iot.com:8010/api/Sensors"
updateAlertStatusEndpoint = "http://mhz-iot.com:8010/api/Sensors/updateAlert"

sensorName = "Fire Detector"		#Sensor name
sensorLocation = "New Castle"		#Sensor Location
sensorRegistered = False
initialAlertStatus = False

#Registering sensor 
if sensorRegistered == False :
	data = {'name': sensorName, 'location': sensorLocation}
	sensorRegResponde = requests.post(RegisteringEndpoint, params = data)
	sensorID = sensorRegResponde.json()['id']
	print ("Sensor has been registered with id '{}'".format(sensorID))
	sensorRegistered = True

delayTime = 1		# export data every 1 sec
counter = 0
maxWait = 5			#Max true reading count from sensor to confirm fire

# Input pin for the digital signal will be picked here
Digital_PIN = 02
GPIO.setup(Digital_PIN, GPIO.IN, pull_up_down= GPIO.PUD_UP)

if __name__ == '__main__':
	start_http_server(9321)
	try:
		while True:
			# Output at the terminal
			if GPIO.input(Digital_PIN) == False: 											#Sensor reading = 0 = no flame detected
				counter = 0;
				initialAlert.set(0)
				ConfirmedAlert.set(0)
				
				if initialAlertStatus == True:
					data = {'sensorID': sensorID, 'alertID': initialAlertID, 'status': 'Discarded'}
					DiscardedResponde = requests.put(updateAlertStatusEndpoint, params = data)
					print(DiscardedResponde.status_code)
					print ("Discarded")
					initialAlertStatus = False


			elif GPIO.input(Digital_PIN) == True: 											#Sensor reading = 1 = flame detected
				counter += 1
				print("Alert : {}".format(counter))
				#Register Alert once
				if counter == 1 :
					initialAlertStatus = True
					initialAlert.set_to_current_time()										#export time of initial alert
					data = {'id': sensorID}
					initialAlertResponde = requests.post(AlertEndpoint, params = data)
					print(initialAlertResponde.status_code)
					initialAlertID = initialAlertResponde.json()['id']  					#Initial alert ID
					print ("Intial Alert with ID {}".format(initialAlertID))

				#Confirm fire alert ( after 5 true inputs )
				if counter == maxWait :
					ConfirmedAlert.set_to_current_time()									#export time of confirmed alert
					data = {'sensorID': sensorID, 'alertID': initialAlertID, 'status': 'Confirmed'}
					ConfirmationResponde = requests.put(updateAlertStatusEndpoint, params = data)
					print(ConfirmationResponde.status_code)
					print ("Confirmed")
					initialAlertStatus = False


			else :
				print GPIO.input(Digital_PIN)
				print "Sensor Failure"
			sleep(delayTime)
	except KeyboardInterrupt:
		GPIO.cleanup()
