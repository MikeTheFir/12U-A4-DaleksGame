
/**
 * This class models a Delek in the game. A Delek has a position and can advance
 * towards the Doctor.
 */
public class Dalek {

    private int row;
    private int col;
    private boolean hasCrashed;

    /**
     * Initializes the variables for a Dalek.
     *
     * @param theRow The row this Dalek starts at.
     * @param theCol The column this Dalek starts at.
     */
    public Dalek(int theRow, int theCol) {
        this.col = theCol;
        this.row = theRow;
    }

    /**
     * Attempts to move the Dalek towards the Doctor by the most direct route,
     * moving up, down, right, left or diagonally. For example, if the Doctor is
     * above and to the right of a Dalek, it will move diagonally. If the Doctor
     * is directly below a Dalek, it will move down.
     *
     * @param doc The Doctor to move towards.
     */
    public void advanceTowards(Doctor doc) {
        // find the doctors position
        int row = doc.getRow();
        int col = doc.getCol();

        // find the distance between them
        int moveCol = this.col - col;
        int moveRow = this.row - row;

        // as long as the dalek has not crashed it can move towards the doctor
        if (hasCrashed == false) {
            // add one to the row to get the dalek closer to the doctor
            if (moveRow <= 0) {
                this.row++;
            }
            // remove one from the row to get dalke closer to the doctor
            if (moveRow >= 0) {
                this.row--;
            }
            // add one to the column to get the dalek closer to the doctor
            if (moveCol <= 0) {
                this.col++;
            }
            // remove one from the column to get dalke closer to the doctor
            if (moveCol >= 0) {
                this.col--;
            }

        }
    }

    /**
     * Returns the row of this Dalek.
     *
     * @return This Dalek's row.
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Returns the column of this Dalek.
     *
     * @return This Dalek's column.
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Sets the Dalek to be in a crashed state.
     */
    public void crash() {
        this.hasCrashed = true;
    }

    /**
     * Returns whether or not this Dalek has crashed.
     *
     * @return true if this Dalek has crashed, false otherwise
     */
    public boolean hasCrashed(Dalek dalek) {
        // see if any of the daleks are ontop of eachother
        if (this.row == dalek.getRow() && this.col == dalek.getCol()) {
            return true;
        } else {
            return false;
        }
    }
}