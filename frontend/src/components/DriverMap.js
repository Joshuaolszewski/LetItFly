import { useEffect } from "react";
import "../css/Home.css";
import { createMap } from "./MapUtilities";
import { createMarker } from "../components/MapUtilities";

function DriverMap({ currentMap, userLocation }) {
  useEffect(() => {
    if (!currentMap.current) {
      initMap();
    }
  });
  function initMap() {
    userLocation.then((location) => {
      const map = createMap(
        document.getElementsByClassName("map-container")[0],
        location
      );
        currentMap.current = map;
        // make marker
        const marker = createMarker(
            map
        );
        //click event
        marker.addListener("click", function () {
            // Place your custom code here
            alert('Marker was clicked!');

            // For example, to center the map at the marker's location:
            map.setCenter(marker.getPosition());
        });
    });
    }


  // initMap();

  return (
    <>
      <div className="map-container" />
    </>
  );
}

export default DriverMap;
