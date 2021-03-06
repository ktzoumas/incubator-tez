/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tez.runtime.library.common.combine;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience.LimitedPrivate;
import org.apache.hadoop.classification.InterfaceStability.Unstable;
import org.apache.tez.common.TezJobConfig;
import org.apache.tez.runtime.library.common.sort.impl.TezRawKeyValueIterator;
import org.apache.tez.runtime.library.common.sort.impl.IFile.Writer;

/**
 *<b>Combiner Initialization</b></p> The Combiner class is picked up
 * using the TEZ_RUNTIME_COMBINER_CLASS attribute in {@link TezJobConfig}
 * 
 * 
 * Partitioners need to provide a single argument ({@link TezRawKeyValueIterator})
 * constructor.
 */
@Unstable
@LimitedPrivate("mapreduce")
public interface Combiner {
  public void combine(TezRawKeyValueIterator rawIter, Writer writer)
      throws InterruptedException, IOException;
}
