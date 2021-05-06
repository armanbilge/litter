# litter

[![Latest version](https://index.scala-lang.org/armanbilge/litter/litter/latest.svg?color=orange)](https://index.scala-lang.org/armanbilge/litter/litter)

A microlibrary to incubate `ZeroSemigroup`, `ZeroMonoid`, and `ZeroGroup` type classes for [Cats](https://github.com/typelevel/cats).
They designate an [absorbing element](https://en.wikipedia.org/wiki/Absorbing_element) _a_ such that
```combine(x, a) == combine(a, x) == a```
for all _x_.
