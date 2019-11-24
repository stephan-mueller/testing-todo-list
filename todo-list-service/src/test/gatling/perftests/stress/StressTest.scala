package scala.loadtests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.concurrent.duration._

class StressTest extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/todo-list-service/api")

  val users = scenario("STRESS_TEST")
    .exec(http("get todos").get("/todos").check(status.is(200)))
    .exitHereIfFailed

  setUp(
    users.inject(constantUsersPerSec(2000) during (30 minutes))
  ).protocols(httpConf).throttle(
    reachRps(2000) in (30 seconds),
    holdFor(10 seconds)
  )
}