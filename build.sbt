
name := "echo-tools"

version := "0.0.1"

organization := "org.software-is-free-on-kashyyyk"

scalaVersion := "2.9.0-1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies ++= Seq(
  "se.scalablesolutions.akka"        %  "akka-actor" % "1.1.3",
  "org.software-is-free-on-kashyyyk" %% "arpgt"      % "latest.integration",
  "org.specs2"                       %% "specs2"     % "1.5"                 % "test"
)

initialCommands := """
  import rpg._
  import echo._
  import cli._
"""

