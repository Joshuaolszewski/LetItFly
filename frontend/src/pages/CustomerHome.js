import Header from "../components/Header";
import Map from "../components/Map";
import SearchBar from "../components/SearchBar";
import { useRef, useState } from "react";
import { useLocation } from "react-router-dom";

function CustomerHome() {
  const currentMap = useRef();
  const currentRoute = useRef();
  const {
    state: {
      tokenObject: { email: userEmail },
    },
  } = useLocation();

  const [distance, setDistance] = useState("0 mi");
  const [duration, setDuration] = useState("0 mins");
  const [cost, setCost] = useState("0$");

  // wrap user location in a promise
  const userLocation = new Promise((resolve, reject) => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        ({ coords: { latitude, longitude } }) => {
          resolve({ lat: latitude, lng: longitude });
        },
        (error) => {
          console.log(error);
        },
        { enableHighAccuracy: true, maximumAge: 1, timeout: 15000 }
      );
    } else {
      reject(null);
    }
  });

  return (
    <>
      <Header userEmail={userEmail} />
      <SearchBar
        currentMap={currentMap}
        userLocation={userLocation}
        currentRoute={currentRoute}
        setDistance={setDistance}
        setDuration={setDuration}
        setCost={setCost}
      />
      <Map currentMap={currentMap} userLocation={userLocation} />
      <div className="route-details">
        <h2>Distance: {distance}</h2>
        <h2>Duration: {duration}</h2>
        <h2>Cost: {cost}</h2>
      </div>
    </>
  );
}

export default CustomerHome;
