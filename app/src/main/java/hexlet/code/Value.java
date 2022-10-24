package hexlet.code;

public final class Value {

    private Object value;
    private Object oldValue;
    private Object newValue;
    private final String diffValue;

    public Value(final Object pOldValue, final Object pNewValue, final String pDiffValue) {
        this.oldValue = pOldValue;
        this.newValue = pNewValue;
        this.diffValue = pDiffValue;
    }

    public Value(final Object pValue, final String pDiffValue) {
        this.value = pValue;
        this.diffValue = pDiffValue;
    }

    public Object getValue() {
        return value;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getDiffValue() {
        return diffValue;
    }
}
