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

import cats.kernel.{Eq, Semigroup, SemigroupFunctions}

import scala.{specialized => sp}

/**
 * A zero semigroup is a semigroup with an absorbing element. A zero semigroup is a specialization of a
 * semigroup, so its operation must be associative. Additionally,
 * `combine(x, absorbing) == combine(absorbing, x) == absorbing`. For example, if we have `ZeroSemigroup[Int]`,
 * with `combine` as integer multiplication, then `absorbing = 0`.
 */
trait ZeroSemigroup[@sp(Int, Long, Float, Double) A] extends Any with Semigroup[A] { self =>

  /**
   * Return the absorbing element for this zero semigroup.
   */
  def absorbing: A

  /**
   * Tests if `a` is the absorbing element.
   */
  def isAbsorbing(a: A)(implicit ev: Eq[A]): Boolean =
    ev.eqv(a, absorbing)

  override def reverse: ZeroSemigroup[A] =
    new ZeroSemigroup[A] {
      override def absorbing: A = self.absorbing
      override def combine(x: A, y: A): A = self.combine(y, x)
      override def reverse: ZeroSemigroup[A] = self
    }
}

trait ZeroSemigroupFunctions[S[T] <: ZeroSemigroup[T]] extends SemigroupFunctions[S] {
  def absorbing[@sp(Int, Long, Float, Double) A](implicit ev: S[A]): A =
    ev.absorbing

  def isAbsorbing[@sp(Int, Long, Float, Double) A](a: A)(implicit s: S[A], ev: Eq[A]): Boolean =
    s.isAbsorbing(a)
}

object ZeroSemigroup extends ZeroSemigroupFunctions[ZeroSemigroup] {

  /**
   * Access an implicit `ZeroSemigroup[A]`.
   */
  @inline final def apply[A](implicit ev: ZeroSemigroup[A]): ZeroSemigroup[A] = ev

  /**
   * Create a `ZeroSemigroup` instance from the given absorbing value and function.
   */
  @inline def instance[A](absorbingValue: A, cmb: (A, A) => A): ZeroSemigroup[A] =
    new ZeroSemigroup[A] {
      override val absorbing: A = absorbingValue
      override def combine(x: A, y: A): A = cmb(x, y)
    }
}
