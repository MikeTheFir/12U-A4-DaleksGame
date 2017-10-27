
import java.awt.Color;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pistm9061
 */
public class BoardExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Board b = new Board(24, 24);
        
        // place a peg
        b.putPeg(Color.GREEN,3,5);
        b.putPeg(Color.BLUE, (int) (Math.random() * 10));
        // remove a peg
        b.removePeg(3,5);
        
        // put a message
        b.displayMessage("Hello Everyone");
       
        while(true){
        // recieve a click from the user
        Coordinate click = b.getClick();
        int clickRow = click.getRow();
        int clickCol = click.getCol();
        // put a peg at the click
        b.putPeg(Color.BLACK, clickRow, clickCol);
        }
        
    }
}
