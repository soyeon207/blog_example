package velog.soyeon.graphql.config;

import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomScalar {

    @Bean
    public GraphQLScalarType customScalarEmail() {
        return ScalarExtend.GraphQLEmail;
    }

}
