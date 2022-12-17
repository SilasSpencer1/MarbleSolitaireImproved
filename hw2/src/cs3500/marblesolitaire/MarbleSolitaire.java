package cs3500.marblesolitaire;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.CharBuffer;
import java.util.Scanner;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

public class MarbleSolitaire {


  public static void main(String[] args) throws IOException {
    MarbleSolitaireModel model = null;
    switch (args.length) {
      case 0:
        model = new EnglishSolitaireModel();
        break;
      case 1: model = new EnglishSolitaireModel(Integer.parseInt(args[0]));
      break;
      case 3:
        model = new EnglishSolitaireModel(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
    }
    // Create a new model.

    Reader rd = new InputStreamReader(System.in);

    // Create a new view and specify where the game should be rendered.
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, System.out);

    // Create a new controller.
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, rd);

    // Play the game.
    controller.playGame();
  }
  }