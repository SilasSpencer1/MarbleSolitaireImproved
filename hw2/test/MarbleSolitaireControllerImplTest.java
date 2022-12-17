import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.controller.MarbleSolitaireController;
import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.io.IOException;
import java.io.StringReader;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import org.junit.Test;

public class MarbleSolitaireControllerImplTest {

  @Test(expected = NoSuchElementException.class)
  public void testSingleLetter() throws IOException {
    Appendable a1 = new StringBuilder();
    Readable r1 = new StringReader("A");
    MarbleSolitaireModel model = new EnglishSolitaireModel();
    MarbleSolitaireView view = new MarbleSolitaireTextView(model, a1);
    MarbleSolitaireController controller = new MarbleSolitaireControllerImpl(model, view, r1);
    controller.playGame();

  }

}