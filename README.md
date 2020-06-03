# Team-C-P2-API
```
AUTHENTICATE 
[POST] /auth : Authenticates user 

USERS 
[GET] /users : Gets all users 
[GET] /users/{id} : Gets user {id} 
[GET] /users/{id}/tickets : Gets all the users tickets with the specified author {id}
[POST] /users : Posts a user given a JSON with user properties
e.g:
    {
        "username": "{String}",
        "password": "{String}",
        "firstName": "{String}",
        "lastName": "{String}",
        "email": "{String}"
    }

[DELETE] /users/{id} : Deletes a user with serial {id} 
[PUT] /users : Updates a user given a JSON with user properties 
e.g:
    {
        "id": {int},
        "username": "{String}",
        "password": "{String}",
        "firstName": "{String}",
        "lastName": "{String}",
        "email": "{String}"
    }


TICKETS 
[GET] /tickets : Gets all tickets 
[GET] /tickets/{id} : Gets ticket {id} 
[POST] /tickets/ : Posts a ticket given a JSON with order properties
e.g:
    {
        "cost": {float},
        "origin": "string",
        "destination": "string",
        "departuretime": "MO-DY-YEAR",
        "arrivaltime": "MO-DY-YEAR",
        "author_id": {int}
    }

[PUT] /tickets/ : Updates a ticket given a JSON with order properties 
e.g:
    {
        "id": {int},
        "cost": {float},
        "origin": "string",
        "destination": "string",
        "departuretime": "MO-DY-YEAR",
        "arrivaltime": "MO-DY-YEAR",
        "author_id": {int}
    }


[DELETE] /tickets/{id} : Deletes a ticket at {id} 
 ```
