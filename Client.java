import javax.swing.JOptionPane;
import java.util.*;
public class Client{
    public static Scanner console = new Scanner(System.in);
    public static int rows;
    public static int columns;
    public static int turn =0;
    public static int wincond = 0; 
    public static int x;
    public static int y; 
    public static void main(String[] args){
        constructor();
    }
    private static void constructor(){
        System.out.println("Hello User,\nhow many rows would you like me to construct?");
        rows = start("Rows: ");
        if(rows < 2){
            throw new IllegalArgumentException();
        }
        System.out.println("And how many columns would you like me to construct?");
        columns = start("Columns: ");
        if(columns < 2){
            throw new IllegalArgumentException();
        }
        System.out.println("How many would you like to win by?");
        wincond = start("Wincondition: ");
        turn = 0;
        boolean status = false;
        Board BX = new Board(rows, columns, wincond);
        Board BO = new Board(rows, columns, wincond);
        while(status != true){
            if(wincond > 1){
                //System.out.print("\033[H\033[2J");
                System.out.print('\u000C'); 
                JOptionPane.showMessageDialog(null, visualizer(BX, BO));
                ask(BX, BO);
                if(turn >= wincond){
                    if(test(BX, x, y) == false){
                        status = test(BO, x, y);
                    } else{
                        status = true;
                    }
                    if(turn == rows*columns-1){
                        status = true;
                    }
                }
                turn++;          
            } else{
                throw new IllegalArgumentException();
            }
        }
        JOptionPane.showMessageDialog(null, visualizer(BX, BO));
    }
    private static int start(String IN){
        int search;
        if(IN.equals("Wincondition: ")){
            search = Integer.parseInt(JOptionPane.showInputDialog("Enter Wincondition: "));
        } else if(IN.equals("Rows: ")){
            search = Integer.parseInt(JOptionPane.showInputDialog("Enter Rows:"));
        } else{
            search = Integer.parseInt(JOptionPane.showInputDialog("Enter Columns"));
        }
        JOptionPane.showMessageDialog(null, IN + search); 
        return search;
    }
    private static boolean test(Board BX, int x, int y){
        int count = 0;
        int i = 0;
        while(y+i <columns && BX.contains(x, y+i) == true ){
            count++;
            if(count ==wincond){
                return true;
            }
            i++;
        }
        i = 0;
        while(y-i-1 < columns && y-i-1 >= 0 && BX.contains(x, y-i-1) == true){
            i++;
            count++;
        }
        if (count == wincond){
            return true;
        } else{
            return vertTest(BX, x, y);
        }
    }
    private static boolean vertTest(Board BX, int x, int y){
        int count = 0;
        int i = 0;
        while(x+i <rows && BX.contains(x+i, y) == true ){
            count++;
            if(count ==wincond){
                return true;
            }
            i++;
        }
        i = 0;
        while(x-i-1 < rows && x-i-1 >= 0 && BX.contains(x-i-1, y) == true){
            i++;
            count++; 
        }
        if (count == wincond){
            return true;
        } else{
            return diagTestL(BX, x, y);
        }
    }
    private static boolean diagTestL(Board BX, int x, int y){
        int count = 0;
        int i = 0;
        while(x+i <rows && y+i < columns && BX.contains(x+i, y+i) == true ){
            count++;
            if(count ==wincond){
                return true;
            }
            i++;
        }
        i = 0;
        while(x-i-1 >= 0 && y-i-1 >=0 && BX.contains(x-i-1, y-i-1) == true){
            i++;
            count++;
        }
        if (count == wincond){
            return true;
        } else{
            return diagTestR(BX, x, y);
        }
    }
    private static boolean diagTestR(Board BX, int x, int y){
        int count = 0;
        int i = 0;
        while(x-i >=0 && y+i <rows && BX.contains(x-i, y+i) == true ){
            count++;
            if(count ==wincond){
                return true;
            }
            i++;
        }
        i = 0;
        while(x+i+1 <columns && y+i+1 <columns && BX.contains(x+i+1, y+i+1) == true){
            i++;
            count++;
        }
        if (count == wincond){
            return true;
        } else{
            return false;
        }
    }
    private static String visualizer(Board BX, Board BO){
        String str = "";
        for(int j = 0; j<rows*7; j++){
            //*9 if reg
            //System.out.print("-");
            str += "-";
        }
        str += "\n";
        //System.out.println();
        for(int i = 0; i<columns; i++){
            for(int j = 0; j<rows; j++){
                //System.out.print("|   ");
                str += "|   ";
                if (BX.contains(i, j)){
                    //System.out.print("X");
                    str += "X";
                } else if (BO.contains(i, j)){
                    //System.out.print("O");
                    str += "O";
                } else{
                   //System.out.print(" ");
                    str += " ";
                }
                //System.out.print("   |");
                str += "   |";
            }
            str += "\n";
            //System.out.println();
            for(int j = 0; j<rows*7; j++){
                //System.out.print("-");
                //*9 if reg
                str += "-";
            }
            str += "\n";
            //System.out.println();
        }
        return str;
    }
    private static void ask(Board BX, Board BO){
        x = ask("X");
        y = ask("Y");
        if(x<rows && y < columns && BX.contains(x, y) == false && BO.contains(x,y) == false){
            if(turn % 2 == 0){
                BX.add(x, y);
            } else{
                BO.add(x, y);
            }
        } else{
            JOptionPane.showInputDialog("Please try again:");
            visualizer(BX, BO);
            ask(BX, BO);
        } 
        //tx = null;
        // ty = null;
        //p = null;
    }
    private static int ask(String C){
        int search;
        if(turn % 2 == 0){
            search = Integer.parseInt(JOptionPane.showInputDialog("Player " + "X's " + "turn\n" + "Position " + C + ": "));
        } else{
            search = Integer.parseInt(JOptionPane.showInputDialog("Player " + "O's " + "turn\n" + "Position " + C + ": "));
        }
        JOptionPane.showMessageDialog(null, C + ": " + search); 
        return search;
    }
}
    