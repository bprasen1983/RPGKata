package com.combat.app

import org.scalatest.mockito.MockitoSugar
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by prasenjit.b on 8/28/2017.
  */
class TestCombat extends FlatSpec with Matchers with MockitoSugar {
  import Combat._

  it should "Test p1ActedDamageOnP2 : return health as 800" in{
    val person = p1ActedDamageOnP2()
    assert( person.health == 800 && person.name == "p2")
  }

  it should "Test p1ActedHealOnP2 : return health as 500" in{
    val person = p1ActedHealOnP2()
    assert( person.health == 500 && person.name == "p2")
  }

  it should "Test p1ActedDamageAndThenHealOnP2 : return health as 900" in{
    val person = p1ActedDamageAndThenHealOnP2()
    assert( person.health == 900 && person.name == "p2")
  }

}
