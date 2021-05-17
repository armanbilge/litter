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

import cats.kernel.{CommutativeMonoid, CommutativeSemigroup, Monoid, Semigroup}
import litter.{CommutativeZeroMonoid, CommutativeZeroSemigroup, ZeroMonoid, ZeroSemigroup}

object option extends OptionInstances

trait OptionInstances extends OptionInstances0 {
  implicit def litterInstancesCommutativeZeroMonoidForOption[A: CommutativeMonoid]
      : CommutativeZeroMonoid[Option[A]] =
    new OptionCommutativeZeroMonoid[A]
}

trait OptionInstances0 extends OptionInstances1 {
  implicit def litterInstancesZeroMonoidForOption[A: Monoid]: ZeroMonoid[Option[A]] =
    new OptionZeroMonoid[A]
}

trait OptionInstances1 extends OptionInstances2 {
  implicit def litterInstancesCommutativeZeroSemigroupForOption[A: CommutativeSemigroup]
      : CommutativeZeroSemigroup[Option[A]] =
    new OptionCommutativeZeroSemigroup[A]
}

trait OptionInstances2 {
  implicit def litterInstancesZeroSemigroupForOption[A: Semigroup]: ZeroSemigroup[Option[A]] =
    new OptionZeroSemigroup[A]
}

class OptionZeroSemigroup[A](implicit A: Semigroup[A]) extends ZeroSemigroup[Option[A]] {
  override def absorbing: Option[A] = None
  override def combine(x: Option[A], y: Option[A]): Option[A] =
    for { x <- x; y <- y } yield A.combine(x, y)
}

class OptionCommutativeZeroSemigroup[A](implicit A: CommutativeSemigroup[A])
    extends OptionZeroSemigroup[A]
    with CommutativeZeroSemigroup[Option[A]]

class OptionZeroMonoid[A](implicit A: Monoid[A])
    extends OptionZeroSemigroup[A]
    with ZeroMonoid[Option[A]] {
  override def empty: Option[A] = Some(A.empty)
}

class OptionCommutativeZeroMonoid[A](implicit A: CommutativeMonoid[A])
    extends OptionZeroMonoid[A]
    with CommutativeZeroMonoid[Option[A]]
