package pl.edu.pw.ee;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.edu.pw.ee.services.MinSpanningTree;

public class KruskalAlgorithm implements MinSpanningTree {

    private List<Edge> edges;
    private RBTMap<String, Integer> trees;

    KruskalAlgorithm() {
        edges = new ArrayList<>();
        trees = new RBTMap<>();
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
        if (!trees.checkConnectionKruskal()) {
            throw new IllegalArgumentException("Input graph has to be connected.");
        }
    }

    private void buildGraph(Scanner scanner) {
        int vertixId = 0;
        String currentLine = null;
        Matcher matcher;
        String firstVertix;
        String secondVertix;
        Edge currentEdge;
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
            if (trees.getValue(firstVertix) == null)
                trees.setValue(firstVertix, vertixId++);
            if (trees.getValue(secondVertix) == null)
                trees.setValue(secondVertix, vertixId++);
            currentEdge = new Edge(firstVertix, secondVertix, Integer.parseInt(matcher.group(3)));
            if (!edges.contains(currentEdge))
                edges.add(currentEdge);
        }
        Collections.sort(edges);
    }

    private String buildMinSpanningTree() {
        StringBuilder minSpanningTree = new StringBuilder();
        Integer firstVertixId;
        Integer secondVertixId;
        for (int i = 0; i < edges.size(); i++) {
            firstVertixId = trees.getValue(edges.get(i).getFirstVertex());
            secondVertixId = trees.getValue(edges.get(i).getSecondVertex());
            if (!firstVertixId.equals(secondVertixId)) {
                trees.updateIds(firstVertixId, secondVertixId);
                if (minSpanningTree.length() > 0)
                    minSpanningTree.append('|');
                minSpanningTree.append(edges.get(i));
            }
        }
        return minSpanningTree.toString();
    }

}
