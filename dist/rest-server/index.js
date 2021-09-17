const express = require('express');
const router = express();

const PORT = 8888;

router.use(express.json());

router.get('/*', (request, response) => {
  console.log(request.body);
  response.send('GET Received!');
});

router.post('/*', (request, response) => {
  console.log(request.body);
  response.send('POST Received!');
});

router.listen(PORT, () => {
  console.log(`Simple REST Server listening on port ${PORT}:`);
});
