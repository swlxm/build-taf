package entities;

public enum Environment {
    DEV("DEV"), QA("QA"), PPE("PPE"), LOCAL("LOCAL"), STAGE("STAGE"), UAT("UAT"), SIT("SIT"), PROD("PROD");

    Environment(String env) {
    }
}
