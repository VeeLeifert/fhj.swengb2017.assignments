package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}

import java.io.File
import java.nio.file.{Files, Paths}
import at.fhj.swengb.apps.battleship.model._
import at.fhj.swengb.apps.battleship.BattleShipProtocol

import javafx.stage.{FileChooser, Stage}
import javafx.stage.FileChooser.ExtensionFilter


class BattleShipFxControllerWelcome extends Initializable {

  override def initialize(url: URL, rb: ResourceBundle): Unit = {

  }

  @FXML def newGame(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/createnewgamescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def loadGame(): Unit = {

  }

  @FXML def toHighscores(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/highscores.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def toCredits(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/credits.fxml"),BattleShipFxApp.FirstStage3000)

}