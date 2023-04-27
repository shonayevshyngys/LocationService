# LocationService
Drei Test application

## prerequisites
Docker and Docker-Compose\
Maven\
Bash\
Free port 5432, 8000\

## Running the application
Github folder contains bash script start.sh
You can run it via terminal bash start.sh

## Documentation:

There are three calls you can make to a server:

### GET http://localhost:8000/location
This will return all locations created

### POST http://localhost:8000/location
With body 
{
  "name": "String",
  "latitude": double,
  "longitude": double,
  "type": "ENUM"  // ENUM [Standard. Premium] case sensetive
}
This will create a location and persist in db

### GET  http://localhost:8000/location/search
With optional parameters:

p1 - double, double
p2 - double, double 
both of the for specifying the rectange of coodrinates to search

type - ENUM // ENUM [Standard. Premium] case sensetive
this field will return locations only of specified type

page and size - pageable, you can specify limit and size of the page.
http://localhost:8000/location/search?p1=40.5,0.4&p2=50.7,10.0&type=Premium&page=0&size=3
