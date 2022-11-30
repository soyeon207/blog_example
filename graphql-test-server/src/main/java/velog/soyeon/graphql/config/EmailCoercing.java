package velog.soyeon.graphql.config;

import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.jetbrains.annotations.NotNull;

public class EmailCoercing implements Coercing<String, String> {
    // Coercing 에 Input, Output 타입을 지정할 수 있는데 각각 타입들은
    // 해당 데이터를 어떤 타입으로 리턴하고 파라미터로 받을지를 지정하기 위한 클래스
    @Override
    public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
        // return 객체 타입이 EMAIL 일 때
        // dataFetchResult 를 scalar type 에 맞는 타입으로 변환시키기 위한 메소드
        if (dataFetcherResult instanceof String) {
            return dataFetcherResult.toString();
        }
        return null;
    }

    @Override
    public @NotNull String parseValue(@NotNull Object input) throws CoercingParseValueException {
        // 파라미터 객체 타입이 EMAIL 일 때
        // -> 해당 EMAIL 파라미터를 내부에서 사용할 수 있도록 입력값으로 변환해주는 메소드
        // 외부 입력 값(변수) 를 내부 입력 값으로 변환하는 메소드
        // 파라미터를 variables 로 넣는 경우 해당 메소드 호출 EX) emailTest(email: $email)
        return input.toString();
    }

    @Override
    public @NotNull String parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
        // 파라미터 객체 타입이 EMAIL 일 때
        // 요청받은 파라미터 query 를 validation 할 때 사용
        // 파라미터를 직접 넣는 경우 해당 메소드 호출 EX) emailTest(email: "psy010207@naver.com")
        return input.toString();
    }

}
