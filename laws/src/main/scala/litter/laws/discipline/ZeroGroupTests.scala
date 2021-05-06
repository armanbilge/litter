/*
 * Copyright 2021 Arman Bilge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package litterbox.laws.discipline

import cats.kernel.Eq
import cats.kernel.laws.discipline.GroupTests
import litterbox.ZeroGroup
import litterbox.laws.ZeroGroupLaws
import org.scalacheck.{Arbitrary, Prop}

trait ZeroGroupTests[A] extends GroupTests[A] with ZeroMonoidTests[A] {
  def laws: ZeroGroupLaws[A]

  def zeroGroup(implicit arbA: Arbitrary[A], eqA: Eq[A]): RuleSet =
    new RuleSet {
      val name: String = "zeroGroup"
      val bases: Seq[(String, RuleSet)] = Nil
      val parents: Seq[RuleSet] = Seq(group, zeroMonoid)
      val props: Seq[(String, Prop)] = Nil
    }

}

object ZeroGroupTests {
  def apply[A: ZeroGroup]: ZeroGroupTests[A] =
    new ZeroGroupTests[A] { def laws: ZeroGroupLaws[A] = ZeroGroupLaws[A] }
}
