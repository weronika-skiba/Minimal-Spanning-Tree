package pl.edu.pw.ee;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PrimAlgorithmTest {
    private PrimAlgorithm primAlgorithm;

    private void exceptionTestMethod(String pathToFile, String message) {
        primAlgorithm = new PrimAlgorithm();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> {
            primAlgorithm.findMST(pathToFile);
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
            "src/files/correct_small_data.txt,D_1_C|C_1_B|B_3_A|D_7_E",
            "src/files/data_from_class.txt,D_3_G|G_2_B|D_3_F|F_4_E|D_4_A|A_2_H|H_5_C",
            "src/files/emptyFile.txt,''",
            "src/files/one_edge.txt,pW_8_Agh",
            "src/files/same_edge_twice.txt,d_7_c|c_1_a|c_3_e|a_4_b|e_65_f|f_0_g|f_4_h",
            "src/files/two_edges.txt,hi_1_world|world_9_hello",
            "src/files/three_edges_same_weight.txt,B_7_c|c_7_D|B_7_A",
            "src/files/allVerticesConnected.txt,d_3_c|c_2_a|a_1_b|b_4_f|f_5_e",
            "src/files/medium_data_2.txt,sixth_40_twice|twice_17_trice|twice_26_once|once_16_seventh|seventh_19_zero|seventh_28_fifth|fifth_35_fourth",
    })
    public void shouldFindCorrectMST(String pathToFile, String expected) {
        primAlgorithm = new PrimAlgorithm();
        assertEquals(expected, primAlgorithm.findMST(pathToFile));
    }

}
