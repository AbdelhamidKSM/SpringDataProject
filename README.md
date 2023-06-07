# SpringDataProject

## Project Entities

This project consists of the following entities:

### Employee
**Description:** Represents an employee.

**Relationships:**
- Many-to-One with Departement
- One-to-One with Folder
- Embedded Contact

### StandardEmployee
**Description:** Represents a standard employee.

**Relationships:**
- Inherits the relationships from the Employee class

### SpecialEmployee
**Description:** Represents a special employee.

**Relationships:**
- Inherits the relationships from the Employee class

### Departement
**Description:** Represents a department.

**Relationships:**
- One-to-Many with Employee
- Many-to-One with Country

### Country
**Description:** Represents a country.

**Relationships:**
- One-to-Many with Departement

### Folder
**Description:** Represents a folder.

**Relationships:**
- One-to-One with Employee

## Spring Data

This project utilizes Spring Data, which simplifies data access and provides a consistent programming model for interacting with various data sources. Here's an overview of the key concepts and features covered in Spring Data:

- **Repository Pattern and CRUD Operations:** Spring Data promotes the use of the Repository pattern for data access, providing CRUD operations for entities without writing explicit queries.
- **Entity Mapping and Relationships:** Spring Data simplifies entity mapping using annotations like `@Entity`, `@Table`, and supports various types of relationships such as one-to-one and one-to-many.
- **Query Methods:** Spring Data generates queries based on method names by following a predefined naming convention, allowing retrieval of data using conditions, sorting, and pagination.
- **Custom Queries:** In addition to query methods, custom queries can be written using annotations like `@Query` to define JPQL, SQL, or native queries for more complex requirements.
- **Paging and Sorting:** Spring Data supports paging and sorting of results using the `Pageable` and `Sort` interfaces, making it easy to retrieve data in a paginated manner with specific sorting criteria.
- **Modifying Queries:** Spring Data allows performing modifying queries for updating or deleting entities, using annotations like `@Modifying` and `@Query` to define custom queries for modifying operations.
- **Transactions:** Spring Data integrates with Spring's transaction management, enabling declarative transactional support for data access operations.
- **Integration with JPA and Hibernate:** Spring Data seamlessly integrates with the Java Persistence API (JPA) and Hibernate as the underlying ORM framework, providing additional features and simplifying data access.
- **Testing with Spring Data:** Spring Data provides utilities and annotations for testing data access code, making it easier to write unit tests and ensure the correctness of data access logic.

