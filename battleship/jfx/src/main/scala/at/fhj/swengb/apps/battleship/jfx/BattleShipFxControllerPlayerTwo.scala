package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control._
import javafx.stage.{FileChooser, Stage}
import javafx.stage.FileChooser.ExtensionFilter
import java.io.File
import java.nio.file.{Files, Paths}
import javafx.scene.layout.GridPane

import at.fhj.swengb.apps.battleship.PlayerFieldProtocol
import at.fhj.swengb.apps.battleship.model._


object BattleShipFxControllerPlayerTwo {

  @FXML var log: TextArea = _
  var SliderState: Int = _

  var newGameChecker2: Int = _

  def resetNewGameChecker(state: Int) {
    newGameChecker2 = state
  }

  def sliderStateRenewer(value: Int) = {

  }

}

class BattleShipFxControllerPlayerTwo extends Initializable {

  @FXML var log: TextArea = _
  @FXML var Title: Label = _
  private var Game2: PlayerField = _
  @FXML private var playerOneField: GridPane = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    Title.setText(BattleShipFxControllerCreateGame.battleName ++ " - " ++ BattleShipFxControllerCreateGame.playerTwo)

    if (BattleShipFxControllerPlayerTwo.newGameChecker2 == 0) {
      BattleShipFxControllerPlayerTwo.newGameChecker2 = 1
      log.setText("")
      log.appendText("A new game has started")
      Initiator3000(GameCreator3000(), List())
    } else {
        val (clickedPos, battleShipGame) = GameLoader3000("./battleship/gamestates/player2.bin")
        //Resetting log
        log.setText("")
        Initiator3000(clickedPos, battleShipGame)
    }
  }

  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def toPlayerOne(): Unit = {
    //Converting and saving
    PlayerFieldProtocol.convert(Game2).writeTo(Files.newOutputStream(Paths.get("./battleship/gamestates/player2.bin")))

    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playeronescreen.fxml"),BattleShipFxApp.FirstStage3000)
  }

  @FXML def saveGame(): Unit = {

  }

  @FXML def disablePane(): Unit = {
    playerOneField.setDisable(true)
  }

  def Initiator3000(game: PlayerField, ClickChecker3000: List[BattlePos]): Unit = {
    Game2 = game
    playerOneField.getChildren.clear()
    for (cells <- game.CellReader3000()) {

      playerOneField.add(cells, cells.pos.x, cells.pos.y)
    }
    game.GameState = List()
    game.CellReader3000().foreach(c => c.init())
    game.RebuildGame(ClickChecker3000)
  }

  private def GameCreator3000(): PlayerField = {
    val Field = BattleField(10, 10, Fleet(FleetConfig.Standard))
    PlayerField(BattleField.RandomPlacer3000(Field), LogAdder3000, SliderAdder3000, WidthReader3000, HeightReader3000, null)
  }

  def LogAdder3000(text: String): Unit = log.appendText(text + "\n")
  def WidthReader3000(width: Int): Int = playerOneField.getColumnConstraints.get(width).getPrefWidth.toInt
  def HeightReader3000(height: Int): Int = playerOneField.getRowConstraints.get(height).getPrefHeight.toInt

  def SliderAdder3000(Stack: Int): Unit = {
    BattleShipFxControllerPlayerOne.sliderStateRenewer(Stack)
  }

  private def GameLoader3000(filePath: String): (PlayerField, List[BattlePos]) = {
    val LoadDestination = at.fhj.swengb.apps.battleship.PlayerFieldProtobuf.PlayerField
      .parseFrom(Files.newInputStream(Paths.get(filePath)))

    val Game = PlayerField(PlayerFieldProtocol.convert(LoadDestination).battleField,
      LogAdder3000,
      SliderAdder3000,
      WidthReader3000,
      HeightReader3000,
      null)

    Game.GameState = List()
    (Game, PlayerFieldProtocol.convert(LoadDestination).GameState.reverse)
  }
}