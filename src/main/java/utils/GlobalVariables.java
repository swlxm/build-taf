package utils;

import java.util.HashMap;
import java.util.Map;

public enum GlobalVariables {
    INSTANCE;

    private static final Map<String, String> GLOBAL_VAR = new HashMap<>();

    public void setVariable(String key, String val) {
        GLOBAL_VAR.put(key, val);
    }

    public String getVariable(String key) {
        return GLOBAL_VAR.get(key);
    }

    public Map<String, String> getAsMap() {
        return GLOBAL_VAR;
    }
}
