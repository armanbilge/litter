ThisBuild / baseVersion := "0.1"

ThisBuild / organization := "com.armanbilge"
ThisBuild / publishGithubUser := "armanbilge"
ThisBuild / publishFullName := "Arman Bilge"
ThisBuild / startYear := Some(2021)

mimaPreviousArtifacts := Set()

enablePlugins(SonatypeCiReleasePlugin)
ThisBuild / homepage := Some(url("https://github.com/armanbilge/litter"))
ThisBuild / scmInfo := Some(
  ScmInfo(url("https://github.com/armanbilge/litter"), "git@github.com:armanbilge/litter.git"))
sonatypeCredentialHost := "s01.oss.sonatype.org"

val Scala212 = "2.12.14"
val Scala213 = "2.13.6"
val Scala3 = "3.0.2"
ThisBuild / crossScalaVersions := Seq(Scala212, Scala213, Scala3)

replaceCommandAlias(
  "ci",
  "; project /; headerCheckAll; scalafmtCheckAll; scalafmtSbtCheck; clean; testIfRelevant; mimaReportBinaryIssuesIfRelevant"
)
addCommandAlias("prePR", "; root/clean; +root/scalafmtAll; scalafmtSbt; +root/headerCreate")

val CatsVersion = "2.6.1"
val Specs2Version = "4.11.0"
val ScalaCheckVersion = "1.15.3"
val DisciplineVersion = "1.1.6"

lazy val root =
  project.aggregate(core, laws).enablePlugins(NoPublishPlugin)

lazy val core = project
  .in(file("core"))
  .settings(
    name := "litter",
    sonatypeCredentialHost := "s01.oss.sonatype.org",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-kernel" % CatsVersion
    )
  )

lazy val laws = project
  .in(file("laws"))
  .dependsOn(core)
  .settings(
    name := "litter-laws",
    sonatypeCredentialHost := "s01.oss.sonatype.org",
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-kernel-laws" % CatsVersion,
      "org.typelevel" %% "discipline-specs2" % DisciplineVersion % Test
    )
  )
