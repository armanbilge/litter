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

import cats.kernel.Band

import scala.{specialized => sp}

/**
 * Zero bands are bands with an absorbing element that satisfies `combine(x, absorbing) ==
 * combine(absorbing, x) == absorbing`.
 */
trait ZeroBand[@sp(Int, Long, Float, Double) A] extends Any with Band[A] with ZeroSemigroup[A]

object ZeroBand extends ZeroSemigroupFunctions[ZeroBand] {

  /**
   * Access an implicit `ZeroBand[A]`.
   */
  @inline final def apply[@sp(Int, Long, Float, Double) A](
      implicit ev: ZeroBand[A]): ZeroBand[A] = ev

  /**
   * Create a `ZeroBand` instance from the given function and absorbing value.
   */
  @inline def instance[A](absorbingValue: A, cmb: (A, A) => A): ZeroBand[A] =
    new ZeroBand[A] {
      override val absorbing: A = absorbingValue
      override def combine(x: A, y: A): A = cmb(x, y)
    }
}
