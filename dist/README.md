# CS370 Team 13 Deliverable 2
### Testing the Containerized REST Server

*by Dorian Ferrer, Indy Hosier, & Andrew Jelkin*

<br>

## Description

In this deliverable, our team developed a simple web server using the Node.js and express.js JavaScript libraries.

At the moment, the server accepts **GET** and **POST** requests only.  In each request, the request body is outputted to standard output, and a confirmation message is sent in the response.

To test the deliverable for yourself, proceed to the **Usage** section.

<br>

## Usage

#### Initial Setup

1. Make sure that your environment has `Docker` installed, this is the only dependency for your machine.
2. In a terminal, navigate to the `rest-server/` directory.  This is the location of the scripts to *build*, *run*, and *stop* the web server.

#### Build Server Image

From the `rest-server/` directory, run:

```
./build.sh
```

This will simply build the docker image on your machine.

#### Run Server Container

From the `rest-server/` directory, run:

```
./run.sh
```

This will run a docker container of the rest-server image, and log the container output to standard output.

#### Send GET or POST Request

After you see the output as follows:

```
Simple REST Server listening on port 8888:
```

you are free to send GET and POST requests to port 8888.  I prefer to use [Postman](https://www.postman.com/), however **curl** also works with the following example request:

```
curl --header "Content-Type: application/json" \
     --request POST \
     --data '{"message": "hello"}' \
     http://localhost:8888
```

With each request, the body will be outputted to standard output, and a confirmation response will be sent!

#### Stop Server Container

In the same terminal session, interrupt the docker logging using *Ctrl-C* and then run:

```
./stop.sh
```
