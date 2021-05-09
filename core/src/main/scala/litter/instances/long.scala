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

object long extends LongInstances

trait LongInstances {
  implicit val litterInstancesZeroMonoidForLong: CommutativeZeroMonoid[Long] =
    new LongZeroMonoid
}

class LongZeroMonoid extends CommutativeZeroMonoid[Long] {
  override val empty: Long = 1
  override val absorbing: Long = 0
  override def combine(x: Long, y: Long): Long = x * y
}
