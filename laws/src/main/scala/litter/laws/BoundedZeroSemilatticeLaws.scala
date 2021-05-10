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

package litter.laws

import cats.kernel.laws.BoundedSemilatticeLaws
import litter.BoundedZeroSemilattice

trait BoundedZeroSemilatticeLaws[A]
    extends BoundedSemilatticeLaws[A]
    with ZeroSemilatticeLaws[A]
    with CommutativeZeroMonoidLaws[A] {
  implicit override def S: BoundedZeroSemilattice[A]
}

object BoundedZeroSemilatticeLaws {
  def apply[A](implicit ev: BoundedZeroSemilattice[A]): BoundedZeroSemilatticeLaws[A] =
    new BoundedZeroSemilatticeLaws[A] { def S: BoundedZeroSemilattice[A] = ev }
}
