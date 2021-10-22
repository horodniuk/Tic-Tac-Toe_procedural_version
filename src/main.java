import java.util.Random;
import java.util.Scanner;

/**
 * @author devonline
 * @link http://devonline.academy/java
 * Tic-Tac-Toe_procedural version
 */

public class main {

    public static void main(String[] args) {
        System.out.println("Use the following mapping table to specify a cell using numbers from 1 to 9:");
        printTableMapping();
        char[][] gameTable = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        if (new Random().nextBoolean()) {
            makeComputerMove(gameTable);
            printGameTable(gameTable);
        }

        while (true) {
            makeUserMove(gameTable);
            printGameTable(gameTable);
            if (isUserWin(gameTable)) {
                System.out.println("YOU WIN!");
                break;
            }
            if (isDraw(gameTable)) {
                System.out.println("Sorry, DRAW!");
                break;
            }
            makeComputerMove(gameTable);
            printGameTable(gameTable);
            if (isComputerWin(gameTable)) {
                System.out.println("COMPUTER WIN!");
                break;
            }

            if (isDraw(gameTable)) {
                System.out.println("Sorry, DRAW!");
                break;
            }
        }
        System.out.println("GAME OVER!");


    }

                  /* -------------
                   | 7 | 8 | 9 |
                   -------------
                   | 4 | 5 | 6 |
                   -------------
                   | 1 | 2 | 3 |
                   -------------*/

