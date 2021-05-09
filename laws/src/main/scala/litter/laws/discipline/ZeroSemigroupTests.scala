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
import cats.kernel.laws.discipline.{SemigroupTests, _}
import litter.ZeroSemigroup
import litter.laws.ZeroSemigroupLaws
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll

trait ZeroSemigroupTests[A] extends SemigroupTests[A] {

  def laws: ZeroSemigroupLaws[A]

  def zeroSemigroup(implicit arbA: Arbitrary[A], eqA: Eq[A]): RuleSet =
    new DefaultRuleSet(
      "zeroSemigroup",
      Some(semigroup),
      "left absorbtion" -> forAll(laws.leftAbsorbtion _),
      "right absorbtion" -> forAll(laws.rightAbsorbtion _),
      "isAbsorbing" -> forAll((a: A) => laws.isAbsorbing(a, eqA))
    )
}

object ZeroSemigroupTests {
  def apply[A: ZeroSemigroup]: ZeroSemigroupTests[A] =
    new ZeroSemigroupTests[A] { def laws: ZeroSemigroupLaws[A] = ZeroSemigroupLaws[A] }
}
