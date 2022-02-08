package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.edu.pw.ee.services.MinSpanningTree;

public class PrimAlgorithm implements MinSpanningTree {

    private PrimGraph graph;

    PrimAlgorithm() {
        graph = new PrimGraph();
    }

    public String findMST(String pathToFile) {

        if (pathToFile == null)
            throw new IllegalArgumentException("Path cannot be null.");

        File file = new File(pathToFile);

        if (!file.exists())
            throw new IllegalArgumentException("File has to exist.");

        if (file.length() == 0)
            return "";

        Scanner scanner;
        try {
            scanner = new Scanner(file);
            buildGraph(scanner);
            String minSpanningTree = buildMinSpanningTree();
            checkIfGraphIsConnected();
            return minSpanningTree;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private void checkIfGraphIsConnected() {
        if (!graph.checkConnection()) {
            throw new IllegalArgumentException("Input graph has to be connected.");
        }
    }

    private void buildGraph(Scanner scanner) {
        String currentLine = null;
        Matcher matcher;
        String firstVertix;
        String secondVertix;
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            if (!currentLine.matches("^(?:[a-zA-Z]+ ){2}\\d+$"))
                throw new IllegalArgumentException("File has incorrect format.");
            matcher = Pattern.compile("^([a-zA-Z]+) ([a-zA-Z]+) (\\d+)$").matcher(currentLine);
            matcher.find();
            firstVertix = matcher.group(1);
            secondVertix = matcher.group(2);
            if (firstVertix.equals(secondVertix))
                throw new IllegalArgumentException("Graph contains loop.");
            graph.addEdge(firstVertix, secondVertix, Integer.parseInt(matcher.group(3)));
        }
    }

    private String buildMinSpanningTree() {
        StringBuilder minSpanningTree = new StringBuilder();
        PrimMinHeap minHeap = new PrimMinHeap();
        PrimVertix tmp = graph.getRootVertix();
        Edge poppedEdge;
        ArrayList<Edge> edgesOfTmp;

        while (true) {
            edgesOfTmp = (ArrayList<Edge>) tmp.getEdges();
            if (!tmp.getVisited()) {
                for (int i = 0; i < edgesOfTmp.size(); i++) {
                    minHeap.put(edgesOfTmp.get(i));
                }
                tmp.setVisited();
            }
            if (!minHeap.isEmpty()) {
                poppedEdge = minHeap.pop(graph);
                if (poppedEdge == null)
                    break;
                if (minSpanningTree.length() > 0)
                    minSpanningTree.append('|');
                minSpanningTree.append(poppedEdge.toString());
                tmp = graph.getVertix(poppedEdge.getSecondVertex());
            } else
                break;
        }
        return minSpanningTree.toString();
    }

}
