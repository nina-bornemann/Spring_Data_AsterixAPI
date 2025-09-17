[![.github/workflows/maven.yml](https://github.com/nina-bornemann/Spring_Data_AsterixAPI/actions/workflows/maven.yml/badge.svg)](https://github.com/nina-bornemann/Spring_Data_AsterixAPI/actions/workflows/maven.yml)

## Asterix Characters API

A simple Spring Boot REST API to manage characters from the
Asterix universe.
Supports basic CRUD operations, filtering by attributes, and
is covered with unit/integration tests.

---

### 🚀 Features

- Get all characters
- Get character by ID
- Get characters by name (e.g. /asterix/characters/byName?name={name})
- Filter characters by age (exact, minimum or maximum)
- Add new characters (POST) and automatically add a random UUID
- Update existing characters (PUT) with DTO (ID cannot be changed)
- Delete characters by ID (DELETE)

---

### 🛠️ Tech Stack
- Java 24
- Spring Boot (Web, Data)
- MongoDB (Datenbank)
- Flapdoodle Embedded MongoDB (for integration tests)
- JUnit 5 & Mockito (testing)
- MockMvc (integration testing)
- Lombok
- In-memory repository (or database depending on config)

---

### ▶️ Running the Project

- Java 24+
- Maven (or use IntelliJ’s built-in support)

Run the application
> mvn spring-boot:run
- The API will be available at:
> http://localhost:8080/asterix/characters

---

### 📖 API Endpoints

Get all characters
> GET /asterix/characters

Get character by ID
> GET /asterix/characters/{id}

Get characters by name
> GET /asterix/characters/byName?name={name}

Get characters by age
> GET /asterix/characters?exactAge={age}  
> GET /asterix/characters?maxAge={age}  
> GET /asterix/characters?minAge={age}

Get average age by profession
> GET /asterix/characters/{profession}

Add new character
> POST /asterix/characters  
> Content-Type: application/json

Update existing character
> PUT /asterix/characters/{id}  
Content-Type: application/json

Delete character
>DELETE /asterix/characters/{id}  

---

### 🧪 Testing
Run all tests with:
> mvn test

Unit tests with Mockito for service logic

Integration tests with MockMvc for REST endpoints