/* **************************************************************************
 *                                                                          *
 *  Copyright (C)  2011  Christian Krause                                   *
 *                                                                          *
 *  Christian Krause <kizkizzbangbang@googlemail.com>                       *
 *                                                                          *
 ****************************************************************************
 *                                                                          *
 *  This file is part of 'echo-tools'.                                      *
 *                                                                          *
 *  This project is free software: you can redistribute it and/or modify    *
 *  it under the terms of the GNU General Public License as published by    *
 *  the Free Software Foundation, either version 3 of the License, or       *
 *  any later version.                                                      *
 *                                                                          *
 *  This project is distributed in the hope that it will be useful,         *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
 *  GNU General Public License for more details.                            *
 *                                                                          *
 *  You should have received a copy of the GNU General Public License       *
 *  along with this project. If not, see <http://www.gnu.org/licenses/>.    *
 *                                                                          *
 ****************************************************************************/


package rpg
package echo

/** Provides factory methods to get the result depending on the abilities of
  * both actor and reactor.
  */
object Result {
  /** Returns the result depending on the abilities of both actor and reactor. */
  def apply(actor: Int, reactor: Int): Result = actor match {
    case n if n < 1 => Incapable
    case n          => Result(chance(actor - reactor))
  }

  /** Returns the result depending on the chance of success. */
  def apply(chance: Int) = chance match {
    case chance if chance <=   0 => Inferior
    case chance if chance >= 100 => Superior
    case chance                  => (chance - util.Random.nextInt(100) + 1) match {
      case diff if diff > 0 => Succeeded(diff)
      case diff             => Failed(diff)
    }
  }

  /** Returns the chance in percent depending on the ability difference. */
  private def chance(diff: Int) = diff match {
    case n if n < -3 =>   0
    case          -3 =>   2
    case          -2 =>  15
    case          -1 =>  40
    case           0 =>  50
    case           1 =>  60
    case           2 =>  85
    case           3 =>  98
    case n if n >  3 => 100
  }
}

/** Represents the outcome of a check.
  *
  * @see [[rpg.echo.Success]]
  * @see [[rpg.echo.Failure]]
  */
sealed trait Result

/** The actor succeeded in any way. */
sealed trait Success extends Result

/** The actor failed in any way. */
sealed trait Failure extends Result

/** The actor is incapable of performing the check. */
case object Incapable extends Failure

/** The actor is inferior. */
case object Inferior extends Failure

/** The actor is superior. */
case object Superior extends Success

/** The actor failed. */
case class Failed(diff: Int) extends Failure

/** The actor succeeded. */
case class Succeeded(diff: Int) extends Success
