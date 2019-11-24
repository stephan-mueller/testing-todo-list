package scala.loadtests

import io.gatling.core.Predef.{reachRps, _}
import io.gatling.http.Predef._

import scala.concurrent.duration._

class PeakTest extends Simulation {

  val httpConf = http.baseUrl("http://localhost:8080/todo-list-service/api")

  val users = scenario("PEAK_TEST")
    .exec(http("get todos").get("/todos").check(status.is(200)))
    .exitHereIfFailed

  setUp(
    users.inject(constantUsersPerSec(500) during (30 minutes))
  ).protocols(httpConf).throttle(
    reachRps(100) in (10 seconds),
    holdFor(5 seconds),
    reachRps(500) in (5 seconds),
    holdFor(10 seconds),
    reachRps(100) in (5 seconds),
    holdFor(5 seconds),
  )
}