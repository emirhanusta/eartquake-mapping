import React, { useState, useEffect } from "react";
import axios from "axios";
import TextField from "@mui/material/TextField";
import Box from "@mui/material/Box";
import Button from "@mui/material/Button";

export default function EarthquakeForm() {
  const [earthquake, setEarthquake] = useState({
    latitude: "",
    longitude: "",
    magnitude: "",
  });

  const [intervalId, setIntervalId] = useState(null);
  const [isScriptRunning, setIsScriptRunning] = useState(true);

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
    const intervalId = setInterval(generateRandomData, 5000);
    setIntervalId(intervalId);
    return () => clearInterval(intervalId);
  }, []);

  const stopScript = () => {
    clearInterval(intervalId);
    setIsScriptRunning(false);
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
    <Box
      component="form"
      onSubmit={handleSubmit}
      sx={{
        display: "flex",
        flexDirection: "column",
        gap: "20px",
        maxWidth: "400px",
        margin: "auto",
      }}
    >
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
      <Button type="submit" variant="contained">
        Ekle
      </Button>
      <Button
        variant="contained"
        onClick={stopScript}
        disabled={!isScriptRunning}
      >
        Scripti Durdur
      </Button>
    </Box>
  );
}
