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

object float extends FloatInstances

trait FloatInstances {
  implicit val litterInstancesZeroGroupForFloat: CommutativeZeroGroup[Float] =
    new FloatZeroGroup
}

class FloatZeroGroup extends CommutativeZeroGroup[Float] {
  override val empty: Float = 1
  override val absorbing: Float = 0
  override def combine(x: Float, y: Float): Float = x * y
  override def inverse(a: Float): Float = 1 / a
  override def remove(a: Float, b: Float): Float = a / b
}
