# HTTP Playground

A simple http playground to run various http requests from REPL, potentially time them and study the results. May come handy when:

- You just want to test or explore an HTTP API and feel more comfortable with a REPL than with a curl or a GUI tool like Postman or Insomnia
- You need to manipulate and/or store the results of your requests and need the flexibility of a data-friendly programming language
- You need to fan out a lot of requests based on data - possibly do a data migration from a set of files to a system with an HTTP interface
- You want to make a simple load test of your API and run a ton of requests against it, timing the results on the way

The thing is by no means complete and doesn't aim to be. The goal is to have a starter, which provides functions to run different HTTP requests, and be able to expand the environment utilizing the flexibility of Clojure REPL.

## How to use

1. Clone the repo
2. Open up src/core.clj in a REPL-enabled editor (I love [VS Code](https://code.visualstudio.com/) + [Calva](https://marketplace.visualstudio.com/items?itemName=betterthantomorrow.calva))
3. Jack in
4. Uncomment some of the examples and evaluate them
5. Compose and execute your own queries
6. def required vars to hold hosts, credentials and tokens, defn your own helpers for repeating operations

