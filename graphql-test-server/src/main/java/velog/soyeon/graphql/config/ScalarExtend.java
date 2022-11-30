package velog.soyeon.graphql.config;

import graphql.schema.GraphQLScalarType;
import org.springframework.stereotype.Component;

@Component
public class ScalarExtend {

    public static final GraphQLScalarType GraphQLEmail = GraphQLScalarType.newScalar()
            .name("EMAIL")
            .description("A custom scalar that handles emails")
            .coercing(new EmailCoercing())
            .build();

}
