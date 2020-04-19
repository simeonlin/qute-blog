# vs-blog project
## TODO:
- Sicherheit vertiefen:
https://stormpath.com/blog/where-to-store-your-jwts-cookies-vs-html5-web-storage  
https://planet.jboss.org/post/jax_rs_and_httponly_flag_in_cookies  
- Expired JWT in Cookie braucht saubere meldung und entfernung von Cookie. Ev. abhängig von folgendem Issue:  
https://github.com/quarkusio/quarkus/issues/7502  
-Ev docker-compose prüfen/testen  

## Applikation starten
```
docker run --name vsblog-mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=vs4tw -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbuser -e MYSQL_DATABASE=vsblog -d mysql:5.7.28
./mvnw quarkus:dev
```
http://localhost:8080