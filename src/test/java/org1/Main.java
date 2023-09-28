package org1;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;

@Slf4j
public class Main {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        log.info(myClass.process("a b c", StringUtils::deleteWhitespace));
        Faker faker = new Faker(Locale.CHINA);
        Address address = faker.address();
        log.info(faker.code().isbnGs1());
        log.info(faker.code().asin());
        log.info(faker.code().imei());
        log.info(faker.code().isbnRegistrant());
        log.info(faker.nation().capitalCity());
        log.info(faker.nation().nationality());
    }
}
