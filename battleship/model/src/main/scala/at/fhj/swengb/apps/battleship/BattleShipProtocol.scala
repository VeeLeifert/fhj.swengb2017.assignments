package at.fhj.swengb.apps.battleship

import at.fhj.swengb.apps.battleship.BattleShipProtobuf.BattleShipGame.{Position, Vessel}
import at.fhj.swengb.apps.battleship.model.{NonEmptyString, _}

import scala.collection.JavaConverters._

object BattleShipProtocol {

  //Converter: BattlePos => Protobuf Position
  private def convert(BattlePos3000: BattlePos): Position = {
    Position
      .newBuilder()
      .setColumn(BattlePos3000.x)
      .setRow(BattlePos3000.y)
      .build()
  }

  //Converter: Protobuf Position => BattlePos
  private def convert(Position3000: Position): BattlePos = {
    BattlePos(Position3000.getColumn, Position3000.getRow)
  }

  //Converter: Vessel => Protobuf Vessel
  def convert(vessel: at.fhj.swengb.apps.battleship.model.Vessel): Vessel = {
    Vessel.newBuilder()
      .setAlignment(vessel.direction match {
        case Vertical => "Vertical";
        case Horizontal => "Horizontal";
        case _ => "Vertical"
      })
      .setName(vessel.name.value)
      .setSize(vessel.size)
      .setPos(
        Position
          .newBuilder()
          .setRow(vessel.InitPosition.y)
          .setColumn(vessel.InitPosition.x)
          .build())
      .build()
  }

  //Converter: Protobuf Vessel => Vessel
  def convert(vessel: Vessel): at.fhj.swengb.apps.battleship.model.Vessel = {
    at.fhj.swengb.apps.battleship.model.Vessel(
      NonEmptyString(vessel.getName),
      BattlePos(vessel.getPos.getColumn, vessel.getPos.getRow),
      vessel.getAlignment match {
        case "Vertical" => Vertical
        case "Horizontal" => Horizontal
        case _ => Vertical
      },
      vessel.getSize)
  }

  //Converter: PlayerField => Protobuf PlayerField
  def convert(game: PlayerField): BattleShipProtobuf.BattleShipGame.PlayerField = {
    val ProtoField3000 = BattleShipProtobuf.BattleShipGame.PlayerField.newBuilder()

    ProtoField3000.setColumns(game.battleField.width)
    ProtoField3000.setRows(game.battleField.height)
    ProtoField3000.setCommander(game.commander)
    //Converting the Vessels into Protobuf Format
    game.battleField.fleet.vessels.map(x => convert(x)).foreach(x => ProtoField3000.addVessels(x))
    //Converting the Cells that were already hit into Protobuf Format
    game.GameState.map(x => convert(x)).foreach(x => ProtoField3000.addHitCells(x))
    //Convert the Game State into Protobuf Format
    ProtoField3000.build()
  }

  //Converter: Protobuf PlayerField => PlayerField
  def convert(game: BattleShipProtobuf.BattleShipGame.PlayerField): PlayerField = {

    //Rebuilding the whole Game State
    val BattleShipGame3000 = PlayerField(
      BattleField(game.getColumns, game.getRows, Fleet(game.getVesselsList.asScala.map(e => convert(e)).toSet)),
      e => (), //Log
      e => (), //Slider
      e => e.toDouble, //CellWidth
      e => e.toDouble,//CellHeight
      game.getCommander) //Commander
    //List with Cells that were already hit:
    val HitCells: List[BattlePos] = game.getHitCellsList.asScala.map(e => convert(e)).toList
    BattleShipGame3000.GameState = HitCells
    BattleShipGame3000 //Returning Game State
  }
/*
  //Converter: Game => Protobuf Game
  def convert(game: BattleShipGame): BattleShipProtobuf.BattleShipGame = {

    val protobufGame = BattleShipProtobuf.BattleShipGame.newBuilder().setBattleName(game.battleName)

    val protoGames = game.WarAreas.map(e => convert(e))
    protoGames.foreach(e => protobufGame.addWarAreas(e))

    if (! game.getChampion.isEmpty) {
      protobufGame.setChampion(convert(game.getChampion.get))
    }
    protobufGame.build()
  }

  //Converter: Protobuf Game => Game
  def convert(protobufGame: BattleShipProtobuf.BattleShipGame): BattleShipGame = {

    val BattleShipGame3000 = BattleShipGame(protobufGame.getWarAreasList.asScala.map(e => convert(e)).toList, protobufGame.getBattleName)

    //Set Winner of round if set (Highscore data)
    if (protobufGame.hasChampion) {
      BattleShipGame3000.setChampion(convert(protobufGame.getChampion))
    }
    BattleShipGame3000
  }*/
}