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

import litter.CommutativeZeroGroup

object double extends DoubleInstances

trait DoubleInstances {
  implicit val litterInstancesZeroGroupForDouble: CommutativeZeroGroup[Double] =
    new DoubleZeroGroup
}

class DoubleZeroGroup extends CommutativeZeroGroup[Double] {
  override val empty: Double = 1
  override val absorbing: Double = 0
  override def combine(x: Double, y: Double): Double = x * y
  override def inverse(a: Double): Double = 1 / a
  override def remove(a: Double, b: Double): Double = a / b
}
