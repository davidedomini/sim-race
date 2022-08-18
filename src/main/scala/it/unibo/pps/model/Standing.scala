package it.unibo.pps.model

trait Standing:

  /** @return the current standing */
  def _standing: List[Car]

  /** Method that updates the standing after an overtake happened */
  def overtake(): Unit

object Standing:
  def apply(standing: List[Car]): Standing = new StadingImpl(standing)

  private class StadingImpl(standing: List[Car]) extends Standing:

    override def _standing: List[Car] = standing
    override def overtake(): Unit = ???