package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {
  private Scanner scanner;
  private Readable rd;
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;

  /**
   * A controller class that contains information
   * about what the user will be playing with.
   * What are the dimensions of their model?
   * and what does that model look like?
   * What are they doing to write their moves?
   * @param model the model of the game.
   * @param view the view associated with the model.
   * @param rd Readable class to read input for moves.
   * @throws IllegalArgumentException when given a false model, view, or readable.
   */

  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view, Readable rd) throws IllegalArgumentException {
    if (rd == null) {
      throw new IllegalArgumentException("null readable");
    }
    if (view == null) {
      throw new IllegalArgumentException("null appendable");
    }
    if (model == null) {
      throw new IllegalArgumentException("null model");
    }
    this.model = model;
    this.rd = rd;
    this.view = view;
  }

  /**
   * reads the next integer that the user intends to provide.
   * @param scanner Scanner object.
   * @param prompt A message telling the user what step of making a move they are
   *               on. (fromRow, toRow, fromRow, or fromCol).
   * @return an single integer provided by the user
   * @throws IOException when the input was not as expected.
   */

  private int readInt(Scanner scanner, String prompt) throws IOException {
    while (true) {
      view.renderMessage(prompt);
      if (scanner.hasNextInt()) {
        return scanner.nextInt();
      } else {
        scanner.next();
      }
    }
  }

  /**
   * Plays a game of MarbleSolitaire.
   * @throws IllegalStateException
   * when an error done by the user was not
   * prepared for
   */

  @Override
  public void playGame() throws IllegalStateException {

    this.scanner = new Scanner(rd);

    try {
      while (!model.isGameOver()) {
        // Render the current game state.
        view.renderBoard();

        // Transmit the current score.
        view.renderMessage("Score: " + model.getScore());

        System.out.println("Make your move");

        try {
          // Get the next user input.
          int fromRow = readInt(scanner, "Enter the row of the marble to be moved: ");
          int fromCol = readInt(scanner, "Enter the column of the marble to be moved: ");
          int toRow = readInt(scanner, "Enter the row of the destination: ");
          int toCol = readInt(scanner, "Enter the column of the destination: ");

          // Move the marble and render the updated game state.
          model.move(fromRow, fromCol, toRow, toCol);
          view.renderBoard();
          view.renderMessage("Score : " + model.getScore());
          view.renderMessage("Enter the row of the marble to be moved:");
        } catch (IllegalArgumentException e) {
          throw new IllegalArgumentException("");
        } catch (IOException e) {
          // An IOException was thrown by the move method.
          // Display an error message and terminate the game.
          view.renderMessage("An error occurred while making the move: " + e.getMessage());
          break;
        }


        // Check if the user wants to quit.
        if (scanner.hasNext("[qQ]")) {
          view.renderMessage("Game quit!");
          view.renderMessage("State of game when quit:");
          view.renderBoard();
          view.renderMessage("Score: " + model.getScore());
          break;
        }
      }

      if (model.isGameOver()) {
        // Game is over.
        view.renderMessage("Game over!");
        view.renderBoard();
        view.renderMessage("Score: " + model.getScore());
      }
    } catch (IOException e) {
      throw new IllegalStateException("Error transmitting game state or message", e);
    }
  }
}