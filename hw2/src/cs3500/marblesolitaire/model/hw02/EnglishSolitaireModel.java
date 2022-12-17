package cs3500.marblesolitaire.model.hw02;

import java.util.ArrayList;
import java.util.List;

public class EnglishSolitaireModel implements MarbleSolitaireModel {
  private int sRow;
  private int sCol;
  private int armThickness;

  private int size;
  double numberOfElementsPerRow;
  private int score;


  private List<SlotState> board = new ArrayList<>();

  // inside the array <empty, empty, marble marble marble empty empty
  public EnglishSolitaireModel() {
    this(3, 3, 3);
  }

  public EnglishSolitaireModel(int sRow, int sCol) throws IllegalArgumentException {
    this(3, sRow, sCol);
  }

  public EnglishSolitaireModel(int armThickness) throws IllegalArgumentException {
    this(armThickness, armThickness, armThickness);
  }

  /**
   * represents a game of English Solitaire, which is played on a board
   * with a cross-shaped arrangement of slots.
   * The class has several instance variables to keep track of the board,
   * the starting position of the game, and the current score
   * @param armThickness A number to determine the size and shape of the board.
   * @param sRow what row the user will start the game from.
   * @param sCol what column the user will start the game from.
   * @throws IllegalArgumentException if the user provides an invalid, starting row,
   * starting column, or an even or negative arm thickness.
   */
  public EnglishSolitaireModel(int armThickness, int sRow, int sCol) throws IllegalArgumentException {

    //first, check for a bad armThickness
    if (armThickness < 0 || armThickness % 2 == 0) {
      throw new IllegalArgumentException("negtive or even arm thickness");
    }


    // check if starting row and column were placed in a non-empty spot.
    if (
            (sRow < armThickness - 1 && sCol < armThickness - 1) || // top left
                    (sRow < armThickness - 1 && sCol > ((2 * armThickness) - 2)) || // top right
                    (sRow > ((armThickness * 2) - 2) && sCol < armThickness - 1) || // bottom left
                    (sRow > ((armThickness * 2) - 2) && sCol > ((2 * armThickness) - 2)) || // bottom right
                    (sRow < 0 || sRow > ((armThickness * 3) - 2) || sCol > ((armThickness * 3) - 2) || sCol < 0)
    ) {

      throw new IllegalArgumentException("invalid starting row and column");
    }
    this.armThickness = armThickness;
    this.sCol = sCol;
    this.sRow = sRow;
    numberOfElementsPerRow = (armThickness * 3) - 2;

    //initialize the board
    for (int row = 0; row < (armThickness * 3) - 2; row++) {
      for (int col = 0; col < (armThickness * 3) - 2; col++) {
        //check for starting spot

        if (row == sRow && col == sCol) {
          board.add(SlotState.Empty);
        } else if (isInvalidSpot(row, col, armThickness)) {
          board.add(SlotState.Invalid);
        } else {
          board.add(SlotState.Marble);
        }
      }
    }

    size = this.getBoardSize();
    score = countMarbles();
  }

  /**
   * Checks if the board is currently on an invalid or "invisible" spot
   * @return a boolean value determing if the current index is at either an empty spot
   * or a marble, or neither.
   */
  private boolean isInvalidSpot(int row, int col, int armThickness) {
    if (row < armThickness - 1 && col < armThickness - 1 || // top left
            (row < armThickness - 1 && col > ((2 * armThickness) - 2)) || // top right
            (row > ((armThickness * 2) - 2) && col < armThickness - 1) || // bottom left
            (row > ((armThickness * 2) - 2) && col > ((2 * armThickness) - 2))) { // bottom right {
      return true;
    }
    return false;
  }

