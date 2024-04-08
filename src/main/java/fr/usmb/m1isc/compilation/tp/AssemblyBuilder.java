package fr.usmb.m1isc.compilation.tp;

import java.util.ArrayList;

public class AssemblyBuilder {
    private final Node tree;

    public AssemblyBuilder(Node tree) {
        this.tree = tree;
    }


    /**
     * Converts a node to a variable string representation.
     *
     * @param node the node to convert.
     * @return the variable string representation.
     */
    private String toVariable(Node node) {
        if (node.isVariable()) {
            return ((Node) node.getElement()).getLeft() + " DD";
        } else {
            return "";
        }
    }

    /**
     * Retrieves the variables of the program.
     *
     * @return a list of the variables of the program.
     */
    private ArrayList<String> getVariables(Node currentNode) {
        ArrayList<String> variables = new ArrayList<>();
        if (currentNode == null) {
            return variables;
        } else if (currentNode.isVariable()) {
            variables.add(toVariable(currentNode));
        } else {
            variables.addAll(getVariables(currentNode.getLeft()));
            variables.addAll(getVariables(currentNode.getRight()));
        }

        return variables;
    }

    /**
     * Retrieves the program lines to be executed.
     * @return the program lines.
     */
    private ArrayList<String> getProgramLines(Node currentNode) {
        ArrayList<String> programLines = new ArrayList<>();
        
        return programLines;
    }


    /**
     * Generates the code segment of the assembly program.
     * @return the code segment of the assembly program.
     */
    private String generateCodeSegment() {
        StringBuilder codeSegment = new StringBuilder("CODE SEGMENT\n");

        ArrayList<String> lines = getProgramLines(tree);
        for (String line : lines) {
            codeSegment.append("\t").append(line).append("\n");
        }

        codeSegment.append("CODE SEGMENT\n");

        return codeSegment.toString();
    }

    /**
     * Generates the data segment of the assembly program.
     *
     * @return the data segment of the assembly program.
     */
    private String generateDataSegment() {
        StringBuilder dataSegment = new StringBuilder("DATA SEGMENT\n");

        ArrayList<String> variables = getVariables(tree);
        for (String variable : variables) {
            dataSegment.append("\t").append(variable).append("\n");
        }

        dataSegment.append("DATA ENDS\n");

        return dataSegment.toString();
    }

    /**
     * Generates an assembly program from the given tree.
     */
    public String build() {
        StringBuilder program = new StringBuilder();

        program.append(generateDataSegment());
        program.append(generateCodeSegment());

        return program.toString();
    }
}
