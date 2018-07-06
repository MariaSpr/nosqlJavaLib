public class QueryCriteria<T> {
    private T value;
    private String field;
    private String operator;

    public QueryCriteria(T value, String field, String operator) {
        this.value = value;
        this.field = field;
        this.operator = operator;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}
