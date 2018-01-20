package at.fhj.swengb.apps.battleship.model

import java.nio.file.{Files, Paths}

import at.fhj.swengb.apps.battleship.BattleShipProtocol

import scala.util.Random

object BattleShipGame {

  private def fieldCreator3000(battlefield: BattleField,
                               log: String => Unit,
                               updateGameState: Int => Unit,
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

}

case class BattleShipGame(WarAreas: Seq[PlayerField], battleName: String){

}
