package enums;

public enum OperatorEnum {
    EQUAL("="),
    NOT_EQUAL("!="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUAL("<="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUAL("<="), 
    IS_NOT("IS NOT"),
    IS("IS");

    private final String operatorValue;

    OperatorEnum(String operatorValue){
        this.operatorValue = operatorValue;
    }

    public String value() {
        return operatorValue;
    }
}
