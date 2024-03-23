import React, { useState, useEffect } from "react";
import axios from "axios";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";
import { FormGroup } from "@mui/material";

export default function EarthquakeForm() {
  const [earthquake, setEarthquake] = useState({
    latitude: "",
    longitude: "",
    magnitude: "",
  });

  const [intervalId, setIntervalId] = useState(null);

  const generateRandomData = () => {
    const latitude = (Math.random() * (85 - -85) - 85).toFixed(6);
    const longitude = (Math.random() * (180 - -180) - 180).toFixed(6);
    const magnitude = (Math.random() * 10).toFixed(2);

    // Yeni deprem verilerini oluştur
    const newEarthquake = { latitude, longitude, magnitude };

    // Üretilen değerleri RESTful bir servise gönderme
    axios.post("http://localhost:8080/api/v1/earthquakes", newEarthquake, {
      headers: {
        "Content-Type": "application/json",
      },
    });

    console.log("Yeni deprem verisi oluşturuldu: ", newEarthquake);
  };

  useEffect(() => {
    const intervalId = setInterval(generateRandomData, 5000); // 5 saniyede bir yeni deprem verisi oluştur
    setIntervalId(intervalId);
    return () => clearInterval(intervalId);
  }, []);

  const stopScript = () => {
    clearInterval(intervalId);
    console.log("Script durduruldu.");
  };

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      await axios.post("http://localhost:8080/api/v1/earthquakes", earthquake, {
        headers: {
          "Content-Type": "application/json",
        },
      });

      console.log("Deprem başarıyla eklendi!", earthquake);
      setEarthquake({ latitude: "", longitude: "", magnitude: "" });
    } catch (error) {
      alert("Deprem eklenirken bir hata oluştu!");
    }
  };

  return (
    <>
      <Box
        component="form"
        onSubmit={handleSubmit}
        sx={{ "& > :not(style)": { m: 1, width: "25ch" } }}
      >
        <FormGroup>
          <TextField
            id="latitude"
            label="Enlem"
            variant="outlined"
            value={earthquake.latitude}
            onChange={(event) =>
              setEarthquake({ ...earthquake, latitude: event.target.value })
            }
          />
          <TextField
            id="longitude"
            label="Boylam"
            variant="outlined"
            value={earthquake.longitude}
            onChange={(event) =>
              setEarthquake({ ...earthquake, longitude: event.target.value })
            }
          />
          <TextField
            id="magnitude"
            label="Büyüklük"
            variant="outlined"
            value={earthquake.magnitude}
            onChange={(event) =>
              setEarthquake({ ...earthquake, magnitude: event.target.value })
            }
          />
        </FormGroup>
        <Button type="submit" variant="contained">
          Ekle
        </Button>
      </Box>
      <Button variant="contained" onClick={stopScript}>
        Scripti Durdur
      </Button>
    </>
  );
}
