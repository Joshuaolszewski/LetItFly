import { useLocation } from "react-router-dom";
import { createMap, userLocation } from "../components/MapUtilities";
import { useState } from "react";

/*
    BEFORE RIDE ACCEPTED
    ::Display::
    "Loading ..."

    ::Background::
    /api/ride-request
    getDriverIdOfRideRequest(ride request id) -> returns driverId
      -1 ? keep polling : driver found = driver id 

    AFTER RIDE ACCEPTED, BEFORE PICKUP
    ::Display::
    display map with route between current location and driver location
    update driver and customer markers

    ::Background::
    make sure i update passenger coords with: 
    /updateCoordinatesPassenger

    /api/driver-status
    findByDriverId(driverId) -> returns whole record -> look at "curLat", "curLong"

    AFTER PASSENGER PICKUP
    ::Display::
    display map with current location and destination location
    -- no need to interact with database anymore
*/

/**
 * ISSUES:
 *  - customerRideWorker sends multiple messages when driverId has been assigned, could be problematic
 */

function CustomerRide() {
  const {
    state: {
      rideRequestId: rideRequestId,
      cookie: { token: token },
    },
  } = useLocation();

  const beforeRideAccepted = "BEFORE RIDE ACCEPTED";
  const rideAcceptedBeforePickup = "RIDE ACCEPTED, BEFORE PICKUP";
  const afterPickup = "AFTER PICKUP";

  const [rideAccepted, setRideAccepted] = useState(false);
  const [passengerPickedUp, setPassengerPickedUp] = useState(false);
  const customerRideWorker = new Worker("/worker/CustomerRideWorker.js");

  const proxy = process.env.REACT_APP_BACKEND_BASE_URL;

  customerRideWorker.postMessage({
    proxy: proxy,
    typeString: beforeRideAccepted,
    token: token,
    param1: rideRequestId,
  });
  customerRideWorker.onmessage = (e) => {
    console.log(e.data);
    if (e.data.driverId) {
      setRideAccepted(true);
    }
  };

  function initMap() {
    userLocation.then((location) => {
      createMap(document.getElementById("ride-accepted-map"), location);
    });
  }

  return (
    <>
      <h1>Customer Ride Page</h1>
      {!rideAccepted ? (
        <h1>WAITING FOR RIDE ...</h1>
      ) : (
        <>
          <h1>RIDE HAS BEEN ACCEPTED!!!!!</h1>
          <div id="ride-accepted-map"></div>
          {initMap()}
        </>
      )}
    </>
  );
}

export default CustomerRide;
