package com.kodewerk.cheapdrink;

public class CompositeKey {

    private String key1;
    private String key2;

    public CompositeKey(String key1, String key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompositeKey)) return false;

        CompositeKey that = (CompositeKey) o;
        if (key1 != null ? !key1.equals(that.key1) : that.key1 != null)
            return false;
        if (key2 != null ? !key2.equals(that.key2) : that.key2 != null)
            return false;
        return true;
    }

    public final int hashCode() {
        int result = (key1 != null ? key1.hashCode() : 0);
        return 31 * result + (key2 != null ? key2.hashCode() : 0);
    }

    public String getKey1() { return key1; }
    public String getKey2() { return key2; }
}
