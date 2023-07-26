

# Phrase API

Phrase API allows you to get text-related data that can be used for your frontend application (ex. A website or
web application).

Client Website: https://phraseapi.vercel.app

## Table of Contents

- [Introduction](#introduction)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Error Handling](#error-handling)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Introduction

Phrase API is a powerful RESTful API designed to supply frontend applications with a diverse range of text-related data. It offers an ever-expanding repository of motivational quotes, hilarious jokes, heartwarming phrases, and more.

With Phrase API, you can seamlessly integrate text-related content into your projects and keep your users engaged with fresh, meaningful, and entertaining phrases. The API not only provides a rich selection of pre-existing content but also invites users to contribute their own jokes, quotes, or phrases, fostering a collaborative and vibrant community.



## Usage

Phrase API provides various endpoints to interact with. Here are some available public endpoints:

- API domain: `https://phrase-api-server-production.up.railway.app`


- `GET /domain/api/phrase?appid={KEY}&type={TYPE}`: Retrieve a random phrase of requested type.
- `GET /domain/api/phrase?appid={KEY}&type={TYPE}&qty={QTY}`: Retrieve multiple random phrases of requested 
  type.
- `GET /domain/api/phrase?appid={KEY}&type={TYPE}&qty={QTY}&query={QUERY}`: Retrieve multiple random phrases of 
  requested type with query.
- `GET /domain/api/phrase?appid={KEY}&type={TYPE}&qty={QTY}&query={QUERY}&page={PAGE}`: Retrieve multiple phrases of
  requested type with query and pagination.

Please refer to the client website for better information.

## API Documentation

For detailed information on the available endpoints, request parameters, and response formats, check out our 
[Official Client Website](https://phraseapi.vercel.app).

## Error Handling

In case of any errors, Phrase API returns JSON-formatted error responses with appropriate HTTP status codes. The 
error format is as follows:

```json
{
  "code": 404,
  "status": "Not Found",
  "message": "API key not found."
}
```

## Contributing

We welcome contributions to improve AwesomeAPI. To contribute, follow these steps:

1. Fork the repository on GitHub.
2. Create a new branch for your feature or bug fix.
3. Make your changes and commit them with descriptive commit messages.
4. Push your changes to your branch on your fork.
5. Submit a pull request to the `main` branch of this repository.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE.md) file for details.

## Contact

If you have any questions or feedback, feel free to reach out to me at jroicealdeza@gmail.com.

## Other

**Technologies Used:**\
**Backend:** Spring Boot REST\
**Build Tool:** Maven\
**Database:** PostgreSQL\
**Authorization Server:** Auth0\
**Deployment:** Railway

### Resources:
https://github.com/amoudgl/short-jokes-dataset/blob/master/data/reddit-cleanjokes.csv\
https://github.com/akhiltak/inspirational-quotes/blob/master/Quotes.csv

### Additional
[Frontend website repository](https://github.com/roiceee/phrase-api-client)
