package pl.edu.pw.ee;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class KruskalAlgorithmTest {
    private KruskalAlgorithm kruskalAlgorithm;

    private void exceptionTestMethod(String pathToFile, String message) {
        kruskalAlgorithm = new KruskalAlgorithm();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            kruskalAlgorithm.findMST(pathToFile);
        });
        assertEquals(message, e.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentException_pathIsNull() {
        exceptionTestMethod(null, "Path cannot be null.");
    }

    @Test
    public void shouldThrowIllegalArgumentException_fileDoesNotExist() {
        exceptionTestMethod("src/files/fakeFile.txt", "File has to exist.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/files/negative_weight.txt,File has incorrect format.",
            "src/files/loop.txt,Graph contains loop.",
            "src/files/incorrect_format1.txt,File has incorrect format.",
            "src/files/incorrect_format2.txt,File has incorrect format.",
            "src/files/forbiddenChars.txt,File has incorrect format.",
            "src/files/digitInVertixName.txt,File has incorrect format.",
            "src/files/floatAsWeight.txt,File has incorrect format.",
    })
    public void shouldThrowIllegalArgumentException_fileFormatIsIncorrect(String pathToFile, String message) {
        exceptionTestMethod(pathToFile, message);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/files/disconnectedGraph_upperCaseLetters.txt",
            "src/files/disconnectedGraph_lowerCaseLetters.txt",
            "src/files/disconnectedGraph_strings.txt",
    })
    public void shouldThrowIllegalArgumentException_graphIsNotConnected(String pathToFile) {
        exceptionTestMethod(pathToFile, "Input graph has to be connected.");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "src/files/correct_small_data.txt,B_1_C|C_1_D|A_3_B|D_7_E",
            "src/files/data_from_class.txt,H_2_A|B_2_G|D_3_F|D_3_G|A_4_D|F_4_E|H_5_C",
            "src/files/emptyFile.txt,''",
            "src/files/one_edge.txt,Agh_8_pW",
            "src/files/same_edge_twice.txt,g_0_f|a_1_c|e_3_c|a_4_b|h_4_f|c_7_d|f_65_e",
            "src/files/two_edges.txt,world_1_hi|hello_9_world",
            "src/files/three_edges_same_weight.txt,A_7_B|B_7_c|c_7_D",
            "src/files/allVerticesConnected.txt,a_1_b|c_2_a|c_3_d|b_4_f|f_5_e",
            "src/files/medium_data_2.txt,once_16_seventh|twice_17_trice|zero_19_seventh|once_26_twice|fifth_28_seventh|fourth_35_fifth|sixth_40_twice",
    })
    public void shouldFindCorrectMST(String pathToFile, String expected) {
        kruskalAlgorithm = new KruskalAlgorithm();
        assertEquals(expected, kruskalAlgorithm.findMST(pathToFile));
    }

}
