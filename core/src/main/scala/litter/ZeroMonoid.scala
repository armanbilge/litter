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

import cats.kernel.{Monoid, MonoidFunctions}

import scala.{specialized => sp}

/**
 * A monoid with an absorbing element.
 */
trait ZeroMonoid[@sp(Int, Long, Float, Double) A]
    extends Any
    with Monoid[A]
    with ZeroSemigroup[A] { self =>
  override def reverse: ZeroMonoid[A] =
    new ZeroMonoid[A] {
      override def empty: A = self.empty
      override def absorbing: A = self.absorbing
      override def combine(x: A, y: A): A = self.combine(y, x)
    }
}

trait ZeroMonoidFunctions[M[T] <: ZeroMonoid[T]]
    extends MonoidFunctions[M]
    with ZeroSemigroupFunctions[M]

object ZeroMonoid extends ZeroMonoidFunctions[ZeroMonoid] {

  /**
   * Access an implicit `ZeroMonoid[A]`.
   */
  @inline final def apply[A](implicit ev: ZeroMonoid[A]): ZeroMonoid[A] = ev

  /**
   * Create a `ZeroMonoid` instance from the given function and empty and absorbing values.
   */
  @inline def instance[A](emptyValue: A, absorbingValue: A, cmb: (A, A) => A): ZeroMonoid[A] =
    new ZeroMonoid[A] {
      override val empty: A = emptyValue
      override val absorbing: A = absorbingValue

      override def combine(x: A, y: A): A = cmb(x, y)
    }
}
