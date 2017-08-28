package com.utilities.app

import scala.concurrent.Future

/**
  * Created by prasenjit.b on 8/28/2017.
  */
object Utilities {

  sealed trait Life{
    val active : Boolean = false
  }


  sealed trait LifeStreanth {
    val health = 0
    val level = 0
  }

  case class Person( name : String, override val health: Int = 1000, override val level : Int = 1, override val active : Boolean = true ) extends Life with LifeStreanth

  case class DealInteraction( amountOfInteraction : Int )
}
