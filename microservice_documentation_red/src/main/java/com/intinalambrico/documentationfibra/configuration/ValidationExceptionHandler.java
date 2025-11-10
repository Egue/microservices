package com.intinalambrico.documentationfibra.configuration;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", 400);
        errors.put("message", "Error de validación");

        Map<String, String> violations = exception.getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(
                        violation -> violation.getPropertyPath().toString(),
                        ConstraintViolation::getMessage
                ));

        errors.put("errors", violations);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errors)
                .build();
    }
}
