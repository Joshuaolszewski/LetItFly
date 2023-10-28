import { useEffect } from "react";
import "../css/Home.css";
import { autocomplete, geocode, getDirections } from "./MapUtilities";
import { useLocation } from "react-router-dom";

function SearchBar({
  currentMap,
  userLocation,
  currentRoute,
  setDistance,
  setDuration,
  setCost,
}) {
  const location = useLocation();
  useEffect(() => {
    var inputElement = document.getElementsByClassName("search-bar")[0];
    autocomplete(inputElement, () => {
      geocode(inputElement.value, geocodeCallback);
    });
  });

  function geocodeCallback(results, status) {
    if (status === "OK") {
      var destinationLocation = {
        lat: results[0].geometry.location.lat(),
        lng: results[0].geometry.location.lng(),
      };
      userLocation.then((userLocation) => {
        getDirections(
          userLocation,
          destinationLocation,
          currentMap.current,
          currentRoute,
          setDistance,
          setDuration,
          setCost
        );
      });
    }
  }

  function goButtonClickHandler() {
    if (!currentRoute.current) {
      return alert("Please choose a destination!");
    }

    const proxy = process.env.REACT_APP_BACKEND_BASE_URL;

    // send a POST request to backend
    const url = `${proxy}/api/ride-request/save`;
    console.log(currentRoute.current);
    console.log(location.state.tokenObject.token);
    fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + location.state.tokenObject.token,
      },
      body: JSON.stringify({
        curLat: currentRoute.current.startLat,
        curLong: currentRoute.current.startLng,
        destLat: currentRoute.current.endLat,
        destLong: currentRoute.current.endLng,
        passengerId: location.state.tokenObject.id,
      }),
    })
      .then((response) => {
        console.log(response);
        return response.json();
      })
      .then((data) => {
        console.log("returned data: ", data);
      });
  }

  return (
    <div className="search-bar-wrapper">
      <input
        className="search-bar"
        type="text"
        placeholder="Enter address..."
      />
      <button id="go-button" onClick={() => goButtonClickHandler()}>
        START
      </button>
    </div>
  );
}

export default SearchBar;
