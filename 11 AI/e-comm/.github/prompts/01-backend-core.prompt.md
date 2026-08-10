---
description: "Generate Spring Boot Backend with Security, JWT, Admin Seeding, and Business Domain"
---
Refer to #file:docs/domain-and-database.md and #file:docs/api-contracts.md and #file:.github/copilot-instructions.md.

Task: Generate the complete Spring Boot backend structure.

Requirements:
1. Setup JPA Entities for User, Product, CartItem, Order, OrderItem, and Invoice.
2. Implement Spring Security configuration with stateless JWT filter (`JwtAuthenticationFilter`), `AuthenticationManager`, and `BCryptPasswordEncoder`.
3. Create a `DataInitializer` bean that runs on app start to auto-create the Admin account (`admin` / `Admin@12345`).
4. Implement REST Controllers for Auth, Inventory Management, Cart Operations, and Checkout Processing.
5. In Checkout service: Implement mock payment handling, stock deduction, order entity creation, and automated invoice generation.
6. Create an Admin Reporting service returning aggregated sales metrics and order history.