package cs3500.marblesolitaire.controller;

import java.io.IOException;

public interface MarbleSolitaireController {

  /**
   * playGame is a method to play a game of marble solitaire.
   * The game is played by entering coordinates for a marble
   * to be moved and its destination. The game continues
   * until the game is over, or the user enters 'q' or 'Q' to quit.
   * When the game is over, the final score is displayed.
   * If an error occurs during the game, a message is displayed and
   * the game terminates.
   * @throws IOException
   */

  void playGame() throws IOException;

}
