package utils;

import com.github.javafaker.Faker;
import exceptions.NoSuchVariableException;
import exceptions.ZeroNumberLengthException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static engine.GlobalVar.GLOBAL_VARIABLES;


@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VariableUtil {

    public static String replace(String text) throws NoSuchVariableException {
        Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z0-9:_/-]+}");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String matched = matcher.group(0);
            String variable = matched.substring(2, matched.length() - 1);
            String replacement = GlobalVariables.INSTANCE.getVariable(variable);
            if (null == replacement) {
                throw new NoSuchVariableException(String.format("the variable '%s' is not defined.", matched));
            }
            text = text.replace(matched, replacement);
            log.info("Replaced variable {} with value {}", matched, replacement);

        }
        return text;
    }
//
//    private static final Faker FAKER = new Faker(new Locale("en", "US"));
//
//    public static String validateVariable(String text) throws NoSuchVariableException, ZeroNumberLengthException {
//        Pattern pattern = Pattern.compile("\\$\\{[a-zA-Z0-9:_/-]+}");
//        Matcher matcher = pattern.matcher(text);
//        while (matcher.find()) {
//            String matched = matcher.group(0);
//            String variable = matched.substring(2, matched.length() - 1);
//            if (variable.contains(":")) {
//                String[] arr = variable.split(":");
//                String varName = arr[0];
//                String symbol = arr[1];
//                switch (varName) {
//                    case "number" -> {
//                        int length = Integer.parseInt(symbol);
//                        String number = FAKER.regexify("\\d{" + length + "}");
//                        text = text.replace(matched, number);
//                        log.debug("replaced ${number:{}} with value {}", length, number);
//                    }
//                    case "today" -> {
//                        String time = TimeUtils.getTodayWithFormatter(symbol);
//                        text = text.replace(matched, time);
//                        log.debug("replaced ${today:{}} with value {}", symbol, time);
//                    }
//                    case "tomorrow" -> {
//                        String time = TimeUtils.getTomorrowWithFormatter(symbol);
//                        text = text.replace(matched, time);
//                        log.debug("replaced ${tomorrow:{}} with value {}", symbol, time);
//                    }
//                    case "yesterday" -> {
//                        String time = TimeUtils.getYesterdayWithFormatter(symbol);
//                        text = text.replace(matched, time);
//                        log.debug("replaced ${yesterday:{}} with value {}", symbol, time);
//                    }
//                }
//            } else {
//                switch (variable) {
//                    case "uuid" -> {
//                        String uuid = UUID.randomUUID().toString();
//                        text = text.replace(matched, uuid);
//                        log.debug("replaced ${uuid} with value {}", uuid);
//                    }
//                    case "name" -> {
//                        String name = FAKER.name().fullName();
//                        text = text.replace(matched, name);
//                        log.debug("replaced ${name} with value {}", name);
//                    }
//                    case "firstName" -> {
//                        String name = FAKER.name().firstName();
//                        text = text.replace(matched, name);
//                        log.debug("replaced ${firstName} with value {}", name);
//                    }
//                    case "lastName" -> {
//                        String name = FAKER.name().lastName();
//                        text = text.replace(matched, name);
//                        log.debug("replaced ${lastName} with value {}", name);
//                    }
//                    case "today" -> {       //yyyy-MM-dd
//                        String date = TimeUtils.getYearMonthDay();
//                        text = text.replace(matched, date);
//                        log.debug("replaced ${today} with value {}", date);
//                    }
//                    case "tomorrow" -> {        //yyyy-MM-dd
//                        String date = TimeUtils.getTomorrowTimeWithFormat(new Date(), "yyyy-MM-dd");
//                        text = text.replace(matched, date);
//                        log.debug("replaced ${tomorrow} with value {}", date);
//                    }
//                    case "yesterday" -> {       //yyyy-MM-dd
//                        String date = TimeUtils.getYesterdayTimeWithFormat(new Date(), "yyyy-MM-dd");
//                        text = text.replace(matched, date);
//                        log.debug("replaced ${today} with value {}", date);
//                    }
//                    case "time" -> {        //yyyy-MM-dd HH:mm:ss
//                        String time = TimeUtils.getLocalFormatTime();
//                        text = text.replace(matched, time);
//                        log.debug("replaced ${time} with value {}", time);
//                    }
//                    case "timestamp" -> {
//                        text = text.replace(matched, String.valueOf(new Date().getTime()));
//                        log.debug("replaced ${timestamp} with value {}", new Date().getTime());
//                    }
//                    case "address" -> {
//                        String address = FAKER.address().fullAddress();
//                        text = text.replace(matched, address);
//                        log.debug("replaced ${address} with value {}", address);
//                    }
//                    case "number" -> throw new ZeroNumberLengthException("No number length definition, the formatter is number:3");
//                    default -> {
//                        String replacement = GLOBAL_VARIABLES.get(variable);
//                        if (null == replacement) {
//                            throw new NoSuchVariableException(String.format("the variable '%s' is not defined.", matched));
//                        }
//                        text = text.replace(matched, replacement);
//                        log.info("Replaced variable {} with value {}", matched, replacement);
//                    }
//                }
//            }
//        }
//        return text;
//    }
//
//    public static int randomNumber() {
//        int max = 99999999, min = 10000000;
//        return (int) (Math.random() * (max - min) + min);
//    }
//
//    public static Map<String, String> convertStringToMap(String string) {
//        string = string.substring(1, string.length()-1);
//        String[] strings = string.split(",");
//        Map<String, String> map = new HashMap<>();
//        for (String s:strings) {
//            String key = s.split("=")[0];
//            String val = s.split("=")[1];
//            map.put(key.trim(), val.trim());
//        }
//        return map;
//    }
}
