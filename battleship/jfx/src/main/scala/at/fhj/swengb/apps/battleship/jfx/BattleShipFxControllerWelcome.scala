package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}


class BattleShipFxControllerWelcome extends Initializable {

  override def initialize(url: URL, rb: ResourceBundle): Unit = {

  }

  // Make a new game -> go to game create mode
  @FXML def newGame(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/createnewgamescreen.fxml"),BattleShipFxApp.FirstStage3000)

  // Set flags for loading the game, got to player 1 screen
  @FXML def loadGame(): Unit = {
    BattleShipFxControllerPlayerOne.loadGameChecker(1)
    BattleShipFxControllerPlayerTwo.loadGameChecker(1)
    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playeronescreen.fxml"),BattleShipFxApp.FirstStage3000)
  }

  // Go to the Highscores
  // No scores saved there yet
  @FXML def toHighscores(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/highscores.fxml"),BattleShipFxApp.FirstStage3000)

  // Go to the credits
  @FXML def toCredits(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/credits.fxml"),BattleShipFxApp.FirstStage3000)

}