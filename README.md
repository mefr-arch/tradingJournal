# Trading Journal
A trading journal for logging trades, calculating PnL, and retrieving performance stats.


## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- H2
- Gradle
- Swagger/OpenAPI

## Features

- Create, view, update, and delete trades
- Calculate PnL for long and short trades
- View trading stats: total trades, wins, losses, total PnL, win rate
- Request validation for required fields and positive numbers

- ## API Endpoints

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/trades` | Create a new trade |
| `GET` | `/trades` | Retrieve all logged trades |
| `GET` | `/trades/{id}` | Retrieve a specific trade by ID |
| `PUT` | `/trades/{id}` | Update an existing trade |
| `DELETE` | `/trades/{id}` | Delete a trade record |
| `GET` | `/trades/stats` | Retrieve aggregate performance statistics |


## Getting Started

### Prerequisites
- Java 21 SDK installed
- PostgreSQL
### Installation & Execution

1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/tradingJournal.git
   cd tradingJournal
   ```

2. Run the application using Gradle:
   ```bash
   # On Linux/macOS
   ./gradlew bootRun

   # On Windows
   gradlew.bat bootRun
   ```

## 📖 API Documentation & Testing

Once the application is running, you can access the interactive API docs to test the endpoints directly from your browser:

* **Local Server URL:** `http://localhost:8080`
* **Swagger UI Docs:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
