package dbservice.result;

import java.util.HashMap;
import java.util.Map;

public class Message {

    private String resultType;
    private String message;
    private Map<String, String> dataMap = new HashMap<>();

    public Message (StatusResult statusResult, String message){
        this.resultType = statusResult.name();
        this.message = message;
    }

    public Message (StatusResult result){
        this.resultType = result.name();
        this.message = result.getMessage();
    }

    public String getResultType() {
        return resultType;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, String> dataMap) {
        this.dataMap = dataMap;
    }
}
