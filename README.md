# VORTECH API FILM

Proyecto Springboot para prueba Vortech

## Requisitos

	Java8
	Gradle 6.2.2

## Build

    gradle clean build

## Run the tests

    gradle test

## Run the app

    java -jar repositoryDirectory/build/libs/film-0.0.1.jar



# REST API

Servicios REST API-VORTECH-FILM, se compone de los siguientes servicios
1. Creación de Películas, asociandolo a registros de actores que ya existen en BD
2. Obtener todos ls registros de películas
3. Obtener detalle de una película específica.

## Crear película
###RequestParam


    actorsIds=1, 2...N - Array de enteros asociado a IDs que ya están registrados en la BD. en el data.sql hay del 1 al 10 actores diferentes
### Request

`POST /api/vortech/films/movies?actorsIds=1, 2...N`

    Host: localhost:8081
    Content-Type: application/json
    Accept: application/json
    Accept-Charset: UTF-8
    {
      "title" : "Buscando a Nemo",
      "genre" : "Animados",
      "year" : 2000,
      "oscarsWonNumber" : 9
    }

### Response - code 201

    {
        "data": {
            "id": 1,
            "title": "Buscando a Nemo",
            "genre": "Animados",
            "year": 2000,
            "oscarsWonNumber": 9,
            "actors": [
                {
                    "name": "Ramón Valdés"
                },
                {
                    "name": "Roberto Gómez Bolaños"
                }
            ]
        }
    }

## Obtener todas las películas

### Request

`GET /api/vortech/films/movies`

    GET /api/vortech/films/movies HTTP/1.1
    Host: localhost:8081

### Response - 202

    {
    "data": [
        {
            "title": "Buscando a Nemo",
            "year": 2000
        },
        {
            "title": "Toy Story",
            "year": 2002
        }
    ]
}

## Obtener una película especifica
###PathVariable
` /{movieId} :  Parámetro asociado al ID de la película`
### Request

`GET /api/vortech/films/movies/{movieId}`

    curl -i -H 'Accept: application/json' http://localhost:7000/thing/1

### Response - Code 202

    GET /api/vortech/films/movies/{movieId} HTTP/1.1
    Host: localhost:8081`

## Get - No existe movieId

### Response - Code 404

    {
    "data": {
        "errorMessage": "Película no encontrada: 22",
        "date": "2020-03-10T00:06:40.88"
    	}
	}