  /**
   * iterates through the board, if we are on a marble, add one to a counter.
   * @return an integer counting the marbles in a board.
   */
  private int countMarbles() {
    int count = 0;
    for (int i = 0; i < size; i++) {
      SlotState item = board.get(i);
      if (item == SlotState.Marble) {
        count++;
      }
    }
    return count;
  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    // check if the "from" position contains a marble
    int fromIndex = (int) ((fromRow * numberOfElementsPerRow) + fromCol);
    if (board.get(fromIndex) != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid move: from position does not contain a marble");
    }

    // Check if the move is too far (more than 2 spaces)
    if (Math.abs(fromRow - toRow) > 2 || Math.abs(fromCol - toCol) > 2) {
      throw new IllegalArgumentException("Cannot move more than 2 spaces at a time");
    }

    // Check if the move is diagonal
    if (fromRow != toRow && fromCol != toCol) {
      throw new IllegalArgumentException("Cannot move diagonally");
    }

    // check if the "to" position is empty
    int toIndex = (int) ((toRow * numberOfElementsPerRow) + toCol);
    if (board.get(toIndex) != SlotState.Empty) {
      throw new IllegalArgumentException("Invalid move: to position is not empty");
    }

    // check if the position between the "from" and "to" positions contains a marble
    int overRow = (fromRow + toRow) / 2;
    int overCol = (fromCol + toCol) / 2;
    int overIndex = (int) ((overRow * numberOfElementsPerRow) + overCol);
    if (board.get(overIndex) != SlotState.Marble) {
      throw new IllegalArgumentException("Invalid move: position between from and to does not contain a marble");
    }

    // make the move
    board.set(fromIndex, SlotState.Empty);
    board.set(toIndex, SlotState.Marble);
    board.set(overIndex, SlotState.Empty);

    // update the score
    score--;
  }

  /**
   * Determine and return if the game is over or not. A game is over if no
   * more moves can be made.
   *
   * @return true if the game is over, false otherwise
   */
  @Override
  public boolean isGameOver() {
    // Count the number of marbles on the board
    int marbles = countMarbles();

    // Check if the game is over by iterating over the board and
    // checking if there are any valid moves remaining
    for (int row = 0; row < numberOfElementsPerRow; row++) {
      for (int col = 0; col < numberOfElementsPerRow; col++) {
        SlotState slot = getSlotAt(row, col);
        if (slot == SlotState.Marble) {
          // Check if there is a marble to the left, right, top, or bottom
          // of the current marble that can be jumped over
          if ((row > 1 && getSlotAtAt(row - 1, col) == SlotState.Marble && getSlotAt(row - 2, col) == SlotState.Empty) ||
                  (row < numberOfElementsPerRow - 2 && getSlotAt(row + 1, col) == SlotState.Marble && getSlotAt(row + 2, col) == SlotState.Empty) ||
                  (col > 1 && getSlotAt(row, col - 1) == SlotState.Marble && getSlotAt(row, col - 2) == SlotState.Empty) ||
                  (col < numberOfElementsPerRow - 2 && getSlotAt(row, col + 1) == SlotState.Marble && getSlotAt(row, col + 2) == SlotState.Empty)) {
            // If there is, then the game is not over
            return false;
          }
        }
      }
    }

    // If we have reached this point, then there are no valid moves remaining
    // and the game is over
    return true;
  }
  

  /**
   * Return the size of this board. The size is roughly the longest dimension of a board
   *
   * @return the size as an integer
   */
  @Override
  public int getBoardSize() {
    return this.board.size();
  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */
  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    return this.board.get((int) ((numberOfElementsPerRow * row) + col));

  }

  /**
   * Get the state of the slot at a given position on the board.
   *
   * @param row the row of the position sought, starting at 0
   * @param col the column of the position sought, starting at 0
   * @return the state of the slot at the given row and column
   * @throws IllegalArgumentException if the row or the column are beyond
   *                                  the dimensions of the board
   */
  @Override
  public SlotState getSlotAtAt(int row, int col) throws IllegalArgumentException {
    if (row <= -1 || row >= numberOfElementsPerRow || col < 0 || col >= numberOfElementsPerRow) {
      throw new IllegalArgumentException("Invalid row or column");
    }

    return board.get((int) ((row * numberOfElementsPerRow) + col));
  }

  /**
   * Return the number of marbles currently on the board.
   *
   * @return the number of marbles currently on the board
   */
  @Override
  public int getScore() {
    return this.countMarbles();
  }

  /**
   * transforms the board into a visible, compressible, string output.
   * @return the state of the current board as a string.
   */
  public String toString() {
    StringBuilder gs = new StringBuilder();

    for (int row = 0; row < (Math.sqrt(size)); row++) {
      for (int col = 0; col < Math.sqrt(size); col++) {
        SlotState item = this.getSlotAtAt(row, col);
        if (col % Math.sqrt(size) == 0) {
          gs.append('\n');
        }
        switch (item) {
          case Empty:
            gs.append("_");
            break;
          case Marble:
            gs.append("O");
            break;
          case Invalid:
            gs.append(" ");
            break;
        }

      }
    }

    return gs.toString();
  }
}