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

import cats.kernel.CommutativeGroup

import scala.{specialized => sp}

/**
 * A commutative zero group is a commutative group with an absorbing element that satisfies
 * `combine(x, absorbing) == combine(absorbing, x) == absorbing`.
 */
trait CommutativeZeroGroup[@sp(Int, Long, Float, Double) A]
    extends Any
    with ZeroGroup[A]
    with CommutativeGroup[A]
    with CommutativeZeroMonoid[A]

object CommutativeZeroGroup extends ZeroGroupFunctions[CommutativeZeroGroup] {

  /**
   * Access an implicit `CommutativeZeroGroup[A]`.
   */
  @inline final def apply[A](implicit ev: CommutativeZeroGroup[A]): CommutativeZeroGroup[A] = ev
}
