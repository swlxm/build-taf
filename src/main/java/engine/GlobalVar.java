package engine;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GlobalVar {

    public static final Map<String, String> GLOBAL_VARIABLES = new HashMap<>();

}
