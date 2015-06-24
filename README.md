# tech-conference-demo
## Phone Normalization Service

Demonstration of building a service in Java from scratch.

The demo builds a simple phone normalization service that uses non-blocking I/O and asynchronous request handling.  It does the following:

* Creates a single executable that is containerization ready
* Uses asynchronous request handling for [high request concurrency](http://callistaenterprise.se/blogg/teknik/2014/04/22/c10k-developing-non-blocking-rest-services-with-spring-mvc/)
* Takes advantage of [Ribbon](https://github.com/Netflix/ribbon) with [netty-io](http://netty.io/) for non-blocking external dependency I/O
* Takes advantage of [Hystrix](https://github.com/netflix/hystrix) for dependency monitoring, circuit-breaking, and fall-backs.
* Provides monitoring end-points using [Spring Boot Actuator](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#production-ready)

Run the project locally:

1. Build with maven: `mvn clean install`
2. Navigate to the `/web` directory
3. Run with the application: `mvn spring-boot:run`

Once the demo is running, you can access the following:

* Basic phone normalization request: [http://localhost:8080/api/phones/801-234-5678](http://localhost:8080/api/phones/801-234-5678)
* Basic normalized user request: [http://localhost:8080/api/users/3](http://localhost:8080/api/users/3)
* API documentation: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* [Hystrix dashboard](http://localhost:8080/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8080%2Fhystrix.stream)
* Performance monitoring endpoint: [http://localhost:8080/metrics](http://localhost:8080/metrics)

The demo stops short of adding framework logging or building a WAR file.  A sample project with these features can be [found in Stash](https://stash.myfamilysouth.com/projects/JDC/repos/demo-service-spring/browse).

A [Java Development Guide](http://confluence.myfamilysouth.com/display/JDC/Java+Development+Guide) can be found in Confluence.