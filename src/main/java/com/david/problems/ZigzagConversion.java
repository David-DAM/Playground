package com.david.problems;

public class ZigzagConversion {
    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     *
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     *
     * Write the code that will take a string and make this conversion given a number of rows:
     *
     * string convert(string s, int numRows);
     * @param args
     */
    public static void main(String[] args) {
        String converted = convert("PAYPALISHIRING", 3);

        System.out.println(converted);
    }

    public static String convert(String s, int numRows) {
        String[] split = s.split("");

        int numColums = split.length / numRows + ( (split.length / numRows) / 2 ) + 1;

        String[][] words = new String[numRows][numColums];
        int pointer = 0;
        int x = 0;
        int y = 0;
        int halfTable = numRows / 2;

        while (pointer < split.length){

            if (x < numRows){

                words[x][y] = split[pointer];
                x++;
                pointer++;

            }else {
                y++;

                words[halfTable][y] = split[pointer];
                pointer++;
                x = 0;
                y++;
            }

        }

        StringBuilder converted = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length; j++) {
                if (words[i][j] == null){
                    System.out.print("  ");
                }else {
                    System.out.print(words[i][j]+" ");
                    converted.append(words[i][j]);
                }
            }
            System.out.println(" ");
        }
        return converted.toString();
    }
}
