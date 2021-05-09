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

import cats.kernel.Eq
import cats.kernel.laws.discipline.SerializableTests
import litter.instances.all._
import litter.laws.discipline.{
  BoundedZeroSemilatticeTests,
  CommutativeZeroGroupTests,
  ZeroMonoidTests,
  ZeroSemilatticeTests
}
import org.scalacheck.Arbitrary
import org.specs2.ScalaCheck
import org.specs2.mutable.Specification
import org.typelevel.discipline.specs2.mutable.Discipline

import scala.collection.immutable.{BitSet, Set, SortedSet}

class LawTests extends Specification with Discipline with ScalaCheck {

  import Arbitrary.arbitrary

  implicit val arbitraryBitSet: Arbitrary[BitSet] =
    Arbitrary(arbitrary[List[Short]].map(ns => BitSet(ns.map(_ & 0xffff): _*)))

  implicit def eqFunction1Byte[A: Eq]: Eq[Byte => A] =
    (Byte.MinValue to Byte.MaxValue)
      .map(i => Eq.by[Byte => A, A](f => f(i.toByte)))
      .reduce(Eq.and[Byte => A])

  checkAll("ZeroMonoid[BigInt]", ZeroMonoidTests[BigInt].zeroMonoid)
  checkAll("ZeroMonoid[BigInt]", SerializableTests.serializable(ZeroMonoid[BigInt]))

  checkAll("ZeroSemilattice[BitSet]", ZeroSemilatticeTests[BitSet].zeroSemilattice)
  checkAll("ZeroSemilattice[BitSet]", SerializableTests.serializable(ZeroSemilattice[BitSet]))

  checkAll("ZeroMonoid[Byte]", ZeroMonoidTests[Byte].zeroMonoid)
  checkAll("ZeroMonoid[Byte]", SerializableTests.serializable(ZeroMonoid[Byte]))

  checkAll("ZeroMonoid[() => Int]", ZeroMonoidTests[() => Int].zeroMonoid)
  checkAll("ZeroMonoid[() => Int]", SerializableTests.serializable(ZeroMonoid[() => Int]))

  checkAll("ZeroMonoid[Byte => Int]", ZeroMonoidTests[Byte => Int].zeroMonoid)
  checkAll("ZeroMonoid[Byte => Int]", SerializableTests.serializable(ZeroMonoid[Int => Int]))

  checkAll("ZeroMonoid[Int]", ZeroMonoidTests[Int].zeroMonoid)
  checkAll("ZeroMonoid[Int]", SerializableTests.serializable(ZeroMonoid[Int]))

  checkAll("ZeroMonoid[Long]", ZeroMonoidTests[Long].zeroMonoid)
  checkAll("ZeroMonoid[Long]", SerializableTests.serializable(ZeroMonoid[Long]))

  checkAll("ZeroSemilattice[Set]", ZeroSemilatticeTests[Set[String]].zeroSemilattice)
  checkAll("ZeroSemilattice[Set]", SerializableTests.serializable(ZeroSemilattice[Set[String]]))

  checkAll(
    "ZeroSemilattice[SortedSet]",
    ZeroSemilatticeTests[SortedSet[String]].zeroSemilattice)
  checkAll(
    "ZeroSemilattice[SortedSet]",
    SerializableTests.serializable(ZeroSemilattice[SortedSet[String]]))

  checkAll("ZeroMonoid[Short]", ZeroMonoidTests[Short].zeroMonoid)
  checkAll("ZeroMonoid[Short]", SerializableTests.serializable(ZeroMonoid[Short]))

  checkAll(
    "BoundedZeroSemilattice[Unit]",
    BoundedZeroSemilatticeTests[Unit].boundedZeroSemilattice)
  checkAll(
    "BoundedZeroSemilattice[Unit]",
    SerializableTests.serializable(BoundedZeroSemilattice[Unit]))
  checkAll(
    "CommutativeZeroGroup[Unit]",
    CommutativeZeroGroupTests[Unit].commutativeZeroSemigroup)
  checkAll(
    "CommutativeZeroGroup[Unit]",
    SerializableTests.serializable(CommutativeZeroGroup[Unit]))
}
