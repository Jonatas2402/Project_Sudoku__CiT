package model;

public class Space {
    private final int expected;
    private final boolean fixed;
    private  Integer actual;


    public Space(int expected, boolean fixed, Integer actual) {
        this.expected = expected;
        this.fixed = fixed;
        this.actual = actual;
        if (fixed){
            actual = expected;
        }
    }

    public int getExpected() {
        return expected;
    }

    public boolean isFixed() {
        return fixed;
    }

    public Integer getActual() {
        return actual;
    }
    public void setActual(final Integer actual) {
        if (fixed) return;
        this.actual = actual;
    }
    public void clearSpace() {
        setActual(null);
    }
}
