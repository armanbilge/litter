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
import cats.kernel.laws.discipline.MonoidTests
import litterbox.ZeroMonoid
import litterbox.laws.ZeroMonoidLaws
import org.scalacheck.{Arbitrary, Prop}

trait ZeroMonoidTests[A] extends MonoidTests[A] with ZeroSemigroupTests[A] {
  def laws: ZeroMonoidLaws[A]

  def zeroMonoid(implicit arbA: Arbitrary[A], eqA: Eq[A]): RuleSet =
    new RuleSet {
      val name: String = "zeroMonoid"
      val bases: Seq[(String, RuleSet)] = Nil
      val parents: Seq[RuleSet] = Seq(monoid, zeroSemigroup)
      val props: Seq[(String, Prop)] = Nil
    }

}

object ZeroMonoidTests {
  def apply[A: ZeroMonoid]: ZeroMonoidTests[A] =
    new ZeroMonoidTests[A] { def laws: ZeroMonoidLaws[A] = ZeroMonoidLaws[A] }
}
