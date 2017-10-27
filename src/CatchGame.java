
import java.awt.Color;

/**
 * This class manages the interactions between the different pieces of the game:
 * the Board, the Daleks, and the Doctor. It determines when the game is over
 * and whether the Doctor won or lost.
 */
public class CatchGame {

    /**
     * Instance variables go up here Make sure to create a Board, 3 Daleks, and
     * a Doctor
     */
    Board b;
    Dalek Dalek1;
    Dalek Dalek2;
    Dalek Dalek3;
    Doctor Doctor;
    boolean finished;

    /**
     * The constructor for the game. Use it to initialize your game variables.
     * (create people, set positions, etc.)
     */
    public CatchGame() {
        // board
        this.b = new Board(12, 12);
        // first Dalek
        this.Dalek1 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        // second Dalek      
        this.Dalek2 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        // third Dalek
        this.Dalek3 = new Dalek((int) (Math.random() * 12), (int) (Math.random() * 12));
        // Doctor        
        this.Doctor = new Doctor((int) (Math.random() * 12), (int) (Math.random() * 12));

        // determine whether or not the game is finished
        finished = false;
    }

    public boolean hasDoctorCrashed() {
        // check to see if Dalek1 and Doctor have crashed
        if (Doctor.getRow() == Dalek1.getRow() && Doctor.getCol() == Dalek1.getCol()) {
            return true;
            // check to see if Dalek2 and Doctor have crashed
        } else if (Doctor.getRow() == Dalek2.getRow() && Doctor.getCol() == Dalek2.getCol()) {
            return true;
            // check to see if Dalek3 and Doctor have crashed
        } else if (Doctor.getRow() == Dalek3.getRow() && Doctor.getCol() == Dalek3.getCol()) {
            return true;
            // if no one has crashed then return false and continue the game
        } else {
            return false;
        }
    }

    /**
     * The playGame method begins and controls a game: deals with when the user
     * selects a square, when the Daleks move, when the game is won/lost.
     */
    public void playGame() {
        // place all pegs on the board
        //Doctor
        b.putPeg(Color.GREEN, Doctor.getRow(), Doctor.getCol());
        // Dalek1
        b.putPeg(Color.BLACK, Dalek1.getRow(), Dalek1.getCol());
        // Dalek2
        b.putPeg(Color.BLACK, Dalek2.getRow(), Dalek2.getCol());
        // Dalek3
        b.putPeg(Color.BLACK, Dalek3.getRow(), Dalek3.getCol());

        // check to see if anything crashed when it was spawned in
        if (hasDoctorCrashed() == true || Dalek1.hasCrashed(Dalek2)
                || Dalek1.hasCrashed(Dalek3) || Dalek2.hasCrashed(Dalek3)) {
            // tell palyer they lost and to start over
            b.displayMessage("ERROR OCCURED. PLEASE RESTART GAME.");
            finished = true;
            
        }

        // as long as the doctor and one dalek are alive keep playing
        while (!finished) {
            // get the coordinates that have been clicked
            Coordinate click = b.getClick();
            // remove old doctor peg
            b.removePeg(Doctor.getRow(), Doctor.getCol());
            // remove old dalek pegs
            b.removePeg(Dalek1.getRow(), Dalek1.getCol());
            b.removePeg(Dalek2.getRow(), Dalek2.getCol());
            b.removePeg(Dalek3.getRow(), Dalek3.getCol());

            // get new doctor coordinates
            int clickRow = click.getRow();
            int clickCol = click.getCol();

            // find out if the doctor can move
            Doctor.move(clickRow, clickCol);
            if (hasDoctorCrashed() == true) {
                break;
            }

            // put peg down where the new docotr is
            b.putPeg(Color.GREEN, Doctor.getRow(), Doctor.getCol());

            // find the movement of the daleks
            Dalek1.advanceTowards(Doctor);
            Dalek2.advanceTowards(Doctor);
            Dalek3.advanceTowards(Doctor);


            // place the daleks at their new spots
            b.putPeg(Color.BLACK, Dalek1.getRow(), Dalek1.getCol());
            b.putPeg(Color.BLACK, Dalek2.getRow(), Dalek2.getCol());
            b.putPeg(Color.BLACK, Dalek3.getRow(), Dalek3.getCol());

            // check to see if dalek1 collides with dalek2
            if (Dalek1.hasCrashed(Dalek2)) {
                Dalek1.crash();
                Dalek2.crash();
                // create red peg
                b.putPeg(Color.RED, Dalek1.getRow(), Dalek1.getCol());
            }
            // check to see if dalek1 collides with dalek3
            if (Dalek1.hasCrashed(Dalek3)) {
                Dalek1.crash();
                Dalek3.crash();
                // create red peg
                b.putPeg(Color.RED, Dalek1.getRow(), Dalek1.getCol());
            }
            // check to see if dalek2 collides with dalek3
            if (Dalek2.hasCrashed(Dalek3)) {
                Dalek2.crash();
                Dalek3.crash();
                // create red peg
                b.putPeg(Color.RED, Dalek2.getRow(), Dalek2.getCol());
            }
            // check to see if the doctor has lost and been captured
            if (hasDoctorCrashed() == true) {
                b.displayMessage("SORRY. YOU LOSE!");
                finished = true;
            }
            // check to see if the doctor has won and that all daleks are crashed
            if (Dalek1.hasCrashed(Dalek2) && Dalek1.hasCrashed(Dalek3) && Dalek2.hasCrashed(Dalek3)) {
                b.displayMessage("CONGRATS! YOU WIN!!!!");
                finished = true;
            }


        }


    }
}
