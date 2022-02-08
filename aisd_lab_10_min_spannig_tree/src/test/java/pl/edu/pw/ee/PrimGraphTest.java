package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PrimGraphTest {
    private PrimGraph primGraph;

    @ParameterizedTest
    @CsvSource(value = {
            ",",
            "test,",
            ",test",
    })
    public void shouldThrowIllegalArgumentException_inputIsNull(String firstVertix, String secondVertix) {
        primGraph = new PrimGraph();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            primGraph.addEdge(firstVertix, secondVertix, 9);
        });
        assertEquals("Input strings cannot be null", e.getMessage());
    }
}
