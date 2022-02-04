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
During the Start of the Service, a key-pair for the authentification with the JWT-session-token is generated.
Don't use this approach in productive Environment with scaling.

## Start in Dev-Mode
To get the app running you need a MYSQL-Database. You can 

    docker run --name qute-blog-mysql -p 3307:3306 -e MYSQL_ROOT_PASSWORD=vs4tw -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=dbuser -e MYSQL_DATABASE=qute-blog -d mysql:5.7.28
    ./mvnw quarkus:dev

http://localhost:8080

## Or start with docker-compose

    ./mvnw clean package
    docker-compose up --build

http://localhost

# How to use Tailwind CSS in a Quarkus-Renarde Project  

Install the Tailwind CLI over npm and execute a `init` according https://tailwindcss.com/docs/installation.  
Create a main-CSS file for example in `/src/main/resources/style.css` with the Tailwind directives and the style-definitions you like.  
Then you can build your CSS with the following command:

    npx tailwindcss -i ./src/main/resources/style.css -o ./src/main/resources/META-INF/resources/css/generated-tailwind.css

Integrate the CSS in your templates like this:

    <link href="/css/generated-tailwind.css" rel="stylesheet">
