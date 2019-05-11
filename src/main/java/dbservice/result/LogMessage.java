package dbservice.result;

public class LogMessage {
    public static final String ORDER_SUCCESS_UPDATE = "Order successfully updated";
    public static final String JMS_FAILED = "Couldn't send message to JMS";
    public static final String JMS_SUCCESS = "Message successfully sent to JMS";
    public static final String ORDER_SUCCESS_CONFIRM = "Order successfully confirmed for customer with id: %d";
    public static final String CUSTOMER_UPDATED = "Customer profile info was updated";
}
