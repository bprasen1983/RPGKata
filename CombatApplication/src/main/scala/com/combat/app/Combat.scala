package com.combat.app

import com.damage.app.Damage
import com.heal.app.Heal
import com.utilities.app.Utilities.{DealInteraction, Person}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Await, Future}
import scala.concurrent.duration._
import scala.util.{Failure, Success}

/**
  * Created by prasenjit.b on 8/28/2017.
  */



object Combat {

  val damage = implicitly[Damage[Person,DealInteraction]]
  val heal = implicitly[Heal[Person,DealInteraction]]


  def p1ActedDamageOnP2(): Person ={
    val person1 = Person("p1")
    val person2 = Person("p2")
    Await.result( damage( person2, DealInteraction(200)), 1 seconds )
  }

  def p1ActedHealOnP2(): Person ={
    val person1 = Person("p1")
    val person2 = Person("p2",300,1,true)
    Await.result( heal( person2, DealInteraction(200)), 1 seconds )
  }

  def p1ActedDamageAndThenHealOnP2():Person={
    val person1 = Person("p1")
    val person2 = Person("p2")
    val damagedP2 = Await.result( damage( person2, DealInteraction(200)), 1 seconds )
    Await.result( heal( damagedP2, DealInteraction(100)), 1 seconds )
  }

}
