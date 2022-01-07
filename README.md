# Simple Blog Showcase with Quarkus Qute
![](example.gif)

## Including
- Responsive layout with Tailwind CSS
- Optimized UX with Unpoly (for partial page-loads)
- Authentication and authorization with a JWT-session-token

## Open Topics:  
- [ ] Optimization of form-validation.
- [ ] Using Unpoly and Tailwind CSS via webjars instead of CDN?
- [ ] Expired JWT is not removed from Cookies. (Check following Discussion for Hints to fix: https://groups.google.com/g/quarkus-dev/c/voY2Hch5IgI)

## Setup of the Keys for the JWT Part
To test the application you need a key-pair for the authentification with the JWT-session-token.
For this you can for example once Start the KeyGeneratorHelper-Class.

## Start in Dev-Mode
To get the app running you need a MYSQL-Database. You can 
```
docker run --name qute-blog-mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=vs4tw -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbuser -e MYSQL_DATABASE=qute-blog -d mysql:5.7.28
./mvnw quarkus:dev
```
http://localhost:8080

## Or start with docker-compose

    ./mvnw clean package
    docker-compose up --build

http://localhost