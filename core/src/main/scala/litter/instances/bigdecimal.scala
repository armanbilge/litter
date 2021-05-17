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

object bigdecimal extends BigDecimalInstances

trait BigDecimalInstances {
  implicit val litterInstancesZeroGroupForBigDecimal: CommutativeZeroGroup[BigDecimal] =
    new BigDecimalZeroGroup
}

class BigDecimalZeroGroup extends CommutativeZeroGroup[BigDecimal] {
  val absorbing: BigDecimal = BigDecimal(0)
  val empty: BigDecimal = BigDecimal(1)
  def combine(x: BigDecimal, y: BigDecimal): BigDecimal =
    new BigDecimal(x.bigDecimal.multiply(y.bigDecimal), x.mc)
  def inverse(x: BigDecimal): BigDecimal =
    new BigDecimal(empty.bigDecimal.divide(x.bigDecimal), x.mc)
  override def remove(x: BigDecimal, y: BigDecimal): BigDecimal =
    new BigDecimal(x.bigDecimal.divide(y.bigDecimal), x.mc)
}
