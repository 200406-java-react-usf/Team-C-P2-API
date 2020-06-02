# Team-C-P2-API

AUTHENTICATE <br />
[POST] /auth : Authenticates user <br />

USERS <br />
[GET] /users : Gets all users  <br />
[GET] /users/{id} : Gets user {id} <br />
[GET] /users/{id}/tickets : Gets all the users tickets with the specified author {id} !!NOT IMPLEMENTED!! <br />
[POST] /users : Posts a user given a JSON with user properties <br />
{<br />
    "username": "{String}",<br />
    "password": "{String}",<br />
    "firstName": "{String}",<br />
    "lastName": "{String}",<br />
    "email": "{String}"<br />
}<br />
[DELETE] /users/{id} : Deletes a user with serial {id} <br />
[PUT] /users : Updates a user given a JSON with user properties <br />
{<br />
    "id": {int},<br />
    "username": "{String}",<br />
    "password": "{String}",<br />
    "firstName": "{String}",<br />
    "lastName": "{String}",<br />
    "email": "{String}"<br />
}<br />
 <br />
TICKETS <br />
[GET] /tickets : Gets all tickets <br />
[GET] /tickets/{id} : Gets ticket {id} <br />
[POST] /tickets/{id} : Posts a ticket at {id} given a JSON with order properties <br />
{<br />
    "cost": {float},<br />
    "origin": "string",<br />
    "destination": "string",<br />
    "departuretime": "MO-DY-YEAR",<br />
    "arrivaltime": "MO-DY-YEAR",<br />
    "author_id": {int}<br />
}<br />
[PUT] /tickets/ : Updates a ticket given a JSON with order properties <br />
{<br />
    "id": {int},<br />
    "cost": {float},<br />
    "origin": "string",<br />
    "destination": "string",<br />
    "departuretime": "MO-DY-YEAR",<br />
    "arrivaltime": "MO-DY-YEAR",<br />
    "author_id": {int}<br />
}<br />
[DELETE] /tickets/{id} : Deletes a ticket at {id} <br />
 
