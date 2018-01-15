package at.fhj.swengb.apps.battleship.model

import scala.util.Random

object BattleShipGame {

  private def fieldCreator3000(battlefield: BattleField,
                               log: String => Unit,
                               updateGameState: PlayerField => Unit,
                               getCellWidth: Int => Double,
                               getCellHeight: Int => Double,
                               commander: String): PlayerField = {

    val battlefield: BattleField = BattleField(10, 10, Fleet(FleetConfig.Standard))

    PlayerField(BattleField.RandomPlacer3000(battlefield),
      log,
      updateGameState,
      getCellWidth,
      getCellHeight,
      commander)
  }


  private def battleNameCreator3000(): String = {

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

}

case class BattleShipGame(WarAreas: Seq[PlayerField], battleName: String){

}
