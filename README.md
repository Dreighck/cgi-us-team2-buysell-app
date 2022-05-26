### Team Members
- Drake Menard    :: Team Lead
- Aarion Ojeda    :: Backend
- Quentin Hewitt  :: Backend
- Kristen Wilson  :: Frontend
### List of Microservices Identified 
- ProductService      :: Drake
- CartService         :: Aarion
- userAccountService  :: Quentin
- ServiceRegistry     :: 
- GatewayService      :: Kristen
********************************* 
### Microservice - Product
#### Models 
- Model - Product
    - Attribute - 1: int (Primary key) id
    - Attribute - 2: String name
    - Attribute - 3: double price 
    - Attribute - 4: String description (Nullable)
    - Attribute - 5: Cart for OnetoOne mapping

#### Endpoints 
- /api/v1/products - GET PUT POST--this will display all current Products in the db that are for sale
- /api/v1/products/{prodID} - DELETE –-this will remove an item from the for sale database  


#### Models
- Model - User Profile (Account)

- user_id int (auto-generated) Primary Key
- username String (text, VARCHAR_200, required)
- password String (text, VARCHAR_200, required)
- firstName String (text, VARCHAR_200, required)
- lastName String (text, VARCHAR_200, required)

### Endpoints
- /api/v1/users/new - POST - Create a user
- /api/v1/users/{userID} - GET - Get the user with the given userID
- /api/v1/users - GET - Get all the users
- /api/v1/users/{userID} - PUT - Update the userID's profile info with the provided JSON
- /api/v1/users/{userID} - DELETE - Delete userID's profile 

#### Models
- Model - Cart ::incorporate the cart service with the transaction service::
    - Product the product that is in the cart
    - Long itemNumber the number of the item in the cart
    
#### Endpoints
- /api/v1/cart GET PUT POST --display items in cart
- /api/v1/checkout/ - DELETE –-this will delete the items from the cart AND the db when transaction is successful

    
    
********************************** 



