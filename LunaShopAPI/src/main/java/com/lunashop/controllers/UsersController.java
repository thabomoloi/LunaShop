package com.lunashop.controllers;

import io.javalin.http.Context;
import io.javalin.openapi.OpenApi;
import io.javalin.openapi.OpenApiParam;
import io.javalin.openapi.OpenApiResponse;

public class UsersController {
    public UsersController() {}

    @OpenApi(
            summary = "Get a user",
            operationId = "getUser",
            path = "/users/{id}",       // must match your route
//            method = HttpMethod.GET,
            tags = {"User"},
            pathParams = {
                    @OpenApiParam(name = "id", description = "User ID", required = true, type = Integer.class)
            },
            responses = {
//                    @OpenApiResponse(status = "200", content = {
//                            @OpenApiContent(from = UserResponse.class)   // schema definition
//                    }),
                    @OpenApiResponse(status = "404", description = "User not found")
            }
    )
    public void getUser(Context ctx) {
        ctx.json("{id: 123, age: 11}");
    }

}
