/*
 * Copyright 2021 John A. De Goes and the ZIO Contributors
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

package zio.flow.remote

import zio.flow.zFlow.ZFlow
import zio.flow.{ ActivityError, ExecutingFlow, FlowId }

class RemoteExecutingFlowSyntax[A](self: Remote[A]) {

  def flowId[E, A2](implicit ev: A <:< ExecutingFlow[E, A2]): Remote[FlowId] = ???

  def await[E, A2](implicit ev: A <:< ExecutingFlow[E, A2]): ZFlow[Any, ActivityError, Either[E, A2]] =
    ZFlow.Await(self.widen[ExecutingFlow[E, A2]])

  def interrupt[E, A2](implicit ev: A <:< ExecutingFlow[E, A2]): ZFlow[Any, ActivityError, Any] =
    ZFlow.Interrupt(self.widen[ExecutingFlow[E, A2]])

}
