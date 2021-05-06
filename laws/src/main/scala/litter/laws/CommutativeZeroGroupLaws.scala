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

package litterbox.laws

import cats.kernel.laws.CommutativeGroupLaws
import litterbox.CommutativeZeroGroup

trait CommutativeZeroGroupLaws[A]
    extends CommutativeGroupLaws[A]
    with ZeroGroupLaws[A]
    with CommutativeZeroMonoidLaws[A] {
  implicit override def S: CommutativeZeroGroup[A]
}

object CommutativeZeroGroupLaws {
  def apply[A](implicit ev: CommutativeZeroGroup[A]): CommutativeZeroGroupLaws[A] =
    new CommutativeZeroGroupLaws[A] { def S: CommutativeZeroGroup[A] = ev }
}
