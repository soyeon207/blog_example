package velog.soyeon.graphql;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    public String test() {
        return "[Query] test";
    }

    public Long longTest() {
        return 3L;
    }

    public String dateTimeTest() {
        return LocalDate.now().toString();
    }

    public String dateTest() {
        return LocalDate.now().toString();
    }

    public String emailTest(String email) {
        if (StringUtils.isEmpty(email)) throw new RuntimeException("이메일을 넘겨주세요.");
        return email;
    }

}
