# Team-C-P2-API

AUTHENTICATE <br />
[POST] /auth : Authenticates user <br />

USERS <br />
[GET] /users : Gets all users  <br />
[GET] /users/{id} : Gets user {id} <br />
[GET] /users/{id}/tickets : Gets all the users tickets with the specified author {id} !!NOT IMPLEMENTED!! <br />
[POST] /users : Posts a user given a JSON with user properties <br />
[DELETE] /users/{id} : Deletes a user with serial {id} <br />
[PUT] /users : Updates a user given a JSON with user properties <br />
 <br />
TICKETS <br />
[GET] /tickets : Gets all tickets <br />
[GET] /tickets/{id} : Gets ticket {id} <br />
[POST] /tickets/{id} : Posts a ticket at {id} given a JSON with order properties <br />
[PUT] /tickets/ : Updates a ticket given a JSON with order properties <br />
[DELETE] /tickets/{id} : Deletes a ticket at {id} <br />
 
