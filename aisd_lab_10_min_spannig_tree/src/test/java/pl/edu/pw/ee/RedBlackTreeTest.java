package pl.edu.pw.ee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RedBlackTreeTest {
    private RedBlackTree<String, String> tree;

    @Test
    public void shouldReturnTrue_rootIsNull_checkConnectionKruskal() {
        tree = new RedBlackTree<>();
        assertTrue(tree.checkConnectionKruskal());
    }

    @Test
    public void shouldReturnTrue_rootIsNull_checkConnectionPrim() {
        tree = new RedBlackTree<>();
        assertTrue(tree.checkConnectionPrim());
    }

    @ParameterizedTest
    @CsvSource(value = {
            ",",
            "test,",
            ",test",
    })
    public void shouldThrowIllegalArgumentException_inputIsNull(String firstId, String secondId) {
        tree = new RedBlackTree<>();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            tree.updateIds(firstId, secondId);
        });
        assertEquals("Input values cannot be null.", e.getMessage());
    }
}
