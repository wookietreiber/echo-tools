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

/** Contains attribute-related messages. */
object Attribute {
  /** Message that will issue the change of an attribute. */
  case class ModifyAttribute(attribute: Attribute, mod: Int => Int)

  /** Message that will check the attribute. */
  case class CheckAttribute(attribute: Attribute, lvl: Int, mod: Int => Int = identity)
}

/** Attributes represent natural abilities of a character.
 *
 *  Attributes are categorized twice into three groups: [[rpg.echo.Mental]],
 *  [[rpg.echo.Physical]], [[rpg.echo.Social]] and [[rpg.echo.Power]],
 *  [[rpg.echo.Finesse]], [[rpg.echo.Resistance]].
 */
sealed abstract class Attribute extends GenericAttribute

/** Mental attributes represent insightfulness, cleverness and determination. */
sealed trait Mental

/** Physical attributes represent power, agility, and resilience. */
sealed trait Physical

/** Social attributes represent assertiveness, charisma and poise. */
sealed trait Social

/** Power represents how surroundings are affected. */
sealed trait Power

/** Finesse represents the ability to influence surroundings. */
sealed trait Finesse

/** Resistance represents the reaction to surroundings. */
sealed trait Resistance

/** Intelligence includes the ability to process and remember information. */
case object Intelligence extends Attribute with Mental with Power

/** Wits keeps one sharp and ready for anything. */
case object Wits extends Attribute with Mental with Finesse

/** Resolve is the mental focus, determination and stamina. */
case object Resolve extends Attribute with Mental with Resistance

/** Strength includes raw power and physical might. */
case object Strength extends Attribute with Physical with Power

/** Dexterity includes hand-eye coordination and response timing. */
case object Dexterity extends Attribute with Physical with Finesse

/** Stamina includes a characters physical toughness. */
case object Stamina extends Attribute with Physical with Resistance

/** Presence includes the sense of command and leadership skills. */
case object Presence extends Attribute with Social with Power

/** Manipulation includes charm and power to coerce others. */
case object Manipulation extends Attribute with Social with Finesse

/** Composure includes self control and emotional fortitude. */
case object Composure extends Attribute with Social with Resistance
