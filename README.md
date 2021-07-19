# productManager

![example workflow](https://github.com/nicolasllk/productManager/actions/workflows/build.yml/badge.svg)


A simple application that allows to manage products.

Functionality provided:

* Add new products
* Update existing products
* Delete existing products
* Find all products (with and without pagination/sorting)
* Find all products by name (missing paginatio/sorting capabilities)

## Usage

* New Product:

`curl --location --request POST 'localhost:8080/product' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Gaming laptop",
    "description": "brand new",
    "weight": 8.04,
    "price": 94200.99,
    "country": "United States"
}'`

* Delete Product

`curl --location --request DELETE 'localhost:8080/product/10' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw ''`

* Update Product

`curl --location --request PUT 'localhost:8080/product/2' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw '{
        "id": 2,
        "name": "Iphone X",
        "description": "Used iphone black",
        "weight": 8.04,
        "price": 1300.99,
        "country": "United States"
    }'`
    
* Get all products

`curl --location --request PUT 'localhost:8080/product/' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw ''`

* Get Products with pagination and sorting

`curl --location --request GET 'localhost:8080/product?page=0&size=2&order=asc' \
--header 'Accept: application/json' \
--header 'Content-Type: application/json' \
--data-raw ''`
