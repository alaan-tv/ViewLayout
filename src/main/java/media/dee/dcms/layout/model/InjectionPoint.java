package media.dee.dcms.layout.model;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InjectionPoint {
    private final static Pattern PATTERN = Pattern.compile("(?<operand>[\\+\\-\\<\\>]?)\\s*?(?<selector>.*)");

    private InjectionOperation operation;
    private String selector;


    public InjectionPoint(String injectionPoint){
        Matcher matcher = PATTERN.matcher(injectionPoint);
        if( !matcher.matches() )
            throw new RuntimeException("invalid injection point");
        String operand = matcher.group("operand");
        this.selector = matcher.group("selector");
        this.operation = Arrays.stream(InjectionOperation.values())
                .filter( o -> o.getCode().equals(operand.isEmpty() ? "+" : operand) )
                .findFirst().orElseThrow(()-> new RuntimeException("invalid operand!"));
    }

    public InjectionPoint(InjectionOperation operation, String selector){
        this.operation = operation;
        this.selector = selector;
    }

    public String getSelector(){
        return this.selector;
    }

    public InjectionOperation getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return String.format("%s%s", operation, selector);
    }
}
