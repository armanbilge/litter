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

object bigint extends BigIntInstances

trait BigIntInstances {
  implicit val litterInstancesZeroMonoidForBigInt: CommutativeZeroMonoid[BigInt] =
    new BigIntZeroMonoid
}

class BigIntZeroMonoid extends CommutativeZeroMonoid[BigInt] {
  override val absorbing: BigInt = BigInt(0)
  override val empty: BigInt = BigInt(1)
  override def combine(x: BigInt, y: BigInt): BigInt = x * y
}
