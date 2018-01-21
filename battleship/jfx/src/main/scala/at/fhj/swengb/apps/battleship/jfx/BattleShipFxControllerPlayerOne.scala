package at.fhj.swengb.apps.battleship.jfx

import at.fhj.swengb.apps.battleship.PlayerFieldProtocol
import at.fhj.swengb.apps.battleship.model._

import java.io.File
import java.net.URL
import java.nio.file.{Files, Paths}
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Label, Slider, TextArea}
import javafx.scene.layout.GridPane
import javafx.stage.{FileChooser, Stage}
import javafx.stage.FileChooser.ExtensionFilter


object BattleShipFxControllerPlayerOne {

  @FXML var log: TextArea = _
  var SliderState: Int = _

  var newGameChecker1: Int = _

  def resetNewGameChecker(state: Int) {
    newGameChecker1 = state
  }

  def sliderStateRenewer(value: Int) = {

  }

}


class BattleShipFxControllerPlayerOne extends Initializable {

  @FXML var log: TextArea = _
  @FXML var Title: Label = _
  private var Game1: PlayerField = _
  @FXML private var playerTwoField: GridPane = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    Title.setText(BattleShipFxControllerCreateGame.battleName ++ " - " ++ BattleShipFxControllerCreateGame.playerOne)

    if (BattleShipFxControllerPlayerOne.newGameChecker1 == 0) {
      BattleShipFxControllerPlayerOne.newGameChecker1 = 1
      log.setText("")
      log.appendText("A new game has started")
      Initiator3000(GameCreator3000(), List())
    } else {
        val (clickedPos, battleShipGame) = GameLoader3000("./battleship/gamestates/player1.bin")
        //Resetting log
        log.setText("")
        Initiator3000(clickedPos, battleShipGame)
    }
  }

  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def toPlayerTwo(): Unit = {
    //Converting and saving
    PlayerFieldProtocol.convert(Game1).writeTo(Files.newOutputStream(Paths.get("./battleship/gamestates/player1.bin")))

    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playertwoscreen.fxml"),BattleShipFxApp.FirstStage3000)
  }

  @FXML def saveGame(): Unit = {

  }

  @FXML def disablePane(): Unit = {
    playerTwoField.setDisable(true)
  }

  def Initiator3000(game: PlayerField, ClickChecker3000: List[BattlePos]): Unit = {
    Game1 = game
    playerTwoField.getChildren.clear()
    for (cells <- game.CellReader3000()) {

      playerTwoField.add(cells, cells.pos.x, cells.pos.y)
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
  def WidthReader3000(width: Int): Int = playerTwoField.getColumnConstraints.get(width).getPrefWidth.toInt
  def HeightReader3000(height: Int): Int = playerTwoField.getRowConstraints.get(height).getPrefHeight.toInt

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