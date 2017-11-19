# Parental Control Service

Author: Pablo López Martínez

# Executing tests

```
    mvn test
```

# About the project

This project is a wildfly swarm project. The tests performed are as follows:

* DBMovieServiceTest: It contains integration database tests working without container.
* ParentalControlServiceTest: It contains mocked tests depending on the results received by MovieService.
