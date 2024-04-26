package fr.usmb.m1isc.compilation.tp;

public class Node {
    public NodeType type;
    public Object element;
    public Node left;
    public Node right;

    private static int idWhile = 0;
    private static int idIf = 0;


    public Node() {
        type = NodeType.NONE;
        element = null;
        left = null;
        right = null;
    }

    public Node(NodeType type) {
        this.type = type;
        element = type;
        left = null;
        right = null;
    }

    public Node(Object element) {
        type = NodeType.NONE;
        this.element = element;
        left = new Node();
        right = new Node();
    }

    public Node(Object element, Node left, Node right) {
        type = NodeType.NONE;
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public Node(NodeType type, Object element) {
        this.type = type;
        this.element = element;
        left = new Node();
        right = new Node();
    }

    public Node(NodeType type, Node left, Node right) {
        this.type = type;
        this.element = type;
        this.left = left;
        this.right = right;
    }

    public Node(NodeType type, Object element, Node left, Node right) {
        this.type = type;
        this.element = element;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return switch (type) {
            case SEMICOLON -> left.toString() + right.toString();
            case LET -> right.toString() +
                    "\tmov " + left.element + ", eax\n";
            case VAR, VALUE -> "\tmov eax, " + element + "\n";
            case UNARY_MINUS -> "\tmov eax, " + element + "\n" +
                    "\tpush eax\n" +
                    "\tmov eax, 0\n" +
                    "\tpop ebx\n" +
                    "\tsub eax, ebx\n";
            case OUTPUT -> left.toString() +
                    "\tout eax\n";
            case INPUT -> "\tin eax\n";
            case OPERATOR -> {
                String op = switch (element.toString()) {
                    case "*" -> "\tmul eax, ebx\n";
                    case "/" -> """
                            \tdiv ebx, eax
                            \tmov eax, ebx
                            """;
                    case "+" -> "\tadd eax, ebx\n";
                    case "-" -> """
                            \tsub ebx, eax
                            \tmov eax, ebx
                            """;
                    case "%" -> """
                            \tmov ecx,ebx
                            \tdiv ecx,eax
                            \tmul ecx,eax
                            \tsub ebx,ecx
                            \tmov eax,ebx
                            """;
                    default -> "";
                };
                yield left.toString() +
                        "\tpush eax\n" +
                        right.toString() +
                        "\tpop ebx\n" +
                        op;
            }
            case COMPARATOR -> {
                String cp = switch (element.toString()) {
                    case "=" -> "\tjz ";
                    case "<" -> "\tjle ";
                    case "<=" -> "\tjl ";
                    default -> "";
                };
                yield left.toString() +
                        "\tpush eax\n" +
                        right.toString() +
                        "\tpop ebx\n" +
                        "\tsub eax,ebx\n" +
                        cp;
            }
            case WHILE -> {
                idWhile++;
                yield "debut_while_" + idWhile + ":\n" +
                        left.toString() + "faux_gt_" + idWhile + "\n" +
                        "\tmov eax,1\n" +
                        "\tjmp sortie_gt_" + idWhile + "\n" +
                        "faux_gt_" + idWhile + ":\n" +
                        "\tmov eax,0\n" +
                        "sortie_gt_" + idWhile + ":\n" +
                        "\tjz sortie_while_" + idWhile + "\n" +
                        right.toString() +
                        "\tjmp debut_while_" + idWhile + "\n" +
                        "sortie_while_" + idWhile + ":\n";
            }
            case IF_THEN -> {
                idIf++;
                yield "cond_if_" + idIf + ":\n" +
                        left.toString() + "sortie_if_" + idIf + "\n" +
                        "\tjmp then_if_" + idIf + "\n" +
                        "then_if_" + idIf + ":\n" +
                        right.toString() +
                        "\tjmp sortie_if_" + idIf + "\n" +
                        "sortie_if_" + idIf + ":\n";
            }
            case IF_THEN_ELSE -> {
                idIf++;
                yield "cond_if_" + idIf + ":\n" +
                        left.toString() + "else_if_" + idIf + "\n" +
                        "\tjmp then_if_" + idIf + "\n" +
                        right.toString() +
                        "sortie_if_" + idIf + ":\n";
            }
            case THEN_ELSE -> "then_if_" + idIf + ":\n" +
                    left.toString() +
                    "\tjmp sortie_if_" + idIf + "\n" +
                    "else_if_" + idIf + ":\n" +
                    right.toString() +
                    "\tjmp sortie_if_" + idIf + "\n";
            default -> "";
        };
    }
}