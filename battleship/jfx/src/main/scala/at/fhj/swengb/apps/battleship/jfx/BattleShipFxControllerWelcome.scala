package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}


class BattleShipFxControllerWelcome extends Initializable {

  override def initialize(url: URL, rb: ResourceBundle): Unit = {

  }

  @FXML def newGame(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/createnewgamescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def loadGame(): Unit = {
    BattleShipFxControllerPlayerOne.loadGameChecker(1)
    BattleShipFxControllerPlayerTwo.loadGameChecker(1)
    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/playeronescreen.fxml"),BattleShipFxApp.FirstStage3000)
  }

  @FXML def toHighscores(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/highscores.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def toCredits(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/credits.fxml"),BattleShipFxApp.FirstStage3000)

}