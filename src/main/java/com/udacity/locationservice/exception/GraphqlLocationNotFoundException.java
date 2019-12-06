package com.udacity.locationservice.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphqlLocationNotFoundException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extentions = new HashMap<>();

    public GraphqlLocationNotFoundException(String message, Long invalidLocationId){
        super(message);
        extentions.put(message, invalidLocationId);
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extentions;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}
