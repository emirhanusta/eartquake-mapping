import React, { useState, useEffect } from "react";
import axios from "axios";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { Icon } from "leaflet";

const customMarker = new Icon({
  iconUrl: "https://cdn-icons-png.flaticon.com/512/14831/14831599.png",
  iconSize: [41, 41],
  iconAnchor: [10, 41],
  popupAnchor: [2, -40],
});

function Map() {
  const [markers, setMarkers] = useState([]);
  const [seconds, setSeconds] = useState(5);
  const [magnitude, setMagnitude] = useState(4.0);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/earthquakes/${seconds}/${magnitude}`
        );
        const data = response.data;
        const newMarkers = data.map((earthquake, index) => {
          return {
            key: index,
            position: [earthquake.latitude, earthquake.longitude],
            content: `Büyüklük: ${earthquake.magnitude}`,
          };
        });

        setMarkers(newMarkers);
      } catch (error) {
        console.error("Deprem verileri alınamadı:", error);
      }
    };

    const intervalId = setInterval(fetchData, 3000); // Her 3 saniyede bir verileri kontrol et
    return () => clearInterval(intervalId);
  }, []);

  return (
    <MapContainer center={[41.18860601085291, 36.34305733463444]} zoom={2}>
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
      {markers.map((marker) => (
        <Marker key={marker.key} position={marker.position} icon={customMarker}>
          <Popup>{marker.content}</Popup>
        </Marker>
      ))}
    </MapContainer>
  );
}

export default Map;
