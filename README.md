# Simple Blog Showcase with Quarkus Renarde with Qute, Unpoly and Tailwind CSS
![](example.gif)

## Including
- Responsive layout with Tailwind CSS
- Optimized UX with Unpoly (for partial page-loads)
- Simple Auth-Example with a JWT in a http-only Cookie

## Open Topics:  
- [ ] Test one-field form-validation with up-validate.
- [ ] DB from dev-services.
- [ ] Include Tailwind CSS in project instead of CDN
- [ ] Optimize paths in template (pages from subfolders are not loading images)
- [ ] Doc: Tailwind and Unpoly including, form-validation with unpoly, search on tipping with unpoly

## Issues with Renarde:  
- [ ] WARNING: duplicate route registered for Post.post ?
- [ ] ifError and Error not working directly ({#ifError it='title'})
- [ ] Why is @Blocking necessary?
- [ ] Include Qute-Extenstion -> Error

## Setup of the Keys for the JWT Part
To test the application you need a key-pair for the authentification with the JWT-session-token.
For this you can for example once Start the KeyGeneratorHelper-Class.

## Start in Dev-Mode
To get the app running you need a MYSQL-Database. You can 

    docker run --name qute-blog-mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=vs4tw -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbuser -e MYSQL_DATABASE=qute-blog -d mysql:5.7.28
    ./mvnw quarkus:dev

http://localhost:8080

## Or start with docker-compose

    ./mvnw clean package
    docker-compose up --build

http://localhost


