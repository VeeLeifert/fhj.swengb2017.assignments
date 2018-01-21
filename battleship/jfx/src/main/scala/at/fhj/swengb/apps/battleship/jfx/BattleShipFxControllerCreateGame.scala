package at.fhj.swengb.apps.battleship.jfx

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control._

import scala.util.Random


object BattleShipFxControllerCreateGame {

  var playerOne: String = _
  var playerTwo: String = _
  var battleName: String = _
  var loadedGame: Int = _

  def getPlayerOne(name: String) = {
    if(name != "") {
      playerOne = name
    } else {
      playerOne = "Player 1"
    }
  }

  def getPlayerTwo(name: String) = {
    if(name != "") {
      playerTwo = name
    } else {
      playerTwo = "Player 2"
    }
  }

  def createBattleName(): String = {

    val listOne: Seq[String] = Seq("Final", "Bloody", "Horrible", "Last")
    val listTwo: Seq[String] = Seq(" combat", " crusade", " bloodshed", " battle", " attack", " warfare")
    val listThree: Seq[String] = Seq(" in", " of", " for", " midst")
    val listFour: Seq[String] = Seq(" Lassnitzhoehe", " Gleisdorf", " Graz", " Urscha", " Puch", " Amstetten")

    val r: Random = new Random

    val a: Int = r.nextInt(3)
    val firstPart: String = listOne(a)

    val b: Int = r.nextInt(5)
    val firstMiddlePart: String = listTwo(b)

    val c: Int = r.nextInt(3)
    val lastMiddlePart: String = listThree(c)

    val d: Int = r.nextInt(5)
    val lastPart: String = listFour(d)

    val battleName: String = firstPart ++ firstMiddlePart ++ lastMiddlePart ++ lastPart
    battleName
  }

  def getFinalBattleName(name: String) = {
    if(name != "") {
      battleName = name
    } else {
      battleName = createBattleName()
    }
  }

}

class BattleShipFxControllerCreateGame extends Initializable {

  @FXML var gameName: TextField =_
  @FXML var playerOneName: TextField = _
  @FXML var playerTwoName: TextField = _

  override def initialize(url: URL, rb: ResourceBundle): Unit = {
    gameName.setText(BattleShipFxControllerCreateGame.createBattleName())
  }

  @FXML def toWelcome(): Unit = BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/welcomescreen.fxml"),BattleShipFxApp.FirstStage3000)

  @FXML def Continue(): Unit = {
      BattleShipFxControllerCreateGame.getPlayerOne(playerOneName.getText())
      BattleShipFxControllerCreateGame.getPlayerTwo(playerTwoName.getText())
      BattleShipFxControllerCreateGame.getFinalBattleName(gameName.getText())
      BattleShipFxControllerPlayerOne.resetNewGameChecker(1)
      BattleShipFxControllerPlayerTwo.resetNewGameChecker(1)
      BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/editone.fxml"), BattleShipFxApp.FirstStage3000)
  }
}