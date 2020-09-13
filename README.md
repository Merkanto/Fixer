# Fixer
Fixer is a web application that collects exchange rates data from the provider Fixer.io, stores it in the local database and accumulates reports for specific periods on the data.

1. Change DB username and DB password in application.properties
2. Change API Key for Fixer.io in application.properties
3. Create tables in DB from SQL queries in sql_queries.txt (src/main/resources/static/)
