package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control._
import javafx.stage.{FileChooser, Stage}
import javafx.stage.FileChooser.ExtensionFilter
import java.io.File
import java.nio.file.{Files, Paths}

import at.fhj.swengb.apps.battleship.BattleShipProtocol
import at.fhj.swengb.apps.battleship.model.PlayerField


object BattleShipFxControllerPlayerOne {

  @FXML var log: TextArea = _

  var newGameChecker: Int = _
  def resetNewGameChecker: Unit = {
    newGameChecker = 0
  }

  def newGame: Unit = {
    newGameChecker = 1
    log.setText("")
    log.appendText("A new game has started")
  }
}


class BattleShipFxControllerPlayerOne extends Initializable {

  @FXML var log: TextArea = _
  @FXML var Title: Label = _
  private var Game: PlayerField = _
  var newGameChecker: Int = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    Title.setText(BattleShipFxControllerCreateGame.battleName ++ " - " ++ BattleShipFxControllerCreateGame.playerOne)

    /*if (newGameChecker == 0) {
      BattleShipFxControllerPlayerOne.newGame
    }*/
  }

  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def toPlayerTwo(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playertwoscreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def saveGame(): Unit = {
      //Using FileChooser for accessing our files
      val FileChooser3000 = new FileChooser();
      //Filtering on our protobuf files with the ending .bin
      val ProtoFilter3000: FileChooser.ExtensionFilter = new ExtensionFilter("Protobuf files","*.bin")
      FileChooser3000.getExtensionFilters.add(ProtoFilter3000)
      //Converting and saving
      val FileSaver3000: File = FileChooser3000.showSaveDialog(BattleShipFxApp.FirstStage3000)
      BattleShipProtocol.convert(Game).writeTo(Files.newOutputStream(Paths.get(FileSaver3000.getAbsolutePath)))
      log.appendText("\n" ++ "Saved Game")
    }


}