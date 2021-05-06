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

package litterbox

import cats.kernel.CommutativeMonoid

import scala.{specialized => sp}

/**
 * CommutativeZeroMonoid represents a commutative zero monoid.
 *
 * A zero monoid is commutative if for all x and y, x |+| y === y |+| x.
 */
trait CommutativeZeroMonoid[@sp(Int, Long, Float, Double) A]
    extends Any
    with ZeroMonoid[A]
    with CommutativeMonoid[A]
    with CommutativeZeroSemigroup[A] { self =>
  override def reverse: CommutativeZeroMonoid[A] = self
}

object CommutativeZeroMonoid extends ZeroMonoidFunctions[CommutativeZeroMonoid] {

  /**
   * Access an implicit `CommutativeZeroMonoid[A]`.
   */
  @inline final def apply[A](implicit ev: CommutativeZeroMonoid[A]): CommutativeMonoid[A] = ev

  /**
   * Create a `CommutativeZeroMonoid` instance from the given function and empty value.
   */
  @inline def instance[A](
      emptyValue: A,
      absorbingValue: A,
      cmb: (A, A) => A): CommutativeMonoid[A] =
    new CommutativeZeroMonoid[A] {
      override val empty: A = emptyValue
      override val absorbing: A = absorbingValue

      override def combine(x: A, y: A): A = cmb(x, y)
    }
}
