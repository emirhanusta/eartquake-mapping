## Introduction

This project is a web application that processes earthquake data from around the world in real time and displays anomalous earthquakes on a map.

## Technologies Used

- Java
- Spring Boot
- React
- PostgreSql
- Docker

## Screenshot

![screenshot 2024-05-27 152716](https://github.com/emirhanusta/eartquake-mapping/assets/83432342/e708d32a-6e7e-469c-a65b-ab462d9f993b)


## Setup

### Prerequisites

Make sure you have Docker on your machine:


### Build with Docker

1. Clone the repository:

    ```bash
    git clone https://github.com/emirhanusta/eartquake-mapping.git
    ```

2. Navigate to the project directory:

    ```bash
    cd eartquake-mapping
    ```

3. Run Docker Compose to start PostgreSQL, React and Spring Boot application:

    ```bash
    docker-compose up
    ```
## Usage

   - The web application is running at http://localhost:3000. You can view the application by directing your browser to this address

   - The script will generate earthquakes with magnitudes between 0 and 10, but only Anomalous earthquakes (above magnitude 4) will be shown as fixed on the map.

   - You can stop the script that adds random earthquake data by clicking on the "Scripti Durdur" button.

   - You can manually add earthquake data using the "Deprem Ekle" form.
    
