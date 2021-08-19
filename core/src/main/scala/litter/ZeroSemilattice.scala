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

import cats.kernel.{Semilattice, SemilatticeFunctions}

import scala.{specialized => sp}

/**
 * Zero semilattices are semilattices with an absorbing element that satisfies `combine(x,
 * absorbing) == combine(absorbing, x) == absorbing`.
 */
trait ZeroSemilattice[@sp(Int, Long, Float, Double) A]
    extends Any
    with Semilattice[A]
    with ZeroBand[A]
    with CommutativeZeroSemigroup[A]

trait ZeroSemilatticeFunctions[S[T] <: ZeroSemilattice[T]]
    extends SemilatticeFunctions[S]
    with ZeroSemigroupFunctions[S]

object ZeroSemilattice extends ZeroSemilatticeFunctions[ZeroSemilattice] {

  /**
   * Access an implicit `ZeroSemilattice[A]`.
   */
  @inline final def apply[@sp(Int, Long, Float, Double) A](
      implicit ev: Semilattice[A]): Semilattice[A] = ev

  /**
   * Create a `ZeroSemilattice` instance from the given function and absorbing value.
   */
  @inline def instance[A](absorbingValue: A, cmb: (A, A) => A): ZeroSemilattice[A] =
    new ZeroSemilattice[A] {
      override val absorbing: A = absorbingValue
      override def combine(x: A, y: A): A = cmb(x, y)
    }
}
