package com.evolution.project

import cats._
import cats.data._
import cats.effect._
import cats.implicits._
import org.http4s._
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.client.dsl.io._
import org.http4s.dsl.io._
import org.http4s.headers._
import org.http4s.implicits._

import scala.concurrent.ExecutionContext

object Dwa extends IOApp {

  private val uri = uri"http://localhost:9001"

  private def printLine(string: String = ""): IO[Unit] = IO(println(string))

  override def run(args: List[String]): IO[ExitCode] =
    BlazeClientBuilder[IO](ExecutionContext.global).resource.use { client =>
      for {
        _ <- IO(println("GET"))
        _ <- client.expect[String](uri / "hello" / "world") >>= printLine
        _ <- IO(println("POST"))
        _ <- client.expect[String](Method.POST("world", uri / "hello")) >>= printLine
      } yield ()
    }.as(ExitCode.Success)
}
