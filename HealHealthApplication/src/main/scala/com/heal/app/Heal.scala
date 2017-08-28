package com.heal.app

import com.utilities.app.Utilities.{DealInteraction, Person}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by prasenjit.b on 8/28/2017.
  */
trait Heal[P,DI] extends ( (P,DI) => Future[P] )

trait PersonHealContract[P,DI] extends ( (P,DI) => P )

object PersonHealContract{
  implicit object HealContractImpl extends PersonHealContract[Person, DealInteraction] {
    override def apply( person: Person, dealInteraction: DealInteraction): Person = {
      if( person.health + dealInteraction.amountOfInteraction >= 1000 ){
        person
      }else{
        Person( person.name, person.health + dealInteraction.amountOfInteraction, person.level, person.active )
      }
    }
  }
}

object Heal{
  implicit object HealCharacter extends Heal[Person, DealInteraction] {
    override def apply(person: Person, dealInteraction: DealInteraction): Future[Person] = {
      Future( addHealthIfPersonIsAlive( person, dealInteraction ) )
    }
  }

  def addHealthIfPersonIsAlive( person: Person, dealInteraction: DealInteraction )(implicit healContract : PersonHealContract[Person, DealInteraction]):Person={
    if( person.active ){
      healContract( person, dealInteraction )
    }else{
      println(s" Dead Charcter ${person.name} can not be healed")
      person
    }
  }
}


