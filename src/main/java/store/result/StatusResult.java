package store.result;

public enum StatusResult {
    CATEGORY_SUCCESS_CREATE ("Category \"%s\" successfully added"),
    CATEGORY_ALREADY_EXIST("Category with name \"%s\" already exist"),
    CATEGORY_SUCCESS_UPDATE("Category \"%s\" successfully updated"),
    CATEGORY_SUCCESS_DELETE("Category  \"%s\" successfully deleted"),
    CATEGORY_FAIL_DELETE("Couldn't delete category \"%s\""),

    PRODUCT_SUCCESS_CREATE ("Product successfully added"),
    PRODUCT_ALREADY_EXIST("Product with name \"%s\" already exist"),
    PRODUCT_SUCCESS_DELETE("Product successfully deleted"),
    PRODUCT_FAIL_DELETE("Couldn't delete product"),

    EMAIL_ALREADY_EXIST("User with such email already exists." +
            " Please, enter another email or go to the login page."),
    CUSTOMER_CREATE_SUCCESS("Registration completed successfully. Please, enter your login details."),
    PASSWORD_CHANGED_SUCCESS("Password changed successfully"),
    INVALID_OLD_PASSWORD("Invalid old password"),

    ORDER_FIND_MISSING_PRODUCTS("Couldn't confirm order. Cart contains missing products"),
    ORDER_CONFIRM_SUCCESS("Order confirm successfully");


    private final String message;

    StatusResult(String message){
        this.message = message;
    }

    public String getFormatMessage(String s){
        return String.format(getMessage(), s);
    }

    public String getMessage(){
        return message;
    }
}
