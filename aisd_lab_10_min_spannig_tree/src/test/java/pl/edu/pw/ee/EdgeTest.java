package pl.edu.pw.ee;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class EdgeTest {
    private Edge edge;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInputIsNull_compareTo() {
        edge = new Edge("check", "test", 8);
        edge.compareTo(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenInputIsNull_equals() {
        edge = new Edge("check", "test", 7);
        edge.equals(null);
    }

    @Test
    public void shouldReturnValidHashcode() {
        edge = new Edge("check", "test", 6);
        assertEquals(-1879788784, edge.hashCode());
    }
}
