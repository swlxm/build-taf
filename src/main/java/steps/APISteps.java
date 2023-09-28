package steps;

import exceptions.NoSuchVariableException;
import io.cucumber.java.en.And;
import lombok.extern.slf4j.Slf4j;
import utils.GlobalVariables;
import utils.VariableUtil;

//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import com.alibaba.fastjson2.JSONPath;
//import com.alibaba.fastjson2.JSONReader;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.http.Cookie;
//import io.restassured.http.Headers;
//import io.restassured.path.json.JsonPath;
//import io.restassured.response.Response;
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.swlxm.utaf.entities.api.Method;
//import org.swlxm.utaf.exception.InvalidParameterException;
//import org.swlxm.utaf.exception.NoSuchJSONPathException;
//import org.swlxm.utaf.exception.NoSuchVariableException;
//import org.swlxm.utaf.exception.ZeroNumberLengthException;
//import org.swlxm.utaf.utils.FileReaderUtil;
//import org.swlxm.utaf.utils.TestDataReader;
//import org.swlxm.utaf.utils.VariableUtil;
//import org.swlxm.utaf.utils.api.Matchers;
//import org.swlxm.utaf.utils.api.SendRequest;
//
//import java.io.IOException;
//import java.util.Map;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.swlxm.utaf.engine.GlobalVar.GLOBAL_VARIABLES;
//
@Slf4j
public class APISteps {
//
//    private Response response;
//    private String method;
//    private String headers;
//    private String params;
//    private String payload;
//
//    /**
//     * this step is to give a brief summary of the test case
//     *
//     * @param module   a free string
//     * @param caseNo   a free digit number
//     * @param caseDesc a free string
//     */
//    @Given("{module} - {caseNo} - {caseDescription}")
//    public void printCaseDescription(String module, String caseNo, String caseDesc) {
//        log.info("Executing test case [" + module + " - " + caseNo + " - " + caseDesc + "]......");
//    }
//
//    /**
//     * setup request method
//     *
//     * @param method the method of the request, the value should one of GET/POST/PUT/DELETE
//     */
//    @And("request method {method}")
//    public void requestMethod(Method method) {
//        this.method = method.getMethod();
//    }
//
//    /**
//     * setup request headers
//     *
//     * @param headers {@code key=value&key2=value2}; parameterization is enabled, for example key=${value}
//     */
//    @And("request headers {headers}")
//    public void requestHeaders(String headers) {
//        if (!headers.isEmpty() && !headers.equals("\"\"")) {
//            this.headers = headers;
//        } else {
//            this.headers = null;
//        }
//    }
//
//    /**
//     * setup request parameters
//     *
//     * @param params {@code key=value&key2=value2}; parameterization is enabled, like key=${value}
//     */
//    @And("request parameters {parameters}")
//    public void requestParams(String params) {
//        if (!params.isEmpty() && !params.equals("\"\"")) {
//            this.params = params;
//        } else {
//            this.params = null;
//        }
//    }
//
//    /**
//     * setup payload file location when sending post request, the default location is src/test/java/org/swlxm/utaf/testData/payload. for
//     * example, if the value of payloadFilePath is /abc.json (abc.json), that means the file location is
//     * src/test/java/org/swlxm/utaf/testData/payload/abc.json; if the value is /api/abc.json ( api/abc.json), that means the file location is
//     * src/test/java/org/swlxm/utaf/testData/payload/api/abc.json
//     *
//     * @param filePath payload file (json file) name, like abc.json
//     */
//    @And("request payload {payloadFilePath}")
//    public void requestPayload(String filePath) throws NoSuchVariableException, ZeroNumberLengthException {
//        String jsonText;
//        if (!filePath.isEmpty() && !filePath.equals("\"\"")) {
//            String payloadFolder = TestDataReader.getPayloadRootFolder();
//            String file = filePath.startsWith("/") ? payloadFolder + filePath : payloadFolder + "/" + filePath;
//            log.info("Load payload from {}", filePath);
//            jsonText = FileReaderUtil.readTextFile(file);
//            jsonText = VariableUtil.validateVariable(jsonText);
//        } else {
//            jsonText = null;
//        }
//        this.payload = jsonText;
//    }
//
//    /** remove key form request payload.
//     * need to be called after setup request payload
//     *
//     * @param jsonpath jsonPath string to the target key
//     */
//    @And("remove key {jsonpath} from payload")
//    public void removeKeyFromRequestPayload(String jsonpath) {
//        this.payload = JSONPath.remove(this.payload, jsonpath);
//    }
//
//    /** add key value into request payload.
//     * need to be called after setup request payload
//     *
//     * @param jsonpath the jsonPath to the key
//     * @param value the value to set to the key. Should use globalVariable_<variableName> instead
//     *              of ${variableName} when need to use global variable which contains double quote,
//     *              or will have errors in cucumber.json and fail to generate report.
//     */
//    @And("set key {jsonpath} and value {jsonValue} into payload")
//    public void setKeyValue2RequestPayload(String jsonpath, String value) throws NoSuchVariableException, ZeroNumberLengthException{
//        String[] tmpStrList = value.split("_");
//        String tmpValue = VariableUtil.validateVariable(value);
//        if(tmpStrList[0].equalsIgnoreCase("globalVariable")) {
//            String variable = "${" + tmpStrList[1] + "}"; //need to deal with not use variable.
//            tmpValue = VariableUtil.validateVariable(variable);
//        }
//        if(tmpValue.startsWith("{") ){
//            JSONObject jsonObject = JSONObject.parseObject(tmpValue);
//            this.payload = JSONPath.set(this.payload, jsonpath, jsonObject);
//        } else if (tmpValue.startsWith("[")){
//            String jsonString = "{\"tmpKey\":" + tmpValue + "}";
//            this.payload = JSONPath.set(this.payload, jsonpath, JSONObject.parseObject(jsonString).get("tmpKey"));
//        } else {
//            this.payload = JSONPath.set(this.payload, jsonpath, tmpValue);
//        }
//    }
//
//    /**
//     * setup request url, it is part of the endpoint, you can find it on swagger
//     *
//     * @param url the relative API path, normally it should be same as the path on swagger, like /product/sales/client/api/v1/vehicle/models/modelList
//     */
//    @When("send request to {url}")
//    public void sendRequest(String url) throws InvalidParameterException, NoSuchVariableException, ZeroNumberLengthException, IOException {
//        final Cookie[] cookie = new Cookie[1];
//        GLOBAL_VARIABLES.keySet().forEach(key -> {
//            if (key.startsWith("ck")) {
//                String k = key.split("_")[1];
//                cookie[0] = new Cookie.Builder(k, GLOBAL_VARIABLES.get(key)).build();
//                log.info("Set cookie {} with value {}", k, GLOBAL_VARIABLES.get(key));
//            }
//        });
//        response = new SendRequest().sendRequest(url, cookie[0], this.method, this.headers, this.params, this.payload);
//    }
//
//    /**
//     * a checkpoint to check the response code
//     *
//     * @param code a three digits number to indicate the expected response code, for example 200
//     */
//    @Then("the status code is {code}")
//    public void validHttpCode(int code) {
//        assertThat(response.statusCode()).withFailMessage(response.prettyPrint() + "\n" + GLOBAL_VARIABLES.get("curl")).isEqualTo(code);
//    }
//
//    /**
//     * a checkpoint to check the full response content
//     *
//     * @param payload file name (json file) of response
//     */
//    @And("the response is {payloadFilePath}")
//    public void validAllResponse(String payload) {
//        if (null != payload) {
//            String expectedResponse = FileReaderUtil.readTextFile("APIGlobalVar.RESOURCE_FILES_ROOT_PATH" + payload);
//            assertThat(response.body().prettyPrint()).isEqualTo(expectedResponse);
//        }
//    }
//
//    /**
//     * a checkpoint to check the specific value in response, the value is fetched by json path. there is a set of matchers which can help
//     * you to verify the result: equals(abc) means same as expected result, for example {'Data.Status':'equals(success)'}, it can be
//     * shortened to {'Data.Status':'success'} startsWith(abc) means the value should start with, like {'Data.Name':'startsWith(abc)'}
//     * contains(abc) means the value contains specific chars, like {'Data.Name':'contains(abc)'} exists() means the field indicated by json
//     * path is existing, like {'Data.Name':'exists()'} isNotEmpty() means the field has value, like {'Data.Name':'isNotEmpty()'} isNull()
//     * means the value of the field is null isNotNull() means the value of the field is not null isAnArray() means the return of the json
//     * path is an array, for example [] hasItemInArray() means the array is not empty arraySizeEquals(n) means the array size should equal
//     * to n eachItemInArrayEquals(abc) means all the elements in the array should equal to abc eachItemInArrayContains(abc) means all the
//     * elements in the array should contain abc
//     *
//     * @param expectedResults {'jsonPath':'matcher(expectedVal)', 'jsonPath2':'matcher(expectedVal2)'}, for example if you want to check if
//     *                        the value of specific field equals to expected result, you can try {'data.message', 'equals(success)'}, the
//     *                        valid matchers include but not limited:
//     *                        - equals
//     *                        - startsWith
//     *                        - contains
//     *                        - exists
//     *                        - isNotEmpty
//     *                        - isNull
//     *                        - isNotNull
//     *                        - isAnArray
//     *                        - hasItemInArray
//     *                        - arraySizeEquals
//     *                        - eachItemInArrayEquals
//     *                        - eachItemInArrayContains
//     *                        parameterization is enabled as well, like {'jsonPath':'${var}'}
//     */
//    @And("CHECK response {response}")
//    public void validResponseField(String expectedResults) throws NoSuchVariableException, ZeroNumberLengthException {
//        if (!expectedResults.equals("")) {
//            Map<String, Object> obj = JSON.parseObject(expectedResults);
//            for (Map.Entry<String, Object> stringObjectEntry : obj.entrySet()) {
//                String key = stringObjectEntry.getKey();
//                String value = stringObjectEntry.getValue().toString();
//                Assertions.assertThat(Matchers.checkField(response, key, value)).withFailMessage(key + "=" + value + "\n" + response.prettyPrint() + "\n" + GLOBAL_VARIABLES.get("curl")).isTrue();
//            }
//        }
//    }
//
//    /**
//     * store the value of specific json path as a variable, other steps can invoke it with ${var}; if the variable is existing,
//     * the value will be replaced by newer.
//     *
//     * @param variables varName=jsonPath&varName2=jsonPath2
//     */
//    @And("store data as variables {variable}")
//    public void storeVariables(String variables) throws NoSuchVariableException, ZeroNumberLengthException, NoSuchJSONPathException {
//        if ("".equals(variables)) {
//            return;
//        }
//        String[] fields = variables.split("&");
//        for (String field : fields) {
//            String[] arr = field.split("=", 2);
//            String variableName = arr[0].trim();
//            String jsonPath = VariableUtil.validateVariable(arr[1].trim());
//            if (jsonPath.startsWith("header_")) {
//                String header = jsonPath.split("_")[1];
//                log.info("Variable name is {}", variableName);
//                log.info("Header field is {}", header);
//                Headers headers = response.headers();
//                String headerVal = headers.getValue(header);
//                if (null == headerVal) {
//                    throw new NoSuchVariableException("Header " + header + " not found.");
//                } else {
//                    GLOBAL_VARIABLES.put(variableName, headerVal);
//                    log.info("Set global variable [{} = {}]", variableName, headerVal);
//                }
//            } else {
//                log.info("Variable name is {}", variableName);
//                log.info("Json path is {}", jsonPath);
//                JsonPath jp = response.jsonPath();
//                if (null == jp.get(jsonPath)) {
//                    throw new NoSuchJSONPathException("The json path " + jsonPath + " is invalid.");
//                }
//                String variableVal = jp.get(jsonPath).toString();
//                GLOBAL_VARIABLES.put(variableName, variableVal);
//                log.info("Set global variable [{} = {}]", variableName, variableVal);
//            }
//        }
//
//    }
//
//    /**
//     * store the value of specific json path as a variable, other steps can invoke it with ${var}; if the variable is existing,
//     * the value will be replaced by newer.
//     * storeVariables() will not include quote into json object string, while this method will include quote.
//     * e.g. the json is {"data": {"name": "json"}}
//     *      storeVariables("varData=data") will set {name=json} to varData
//     *      storeVariable("varData", "data") will set {"name": "json"} to varData
//     * And note that storeVariables() is using GPath, and this is using fastjson2 jsonpath.
//     *
//     * @param varName variable name
//     * @param JPathString json path string (fastjson2 jsonpath format)
//     *
//     */
//    @And("store response data as variable {variable} via jsonpath {jsonpath}")
//    public void storeVariable(String varName, String JPathString) {
//        if ("".equals(varName) || "".equals(JPathString)) {
//            return;
//        }
//        log.info("jsonpath: {}", JPathString);
//        Object result = JSONPath.of(JPathString).extract(JSONReader.of(response.jsonPath().prettify()));
//        String variableVal = JSON.toJSONString(result);
//        log.info("Set global variable [{} = {}]", varName, variableVal);
//        GLOBAL_VARIABLES.put(varName, variableVal);
//    }
//
//    /**
//     * store the value of specific json path as a variable, other steps can invoke it with ${var}; if the variable is existing,
//     * the value will be replaced by newer.
//     *
//     * @param variable varName=jsonPath
//     */
//    @And("store data as variable with JSON format {variable}")
//    public void storeVariablesAsJson(String variable) throws NoSuchVariableException, ZeroNumberLengthException, NoSuchJSONPathException {
//        if ("".equals(variable)) {
//            return;
//        }
//        String[] arr = variable.split("=", 2);
//        String variableName = arr[0].trim();
//        String jsonPath = VariableUtil.validateVariable(arr[1].trim());
//        log.info("Variable name is {}", variableName);
//        log.info("Json path is {}", jsonPath);
//        JsonPath jp = response.jsonPath();
//        if (null == jp.get(jsonPath)) {
//            throw new NoSuchJSONPathException("The json path " + jsonPath + " is invalid.");
//        }
//        String variableVal = new JSONObject(VariableUtil.convertStringToMap(jp.get(jsonPath))).toString();
//        GLOBAL_VARIABLES.put(variableName, variableVal);
//        log.info("Set global variable [{} = {}]", variableName, variableVal);
//    }
//
//    /**
//     * Set thinking time before sending request with second
//     *
//     * @param min seconds number
//     */
    @And("sleep {}")
    public void makeSleep(String min) throws InterruptedException, NoSuchVariableException {
        Thread.sleep(Integer.parseInt(VariableUtil.replace(min)) * 1000L);
        log.info("Waiting {} seconds to generate contract", VariableUtil.replace(min));
    }
//
}
