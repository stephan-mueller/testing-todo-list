package scala.loadtests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class LoadTest extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/todo-list-service/api")

  val users = scenario("LOAD_TEST")
    .exec(http("get todos").get("/todos").check(status.is(200)))
    .exitHereIfFailed

  setUp(
    users.inject(constantUsersPerSec(100) during (30 minutes))
  ).protocols(httpConf).throttle(
    reachRps(100) in (10 seconds),
    holdFor(30 seconds),
    reachRps(0) in (10 seconds),
  )
}