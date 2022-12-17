package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

public class MarbleSolitaireTextView implements MarbleSolitaireView {
  MarbleSolitaireModelState modelState;
  Appendable appendable;

  public MarbleSolitaireTextView(MarbleSolitaireModelState modelState, Appendable appendable) throws
          IllegalArgumentException {
    if (modelState == null || appendable == null) {
      throw new IllegalArgumentException("null model or appendable");
    }

    this.modelState = modelState;
    this.appendable = appendable;
  }

  /**
   * @return takes the game state of the board.
   * provides it as a string.
   */
  public String toString() {
    StringBuilder gs = new StringBuilder();
    int size = modelState.getBoardSize();
    for (int row = 0; row < (Math.sqrt(size)); row++) {
      for (int col = 0; col < Math.sqrt(size); col++) {
        MarbleSolitaireModelState.SlotState item = modelState.getSlotAt(row, col);
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

  /**
   * Render the board to the provided data destination. The board should be rendered exactly
   * in the format produced by the toString method above
   *
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderBoard() throws IOException {
    StringBuilder sb = new StringBuilder();
    int size = this.modelState.getBoardSize();

    // Compute the number of rows and columns in the board.
    int numRows = (int) Math.sqrt(size);
    int numCols = (int) Math.sqrt(size);

    // Append each row of the board to the StringBuilder.
    for (int row = 0; row < numRows; row++) {
      for (int col = 0; col < numCols; col++) {
        MarbleSolitaireModelState.SlotState slot = this.modelState.getSlotAt(row, col);

        // Append the appropriate character for the slot state.
        switch (slot) {
          case Empty:
            sb.append("_");
            break;
          case Marble:
            sb.append("O");
            break;
          case Invalid:
            sb.append(" ");
            break;
        }
      }
      // Add a newline after each row.
      sb.append("\n");
    }

    // Append the string representation of the board to the provided appendable object.
    this.appendable.append(sb);
  }

  @Override
  public Appendable getAppendable() {
    return this.appendable;
  }

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  @Override
  public void renderMessage(String message) throws IOException {
    // Append the message to the provided appendable object.
    this.appendable.append(message + "\n");
  }
}
