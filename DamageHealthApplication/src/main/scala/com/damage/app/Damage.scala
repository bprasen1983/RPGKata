package com.damage.app

import com.utilities.app.Utilities.{DealInteraction, Person}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

/**
  * Created by prasenjit.b on 8/28/2017.
  */
trait Damage[P,DI] extends( (P,DI) => Future[P] )

trait IsDeadCharacter[P] extends ( P => Boolean )

object IsDeadCharacter{
  implicit object IsDeadCharacterImpl extends IsDeadCharacter[ Person ] {
    override def apply( person: Person ): Boolean = {
      if( person.health == 0 || !person.active ){
        true
      }else{
        false
      }
    }
  }
}

trait KillCharacter[P] extends ( P => P )

object KillCharacter{
  implicit object KillCharacterImpl extends KillCharacter[Person] {
    override def apply( person: Person ): Person = {
      if( person.health == 0 ){
        Person( person.name, person.health, person.level, false)
      }else{
        person
      }
    }
  }
}

object Damage {
  implicit object DamagePerson extends Damage[Person, DealInteraction]{
    override def apply( person: Person, interaction: DealInteraction ): Future[Person] = {
      Future( substractHealth( person, interaction ) )
    }
  }

  def substractHealth( person: Person, interaction: DealInteraction )( implicit isDead : IsDeadCharacter[Person], killCharacter : KillCharacter[Person] ) : Person = {
    if( isDead( person ) ){
      person
    }else{
      if( interaction.amountOfInteraction > person.health ){
        killCharacter( Person( person.name, 0, person.level, person.active ) )
      }else{
        killCharacter( Person( person.name, person.health - interaction.amountOfInteraction, person.level, person.active ) )
      }
    }
  }
}
