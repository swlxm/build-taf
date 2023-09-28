package org1;

public class MyClass {

    public String process(String original, MyLambda expression) {
        return expression.parse(original);
    }
}
