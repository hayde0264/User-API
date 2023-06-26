# User-API

<b> <code>Intentions:</code> </b>

Strength my back-end skills with Java, Spring Boot, and MySQL by create a User API.

<b> <code>Tech Used:</code> </b>

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Visual Studio Code](https://img.shields.io/badge/Visual%20Studio%20Code-0078d7.svg?style=for-the-badge&logo=visual-studio-code&logoColor=white)


<b> <code>From:</code> </b>

<link>https:/spring.io/guides/gs/accessing-data-mysql/</link>


## Steps
### Install the correct dependencies with spring initializr

![Init](https://github.com/hayde0264/MySQL-API/blob/main/assets/init.png)
- See [URL](https://start.spring.io)

### Create a MySQL Database called Users
![Create](https://github.com/hayde0264/MySQL-API/blob/main/assets/create.png)



### Create a user
![User](https://github.com/hayde0264/MySQL-API/blob/main/assets/allow.png)


### Grant privileges to user
![Grant](https://github.com/hayde0264/MySQL-API/blob/main/assets/grant.png)



### Update src/main/resources/application.properties
![Prop](https://github.com/hayde0264/MySQL-API/blob/main/assets/properties.png)



### Create the User Model
```java
package com.example.accessingdatamysql;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class UserModel {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;

  private String name;

  private String email;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
```
- See [file]()

### Create User Respository
```java
package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

import com.example.accessingdatamysql.UserModel;

public interface UserRepository extends CrudRepository<UserModel, Integer> {}
```
- See [file]()


### Create User Controller
```java
package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class UserController {
  @Autowired // This means to get the bean called userRepository
         // Which is auto-generated by Spring, we will use it to handle the data
  private UserRepository userRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String name
      , @RequestParam String email) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

    UserModel n = new UserModel();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<UserModel> getAllUsers() {
    // This returns a JSON or XML with the users
    return userRepository.findAll();
  }
}
```
- See [file]()

### Create an Application Class
```java
package com.example.accessingdatamysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccessingDataMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataMysqlApplication.class, args);
	}

}
```
- See [file]()


### Create Project with Maven
```sh
./mvnm spring-boot:run
```

### TEST

#### Demo 1
```sh
curl http://localhost:8080/demo/add -d name=Hayden -d email=hayde0264@gmail.com
```

![First](https://github.com/hayde0264/MySQL-API/blob/main/assets/first.png)


#### Demo 2
```sh
curl http://localhost:8080/demo/add -d name=Charlotte -d email=charjabug@yahoo.com
```
![Second](https://github.com/hayde0264/MySQL-API/blob/main/assets/second.png)


#### Demo 3
```sh
curl http://localhost:8080/demo/add -d name=Emily -d email=emilymetzger@gmail.com
```
![Third](https://github.com/hayde0264/MySQL-API/blob/main/assets/third.png)



#### Demo 4
```sh
curl http://localhost:8080/demo/add -d name=Grayson -d email=graysonbaker@hotmail.com
```

![Fourth](https://github.com/hayde0264/MySQL-API/blob/main/assets/fourth.png)
- If the endpoint includes <code>/add</code> a <code>POST</code> request will be made
- The terminal should respond <code>Saved</code>

