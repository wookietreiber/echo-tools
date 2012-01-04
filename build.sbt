
name := "echo-tools"

version := "0.0.1"

organization := "com.github.wookietreiber"

scalaVersion := "2.9.1"

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases"

libraryDependencies ++= Seq(
  "se.scalablesolutions.akka" %  "akka-actor" % "1.1.3",
  "com.github.wookietreiber"  %% "arpgt"      % "latest.integration",
  "org.specs2"                %% "specs2"     % "1.7.1"                 % "test"
)

initialCommands := """
  import rpg._
  import echo._
  import cli._
"""

