import static org.junit.Assert.assertEquals;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import org.junit.Test;

public class EnglishSolitaireModelTest {

  private EnglishSolitaireModel model;
  String standardModel = "\n" + "  OOO  \n" +
          "  OOO  \n" +
          "OOOOOOO\n" +
          "OOO_OOO\n" +
          "OOOOOOO\n" +
          "  OOO  \n" +
          "  OOO  " ;
  @Test
  public void testConstructor1() {
    this.model = new EnglishSolitaireModel();
    assertEquals(standardModel, model.toString());
  }

  @Test
  public void testConstructor2Valid() {
    this.model = new EnglishSolitaireModel(3, 3);
    assertEquals(standardModel, model.toString());

  }

  @Test
  public void testConstructor3Valid() {
    this.model = new EnglishSolitaireModel(3, 3, 3);
    assertEquals(standardModel, model.toString());

  }


  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    this.model = new EnglishSolitaireModel(0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2RowTooSmall() {
    this.model = new EnglishSolitaireModel(-1, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2ColTooSmall() {
    this.model = new EnglishSolitaireModel(3, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorAllInvalid() {
    this.model = new EnglishSolitaireModel(-3, -3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2TopLeft() {
    this.model = new EnglishSolitaireModel(1, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2TopRight() {
    this.model = new EnglishSolitaireModel(0, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2TopRight2() {
    this.model = new EnglishSolitaireModel(3, 0, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2BottomLeft() {
    this.model = new EnglishSolitaireModel(5, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2BottomLeft2() {
    this.model = new EnglishSolitaireModel(3, 5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2BottomRight() {
    this.model = new EnglishSolitaireModel(5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructor3WithEvenArmThickness() {
    this.model = new EnglishSolitaireModel(4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructor4ColTooBig() {
    this.model = new EnglishSolitaireModel(3, 3, 113);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructor4ColTooSmall() {
    this.model = new EnglishSolitaireModel(3, 3, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructor4RowTooSmall() {
    this.model = new EnglishSolitaireModel(3, -3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructor4RowTooBig() {
    this.model = new EnglishSolitaireModel(3, 300, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructor3WithNegativeArmThickness() {
    this.model = new EnglishSolitaireModel(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testContructor3WithLength1ArmThickness() {
    this.model = new EnglishSolitaireModel(1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4AllInvalid() {
    this.model = new EnglishSolitaireModel(4, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorArmThicknessInvalid() {
    this.model = new EnglishSolitaireModel(-4, 3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorEmptySpaceInvalid() {
    this.model = new EnglishSolitaireModel(4, 0, 0);
  }

  @Test
  public void testGetBoardStateDefault() {
    this.model = new EnglishSolitaireModel();
    assertEquals(standardModel, this.model.toString());
  }

  @Test
  public void testGetBoardState5CenterOff() {
    this.model = new EnglishSolitaireModel(5, 4, 5);
    assertEquals("\n    OOOOO    \n" +
            "    OOOOO    \n" +
            "    OOOOO    \n" +
            "    OOOOO    \n" +
            "OOOOO_OOOOOOO\n" +
            "OOOOOOOOOOOOO\n" +
            "OOOOOOOOOOOOO\n" +
            "OOOOOOOOOOOOO\n" +
            "OOOOOOOOOOOOO\n" +
            "    OOOOO    \n" +
            "    OOOOO    \n" +
            "    OOOOO    \n" +
            "    OOOOO    ", this.model.toString());


  }



  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveTooFar() {
    this.model = new EnglishSolitaireModel(5);
    this.model.move(3, 5, 3, 3);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testMoveDiagonal() {
    this.model = new EnglishSolitaireModel();
    model.move(3, 5, 3, 3);
    model.move(1, 2, 3, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveToSpotNotEmpty() {
    this.model = new EnglishSolitaireModel(3);
    this.model.move(1, 3, 3, 3);
    this.model.move(3, 3, 1, 3);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveFromSpotEmpty() {
    this.model = new EnglishSolitaireModel(3);
    this.model.move(0, 2, 0, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMoveBetweenSpotNoMarble() {
    this.model = new EnglishSolitaireModel(3);
    this.model.move(3, 0, 3, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidMove() {
    this.model = new EnglishSolitaireModel(3);
    this.model.move(3, 0, 3, 3);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void testInvalidMoveAllBad() {
    this.model = new EnglishSolitaireModel(3);
    this.model.move(-3, -3, -3, -3);
  }

  @Test
  public void testValidMove() {
    this.model = new EnglishSolitaireModel(3);
    this.model.move(3, 5, 3, 3);

    System.out.println(model.toString());
    assertEquals("\n" + "  OOO  \n" +
            "  OOO  \n" +
            "OOOOOOO\n" +
            "OOOO__O\n" +
            "OOOOOOO\n" +
            "  OOO  \n" +
            "  OOO  ", this.model.toString());

  }

  @Test
  public void testIsGameOverFalse() {
    this.model = new EnglishSolitaireModel(3); // 2,2,2,4
    assertEquals(false, this.model.isGameOver());
  }

  @Test
  public void testIsGameOverTrue() {
    this.model = new EnglishSolitaireModel();
    this.model.move(1, 3, 3, 3);
    this.model.move(4, 3, 2, 3);
    this.model.move(6, 3, 4, 3);
    this.model.move(3, 5, 3, 3);
    this.model.move(3, 2, 3, 4);
    this.model.move(3, 0, 3, 2);
    System.out.print(this.model.toString());
    assertEquals(true, this.model.isGameOver());

  }

  @Test
  public void testGetScore() {
    this.model = new EnglishSolitaireModel();
    assertEquals(32, this.model.getScore());
    this.model.move(1, 3, 3, 3);
    assertEquals(31, this.model.getScore());
    this.model.move(4, 3, 2, 3);
    assertEquals(30, this.model.getScore());
  }


}