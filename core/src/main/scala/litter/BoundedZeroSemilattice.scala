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

import cats.kernel.BoundedSemilattice

import scala.{specialized => sp}

/**
 * Bounded zero semilattices are bounded semilattices with an absorbing element that
 * satisfies `combine(x, absorbing) == combine(absorbing, x) == absorbing`.
 */
trait BoundedZeroSemilattice[@sp(Int, Long, Float, Double) A]
    extends Any
    with BoundedSemilattice[A]
    with ZeroSemilattice[A]
    with CommutativeZeroMonoid[A]

object BoundedZeroSemilattice extends ZeroSemilatticeFunctions[BoundedZeroSemilattice] {

  /**
   * Access an implicit `BoundedZeroSemilattice[A]`.
   */
  @inline final def apply[@sp(Int, Long, Float, Double) A](
      implicit ev: BoundedZeroSemilattice[A]): BoundedZeroSemilattice[A] =
    ev

  /**
   * Create a `BoundedZeroSemilattice` instance from the given function and empty and absorbing values.
   */
  @inline def instance[A](
      emptyValue: A,
      absorbingValue: A,
      cmb: (A, A) => A): BoundedZeroSemilattice[A] =
    new BoundedZeroSemilattice[A] {
      override val empty: A = emptyValue
      override val absorbing: A = absorbingValue

      override def combine(x: A, y: A): A = cmb(x, y)
    }
}
