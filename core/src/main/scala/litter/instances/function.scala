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

import cats.kernel.instances.{
  Function0Group,
  Function0Monoid,
  Function0Semigroup,
  Function1Group,
  Function1Monoid,
  Function1Semigroup
}
import litter.{ZeroGroup, ZeroMonoid, ZeroSemigroup}

object function extends FunctionInstances

trait FunctionInstances extends FunctionInstances0 {
  implicit def litterInstancesZeroSemigroupForFunction0[A](
      implicit ev: ZeroSemigroup[A]): ZeroSemigroup[() => A] =
    new Function0ZeroSemigroup[A] { override val A = ev }
  implicit def litterInstancesZeroSemigroupForFunction1[A, B](
      implicit ev: ZeroSemigroup[B]): ZeroSemigroup[A => B] =
    new Function1ZeroSemigroup[A, B] { override val B = ev }
}

trait FunctionInstances0 extends FunctionInstances1 {
  implicit def litterInstancesZeroMonoidForFunction0[A](
      implicit ev: ZeroMonoid[A]): ZeroMonoid[() => A] =
    new Function0ZeroMonoid[A] { override val A = ev }
  implicit def litterInstancesZeroMonoidForFunction1[A, B](
      implicit ev: ZeroMonoid[B]): ZeroMonoid[A => B] =
    new Function1ZeroMonoid[A, B] { override val B = ev }
}

trait FunctionInstances1 {
  implicit def litterInstancesZeroGroupForFunction0[A](
      implicit ev: ZeroGroup[A]): ZeroGroup[() => A] =
    new Function0ZeroGroup[A] { override val A = ev }
  implicit def litterInstancesZeroGroupForFunction1[A, B](
      implicit ev: ZeroGroup[B]): ZeroGroup[A => B] =
    new Function1ZeroGroup[A, B] { override val B = ev }
}

trait Function0ZeroSemigroup[A] extends Function0Semigroup[A] with ZeroSemigroup[() => A] {
  override implicit def A: ZeroSemigroup[A]

  override val absorbing: () => A = () => A.absorbing
}

trait Function0ZeroMonoid[A]
    extends Function0Monoid[A]
    with Function0ZeroSemigroup[A]
    with ZeroMonoid[() => A] {
  override implicit def A: ZeroMonoid[A]
}

trait Function0ZeroGroup[A]
    extends Function0Group[A]
    with Function0ZeroMonoid[A]
    with ZeroGroup[() => A] {
  override implicit def A: ZeroGroup[A]
}

trait Function1ZeroSemigroup[A, B] extends Function1Semigroup[A, B] with ZeroSemigroup[A => B] {
  override implicit def B: ZeroSemigroup[B]

  override val absorbing: A => B = _ => B.absorbing
}

trait Function1ZeroMonoid[A, B]
    extends Function1Monoid[A, B]
    with Function1ZeroSemigroup[A, B]
    with ZeroMonoid[A => B] {
  override implicit def B: ZeroMonoid[B]
}

trait Function1ZeroGroup[A, B]
    extends Function1Group[A, B]
    with Function1ZeroMonoid[A, B]
    with ZeroGroup[A => B] {
  override implicit def B: ZeroGroup[B]
}
