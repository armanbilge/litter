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
import cats.kernel.laws.discipline.SemilatticeTests
import litter.ZeroSemilattice
import litter.laws.ZeroSemilatticeLaws
import org.scalacheck.{Arbitrary, Prop}

trait ZeroSemilatticeTests[A]
    extends SemilatticeTests[A]
    with ZeroBandTests[A]
    with CommutativeZeroSemigroupTests[A] {
  def laws: ZeroSemilatticeLaws[A]

  def zeroSemilattice(implicit arbA: Arbitrary[A], eqA: Eq[A]): RuleSet =
    new RuleSet {
      val name: String = "ZeroSemilattice"
      val bases: Seq[(String, RuleSet)] = Nil
      val parents: Seq[RuleSet] = Seq(semilattice, zeroBand, commutativeZeroSemigroup)
      val props: Seq[(String, Prop)] = Nil
    }

}

object ZeroSemilatticeTests {
  def apply[A: ZeroSemilattice]: ZeroSemilatticeTests[A] =
    new ZeroSemilatticeTests[A] { def laws: ZeroSemilatticeLaws[A] = ZeroSemilatticeLaws[A] }
}
