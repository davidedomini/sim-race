package it.unibo.pps

import alice.tuprolog.{Term, Theory}
import it.unibo.pps.utility.PimpScala.RichTuple2.*
import it.unibo.pps.utility.PimpScala.RichInt.*
import it.unibo.pps.view.simulation_panel.{DrawingParams, DrawingTurnParams}
import monix.eval.Task
import monix.execution.Scheduler.Implicits.global

package object engine:

  val computeRadius = (d: DrawingParams, position: Tuple2[Int, Int]) =>
    d match
      case DrawingTurnParams(center, _, _, _, _, _, _) => center euclideanDistance position

  val angleBetweenPoints = (a: Tuple2[Int, Int], b: Tuple2[Int, Int], radius: Int) =>
    val distance = a euclideanDistance b
    Math.acos(((2 * radius ** 2) - distance) / (2 * radius ** 2))

  val circularArc = (teta: Double, radius: Int) => (teta / 360) * 2 * radius * Math.PI

  given RunTask[E]: Conversion[Task[E], E] = _.runSyncUnsafe()
  given Itearable2List[E]: Conversion[Iterable[E], List[E]] = _.toList
  given Conversion[String, Term] = Term.createTerm(_)
  given Conversion[Seq[_], Term] = _.mkString("[", ",", "]")
  given Conversion[String, Theory] = Theory.parseLazilyWithStandardOperators(_)
