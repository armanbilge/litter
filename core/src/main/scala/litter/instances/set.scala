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

import litter.ZeroSemilattice

import scala.collection.immutable.Set

object set extends SetInstances

trait SetInstances {
  implicit def litterInstancesZeroSemilatticeForSet[A]: ZeroSemilattice[Set[A]] =
    new SetZeroSemilattice[A]
}

class SetZeroSemilattice[A] extends ZeroSemilattice[Set[A]] {
  override def absorbing: Set[A] = Set.empty
  override def combine(x: Set[A], y: Set[A]): Set[A] = x & y
}
