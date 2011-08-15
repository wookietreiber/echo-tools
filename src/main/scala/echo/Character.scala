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

class Character(val name: String)
  extends rpg.Character[Attribute,Skill,Check[Attribute],Check[Skill]] {

  val attributes = new Attributes[Attribute,Check[Attribute]] {
    override lazy val defaultAttributeValues = (a: Attribute) => 2
    override def check(a: Attribute) = Check(name, a, attributes(a))
  }

  val skills = new Skills[Attribute,Skill,Check[Skill]] {
    override lazy val defaultSkillValues = (s: Skill) => -1
    override def check(s: Skill, using: List[Attribute]) = {
      val avsAvg = avg(using map { attributes(_) })
      var sv = skills(s)
      sv = avg(List(sv,sv,avsAvg))
      Check(name, s, sv)
    }
  }

  val hitpoints = new HitPoints {
    override def maxhp = 6 + 2 * attributes(Stamina)
  }
/*
  def lowhpMod = hp match {
    case hp if hp < maxhp * 2 / 3. => -1
    case hp if hp < maxhp     / 3. => -2
    case _                         =>  0
  }
*/
}
