package at.fhj.swengb.apps.battleship.jfx

import at.fhj.swengb.apps.battleship.PlayerFieldProtocol
import at.fhj.swengb.apps.battleship.model._
import java.io.File
import java.net.URL
import java.nio.file.{Files, Paths}
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Label, Slider, TextArea, Button}
import javafx.scene.layout.GridPane
import javafx.stage.{FileChooser, Stage}
import javafx.stage.FileChooser.ExtensionFilter



object BattleShipFxControllerPlayerOne {

  @FXML var log: TextArea = _
  var SliderState: Int = _

  // Different flags to check the actual state
  var savedGame: Int = _
  var loadedGame: Int = _
  var newGameChecker1: Int = _

  // Functions to get the flags
  def resetNewGameChecker(state: Int) {
    newGameChecker1 = state
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


class BattleShipFxControllerPlayerOne extends Initializable {

  @FXML var log: TextArea = _
  @FXML var labelGame: Label = _
  @FXML var labelPlayer: Label = _
  private var Game1: PlayerField = _
  @FXML private var playerTwoField: GridPane = _
  @FXML private var saveGameButton: Button = _
  @FXML private var toPlayerTwoButton: Button = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {

    // Disable save and next player function when the screen is initiated
    saveGameButton.setDisable(true)
    toPlayerTwoButton.setDisable(true)

    // Set battle- and playername, refill if empty
    if (BattleShipFxApp.battleName != null) {
      labelGame.setText(BattleShipFxApp.battleName)
    } else {
      BattleShipFxApp.getFinalBattleName("")
      labelGame.setText(BattleShipFxApp.battleName)
    }
    if (BattleShipFxApp.playerOne != null) {
      labelPlayer.setText(BattleShipFxApp.playerOne)
    } else {
      labelPlayer.setText("Player 1")
    }

    // Game is not freshly loaded
    if(BattleShipFxControllerPlayerOne.loadedGame == 0) {

      // Game is totally new
      if (BattleShipFxControllerPlayerOne.newGameChecker1 == 1) {
        BattleShipFxControllerPlayerOne.newGameChecker1 = 0
        log.setText("")
        log.appendText("A new game has started \n")
        Initiator3000(GameCreator3000(), List())

      } else { // Game is not new, just load state from before
        val (clickedPos, battleShipGame) = GameLoader3000("./battleship/gamestates/player1.bin")
        log.setText("")
        Initiator3000(clickedPos, battleShipGame)
      }

      // Player 2 cklicked save, so player 1 also needs to save; open explorer
      if (BattleShipFxControllerPlayerOne.savedGame == 1){
        BattleShipFxControllerPlayerOne.savedGame = 0
        val FileChooser3000 = new FileChooser()
        FileChooser3000.setTitle("Save Player 1 Playerfield")
        val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
        FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
        val FileSaver3000: File = FileChooser3000.showSaveDialog(BattleShipFxApp.FirstStage3000)
        PlayerFieldProtocol.convert(Game1).writeTo(Files.newOutputStream(Paths.get(FileSaver3000.getAbsolutePath)))
        LogAdder3000("Saved Game")
      }
    } else { // Game is loaded, open explorer to load file
      BattleShipFxControllerPlayerOne.loadedGame = 0
      val FileChooser3000 = new FileChooser()
      FileChooser3000.setTitle("Load Player 1 Playerfield")
      val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
      FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
      val FileLoader3000: File = FileChooser3000.showOpenDialog(BattleShipFxApp.FirstStage3000)
      val (clickedPos, battleShipGame) = GameLoader3000(FileLoader3000.getAbsolutePath)
      log.setText("")
      Initiator3000(clickedPos, battleShipGame)
      LogAdder3000("Loaded Game")
      PlayerFieldProtocol.convert(Game1).writeTo(Files.newOutputStream(Paths.get("./battleship/gamestates/player1.bin")))
      BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playertwoscreen.fxml"),BattleShipFxApp.FirstStage3000)
    }
  }

  // Go back to welcome screen
  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)


  @FXML def toPlayerTwo(): Unit = {

    // Save own gamestate and switch to player 2
    PlayerFieldProtocol.convert(Game1).writeTo(Files.newOutputStream(Paths.get("./battleship/gamestates/player1.bin")))
    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playertwoscreen.fxml"),BattleShipFxApp.FirstStage3000)
  }

  // Save the whole game; open explorer
  // Is permanently disabled though
  @FXML def saveGame(): Unit = {
    val FileChooser3000 = new FileChooser();
    FileChooser3000.setTitle("Save Player 1 Playerfield")
    val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
    FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
    val FileSaver3000: File = FileChooser3000.showSaveDialog(BattleShipFxApp.FirstStage3000)
    PlayerFieldProtocol.convert(Game1).writeTo(Files.newOutputStream(Paths.get(FileSaver3000.getAbsolutePath)))
    LogAdder3000("Saved Game")
    BattleShipFxControllerPlayerTwo.saveGameChecker(1)
    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playertwoscreen.fxml"),BattleShipFxApp.FirstStage3000)
  }

  // When player shot, disable field and enable next player button
  @FXML def disablePane(): Unit = {
    playerTwoField.setDisable(true)
    toPlayerTwoButton.setDisable(false)
  }

  // Initiator and Gamecreator needed to construnct/reconstruct the game
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
    PlayerField(BattleField.RandomPlacer3000(Field), LogAdder3000, SliderAdder3000, WidthReader3000, HeightReader3000)
  }

  def LogAdder3000(text: String): Unit = log.appendText(text + "\n")
  def WidthReader3000(width: Int): Int = playerTwoField.getColumnConstraints.get(width).getPrefWidth.toInt
  def HeightReader3000(height: Int): Int = playerTwoField.getRowConstraints.get(height).getPrefHeight.toInt

  // Get changes for the slider
  // Would have been needed in replay mode
  def SliderAdder3000(Stack: Int): Unit = {
    BattleShipFxControllerPlayerOne.sliderStateRenewer(Stack)
  }

  // Gameloader needed to load a previous gamestate
  private def GameLoader3000(filePath: String): (PlayerField, List[BattlePos]) = {
    val LoadDestination = at.fhj.swengb.apps.battleship.PlayerFieldProtobuf.PlayerField
      .parseFrom(Files.newInputStream(Paths.get(filePath)))

    val Game = PlayerField(PlayerFieldProtocol.convert(LoadDestination).battleField,
      LogAdder3000,
      SliderAdder3000,
      WidthReader3000,
      HeightReader3000)

    Game.GameState = List()
    (Game, PlayerFieldProtocol.convert(LoadDestination).GameState.reverse)
  }
}