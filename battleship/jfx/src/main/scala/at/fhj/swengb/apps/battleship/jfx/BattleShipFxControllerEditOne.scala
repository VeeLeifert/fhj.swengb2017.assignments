package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.Label

class BattleShipFxControllerEditOne extends Initializable {


  override def initialize(url: URL, rb: ResourceBundle): Unit = {
  }

  // Go back to welcome screen
  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  // Go to the next player's edit mode
  @FXML def toEditTwo(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/edittwo.fxml"),BattleShipFxApp.FirstStage3000)


  // Functionalities are not here yet
  @FXML def horizontal(): Unit = {

  }

  @FXML def vertical(): Unit = {

  }

  @FXML def reset(): Unit = {

  }

}