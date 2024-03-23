import "./styles.css";
import "leaflet/dist/leaflet.css";
import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import { useState, useEffect } from "react";
import { Icon } from "leaflet";
import axios from "axios";

export default function App() {
  const customMarker = new Icon({
    iconUrl: "https://cdn-icons-png.flaticon.com/512/14831/14831599.png",
    iconSize: [41, 41],
    iconAnchor: [10, 41],
    popupAnchor: [2, -40],
  });

  const [markers, setMarkers] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/earthquakes/5/4"
        );
        const data = response.data;
        const newMarkers = data.map((earthquake) => {
          const marker = {
            position: [earthquake.latitude, earthquake.longitude],
            content: `Büyüklük: ${earthquake.magnitude}`,
            showMarker: true,
          };
          return marker;
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
    <MapContainer center={[41.18860601085291, 36.34305733463444]} zoom={0}>
      <TileLayer url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />{" "}
      {markers.map(
        (marker, index) =>
          marker.showMarker && (
            <Marker key={index} position={marker.position} icon={customMarker}>
              <Popup>{marker.content}</Popup>
            </Marker>
          )
      )}
    </MapContainer>
  );
}
