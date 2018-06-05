package media.dee.dcms.layout.model;

public enum InjectionOperation {
    APPEND("+"), PREAPPEND("-"), INSERT_BEFORE("<"), INSERT_AFTER(">");

    private final String code;

    private InjectionOperation(String code){
        this.code = code;
    }

    public final String getCode() {
        return code;
    }

    @Override
    public final String toString() {
        return this.code;
    }
}
