# ⚡ AlgoVoltix – EV Slot Booking Backend

AlgoVoltix is a backend service for **discovering, booking and paying for EV charging slots** at public charging stations.  
It models real-world actors (customers, station owners, stations, slots) and wraps them with a wallet + transaction system and configurable pricing.

> Tech focus: clean domain modelling, transactional booking, wallet accounting, and modular pricing.

---

## 🧠 Core Concepts

AlgoVoltix treats an EV station as a collection of **slots** (chargers).  
Each slot can be booked by exactly one customer for a given time range.

Main domain entities:

- **BaseUser** – common fields for all users.
- **Customer** – EV driver who books slots.
- **StationOwner** – owns one or more EV stations.
- **EvStation** – physical charging station with location + status.
- **StationSlot** – individual charger at a station.
- **Booking** – reservation of a slot by a customer.
- **Wallet** – one per user; stores current balance.
- **Payment** – external payment attempt for wallet top-up.
- **Transaction** – every credit/debit applied to a wallet.
- **RateCard** – price configuration for a slot.
- **PriceAlgorithm** – reusable pricing strategy used by multiple rate cards.

---

## ✨ Features (MVP)

- **User & Auth**
  - Register / login as Customer or StationOwner.
  - JWT-based authentication via Spring Security (Keycloak can be plugged in later).

- **Customer**
  - View profile and wallet balance.
  - Discover nearby stations based on location.
  - View station details, slots and pricing.
  - Book a specific slot for a time range.
  - Pay using wallet balance.
  - View booking & transaction history.

- **Station Owner**
  - Create and manage EV stations.
  - Add / update / disable station slots.
  - Configure rate cards for slots.
  - Attach price algorithms to rate cards.
  - View bookings and earnings (credits into wallet).

- **Wallet, Payments & Transactions**
  - One wallet per user.
  - Top-up wallet using external payments.
  - Every balance change stored as a `Transaction`.
  - Bookings create debit transactions; top-ups create credit transactions.

- **Pricing**
  - One active rate card per slot.
  - Multiple rate cards can reuse a single `PriceAlgorithm` (e.g. flat vs time-based).

---

## 🏗️ Architecture (High Level)

- **Spring Boot 3** backend.
- Layered design: `controller → service → repository`.
- JPA entities aligned with the ER/class diagram.
- REST APIs returning JSON.
- Stateless auth with JWT.
- Relational database (MySQL/PostgreSQL) for strong consistency.

> _Class/ER diagrams are available in the `docs/` folder (not committed yet in this template)._

---

## 🧰 Tech Stack

- **Language:** Java (17/21)
- **Framework:** Spring Boot
- **Security:** Spring Security + JWT
- **Persistence:** Spring Data JPA / Hibernate
- **Database:** MySQL or PostgreSQL
- **Build:** Maven or Gradle
- **Containerisation (optional):** Docker

Update this section once your exact versions are fixed.

---

## 📂 Project Structure (suggested)

```text
algo-voltix/
 ├─ src/main/java/com/algovoltix/evbooking
 │   ├─ controller/      # REST controllers
 │   ├─ service/         # Business logic
 │   ├─ service/impl/    # Service implementations
 │   ├─ repository/      # Spring Data repositories
 │   ├─ entity/          # JPA entities (BaseUser, Wallet, Booking, ...)
 │   ├─ dto/             # Request/response DTOs
 │   ├─ config/          # Security & app config
 │   └─ AlgoVoltixApplication.java
 ├─ src/main/resources/
 │   ├─ application.yml
 │   └─ db/migration/    # Flyway/Liquibase scripts (if used)
 ├─ docs/                # Diagrams, Scaler report artefacts
 ├─ pom.xml / build.gradle
 └─ README.md
