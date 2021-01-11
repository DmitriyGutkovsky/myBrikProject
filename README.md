# myBrikProject

online-shop for hats. Two type of orders: 
 - standard order (purchase from stock) 
 - individual order (client choose product and textile for it by himself )

1) project separated into twp modules: comon and api

2) used  Spring Data 

3) 8 tables with entities for business logic: m_goods
                                              m_individual_order
                                              m_price_for_individual_order
                                              m_product_type
                                              m_roles
                                              m_standard_order
                                              m_textile
                                              m_users
4) for all entities were implemented standard CRUD operations: findAll, findById, delete, create, update 

5) in the project was implemented Swagger API

6) for all entities in controllers were implemented additional requests into database (from 3 to 7), 
Application sends email-confirmation after registration.

7) Flyway

8) Authorization: added security via token, separated admin and user endpoints and without registration (permitAll)

9) Hosting Spring Boot Application: ngrok - while checking, please let me know - I will switch it on, for your convenience 

10) Spring Caches (Caffeine): "goods", "textile"

11) Spring Converters: for all entities (create/update operations)