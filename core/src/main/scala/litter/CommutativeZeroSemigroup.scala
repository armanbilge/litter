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

package litter

import cats.kernel.CommutativeSemigroup

import scala.{specialized => sp}

/**
 * CommutativeZeroSemigroup represents a commutative zero semigroup.
 *
 * A zero semigroup is commutative if for all x and y, x |+| y === y |+| x.
 */
trait CommutativeZeroSemigroup[@sp(Int, Long, Float, Double) A]
    extends Any
    with ZeroSemigroup[A]
    with CommutativeSemigroup[A] { self =>
  override def reverse: CommutativeZeroSemigroup[A] = self
  override def intercalate(middle: A): CommutativeZeroSemigroup[A] =
    new CommutativeZeroSemigroup[A] {
      override val absorbing: A = self.absorbing

      def combine(a: A, b: A): A =
        self.combine(a, self.combine(middle, b))

      override def combineN(a: A, n: Int): A =
        if (n <= 1) self.combineN(a, n)
        else {
          // a + m + a ... = combineN(a, n) + combineN(m, n - 1)
          self.combine(self.combineN(a, n), self.combineN(middle, n - 1))
        }
    }
}

object CommutativeZeroSemigroup extends ZeroSemigroupFunctions[CommutativeZeroSemigroup] {

  /**
   * Access an implicit `CommutativeZeroSemigroup[A]`.
   */
  @inline final def apply[A](
      implicit ev: CommutativeZeroSemigroup[A]): CommutativeZeroSemigroup[A] = ev

  /**
   * Create a `CommutativeZeroSemigroup` instance from the given absorbing value and function.
   */
  @inline def instance[A](absorbingValue: A, cmb: (A, A) => A): CommutativeZeroSemigroup[A] =
    new CommutativeZeroSemigroup[A] {
      override val absorbing: A = absorbingValue
      override def combine(x: A, y: A): A = cmb(x, y)
    }
}
