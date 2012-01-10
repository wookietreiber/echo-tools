import sbt._
import Keys._

import Resolvers._
import Dependencies._
import BuildSettings._

object BuildSettings {
  val buildOrganization = "com.github.echo-tools"
  val buildVersion      = "0.0.1"
  val buildScalaVersion = "2.9.1"
  val akkaVersion       = "1.2"
  val latest            = "latest.integration"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion
  )
}

object EchoBuild extends Build {

  // -----------------------------------------------------------------------
  // project definition
  // -----------------------------------------------------------------------

  lazy val root = Project ( "echo-tools", file ("."),
    settings = buildSettings ++ Seq (
      libraryDependencies ++= Seq ( actor, arpgt, specs2 ),
      initialCommands in Compile in console := """
        import rpg._
        import rpg.cli._
        import rpg.echo._
        import rpg.echo.cli._
      """
    )
  )

}

object Dependencies {

  // -----------------------------------------------------------------------
  // compile
  // -----------------------------------------------------------------------

  val actor = "se.scalablesolutions.akka" %  "akka-actor" % akkaVersion
  val arpgt = "com.github.arpgt"          %% "arpgt"      % latest

  // -----------------------------------------------------------------------
  // test
  // -----------------------------------------------------------------------

  val specs2 = "org.specs2" %% "specs2" % "1.7.1" % "test"

}

object Resolvers {
  val typesafe = "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"
}
