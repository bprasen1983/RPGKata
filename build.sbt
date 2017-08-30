name := "RPGKata"

version := "1.0"

javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")
publishArtifact := false

scalaVersion := "2.12.1"

val versions = new {
  val junit = "4.12"
  val scalatest = "3.0.1"
  val mockito = "1.10.19"
 }

lazy val commonSettings = Seq(
  libraryDependencies += "org.scalatest" %% "scalatest" % versions.scalatest % "test",
  libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
  libraryDependencies += "org.mockito" % "mockito-all" % versions.mockito % "test"
)

lazy val utilities = (project in file ("RPGUtilities"))

lazy val damageApp = (project in file ("DamageHealthApplication"))
  .dependsOn( utilities ).aggregate( utilities)
  .settings(commonSettings :_*)

lazy val healApp = (project in file ("HealHealthApplication"))
  .dependsOn( utilities ).aggregate( utilities)
  .settings(commonSettings :_*)

lazy val combatApp = (project in file ("CombatApplication"))
  .dependsOn(healApp, damageApp, utilities)
  .aggregate(healApp, damageApp, utilities)
  .settings( commonSettings :_*)
        