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
		// TODO: define your main and details tiles here
		standardTile("Door Control", "device.doorControl", width: 2, height: 2, canChangeIcon: true) {
	  		state "open", label: '${name}', action: "switch.open", icon: "st.switches.switch.on", backgroundColor: "#79b821"
    		state "closed", label: '${name}', action: "switch.closed", icon: "st.switches.switch.off", backgroundColor: "#ffffff"
		}

		main "doorControl"
		details "doorControl"

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
	// TODO: handle 'open' command
    httpPost(
		uri: "https://api.particle.io/v1/devices/${deviceID}/setLatch",
        body: [access_token: accessToken, args: "open"],  
	) {response -> log.debug (response.data)}
    
}

def close() {
	log.debug "Executing 'close'"
	// TODO: handle 'close' command
    httpPost(
		uri: "https://api.particle.io/v1/devices/${deviceID}/setLatch",
        body: [access_token: accessToken, args: "close"],  
	) {response -> log.debug (response.data)}
}