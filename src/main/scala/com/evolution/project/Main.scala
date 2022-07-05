package com.evolution.project

import doobie._
import doobie.implicits._
import doobie.util.ExecutionContexts

import cats._
import cats.data._
import cats.effect._
import cats.implicits._

// import cats.effect.{ExitCode, IO, IOApp}
import org.http4s.{HttpApp, HttpRoutes, Response}
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.dsl.io._
import org.http4s.implicits._

import scala.concurrent.ExecutionContext
// import cats.effect.concurrent.Ref

import java.time.Instant

import cats.syntax.either._
import io.circe._
import io.circe.Decoder.Result
import io.circe.generic.JsonCodec
import io.circe.parser._
import io.circe.syntax._

object Main extends IOApp {
  // scala.io.StdIn.readLine()
  // println("hello from Main")
  // IO(println("Hello from Main!"))

  private val helloRoutes = HttpRoutes.of[IO] {

    // curl "localhost:9001/hello/world"
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name!")

    // curl -XPOST "localhost:9001/hello" -d "world"
    case req @ POST -> Root / "hello" =>
      Ok(req.as[String].map(name => s"Hello again, $name!"))
  }

  val httpApp = helloRoutes.orNotFound

  // override def run(args: List[String]): IO[ExitCode] = {
  //   IO(println("Hello from Main!")).as(ExitCode.Success)
  // }

  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO](ExecutionContext.global)
      .bindHttp(port = 9001, host = "localhost")
      .withHttpApp(httpApp)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
