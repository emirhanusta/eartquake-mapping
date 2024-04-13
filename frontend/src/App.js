import "./styles.css";
import "leaflet/dist/leaflet.css";
import EarthquakeForm from "./EarthquakeForm";
import Map from "./Map";
import { Grid } from "@mui/material";

export default function App() {
  return (
    <Grid container spacing={2}>
      <Grid
        item
        xs={2}
        className="form"
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <EarthquakeForm />
      </Grid>
      <Grid
        item
        xs={10}
        className="map"
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
        }}
      >
        <Map />
      </Grid>
    </Grid>
  );
}
