name := """try-Thogakade"""

version := "1.0"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  "org.slf4j" % "slf4j-log4j12" % "1.7.5",
  "mysql" % "mysql-connector-java" % "5.1.6"
)


playEbeanModels in Compile := Seq("com.thogakade.models.*")
playEbeanDebugLevel := 4
playEbeanAgentArgs += ("detect" -> "false")
inConfig(Test)(PlayEbean.scopedSettings)

playEbeanModels in Test := Seq("com.thogakade.models.*")
routesGenerator := InjectedRoutesGenerator

fork in run := false