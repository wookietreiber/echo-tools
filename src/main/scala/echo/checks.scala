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

import rpg.Check.Checkee

object Check {
  def apply[A](description: String, checked: A, value: Int) =
    new Check(Checkee(description, checked, value))

  def check(actor: Int, reactor: Int): Result = actor match {
    case n if n < 1 => Incapable
    case n          => Check.check(chance(actor - reactor))
  }

  private def check(chance: Int): Result = chance match {
    case chance if chance <=   0 => Inferior
    case chance if chance >= 100 => Superior
    case chance                  => (chance - util.Random.nextInt(100) + 1) match {
      case diff if diff > 0 => Succeeded(diff.toString)
      case diff             => Failed(diff.toString)
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

case class Check[A](
    checkee: Checkee[A],
    opponent: Option[Checkee[A]] = None)
  extends rpg.Check[A,Check[A]] {

  def copy(checkee: Checkee[A] = checkee, opponent: Option[Checkee[A]] = opponent) =
    new Check(checkee, opponent)

  def result = Check.check(
    checkee.value,
    opponent.map { _.value } getOrElse checkee.value)
}
