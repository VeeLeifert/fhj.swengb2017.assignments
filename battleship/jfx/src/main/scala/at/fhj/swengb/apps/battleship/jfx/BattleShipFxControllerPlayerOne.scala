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
        val FileChooser3000 = new FileChooser();
        val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
        FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
        val FileLoader3000: File = FileChooser3000.showOpenDialog(BattleShipFxApp.FirstStage3000)
        val (clickedPos, battleShipGame) = GameLoader3000(FileLoader3000.getAbsolutePath)
        //Resetting log
        log.setText("")
        Initiator3000(clickedPos, battleShipGame)
        LogAdder3000("Loaded Game")
    }
  }

  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def toPlayerTwo(): Unit = {
    //Using FileChooser for accessing our files
    val FileChooser3000 = new FileChooser();
    //Filtering on our protobuf files with the ending .bin
    val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
    FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
    //Converting and saving
    val FileSaver3000: File = FileChooser3000.showSaveDialog(BattleShipFxApp.FirstStage3000)
    PlayerFieldProtocol.convert(Game1).writeTo(Files.newOutputStream(Paths.get(FileSaver3000.getAbsolutePath)))
    log.appendText("\n" ++ "Saved Game")

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