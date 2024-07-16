# Employee Management RMI

By using Rmote Method Invocation. I create small empolyee managment to apply this concept

- **Feature**
  - Add employee (name, email, salary, department).
  - Update any employee's field.
  - Get all employees.
  - Get one employee using id.
  - Delete one employee by id.
  - Delete all employee.

> this application is java maven

## Follow step To run project

### Prerequisites
Ensure Docker is installed on your machine. If you don't have Docker installed, you can download and install it from [here](https://docs.docker.com/engine/install/).

#### Running the Application
To run the application, follow these steps:

Navigate to the root directory of the project.
Run the following command to start the Docker containers:

```bash
docker-compose up
```

**Install dependencies using maven**
you can use terminal or just run it inside intellj:
```bash
mvn clean install
```

- Run `server.class` individual
- Then run `client.class` selection current file from run menu in intellj.
