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

package litter.laws.discipline

import cats.kernel.Eq
import cats.kernel.laws.discipline.CommutativeSemigroupTests
import litter.CommutativeZeroSemigroup
import litter.laws.CommutativeZeroSemigroupLaws
import org.scalacheck.{Arbitrary, Prop}

trait CommutativeZeroSemigroupTests[A]
    extends CommutativeSemigroupTests[A]
    with ZeroSemigroupTests[A] {
  def laws: CommutativeZeroSemigroupLaws[A]

  def commutativeZeroSemigroup(implicit arbA: Arbitrary[A], eqA: Eq[A]): RuleSet =
    new RuleSet {
      val name: String = "commutativeZeroSemigroup"
      val bases: Seq[(String, RuleSet)] = Nil
      val parents: Seq[RuleSet] = Seq(commutativeSemigroup, zeroSemigroup)
      val props: Seq[(String, Prop)] = Nil
    }

}

object CommutativeZeroSemigroupTests {
  def apply[A: CommutativeZeroSemigroup]: CommutativeZeroSemigroupTests[A] =
    new CommutativeZeroSemigroupTests[A] {
      def laws: CommutativeZeroSemigroupLaws[A] = CommutativeZeroSemigroupLaws[A]
    }
}
