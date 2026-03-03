# CAWAProject

Simple Java JSP/Servlet web app (JDBC) — package `cawa.war`.

Prerequisites
- Java 17 (compile target)
- GlassFish 7 (run server, domain1)
- MySQL (database `projetcawa`)

Quick start
1. Ensure MySQL is running and `projetcawa` schema exists with tables `client`, `article`, `facture`, `ligne_facture`.
2. Compile Java sources (example used in this project):

```bash
javac --release 17 -cp "<path-to>/jakarta.servlet-api.jar;build/web/WEB-INF/lib/mysql-connector-j-8.0.32.jar" -d build/web/WEB-INF/classes src/java/*.java
```

3. Build the WAR and deploy to GlassFish:

```bash
jar -cvf cawa.war -C build/web .
asadmin deploy --name cawa --contextroot cawa --force cawa.war
```

4. Open the app: http://localhost:8080/cawa/login.jsp

Notes
- JDBC in sources uses `jdbc:mysql://localhost/projetcawa?useSSL=false` and the root user (no password) — update credentials or configure a GlassFish JNDI `DataSource` for production.
- Project uses `jakarta.servlet` (GlassFish 7) and was compiled with `--release 17` for compatibility.
