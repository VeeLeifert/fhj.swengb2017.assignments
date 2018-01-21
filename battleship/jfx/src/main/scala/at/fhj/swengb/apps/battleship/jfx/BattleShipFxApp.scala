package at.fhj.swengb.apps.battleship.jfx

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

import scala.util.{Failure, Random, Success, Try}

object BattleShipFxApp {

  var FirstStage3000: Stage = _;
  var playerOne: String = _
  var playerTwo: String = _
  var battleName: String = _

  def main(args: Array[String]): Unit = {
    Application.launch(classOf[BattleShipFxApp], args: _*)
  }

  def ScenePresenter3000(scene: Scene, stage: Stage): Unit = {
    stage.setScene(scene)
    stage.setResizable(false)
    stage.show()
  }

  def SceneLoader3000(sceneString: String): Scene = {
    val triedScene = Try(FXMLLoader.load[Parent](getClass.getResource(sceneString)))
    triedScene match {
      case Success(root) =>
        val scene: Scene = new Scene(root)
        scene
      case Failure(e) => {
        e.printStackTrace()
        null
      }
    }
  }

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


class BattleShipFxApp extends Application {

  override def start(stage: Stage): Unit = {
    BattleShipFxApp.FirstStage3000 = stage
    BattleShipFxApp.ScenePresenter3000(BattleShipFxApp.SceneLoader3000("/at/fhj/swengb/apps/battleship/jfx/splashscreen.fxml"),stage)
  }
}

