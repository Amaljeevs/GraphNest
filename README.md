# GraphNest – Spring Boot GraphQL Demo

GraphNest is a **Spring Boot 3 + GraphQL** demo project using H2 database, designed to showcase **CRUD operations for multiple entities** (User, Product, Category, Order) with a **common ServerResponse wrapper** for consistent API responses.

It demonstrates how to build **scalable, high-throughput GraphQL APIs** with Spring Boot and use a generic response pattern.

---

## **Features**

* CRUD operations for **User, Product, Category, and Order**.
* GraphQL endpoints with **queries and mutations**.
* Common response object (`ServerResponse`) including:

  * `code` – HTTP-like status code
  * `message` – Status message
  * `details` – Payload (list or single object)
* H2 in-memory database for easy setup.
* Spring Boot + Lombok + JPA.
* Easily extendable for other entities.

---

## **Technologies**

* Java 17
* Spring Boot 3.5
* Spring Boot GraphQL
* GraphQL Java
* H2 Database
* Lombok
* Maven

---

## **Project Structure**

```
GraphNest
│
├── src/main/java/com/antspace/GraphNest
│   ├── models/          # JPA Entities
│   ├── repository/      # Spring Data JPA Repositories
│   ├── service/         # Business logic
│   ├── resolver/        # GraphQL Resolvers (Queries & Mutations)
│   └── GraphNestApplication.java
│
├── src/main/resources
│   ├── application.yml  # Spring Boot configuration
│   └── graphql/
│       ├── user.graphqls
│       ├── product.graphqls
│       ├── category.graphqls
│       └── order.graphqls
│
└── pom.xml
```

---

## **GraphQL Queries & Mutations**

Example GraphQL queries for **User**:

**Query all users**

```graphql
query {
  users(search: "") {
    code
    message
    details {
      id
      name
      email
      role
    }
  }
}
```

**Add a new user**

```graphql
mutation {
  addUser(name: "John Doe", email: "john@example.com", role: "ADMIN") {
    code
    message
    details {
      id
      name
      email
      role
    }
  }
}
```

> Similar queries and mutations exist for Product, Category, and Order.

---

## **Setup Instructions**

1. Clone the repository:

```bash
git clone https://github.com/<your-username>/GraphNest.git
cd GraphNest
```

2. Build and run:

```bash
mvn clean install
mvn spring-boot:run
```

3. Access GraphQL Playground at:

```
http://localhost:8080/graphiql
```

---

## **License**

This project is open-source and free to use.
