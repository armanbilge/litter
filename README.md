# litter

[![Latest version](https://index.scala-lang.org/armanbilge/litter/litter/latest.svg?color=orange)](https://index.scala-lang.org/armanbilge/litter/litter)

A microlibrary to incubate a `ZeroSemigroup` type class and friends for [Cats](https://github.com/typelevel/cats).
They designate an [absorbing element](https://en.wikipedia.org/wiki/Absorbing_element) _a_ such that
```combine(x, a) == combine(a, x) == a```
for all _x_.

The complete list of type classes introduced is:
* `ZeroSemigroup` and `CommutativeZeroSemigroup`
* `ZeroMonoid` and `CommutativeZeroMonoid`
* `ZeroGroup` and `CommutativeZeroGroup`
* `ZeroBand`
* `ZeroSemilattice`
* `BoundedZeroSemilattice`
