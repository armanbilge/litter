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
import cats.kernel.laws.discipline.BandTests
import litter.ZeroBand
import litter.laws.ZeroBandLaws
import org.scalacheck.{Arbitrary, Prop}

trait ZeroBandTests[A] extends BandTests[A] with ZeroSemigroupTests[A] {
  def laws: ZeroBandLaws[A]

  def zeroBand(implicit arbA: Arbitrary[A], eqA: Eq[A]): RuleSet =
    new RuleSet {
      val name: String = "ZeroBand"
      val bases: Seq[(String, RuleSet)] = Nil
      val parents: Seq[RuleSet] = Seq(band, zeroSemigroup)
      val props: Seq[(String, Prop)] = Nil
    }

}

object ZeroBandTests {
  def apply[A: ZeroBand]: ZeroBandTests[A] =
    new ZeroBandTests[A] { def laws: ZeroBandLaws[A] = ZeroBandLaws[A] }
}
