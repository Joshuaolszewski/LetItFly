import { Loader } from "@googlemaps/js-api-loader";

// reusable loader with api key
const googleApiLoader = new Loader({
  apiKey: process.env.REACT_APP_GOOGLE_KEY,
});

/**
 * Given a destination string, return the lat and lng coords
 * @param {String} destinationString
 */
function geocode(destinationString, callbackFunction) {
  googleApiLoader.importLibrary("geocoding").then(({ Geocoder }) => {
    var geocoder = new Geocoder();
    geocoder.geocode({ address: destinationString }, callbackFunction);
  });
}

// Expectation: given an input element, return set of destination coords
function autocomplete(inputElement, callback) {
  // prioritize search results in these bounds
  const bayAreaBounds = {
    north: 37.96328887, //37.96328887243628
    south: 37.23556706, //37.235567063362325
    east: -121.61026004, //-121.61026004010768
    west: -122.66290189, //-122.6629018966861
  };

  // search options
  const options = {
    bounds: bayAreaBounds,
    componentRestrictions: { country: "us" },
    fields: ["geometry"],
    type: ["airport", "geocode", "street_address", "street_number"],
  };

  googleApiLoader.importLibrary("places").then(({ Autocomplete }) => {
    var autocomplete = new Autocomplete(inputElement, options);
    autocomplete.addListener("place_changed", callback);
  });
}

/**
 *
 * @param {lat: current_latitude, lng: current_longitude} currentLocation
 * @param {lat: destination_latitude, lng: destination_longitude} destinationLocation
 * @param {Promise} currentMap
 * @returns
 */
var directionsRenderer;
var directionsService;
function getDirections(
  currentLocation,
  destinationLocation,
  currentMap,
  currentRoute,
  setDistance,
  setDuration,
  setCost
) {
  googleApiLoader
    .importLibrary("routes")
    .then(({ DirectionsService, DirectionsRenderer }) => {
      if (!directionsService) {
        directionsService = new DirectionsService();
      }
      if (!directionsRenderer) {
        directionsRenderer = new DirectionsRenderer();
        currentMap.then((map) => {
          directionsRenderer.setMap(map);
        });
      }

      var request = {
        origin: currentLocation,
        destination: destinationLocation,
        travelMode: "DRIVING",
      };

      directionsService.route(request, (results, status) => {
        if (status === "OK") {
          directionsRenderer.setDirections(results);
        } else console.log("Directions Failed: ", status);
        currentRoute.current = {
          distance: results.routes[0].legs[0].distance.text,
          duration: results.routes[0].legs[0].duration.text,
          cost: (
            (Number(results.routes[0].legs[0].distance.value) / 1609.34 - 2) /
              5 +
            15
          ).toFixed(2),
          startLat: results.routes[0].legs[0].start_location.lat(),
          startLng: results.routes[0].legs[0].start_location.lng(),
          endLat: results.routes[0].legs[0].end_location.lat(),
          endLng: results.routes[0].legs[0].end_location.lng(),
        };
        setDistance(currentRoute.current.distance);
        setDuration(currentRoute.current.duration);
        setCost(currentRoute.current.cost);
      });
    });
}

function createMap(mapContainer, centerCoords) {
  const mapOptions = {
    center: centerCoords,
    zoom: 17,
    disableDefaultUI: true,
    clickableIcons: false,
  };

  return googleApiLoader.importLibrary("maps").then(({ Map }) => {
    var map = new Map(mapContainer, mapOptions);
    return map;
  });
}


function createMarker(map) {
    return googleApiLoader.importLibrary("marker").then(({ Marker }) => {
        const image =
            "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png";

        map.then(( actualMap ) => {
            const marker = new Marker({
                position: { lat: 37.35646273790564, lng: -121.89294433357483 },
                map: actualMap,
                icon: image,
            });
            //marker.setMap(map);
            //console.log("Marker", marker);
            //console.log("THIS IS THE MAP qweeqw: ", map);
            return marker;
        })

    });
}

export { googleApiLoader, autocomplete, geocode, createMap, createMarker, getDirections };
