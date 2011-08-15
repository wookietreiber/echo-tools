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

/** Provides useful aliases and implicit conversions for command line
  * interfaces.
  *
  * This package is automatically imported by the sbt console task.
  *
  * The aliases are short and lowercase to type them fast and should not be used
  * within the project because they are much harder to understand than usual
  * descriptors.
  */
package object cli {

  // -------------------------------------------------------------------
  // attribute related aliases
  // -------------------------------------------------------------------

  val int = Intelligence
  val wit = Wits
  val res = Resolve
  val str = Strength
  val dex = Dexterity
  val sta = Stamina
  val pre = Presence
  val man = Manipulation
  val com = Composure

  // -------------------------------------------------------------------
  // hit point related aliases
  // -------------------------------------------------------------------

  val  dmg  = HitPoints.Damage
  val  life = HitPoints.Life

  // -------------------------------------------------------------------
  // implicit conversions
  // -------------------------------------------------------------------

  implicit def attr2list(attr: Attribute) = List(attr)
  implicit def skill2list(skill: Skill) = List(skill)
}
