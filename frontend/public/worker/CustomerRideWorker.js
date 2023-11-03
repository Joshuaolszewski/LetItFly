/**
 * AFTER RIDE ACCEPTED, BEFORE PICKUP
 * make sure i update passenger coords with:
 * /updateCoordinatesPassenger
 *
 * /api/driver-status
 * findByDriverId(driverId) -> returns whole record -> look at "curLat", "curLong"
 */

/**
 * ISSUES:
 *  - customerRideWorker sends multiple messages when driverId has been assigned, could be problematic
 */

var driverIdPollCount = 0;

function driverIdPoll(proxy, token, rideRequestId) {
  const url = `${proxy}/api/ride-request/findById?id=${rideRequestId}`;
  return fetch(url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + token,
    },
  })
    .then((response) => {
      return response.json();
    })
    .then((data) => {
      if (!data.driverId) {
        if (driverIdPollCount > 5) {
          console.log("ending poll: ", data);
        } else {
          driverIdPollCount += 1;
          console.log(driverIdPollCount);
          setTimeout(() => {
            driverIdPoll(proxy, token, rideRequestId);
          }, 5000);
        }
      } else {
        console.log("posting message: ", data);
        postMessage(data);
      }
    });
}

/*
event.data format:
typeString, token, url, param1, param2, ...
*/
onmessage = (event) => {
  console.log(event);

  if (event.data.typeString === "BEFORE RIDE ACCEPTED") {
    driverIdPoll(event.data.proxy, event.data.token, event.data.param1);
  }

  if (event.data.typeString === "RIDE ACCEPTED, BEFORE PICKUP") {
    // create this function
    // update passenger coords and read driver coords until some sort of trigger
    // figure out how to stop the poll, what will be the trigger?
  }
};
