package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control._


class BattleShipFxControllerCreateGame extends Initializable {

  @FXML var gameName: TextField =_
  @FXML var playerOneName: TextField = _
  @FXML var playerTwoName: TextField = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    // Give the players a random generated battlename:
    gameName.setText(BattleShipFxApp.createBattleName())
  }

  // Go back to the welcome screen
  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def Continue(): Unit = {
    // Reading the textfields and give the strings as parameter to the functions in charge
    BattleShipFxApp.getPlayerOne(playerOneName.getText())
    BattleShipFxApp.getPlayerTwo(playerTwoName.getText())
    BattleShipFxApp.getFinalBattleName(gameName.getText())

    // If the game was started from this controller, it is a new game
    BattleShipFxControllerPlayerOne.resetNewGameChecker(1)
    BattleShipFxControllerPlayerTwo.resetNewGameChecker(1)

    // Switching to the edit mode
    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/editone.fxml"), BattleShipFxApp.FirstStage3000)
  }
}