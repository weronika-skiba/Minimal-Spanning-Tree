package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RBTMapTest {
    private RBTMap<String, String> map;

    @ParameterizedTest
    @CsvSource(value = {
            ",",
            "test,",
            ",test",
    })
    public void shouldThrowIllegalArgumentException_inputArgumentsAreNull(String key, String value) {
        map = new RBTMap<>();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            map.setValue(key, value);
            ;
        });
        assertEquals("Params (key, value) cannot be null.", e.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentException_inputKeyIsNull() {
        map = new RBTMap<>();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            map.getValue(null);
        });
        assertEquals("Cannot get value by null key.", e.getMessage());
    }

    @ParameterizedTest
    @CsvSource(value = {
            ",",
            "test,",
            ",test",
    })
    public void shouldThrowIllegalArgumentException_inputIdsAreNull(String firstId, String secondId) {
        map = new RBTMap<>();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            map.updateIds(firstId, secondId);
        });
        assertEquals("Input values cannot be null.", e.getMessage());
    }

}
