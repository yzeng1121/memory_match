import java.util.*;

public class memory_match_1d_array {   
     
    public static void main(String[] args) {
        final int NUM_PAIRS = 4; 
        int board[] = new int[NUM_PAIRS * 2];
        boolean printElement[] = new boolean[NUM_PAIRS * 2 + 1];
        int temp, position1, position2, i;
    
        // fill board will fill with [1, 1, 2, 2, 3, 3, 4, 4]
        int k = 1;
        for (i = 0 ; i < NUM_PAIRS * 2; i += 2) {
            board[i] = k;
            board[i + 1] = k;
            k++;
        }

        // shuffle the board 1000 times
        for (i = 1; i <= 1000; i++) {   // pick 2 random positions to swap
            position1 = (int) (Math.random() * (board.length - 1) + 1);
            position2 = (int) (Math.random() * (board.length - 1) + 1);   
           
            temp = board[position1];
            board[position1] = board[position2];
            board[position2] = temp;
        }
        
        boolean win = false; 
        int score1 = 0; int score2 = 0; String p1; String p2; int c1 = -1; int c2 = -1;
        Scanner console = new Scanner(System.in);
        final int indexes[] = {1, 2, 3, 4, 5, 6, 7, 8};
        
        System.out.println("Player 1: ");
        p1 = console.next();
        System.out.println("Player 2: ");
        p2 = console.next();
        String currPlayer = p1;
        
        String cont;
        
        // Starts the game until ends: 1) a player gains more than two points (give option to continue?) 
        //                             2) all the cards of the game are revealed
        while (win == false || checkAll(printElement)) {
            System.out.println("\n\n");
            System.out.println("\tPlayer 1: " + p1 + "\t\tScore: " + score1);
            System.out.println("\tPlayer 2: " + p2 + "\t\tScore: " + score2);
            System.out.println("\n");
            
            // first choice
            System.out.println(Arrays.toString(indexes));
            for (i = 0; i < board.length; i++) { // prints the game board
                if (printElement[i] == true) { // || i == guess1 || i == guess2)
                    System.out.print(" " + board[i] + " ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println("\n\n");
            
            System.out.println(currPlayer + ", what is your 1st choice?");
            c1 = console.nextInt() - 1;
            printElement[c1] = true;
            
            // second choice
            System.out.println(Arrays.toString(indexes));
            for (i = 0; i < board.length; i++) { // prints the game board
                if (printElement[i] == true) { // || i == guess1 || i == guess2)
                    System.out.print(" " + board[i] + " ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println("\n\n");
            
            System.out.println(p1 + ", what is your 2nd choice?");
            c2 = console.nextInt() - 1;
            printElement[c2] = true;
            
            // results of this round
            System.out.println(Arrays.toString(indexes));
            for (i = 0; i < board.length; i++) { // prints the game board
                if (printElement[i] == true) { // || i == guess1 || i == guess2)
                    System.out.print(" " + board[i] + " ");
                } else {
                    System.out.print(" * ");
                }
            }
            
            if (board[c1] != board[c2]) {
                if (currPlayer == p1) {
                    System.out.println("\n\n>>>>> NO Match! " + p2 + "'s turn. <<<<<<");
                    currPlayer = p2;
                } else {
                    System.out.println("\n\n>>>>> NO Match! " + p1 + "'s turn. <<<<<<");
                    currPlayer = p1;
                }
                printElement[c1] = false;
                printElement[c2] = false;
            } else {
                if (currPlayer == p1) {
                    score1++;
                    System.out.println("\n\n>>>>> MATCH!! You get to go again! <<<<<<");
                } else {
                    score2++;
                    System.out.println("\n\n>>>>> MATCH!! You get to go again! <<<<<<");
                }
                if (score1 == 4 || score2 == 4) {
                    System.out.println("\n\n>>>>> Congratulations! You've won the game! <<<<<<");
                    break;
                } else if (score1 == 2 && score2 == 2) {
                    System.out.println("\n\n>>>>> TIE! You've both won the game! <<<<<<");
                    break;
                }
                if (score1 > 2 || score2 > 2) {
                    Scanner console2 = new Scanner(System.in);
                    System.out.println("\n\n>>>>> You have more than two points! Want to continue to game? <<<<<<");
                    cont = console2.nextLine();
                    if (cont.equals("no")) {
                        win = true;
                        System.out.println("\n\n>>>>> Congratulations! You've won the game! <<<<<<");
                    } else if (cont.equals("yes")) {
                        win = false;
                    }
                } 
            }
        }
        
    }
    
    
    public static boolean checkAll(boolean[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == false) {
                return false;
            }
        }
        return true;
    }
}
