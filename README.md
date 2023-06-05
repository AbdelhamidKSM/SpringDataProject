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
