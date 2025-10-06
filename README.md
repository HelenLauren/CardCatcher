# CardCatcher

CardCatcher is a collectible trading card (TCG) collection management system. It allows users to organize cards, create custom categories, manage personal collections, and share collections with other users.

---

## 📌 Features

- Full CRUD for cards (create, read, update, delete)
- Full CRUD for collections
- Integration between cards and collections CRUDs
- Authentication and authorization using JWT
  - Roles: **Admin** and **User**
- Data persistence with MySQL
- Standardized error handling with coded messages
- Share collections via link
- Frontend developed with Vue.js + Quasar
- Backend developed with Java Spring Boot

---

## 🛠️ Technologies Used

- **Backend:** Java 17, Spring Boot, Maven
- **Frontend:** Vue.js, Quasar Framework
- **Database:** MySQL
- **Security:** JWT (JSON Web Token)
- **Version Control:** Git/GitHub
- **Other Dependencies:** Lombok, Spring Data JPA, Axios, etc.

---

## 🚀 Installation and Running

### Prerequisites

- Java 17 or higher
- Node.js 18 or higher
- MySQL 8 or higher
- Maven
- Git

### Steps

1. **Create the MySQL database**:

```sql
CREATE DATABASE cardcatcher;
```

2. Clone the repository:

```bash
git clone https://github.com/HelenLauren/CardCatcher.git
cd CardCatcher
```

3. Backend:

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

4. Frontend:

```bash
cd frontend
npm install
quasar dev
```

5. Access the application in your browser at http://localhost:8080 (or the port defined in Quasar).
Note: The application will automatically create tables in the cardcatcher database based on the entity classes.