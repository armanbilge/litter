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

import cats.kernel.{Group, GroupFunctions}

import scala.{specialized => sp}

/**
 * A group with an absorbing element.
 */
trait ZeroGroup[@sp(Int, Long, Float, Double) A] extends Any with Group[A] with ZeroMonoid[A]

// TODO Drop overrides in next binary-breaking release
trait ZeroGroupFunctions[G[T] <: ZeroGroup[T]]
    extends GroupFunctions[G]
    with ZeroMonoidFunctions[G] {
  override def inverse[@sp(Int, Long, Float, Double) A](a: A)(implicit ev: G[A]): A =
    ev.inverse(a)

  override def remove[@sp(Int, Long, Float, Double) A](x: A, y: A)(implicit ev: G[A]): A =
    ev.remove(x, y)
}

object ZeroGroup extends ZeroGroupFunctions[ZeroGroup] {

  /**
   * Access an implicit `ZeroGroup[A]`.
   */
  @inline final def apply[A](implicit ev: ZeroGroup[A]): ZeroGroup[A] = ev
}