    private static void printTableMapping() {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'}
        };
        printGameTable(mappingTable);
    }

    private static void printGameTable(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            System.out.println("----------");
            for (int j = 0; j < 3; j++) {
                System.out.print("|" + gameTable[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("----------");
    }

    private static void makeUserMove(char[][] gameTable) {

        while (true) {
            System.out.println("Please type number between 1 and 9:");
            String string = new Scanner(System.in).nextLine();
            if (string.length() == 1) {
                char digit = string.charAt(0);
                if (digit >= '0' && digit <= '9') {
                    if (makeUserMoveToCell(gameTable, digit)) {
                        return;
                    }
                }

            }
        }
    }


    private static boolean makeUserMoveToCell(char[][] gameTable, char digit) {
        char[][] mappingTable = {
                {'7', '8', '9'},
                {'4', '5', '6'},
                {'1', '2', '3'}
        };
        for (int i = 0; i < mappingTable.length; i++) {
            for (int j = 0; j < mappingTable.length; j++) {
                if (mappingTable[i][j] == digit) {
                    if (gameTable[i][j] == ' ') {
                        gameTable[i][j] = 'x';
                        return true;
                    } else {
                        System.out.println("Can't make a move, because the cell is not free! Try again!");
                        return false;
                    }
                }
            }
        }
        return false;
    }


    private static void makeComputerMove(char[][] gameTable) {
        boolean isMadeMove = false;
        isMadeMove = tryToMakeMove('0', 'x', gameTable);

        if (!isMadeMove) {
            isMadeMove = tryToMakeMove('x', '0', gameTable);

            if (!isMadeMove) {
                goToCheckingNearestMove('0', 'x', gameTable);
            }
        }
        System.out.println("computer move - " + isMadeMove);
    }

    private static boolean tryToMakeMove(char searchSymbol, char skipSymbol, char[][] gameTable) {
        if (tryToMakeMoveByHorizontal(searchSymbol, skipSymbol, gameTable, 2, 1)) {
            return true;
        }
        if (tryToMakeMoveByVertical(searchSymbol, skipSymbol, gameTable, 2, 1)) {
            return true;
        }
        if (tryToMakeMoveByDiagonal1(searchSymbol, skipSymbol, gameTable, 2, 1)) {
            return true;
        }
        return tryToMakeMoveByDiagonal2(searchSymbol, skipSymbol, gameTable, 2, 1);
    }


    private static boolean tryToMakeMoveByHorizontal(char searchSymbol,
                                                     char skipSymbol,
                                                     char[][] gameTable,
                                                     int expectedSearchSymbolCount,
                                                     int expectedEmptyCount) {
        for (int i = 0; i < 3; i++) {
            int mooveIndex = 0;
            int empty = 0;
            int count = 0;
            for (int j = 0; j < gameTable.length; j++) {
                if (gameTable[i][j] == skipSymbol) {
                    break;
                }
                if (gameTable[i][j] == ' ') {
                    mooveIndex = j;
                    empty++;
                }
                if (gameTable[i][j] == searchSymbol) {
                    count++;
                }
            }
            if (count == expectedSearchSymbolCount && empty == expectedEmptyCount) {
                gameTable[i][mooveIndex] = '0';
                return true;
            }
        }
        return false;
    }

    private static boolean tryToMakeMoveByVertical(char searchSymbol,
                                                   char skipSymbol,
                                                   char[][] gameTable,
                                                   int expectedSearchSymbolCount,
                                                   int expectedEmptyCount) {
        for (int i = 0; i < 3; i++) {
            int mooveIndex = 0;
            int empty = 0;
            int count = 0;
            for (int j = 0; j < gameTable.length; j++) {
                if (gameTable[j][i] == skipSymbol) {
                    break;
                }
                if (gameTable[j][i] == ' ') {
                    mooveIndex = j;
                    empty++;
                }
                if (gameTable[j][i] == searchSymbol) {
                    count++;
                }
            }
            if (count == expectedSearchSymbolCount && empty == expectedEmptyCount) {
                gameTable[mooveIndex][i] = '0';
                return true;
            }

        }
        return false;
    }

    private static boolean tryToMakeMoveByDiagonal1(char searchSymbol,
                                                    char skipSymbol,
                                                    char[][] gameTable,
                                                    int expectedSearchSymbolCount,
                                                    int expectedEmptyCount) {
        for (int i = 0; i < 1; i++) {
            int mooveIndex = 0;
            int empty = 0;
            int count = 0;
            for (int j = 0; j < gameTable.length; j++) {
                if (gameTable[j][j] == skipSymbol) {
                    break;
                }
                if (gameTable[j][j] == ' ') {
                    mooveIndex = j;
                    empty++;
                }
                if (gameTable[j][j] == searchSymbol) {
                    count++;
                }
            }
            if (count == expectedSearchSymbolCount && empty == expectedEmptyCount) {
                gameTable[mooveIndex][mooveIndex] = '0';
                return true;
            }
        }
        return false;
    }

    private static boolean tryToMakeMoveByDiagonal2(char searchSymbol,
                                                    char skipSymbol,
                                                    char[][] gameTable,
                                                    int expectedSearchSymbolCount,
                                                    int expectedEmptyCount) {
        for (int i = 0; i < 1; i++) {
            int backUpwards = gameTable.length - 1;
            int mooveIndex = 0;
            int mooveIndexSecond = 0;
            int empty = 0;
            int count = 0;

            for (int j = 0; j < gameTable.length; j++) {
                if (gameTable[j][backUpwards] == skipSymbol) {
                    break;
                }
                if (gameTable[j][backUpwards] == ' ') {
                    mooveIndex = backUpwards;
                    mooveIndexSecond = j;
                    empty++;
                }
                if (gameTable[j][backUpwards] == searchSymbol) {
                    count++;
                }
                if (j < 2) {
                    backUpwards--;
                }
            }
            if (count == expectedSearchSymbolCount && empty == expectedEmptyCount) {
                gameTable[mooveIndexSecond][mooveIndex] = '0';
                return true;
            }
        }
        return false;
    }

    private static boolean tryToMakeMoveCenter(char[][] gameTable) {
        if (gameTable[1][1] == ' ') {
            gameTable[1][1] = '0';
            return true;
        } return false;
    }

    private static void makeComputerMoveRandom(char[][] gameTable) {
        Random random = new Random();
        while (true) {
            int row = random.nextInt(3);
            int col = random.nextInt(3);
            if (gameTable[row][col] == ' ') {
                gameTable[row][col] = '0';
                return;
            }
        }
    }




    private static void goToCheckingNearestMove(char searchSymbol, char skipSymbol, char[][] gameTable) {
        if (tryToMakeMoveCenter(gameTable)){
           return;
        }



        if (tryToMakeMoveByHorizontal(searchSymbol, skipSymbol, gameTable, 2, 1)) {
            return;
        }
        if (tryToMakeMoveByVertical(searchSymbol, skipSymbol, gameTable, 2, 1)) {
            return;
        }
        if (tryToMakeMoveByDiagonal1(searchSymbol, skipSymbol, gameTable, 2, 1)) {
            return;
        }
        if (tryToMakeMoveByDiagonal2(searchSymbol, skipSymbol, gameTable, 2, 1)) {
            return;
        }

        makeComputerMoveRandom(gameTable);
    }



    private static boolean isUserWin(char[][] gameTable) {
        return isWinner(gameTable, 'x');
    }

    private static boolean isComputerWin(char[][] gameTable) {
        return isWinner(gameTable, '0');
    }

    private static boolean isWinner(char[][] gameTable, char ch) {
        for (int i = 0; i < 3; i++) {
            if (gameTable[i][0] == gameTable[i][1] && gameTable[i][2] == gameTable[i][0] && gameTable[i][0] == ch) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameTable[0][i] == gameTable[1][i] && gameTable[2][i] == gameTable[0][i] && gameTable[0][i] == ch) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (gameTable[0][0] == gameTable[1][1] && gameTable[2][2] == gameTable[0][0] && gameTable[0][0] == ch) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (gameTable[0][2] == gameTable[1][1] && gameTable[0][2] == gameTable[2][0] && gameTable[0][2] == ch) {
                return true;
            }
        }
        return false;
    }


    private static boolean isDraw(char[][] gameTable) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gameTable[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

}