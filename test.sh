curl --header "Content-Type: application/json" --request POST --data '{"credit": 2000, "name": "TEODIO"}' http://localhost/api/customer/
curl --header "Content-Type: application/json" --request POST --data '{"stock": 20, "name": "TEODIO", "price": 20.4}' http://localhost/api/product
curl --header "Content-Type: application/json" --request POST --data '{"customerId": 1, "productId": 2, "units": 1}' http://localhost/api/order
