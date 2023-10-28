import { useEffect } from "react";
import "../css/Home.css";
import { createMap } from "./MapUtilities";
import { createMarker } from "../components/MapUtilities";

function Map({ currentMap, userLocation }) {
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
    });
  }

  // initMap();

  return (
    <>
      <div className="map-container" />
    </>
  );
}

export default Map;
