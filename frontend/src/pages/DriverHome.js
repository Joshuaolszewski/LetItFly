import Header from "../components/Header";
import DriverMap from "../components/DriverMap";
import History from "../components/History";
import SearchBar from "../components/SearchBar";
import { useRef } from "react";
import { useLocation } from "react-router-dom";
import { createMarker } from "../components/MapUtilities";

function DriverHome() {
    const currentMap = useRef();

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

    // const location = useLocation();
    // console.log("logging cookie:", location.state);


    return (
        <>
            <Header />
            <DriverMap currentMap={currentMap} userLocation={userLocation} />
            <History />
        </>
    );
}

export default DriverHome;
