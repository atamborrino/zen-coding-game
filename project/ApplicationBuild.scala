import sbt._
import com.typesafe.sbt.SbtStartScript

import play.Project._

object ApplicationBuild extends Build {
  val appName = "scastie"
  val appVersion = "1.0"

  val scalaVersion = "2.10.3"
  val akkaVersion = "2.2.0"

  val renderer = {
    def akka(module: String) = "com.typesafe.akka" %% ("akka-" + module) % akkaVersion
    def scalaIo(module: String) = "com.github.scala-incubator.io" %% ("scala-io-" + module) % "0.4.2"
    Project(id = "renderer", base = file("renderer"),
      settings = Defaults.defaultSettings ++
      //play.PlayProject.intellijCommandSettings("SCALA") ++
        Seq(
          Keys.scalaVersion := scalaVersion
          , Keys.libraryDependencies ++= Seq(
            "org.slf4j" % "jul-to-slf4j" % "1.6.6",
            akka("actor"),
            akka("remote"),
            akka("slf4j"),
            "com.typesafe" % "config" % "1.0.0",
            "ch.qos.logback" % "logback-classic" % "1.0.7",
            scalaIo("core"),
            scalaIo("file"),
            "org.apache.commons" % "commons-lang3" % "3.1",
            "net.sourceforge.collections" % "collections-generic" % "4.01",
            "org.scalaz" %% "scalaz-core" % "7.0.4",
            "org.scala-lang" % "scala-compiler" % scalaVersion
          )
        )
        ++ SbtStartScript.startScriptForClassesSettings
        ++ Seq(Keys.mainClass in Compile := Option("com.olegych.scastie.RendererMain"))
    )
  }

  val main = play.Project(appName, appVersion).settings(
    SbtStartScript.startScriptForClassesSettings ++
      Seq(Keys.mainClass in Compile := Option("ProdNettyServer")
        , Keys.scalaVersion := scalaVersion
        , Keys.watchSources <++= Keys.baseDirectory map { path => ((path / "public") ** "*").get}
      ): _*
  ) dependsOn renderer aggregate renderer

}
