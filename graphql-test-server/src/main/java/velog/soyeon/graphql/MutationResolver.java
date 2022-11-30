package velog.soyeon.graphql;

import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class MutationResolver implements GraphQLMutationResolver {

    public String test() {
        return "[Mutation] test";
    }

    public String emailTest(String email) {
        return email;
    }

}
