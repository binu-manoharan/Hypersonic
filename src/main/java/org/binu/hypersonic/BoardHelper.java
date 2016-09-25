package org.binu.hypersonic;

/**
 * Helper class to process row data in string to int array
 */
public class BoardHelper {
    public char[] convertRow(String rowString) {
        final int rowLength = rowString.length();
        char[] row = new char[rowLength];

        for (int index = 0; index < rowLength; index++) {
            final char charAtIndex = rowString.charAt(index);
            switch (charAtIndex) {
                case '.':
                    row[index] = '.';
                    break;
                case '0':
                    row[index] = '0';
                    break;
                default:
                    assert false: "unhandled char in row string";
            }
        }
        return row;
    }
}
