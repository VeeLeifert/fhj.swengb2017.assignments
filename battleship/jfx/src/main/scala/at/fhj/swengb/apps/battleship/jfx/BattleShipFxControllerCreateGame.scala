package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}

import javafx.scene.control._


object BattleShipFxControllerCreateGame {

  @FXML var playerOneName: TextField = _
  @FXML var playerTwoName: TextField = _
  var playerOne: String = _
  var playerTwo: String = _

  def getPlayerOne = {
    if(playerOneName.getText().nonEmpty) {
      playerOne = playerOneName.getText()
    } else {
      playerOne = "Player 1"
    }
  }
  def getPlayerTwo = {
    if(playerTwoName.getText().nonEmpty) {
      playerTwo = playerTwoName.getText()
    } else {
      playerTwo = "Player 2"
    }
  }
}

class BattleShipFxControllerCreateGame extends Initializable {

  override def initialize(url: URL, rb: ResourceBundle): Unit = {

  }

  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def Continue(): Unit = {
      BattleShipFxControllerCreateGame.getPlayerOne
      BattleShipFxControllerCreateGame.getPlayerTwo
      BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/editone.fxml"), BattleShipFxApp.FirstStage3000)
  }
}