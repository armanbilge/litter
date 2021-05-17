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

package litter.instances

import litter.CommutativeZeroMonoid

object int extends IntInstances

trait IntInstances {
  implicit val litterInstancesZeroMonoidForInt: CommutativeZeroMonoid[Int] =
    new IntZeroMonoid
}

class IntZeroMonoid extends CommutativeZeroMonoid[Int] {
  override val empty: Int = 1
  override val absorbing: Int = 0
  override def combine(x: Int, y: Int): Int = x * y
}
