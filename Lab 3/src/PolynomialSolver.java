
class NodeQasem {
    int data;
    NodeQasem next;

    NodeQasem(int d) { data = d; next = null; }
}

class Nod {
    int coefficient;
    int exponent;
    Nod next;

    Nod(int coeff, int exp) { coefficient = coeff; exponent = exp; next = null; }
}

public class PolynomialSolver {
    Nod headA;    // Head for A terms
    Nod headB;
    Nod headC;
    Nod headR;
    NodeQasem intHead; // linked list for storing input values

    public void add(int value) {
        NodeQasem newNode = new NodeQasem(value);

        if (intHead == null) {
            intHead = newNode;
        } else {
            NodeQasem current = intHead;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    void setFromLinkedList(NodeQasem coeffHead, char poly) {
        Nod head = null, current = null;

        int length = 0;
        NodeQasem temp = coeffHead;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        int exponent = length - 1;

        temp = coeffHead;
        while (temp != null) {
            int coefficient = temp.data;
            Nod newNode = new Nod(coefficient, exponent);

            if (head == null) {
                head = newNode;
                current = head;
            } else {
                current.next = newNode;
                current = current.next;
            }

            exponent--;
            temp = temp.next;
        }

        switch (poly) {
            case 'A': headA = head; break;
            case 'B': headB = head; break;
            case 'C': headC = head; break;
            case 'R': headR = head; break;
            default: System.out.println("Error"); break;
        }
    }

    void print(char poly) {
        Nod head = null;
        switch (poly) {
            case 'A': head = headA; break;
            case 'B': head = headB; break;
            case 'C': head = headC; break;
            case 'R': head = headR; break;
            default: System.out.println("Error"); return;
        }

        if (head == null) {
            return;
        }

        StringBuilder result = new StringBuilder();
        Nod temp = head;

        while (temp != null) {
            int coeff = temp.coefficient;

            if (temp.exponent == 0) {
                result.append(coeff);
            } else if (temp.exponent == 1) {
                if (coeff == 1) {
                    result.append("x");
                } else if (coeff == -1) {
                    result.append("-x");
                } else {
                    result.append(coeff).append("x");
                }
            } else {
                if (coeff == 1) {
                    result.append("x^").append(temp.exponent);
                } else if (coeff == -1) {
                    result.append("-x^").append(temp.exponent);
                } else {
                    result.append(coeff).append("x^").append(temp.exponent);
                }
            }

            if (temp.next != null && temp.next.coefficient >= 0) {
                result.append(" + ");
            } else if (temp.next != null) {
                result.append(" ");
            }

            temp = temp.next;
        }

        System.out.println(result.toString());
    }

    Nod addPolynomials(Nod poly1, Nod poly2) {
        Nod resultHead = null, current = null;

        while (poly1 != null || poly2 != null) {
            int coeff = 0;
            int exp;
            if (poly1 == null) {
                coeff = poly2.coefficient;
                exp = poly2.exponent;
                poly2 = poly2.next;
            } else if (poly2 == null) {
                coeff = poly1.coefficient;
                exp = poly1.exponent;
                poly1 = poly1.next;
            } else if (poly1.exponent == poly2.exponent) {
                coeff = poly1.coefficient + poly2.coefficient;
                exp = poly1.exponent;
                poly1 = poly1.next;

                poly2 = poly2.next;
            } else if (poly1.exponent > poly2.exponent) {
                coeff = poly1.coefficient;
                exp = poly1.exponent;
                poly1 = poly1.next;
            } else {
                coeff = poly2.coefficient;
                exp = poly2.exponent;
                poly2 = poly2.next;
            }

            if (coeff != 0) {
                Nod newNode = new Nod(coeff, exp);
                if (resultHead == null) {
                    resultHead = newNode;
                    current = resultHead;
                } else {
                    current.next = newNode;
                    current = current.next;
                }
            }
        }
        return resultHead;
    }

    void addPolynomialsToR(char poly1, char poly2) {
        Nod p1 = null, p2 = null;
        switch (poly1) {
            case 'A': p1 = headA; break;
            case 'B': p1 = headB; break;
            case 'C': p1 = headC; break;
            default: System.out.println("Error"); return;
        }

        switch (poly2) {
            case 'A': p2 = headA; break;
            case 'B': p2 = headB; break;
            case 'C': p2 = headC; break;
            default: System.out.println("Error"); return;
        }

        if (p1 == null || p2 == null) {
            System.out.println("Error");
            return;
        }

        headR = addPolynomials(p1, p2);
        print('R');  // Print R immediately after performing the addition
    }

    Nod subPolynomials(Nod poly1, Nod poly2) {
        Nod resultHead = null, current = null;

        while (poly1 != null || poly2 != null) {
            int coeff;
            int exp;

            if (poly1 == null) {
                coeff = -poly2.coefficient;
                exp = poly2.exponent;
                poly2 = poly2.next;
            } else if (poly2 == null) {
                coeff = poly1.coefficient;
                exp = poly1.exponent;
                poly1 = poly1.next;
            } else if (poly1.exponent == poly2.exponent) {
                coeff = poly1.coefficient - poly2.coefficient;
                exp = poly1.exponent;
                poly1 = poly1.next;
                poly2 = poly2.next;
            } else if (poly1.exponent > poly2.exponent) {
                coeff = poly1.coefficient;
                exp = poly1.exponent;
                poly1 = poly1.next;
            } else {
                coeff = -poly2.coefficient;
                exp = poly2.exponent;
                poly2 = poly2.next;
            }

            if (coeff != 0) {
                Nod newNode = new Nod(coeff, exp);
                if (resultHead == null) {
                    resultHead = newNode;
                    current = resultHead;
                } else {
                    current.next = newNode;
                    current = current.next;
                }
            }
        }
        return resultHead;
    }

    void subPolynomialsToR(char poly1, char poly2) {
        Nod p1 = null, p2 = null;
        switch (poly1) {
            case 'A': p1 = headA; break;
            case 'B': p1 = headB; break;
            case 'C': p1 = headC; break;
            default: System.out.println("Error"); return;
        }

        switch (poly2) {
            case 'A': p2 = headA; break;
            case 'B': p2 = headB; break;
            case 'C': p2 = headC; break;
            default: System.out.println("Error"); return;
        }

        if (p1 == null || p2 == null) {
            System.out.println("Error");
            return;
        }

        headR = subPolynomials(p1, p2);
        print('R');  // Print R immediately after performing the subtraction
    }

    int evaluatePolynomial(Nod poly, int x) {
        int result = 0;

        while (poly != null) {
            result += poly.coefficient * Math.pow(x, poly.exponent);
            poly = poly.next;
        }

        return result;
    }

    void evaluatePolynomialAt(int x, char polynomial) {
        Nod selectedPoly = null;

        switch (polynomial) {
            case 'A':
                selectedPoly = headA;
                break;
            case 'B':
                selectedPoly = headB;
                break;
            case 'C':
                selectedPoly = headC;
                break;
            default:
                System.out.println("Error");
                return;
        }

        if (selectedPoly != null) {
            int result = evaluatePolynomial(selectedPoly, x);
            System.out.println(result);
        } else {
            System.out.println("Error");
        }
    }

    void clear(char poly) {
        switch (poly) {
            case 'A': headA = null; break;
            case 'B': headB = null; break;
            case 'C': headC = null; break;
            default: System.out.println("Error"); return;
        }
        System.out.println("[]");
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PolynomialSolver PS = new PolynomialSolver();

        while (in.hasNext()) {
            String operation = in.nextLine().trim();
            switch (operation) {
                case "clear":
                    String polyToClear = in.nextLine().trim();
                    if (polyToClear.equals("A")  polyToClear.equals("B")  polyToClear.equals("C")) {
                    PS.clear(polyToClear.charAt(0));
                } else {
                    System.out.println("Error");
                }
                break;

                case "set":
                    String polyName = in.nextLine().trim();
                    if (!polyName.equals("A") && !polyName.equals("B") && !polyName.equals("C")) {
                        System.out.println("Error");
                        break;
                    }

                    String input = in.ne xtLine().replaceAll("\\[|\\]", "");
                    if (!input.isEmpty()) {
                        PS.intHead = null;
                        String[] s = input.split(", ");
                        for (String value : s) {
                            try {
                                int number = Integer.parseInt(value.trim());
                                PS.add(number);
                            } catch (NumberFormatException e) {
                                System.out.println("Error");
                            }
                        }
                        PS.setFromLinkedList(PS.intHead, polyName.charAt(0));
                    }
                    break;

                case "print":
                    String polyToPrint = in.nextLine().trim();
                    if (!polyToPrint.equals("A") && !polyToPrint.equals("B") && !polyToPrint.equals("C") && !polyToPrint.equals("R")) {
                        System.out.println("Error");
                    } else {
                        PS.print(polyToPrint.charAt(0));
                    }
                    break;

                case "add":
                    String poly1 = in.nextLine().trim();
                    String poly2 = in.nextLine().trim();
                    if (!poly1.equals("A") && !poly1.equals("B") && !poly1.equals("C") ||
                            !poly2.equals("A") && !poly2.equals("B") && !poly2.equals("C")) {
                        System.out.println("Error");
                    } else {
                        PS.addPolynomialsToR(poly1.charAt(0), poly2.charAt(0));
                    }
                    break;

                case "sub":
                    String poly3 = in.nextLine().trim();
                    String poly4 = in.nextLine().trim();
                    if (!poly3.equals("A") && !poly3.equals("B") && !poly3.equals("C") ||
                            !poly4.equals("A") && !poly4.equals("B") && !poly4.equals("C")) {
                        System.out.println("Error");
                    } else {
                        PS.subPolynomialsToR(poly3.charAt(0), poly4.charAt(0));
                    }
                    break;

                case "eval":
                    String polyToEval = in.nextLine().trim();
                    if (!polyToEval.equals("A") && !polyToEval.equals("B") && !polyToEval.equals("C")) {
                        System.out.println("Error");
                    } else {
                        int x = in.nextInt();
                        PS.evaluatePolynomialAt(x, polyToEval.charAt(0));
                    }
                    break;

                case "mult":
                    String poly8 = in.nextLine().trim();
                    String poly9 = in.nextLine().trim();
                    if (!poly8.equals("A") && !poly8.equals("B") && !poly8.equals("C") ||
                            !poly9.equals("A") && !poly9.equals("B") && !poly9.equals("C")) {
                        System.out.println("Error");
                    } else {
                        PS.subPolynomialsToR(poly8.charAt(0), poly9.charAt(0));
                    }
                    break;

                default:
                    System.out.println("Error");
            }
        }
    }
}