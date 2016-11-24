/**
 *  Goat House Door Control
 *
 *  Copyright 2016 Bartram Nason
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
 
 
preferences {
    input("deviceID", "text", title: "Device ID")
    input("accessToken", "text", title: "Access Token")
    input("latchOpen", "number", title: "Latch Open", description: "Servo position (in degrees) when latch is open.", defaultValue: 95)
    input("latchClosed", "number", title: "Latch Closed", description: "Servo position (in degrees) when latch is closed.", defaultValue: 15)
}

metadata {
	definition (name: "Goat House Door Control", namespace: "bartram", author: "Bartram Nason") {
		capability "Door Control"

		attribute "latchOpen", "string"
		attribute "latchClosed", "string"
	}


	simulator {
		// TODO: define status and reply messages here
	}

	tiles {
		standardTile("door", "device.door", inactiveLabel: false, decoration: "flat") {
			state "default", label:'open', icon:"st.locks.lock.unlocked", action:"door control.open", nextState: "opening"
            state "opening", label:'opening', icon:"st.locks.lock.unlocked"
		}

		main "door"
		details(["door"])
	}
}

// parse events into attributes
def parse(String description) {
	log.debug "Parsing '${description}'"
	// TODO: handle 'door' attribute
	// TODO: handle 'latchOpen' attribute
	// TODO: handle 'latchClosed' attribute

}

// handle commands
def open() {
	log.debug "Executing 'open'"

	httpPost(
		uri: "https://api.particle.io/v1/devices/${deviceID}/doorOpen",
        body: [access_token: accessToken],  
	) {response -> log.debug (response.data)}
    
    sendEvent(name: "door", value: "default", isStateChange: true);

}

def close() {
	log.debug "Executing 'close'"
	// TODO: handle 'close' command
}