package com.evolution.project

import cats._
import cats.data._
import cats.effect._
import cats.implicits._

case class Costam(foo: String, bar: String)

object Dwa extends IOApp {
  import Dane._

  override def run(args: List[String]): IO[ExitCode] = {
    val blah = Klasa("666")
    IO(println("hello2")).as(ExitCode.Success)
  }
}
