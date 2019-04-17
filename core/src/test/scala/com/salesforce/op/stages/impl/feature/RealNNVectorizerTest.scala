/*
 * Copyright (c) 2017, Salesforce.com, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its
 *   contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package com.salesforce.op.stages.impl.feature

import com.salesforce.op.features.types.{ArrayDoubleConversions, OPVector, RealNN}
import com.salesforce.op.test.{OpTransformerSpec, TestFeatureBuilder}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class RealNNVectorizerTest extends OpTransformerSpec[OPVector, RealNNVectorizer] {
  val sample = Seq(RealNN(-1.0), RealNN(-4.0), RealNN(5.0), RealNN(-5.5), RealNN(0.1), RealNN(2.0), RealNN(0.0))
  val (inputData, f1) = TestFeatureBuilder(sample)
  val transformer: RealNNVectorizer = new RealNNVectorizer().setInput(f1)

  val expectedResult: Seq[OPVector] = Array(-1.0, -4.0, 5.0, -5.5, 0.1, 2.0, 0.0).map(Array(_).toOPVector)

  it should "be applied via shortcut" in {
    val f2 = f1.vectorize()
    f2.originStage.isInstanceOf[RealNNVectorizer] shouldBe true
  }
}
