package com.damage.app

import com.damage.app.IsDeadCharacter.IsDeadCharacterImpl
import com.damage.app.KillCharacter.KillCharacterImpl
import com.utilities.app.Utilities.Person
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.mockito.MockitoSugar

/**
  * Created by prasenjit.b on 8/30/2017.
  */
class TestDamage extends FlatSpec with Matchers with MockitoSugar{

  it should "Test IsDeadCharacter : return DEAD character as Person health is 0" in{
    val person = Person("person", 0,1,true)
    val isDead = IsDeadCharacterImpl(person)
    isDead shouldBe( true )
  }

  it should "Test IsDeadCharacter : return DEAD character as Person active flag is false" in{
    val person = Person("person", 100,1,false)
    val isDead = IsDeadCharacterImpl(person)
    isDead shouldBe( true )
  }

  it should "Test IsDeadCharacter : return LIVE character as Person active flag is not false or health is not 0" in{
    val person = Person("person", 100,1,true)
    val isDead = IsDeadCharacterImpl(person)
    isDead shouldBe( false )
  }

  it should "Test KillCharacter : return Person active flag as false as health is 0" in{
    val person = KillCharacterImpl( Person("person", 0,1,true) )
    person.active shouldBe( false )
  }

  it should "Test KillCharacter : return Person active flag as true as health is not 0" in{
    val person = KillCharacterImpl( Person("person", 1,1,true) )
    person.active shouldBe( true )
  }
}
