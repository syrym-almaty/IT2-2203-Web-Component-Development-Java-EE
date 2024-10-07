package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demo")
@Tag(name = "Demo Controller", description = "This is a demo controller for educational purposes")
public class DemoController {

    @Operation(summary = "Say Hello", description = "This endpoint returns a simple greeting message.")
    @ApiResponse(responseCode = "200", description = "Greeting message returned successfully")
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, Swagger!";
    }

    @Operation(summary = "Greet User", description = "Returns a greeting message with a note in JSON format.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved greeting message",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Greeting.class)) }),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/greet")
    public Greeting greetUser() {
        return new Greeting("Greetings from the Demo Controller!", "Have a great day!");
    }

    @Operation(summary = "Create User", description = "Creates a new user by accepting a JSON body with name and email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping("/createUser")
    public User createUser(
            @Parameter(description = "User object containing name and email", required = true)
            @RequestBody User user) {
        return new User(user.getName(), user.getEmail());
    }
}

// A simple POJO class to return a greeting message as JSON
class Greeting {
    private String message;
    private String note;

    public Greeting(String message, String note) {
        this.message = message;
        this.note = note;
    }

    // Getters and Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

// A simple POJO class representing a User
class User {
    private String name;
    private String email;

    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
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