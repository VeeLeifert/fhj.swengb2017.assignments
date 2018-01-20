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

import at.fhj.swengb.apps.battleship.BattleShipProtocol
import at.fhj.swengb.apps.battleship.model._


object BattleShipFxControllerPlayerOne {

  @FXML var log: TextArea = _
  var SliderState: Int = _

  var newGameChecker: Int = _

  def resetNewGameChecker(state: Int) {
    newGameChecker = state
  }

  def sliderStateRenewer(value: Int) = {

  }

}

class BattleShipFxControllerPlayerTwo extends Initializable {

  @FXML var log: TextArea = _
  @FXML var Title: Label = _
  private var Game2: PlayerField = _
  var newGameChecker2: Int = _
  @FXML private var playerOneField: GridPane = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    Title.setText(BattleShipFxControllerCreateGame.battleName ++ " - " ++ BattleShipFxControllerCreateGame.playerTwo)

    if (newGameChecker2 == 0) {
      newGameChecker2 = 1
      log.setText("")
      log.appendText("A new game has started")
      Initiator3000(GameCreator3000(), List())
    }
  }

  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def toPlayerOne(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playeronescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def saveGame(): Unit = {
    //Using FileChooser for accessing our files
    val FileChooser3000 = new FileChooser();
    //Filtering on our protobuf files with the ending .bin
    val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
    FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
    //Converting and saving
    val FileSaver3000: File = FileChooser3000.showSaveDialog(BattleShipFxApp.FirstStage3000)
    BattleShipProtocol.convert(Game2).writeTo(Files.newOutputStream(Paths.get(FileSaver3000.getAbsolutePath)))
    log.appendText("\n" ++ "Saved Game")
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
}