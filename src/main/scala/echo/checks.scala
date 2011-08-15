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

object Check {
  def check(actor: Int, reactor: Int)(description: => String): Result = actor match {
    case n if n < 1 => Incapable()
    case n          => Check.check(chance(actor - reactor), description)
  }

  private def check(chance: Int, description: => String): Result = chance match {
    case chance if chance <=   0 => Inferior()
    case chance if chance >= 100 => Superior()
    case chance                  => (chance - util.Random.nextInt(100) + 1) match {
      case diff if diff > 0 => Succeeded()
      case diff             => Failed()
    }
  }

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

case class Check(
    actorChecked: Any,
    actorValue: Int,
    reactorChecked: Any,
    reactorValue: Int)
  extends rpg.Check {

  def this(checked: Any, value: Int) =
    this(checked, value, checked, value)

  override def vs(difficulty: Int) = copy(reactorValue = difficulty)

  override def under(mod: Mod[Int]) = copy(actorValue = mod(actorValue))

  def result = Check.check(actorValue, reactorValue) {
    actorChecked + "(" + actorValue + ") vs " +
    reactorChecked + "(" + reactorValue + ")"
  }

  override def toString = result.toString
}
