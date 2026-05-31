# StockIT
A Java-based inventory management system built to learn software architecture, JDBC, PostgreSQL, testing, and repository design.

# Features
- Product management
- Category management
- Stock tracking
- UUID-based entity identifiers
- PostgreSQL persistence
- JDBC repositories
- JUnit testing

# Architecture
This project follows a layered architecture to separate business logic from data access.
### System Architecture
<p align ="center">
  <img src="src/main/resources/SYS.drawio.svg" width="200"/>
</p>
The application is split into:

- **Models**: Core business objects such as `User` and `Product`
- **Services**: Business rules and validation
- **Repositories**: Data access logic
- **JDBC Implementations**: PostgreSQL-specific repository implementations
- **Database**: PostgreSQL tables

### Entity Relation Diagram (ERD)

<p align ="center">
  <img src="src/main/resources/ERD.drawio.svg" width="700"/>
</p>




## Development Roadmap

### 1. Domain Models
Created the core models such as `User`,`Product`, and `StockMovement` to represent the main business objects.

### 2. Service Layer
Added services to handle business rules, validation, and application logic separately from storage logic.

### 3. In-Memory Repositories
Started with list-based repositories to test the application logic without needing a database.

### 4. JDBC Repositories
Replaced the in-memory repositories with PostgreSQL-backed JDBC repositories using `PreparedStatement`, UUIDs, and row-to-object mapping.

### 5. Repository Testing
Added JUnit tests for saving, finding, listing, and deleting records from the database.

### 6. Next Steps
- Add product stock movement tracking
- Add transaction history
- Add reporting/analytics layer
- Later migrate to Spring Boot/JPA

# Testing
Repository Tests  
✓ Save entities  
✓ Find by ID  
✓ Delete entities  

Service Tests  
✓ Business rule validation  
✓ Duplicate prevention  
✓ Error handling