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
  var savedGame: Int = _
  var loadedGame: Int = _

  def resetNewGameChecker(state: Int) {
    newGameChecker2 = state
  }

  def sliderStateRenewer(value: Int) = {

  }

  def saveGameChecker(value: Int) ={
    savedGame = value
  }

  def loadGameChecker(value: Int) ={
    loadedGame = value
  }

}

class BattleShipFxControllerPlayerTwo extends Initializable {

  @FXML var log: TextArea = _
  @FXML var Title: Label = _
  private var Game2: PlayerField = _
  @FXML private var playerOneField: GridPane = _
  @FXML private var saveGameButton: Button = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    saveGameButton.setDisable(true)
    if (BattleShipFxControllerPlayerTwo.loadedGame == 0) {
      if (BattleShipFxApp.battleName != null && BattleShipFxApp.playerTwo != null) {
        Title.setText(BattleShipFxApp.battleName ++ " - " ++ BattleShipFxApp.playerTwo)
      } else {
        Title.setText("Game - Player 2")
      }
      if (BattleShipFxControllerPlayerTwo.newGameChecker2 == 1) {
        BattleShipFxControllerPlayerTwo.newGameChecker2 = 0
        log.setText("")
        log.appendText("A new game has started")
        Initiator3000(GameCreator3000(), List())
      } else {
        val (clickedPos, battleShipGame) = GameLoader3000("./battleship/gamestates/player2.bin")
        //Resetting log
        log.setText("")
        Initiator3000(clickedPos, battleShipGame)
      }

      if (BattleShipFxControllerPlayerTwo.savedGame == 1){
        BattleShipFxControllerPlayerTwo.savedGame = 0
        //Using FileChooser for accessing our files
        val FileChooser3000 = new FileChooser()
        //Filtering on our protobuf files with the ending .bin
        val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
        FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
        //Converting and saving
        val FileSaver3000: File = FileChooser3000.showSaveDialog(BattleShipFxApp.FirstStage3000)
        PlayerFieldProtocol.convert(Game2).writeTo(Files.newOutputStream(Paths.get(FileSaver3000.getAbsolutePath)))
        LogAdder3000("Saved Game")
        BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playeronescreen.fxml"),BattleShipFxApp.FirstStage3000)
      }
    } else {
      BattleShipFxControllerPlayerTwo.loadedGame = 0
      val FileChooser3000 = new FileChooser();
      val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
      FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
      val FileLoader3000: File = FileChooser3000.showOpenDialog(BattleShipFxApp.FirstStage3000)
      val (clickedPos, battleShipGame) = GameLoader3000(FileLoader3000.getAbsolutePath)
      //Resetting log
      log.setText("")
      Initiator3000(clickedPos, battleShipGame)
      LogAdder3000("Loaded Game")
      PlayerFieldProtocol.convert(Game2).writeTo(Files.newOutputStream(Paths.get("./battleship/gamestates/player2.bin")))
      BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playeronescreen.fxml"),BattleShipFxApp.FirstStage3000)
    }
  }

  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def toPlayerOne(): Unit = {
    //Converting and saving
    PlayerFieldProtocol.convert(Game2).writeTo(Files.newOutputStream(Paths.get("./battleship/gamestates/player2.bin")))

    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playeronescreen.fxml"),BattleShipFxApp.FirstStage3000)
  }

  @FXML def saveGame(): Unit = {
    //Using FileChooser for accessing our files
    val FileChooser3000 = new FileChooser();
    //Filtering on our protobuf files with the ending .bin
    val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
    FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
    //Converting and saving
    val FileSaver3000: File = FileChooser3000.showSaveDialog(BattleShipFxApp.FirstStage3000)
    PlayerFieldProtocol.convert(Game2).writeTo(Files.newOutputStream(Paths.get(FileSaver3000.getAbsolutePath)))
    LogAdder3000("Saved Game")
    BattleShipFxControllerPlayerOne.saveGameChecker(1)
    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playeronescreen.fxml"),BattleShipFxApp.FirstStage3000)
  }

  @FXML def disablePane(): Unit = {
    playerOneField.setDisable(true)
    saveGameButton.setDisable(false)
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