const net = require('net');
const express = require('express');


const HTTP_PORT = 8888;
const SOCKET_PORT = 5678;
const SOCKET_HOST = 'localhost';


const router = express();
router.use(express.json());

router.get('/trends/:location', (request, response) => {
  const socket = net.createConnection(SOCKET_PORT, SOCKET_HOST, () => {
    const tweetsRequest = 'trends ' + request.params.location + '\n';

    socket.write(tweetsRequest);
  });

  socket.on('data', (data) => {
    response.send(data);
  });
});

router.get('/tweets/:query', (request, response) => {
  const socket = net.createConnection(SOCKET_PORT, SOCKET_HOST, () => {
    const tweetsRequest = 'tweets ' + request.params.query + '\n';

    socket.write(tweetsRequest);
  });

  socket.on('data', (data) => {
    response.send(data);
  });
});

router.post('/*', (request, response) => {
  console.log(request.body);
  response.send('POST Received!');
});

router.listen(HTTP_PORT, () => {
  console.log(`Simple REST Server listening on port ${HTTP_PORT}:`);
});



