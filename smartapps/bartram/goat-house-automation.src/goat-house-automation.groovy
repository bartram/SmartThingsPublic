/**
 *  Goat House Automation
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
definition(
    name: "Goat House Automation",
    namespace: "bartram",
    author: "Bartram Nason",
    description: "Controls goat house door opening in the mornings.",
    category: "Convenience",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png",
    iconX3Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")


preferences {
	section {
        input "door", "capability.doorControl"
    }
}

def installed() {
	log.debug "Installed with settings: ${settings}"

	initialize()
}

def updated() {
	log.debug "Updated with settings: ${settings}"

	unsubscribe()
	initialize()
}

def initialize() {
	log.info "Goat House Automation 'initialize'"
	// TODO: subscribe to attributes, devices, locations, etc.
    subscribe(app, appHandler)
//    subscribe(location, "sunrise", sunriseHandler)

    subscribe(location, "sunrise", sunriseHandler)
	subscribe(location, "sunset", sunsetHandler)

//  scheduleScheduler()
//	runEvery5Minutes(doorRefreshHandler);
}

def appHandler(evt) {
    log.info "Goat House Automation 'appHandler'"
	door.open();
}

def doorRefreshHandler() {
	door.refresh();
}

// TODO: implement event handlers
def sunriseHandler(evt) {

	// @todo open goat house door
    log.info "Goat House Automation 'sunriseHandler'"
	door.open();

//    scheduleScheduler();

}

def sunsetHandler() {

    log.info "Goat House Automation 'sunsetHandler'"
//	scheduleDoorOpen();
    
}

