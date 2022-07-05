package com.evolution.project

import cats._
import cats.data._
import cats.effect._
import cats.implicits._

case class Costam(foo: String, bar: String)

object Dwa extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    IO(println("hello2")).as(ExitCode.Success)
  }
}
