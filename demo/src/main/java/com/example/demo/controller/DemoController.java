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
            @ApiResponse(responseCode = "200", description = "User created successfully",
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
public class DemoController {
}
