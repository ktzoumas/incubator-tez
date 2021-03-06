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
package org.apache.tez.common;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceAudience.Private;
import org.apache.hadoop.classification.InterfaceStability.Evolving;
import org.apache.hadoop.classification.InterfaceStability.Unstable;

/**
 * Meant for user configurable job properties.
 *
 * Note for developers: Wheenver adding a new key to this file, it must also be added to the set of
 * known keys.
 */

// TODO EVENTUALLY A description for each property.
@InterfaceAudience.Public
@Evolving
public class TezJobConfig {

  private static final Set<String> keys = new HashSet<String>();
  private static final String TEZ_RUNTIME_PREFIX = "tez.runtime.";

  /**
   * Configuration key to enable/disable IFile readahead.
   */
  public static final String TEZ_RUNTIME_IFILE_READAHEAD = TEZ_RUNTIME_PREFIX +
      "ifile.readahead";
  public static final boolean TEZ_RUNTIME_IFILE_READAHEAD_DEFAULT = true;

  /**
   * Configuration key to set the IFile readahead length in bytes.
   */
  public static final String TEZ_RUNTIME_IFILE_READAHEAD_BYTES = TEZ_RUNTIME_PREFIX +
      "ifile.readahead.bytes";
  public static final int TEZ_RUNTIME_IFILE_READAHEAD_BYTES_DEFAULT =
      4 * 1024 * 1024;

  public static final int TEZ_RUNTIME_IFILE_BUFFER_SIZE_DEFAULT = -1;

  /**
   * This is copy of io.file.buffer.size from Hadoop, which is used in several places such
   * as compression codecs, buffer sizes in IFile, while fetching etc.
   * Variable exists so that it can be referenced, instead of using the string name directly.
   */
  public static final String TEZ_RUNTIME_IO_FILE_BUFFER_SIZE = "io.file.buffer.size";


  public static final String TEZ_RUNTIME_IO_SORT_FACTOR = TEZ_RUNTIME_PREFIX +
      "io.sort.factor";
  public static final int TEZ_RUNTIME_IO_SORT_FACTOR_DEFAULT = 100;


  public static final String TEZ_RUNTIME_SORT_SPILL_PERCENT = TEZ_RUNTIME_PREFIX +
      "sort.spill.percent";
  public static float TEZ_RUNTIME_SORT_SPILL_PERCENT_DEFAULT = 0.8f;


  public static final String TEZ_RUNTIME_IO_SORT_MB = TEZ_RUNTIME_PREFIX + "io.sort.mb";
  public static final int TEZ_RUNTIME_IO_SORT_MB_DEFAULT = 100;


  public static final String TEZ_RUNTIME_INDEX_CACHE_MEMORY_LIMIT_BYTES = TEZ_RUNTIME_PREFIX +
      "index.cache.memory.limit.bytes";
  public static final int TEZ_RUNTIME_INDEX_CACHE_MEMORY_LIMIT_BYTES_DEFAULT =
      1024 * 1024;


  // TODO Use the default value
  public static final String TEZ_RUNTIME_COMBINE_MIN_SPILLS = TEZ_RUNTIME_PREFIX +
      "combine.min.spills";
  public static final int TEZ_RUNTIME_COMBINE_MIN_SPILLS_DEFAULT = 3;
  

  public static final String TEZ_RUNTIME_SORT_THREADS = TEZ_RUNTIME_PREFIX +
	      "sort.threads";
  public static final int TEZ_RUNTIME_SORT_THREADS_DEFAULT = 1;

  /**
   * Size of the buffer to use if not writing directly to disk.
   */
  public static final String TEZ_RUNTIME_UNORDERED_OUTPUT_BUFFER_SIZE_MB = TEZ_RUNTIME_PREFIX +
      "unordered.output.buffer.size-mb";
  public static final int TEZ_RUNTIME_UNORDERED_OUTPUT_BUFFER_SIZE_MB_DEFAULT = 100;
  
  /**
   * Maximum size for individual buffers used in the UnsortedPartitionedOutput.
   * This is only meant to be used by unit tests for now.
   */
  @Private
  public static final String TEZ_RUNTIME_UNORDERED_OUTPUT_MAX_PER_BUFFER_SIZE_BYTES = TEZ_RUNTIME_PREFIX +
      "unordered.output.max-per-buffer.size-bytes";
  
  /**
   * Specifies a partitioner class, which is used in Tez Runtime components
   * like OnFileSortedOutput
   */
  public static final String TEZ_RUNTIME_PARTITIONER_CLASS = TEZ_RUNTIME_PREFIX + "partitioner.class";
  
  /**
   * Specifies a combiner class (primarily for Shuffle)
   */
  public static final String TEZ_RUNTIME_COMBINER_CLASS = TEZ_RUNTIME_PREFIX + "combiner.class";

  public static final String TEZ_RUNTIME_COUNTERS_MAX_KEY = TEZ_RUNTIME_PREFIX + "job.counters.max";
  public static final int TEZ_RUNTIME_COUNTERS_MAX_DEFAULT = 1200;


  public static final String TEZ_RUNTIME_COUNTER_GROUP_NAME_MAX_KEY = TEZ_RUNTIME_PREFIX + "job.counters.group.name.max";
  public static final int TEZ_RUNTIME_COUNTER_GROUP_NAME_MAX_DEFAULT = 128;


  public static final String TEZ_RUNTIME_COUNTER_NAME_MAX_KEY = TEZ_RUNTIME_PREFIX + "job.counters.counter.name.max";
  public static final int TEZ_RUNTIME_COUNTER_NAME_MAX_DEFAULT = 64;


  public static final String TEZ_RUNTIME_COUNTER_GROUPS_MAX_KEY = TEZ_RUNTIME_PREFIX + "job.counters.groups.max";
  public static final int TEZ_RUNTIME_COUNTER_GROUPS_MAX_DEFAULT = 500;


  public static final String TEZ_RUNTIME_SHUFFLE_PARALLEL_COPIES = TEZ_RUNTIME_PREFIX +
      "shuffle.parallel.copies";
  public static final int TEZ_RUNTIME_SHUFFLE_PARALLEL_COPIES_DEFAULT = 20;


  public static final String TEZ_RUNTIME_SHUFFLE_FETCH_FAILURES_LIMIT = TEZ_RUNTIME_PREFIX +
      "shuffle.fetch.failures.limit";
  public static final int TEZ_RUNTIME_SHUFFLE_FETCH_FAILURES_LIMIT_DEFAULT = 5;


  public static final String TEZ_RUNTIME_SHUFFLE_FETCH_MAX_TASK_OUTPUT_AT_ONCE = TEZ_RUNTIME_PREFIX +
    "shuffle.fetch.max.task.output.at.once";
  public final static int TEZ_RUNTIME_SHUFFLE_FETCH_MAX_TASK_OUTPUT_AT_ONCE_DEFAULT
          = 20;


  public static final String TEZ_RUNTIME_SHUFFLE_NOTIFY_READERROR = TEZ_RUNTIME_PREFIX +
      "shuffle.notify.readerror";
  public static final boolean TEZ_RUNTIME_SHUFFLE_NOTIFY_READERROR_DEFAULT = true;


  public static final String TEZ_RUNTIME_SHUFFLE_CONNECT_TIMEOUT = TEZ_RUNTIME_PREFIX +
      "shuffle.connect.timeout";
  public static final int TEZ_RUNTIME_SHUFFLE_STALLED_COPY_TIMEOUT_DEFAULT =
      3 * 60 * 1000;


  public static final String TEZ_RUNTIME_SHUFFLE_KEEP_ALIVE_ENABLED = TEZ_RUNTIME_PREFIX +
      "shuffle.keep-alive.enabled";
  public static final boolean TEZ_RUNTIME_SHUFFLE_KEEP_ALIVE_ENABLED_DEFAULT = false;


  public static final String TEZ_RUNTIME_SHUFFLE_KEEP_ALIVE_MAX_CONNECTIONS = TEZ_RUNTIME_PREFIX +
      "shuffle.keep-alive.max.connections";
  public static final int TEZ_RUNTIME_SHUFFLE_KEEP_ALIVE_MAX_CONNECTIONS_DEFAULT = 20;


  public static final String TEZ_RUNTIME_SHUFFLE_READ_TIMEOUT = TEZ_RUNTIME_PREFIX + "shuffle.read.timeout";
  public final static int TEZ_RUNTIME_SHUFFLE_READ_TIMEOUT_DEFAULT =
      3 * 60 * 1000;


  public static final String TEZ_RUNTIME_SHUFFLE_BUFFER_SIZE = TEZ_RUNTIME_PREFIX + "shuffle.buffersize";
  public final static int TEZ_RUNTIME_SHUFFLE_BUFFER_SIZE_DEFAULT =
      8 * 1024;


  public static final String TEZ_RUNTIME_SHUFFLE_ENABLE_SSL = TEZ_RUNTIME_PREFIX +
      "shuffle.ssl.enable";
  public static final boolean TEZ_RUNTIME_SHUFFLE_ENABLE_SSL_DEFAULT = false;


  public static final String TEZ_RUNTIME_SHUFFLE_INPUT_BUFFER_PERCENT = TEZ_RUNTIME_PREFIX +
      "shuffle.input.buffer.percent";
  public static final float TEZ_RUNTIME_SHUFFLE_INPUT_BUFFER_PERCENT_DEFAULT =
      0.90f;


  public static final String TEZ_RUNTIME_SHUFFLE_MEMORY_LIMIT_PERCENT = TEZ_RUNTIME_PREFIX +
      "shuffle.memory.limit.percent";
  public static final float TEZ_RUNTIME_SHUFFLE_MEMORY_LIMIT_PERCENT_DEFAULT =
      0.25f;

  // Rename to fraction
  public static final String TEZ_RUNTIME_SHUFFLE_MERGE_PERCENT = TEZ_RUNTIME_PREFIX +
      "shuffle.merge.percent";
  public static final float TEZ_RUNTIME_SHUFFLE_MERGE_PERCENT_DEFAULT = 0.90f;

  /**
   * TODO TEZAM3 default value ?
   */
  public static final String TEZ_RUNTIME_SHUFFLE_MEMTOMEM_SEGMENTS = TEZ_RUNTIME_PREFIX +
      "shuffle.memory-to-memory.segments";


  public static final String TEZ_RUNTIME_SHUFFLE_ENABLE_MEMTOMEM = TEZ_RUNTIME_PREFIX +
      "shuffle.memory-to-memory.enable";
  public static final boolean TEZ_RUNTIME_SHUFFLE_ENABLE_MEMTOMEM_DEFAULT =
      false;


  // TODO Maybe rename to reflect what this actually does
  public static final String TEZ_RUNTIME_INPUT_BUFFER_PERCENT = TEZ_RUNTIME_PREFIX +
      "task.input.buffer.percent";
  public static final float TEZ_RUNTIME_INPUT_BUFFER_PERCENT_DEFAULT = 0.0f;


  public static final String TEZ_RUNTIME_GROUP_COMPARATOR_CLASS = TEZ_RUNTIME_PREFIX +
      "group.comparator.class";

  public static final String TEZ_RUNTIME_INTERNAL_SORTER_CLASS = TEZ_RUNTIME_PREFIX +
      "internal.sorter.class";
  
  public static final String TEZ_RUNTIME_INTERMEDIATE_OUTPUT_KEY_COMPARATOR_CLASS = TEZ_RUNTIME_PREFIX +
      "intermediate-output.key.comparator.class";
  public static final String TEZ_RUNTIME_INTERMEDIATE_INPUT_KEY_COMPARATOR_CLASS = TEZ_RUNTIME_PREFIX +
      "intermediate-input.key.comparator.class";

  public static final String TEZ_RUNTIME_INTERMEDIATE_OUTPUT_KEY_CLASS = TEZ_RUNTIME_PREFIX +
      "intermediate-output.key.class";
  public static final String TEZ_RUNTIME_INTERMEDIATE_INPUT_KEY_CLASS = TEZ_RUNTIME_PREFIX +
      "intermediate-input.key.class";
  
  public static final String TEZ_RUNTIME_INTERMEDIATE_OUTPUT_VALUE_CLASS = TEZ_RUNTIME_PREFIX +
      "intermediate-output.value.class";
  public static final String TEZ_RUNTIME_INTERMEDIATE_INPUT_VALUE_CLASS = TEZ_RUNTIME_PREFIX +
      "intermediate-input.value.class";


  /** Whether intermediate output should be compressed or not */
  public static final String TEZ_RUNTIME_INTERMEDIATE_OUTPUT_SHOULD_COMPRESS = TEZ_RUNTIME_PREFIX +
      "intermediate-output.should-compress";
  /** Whether intermediate input is compressed */
  public static final String TEZ_RUNTIME_INTERMEDIATE_INPUT_IS_COMPRESSED = TEZ_RUNTIME_PREFIX +
      "intermediate-input.is-compressed";
  /**
   * The coded to be used if compressing intermediate output. Only applicable if
   * tez.runtime.intermediate-output.should-compress is enabled.
   */
  public static final String TEZ_RUNTIME_INTERMEDIATE_OUTPUT_COMPRESS_CODEC = TEZ_RUNTIME_PREFIX +
      "intermediate-output.compress.codec";
  /**
   * The coded to be used when reading intermediate compressed input. Only
   * applicable if tez.runtime.intermediate-input.is-compressed is enabled.
   */
  public static final String TEZ_RUNTIME_INTERMEDIATE_INPUT_COMPRESS_CODEC = TEZ_RUNTIME_PREFIX +
      "intermediate-input.compress.codec";


  public static final String TEZ_RUNTIME_INTERMEDIATE_INPUT_KEY_SECONDARY_COMPARATOR_CLASS = TEZ_RUNTIME_PREFIX +
      "intermediate-input.key.secondary.comparator.class";

  public static final String TEZ_RUNTIME_EMPTY_PARTITION_INFO_VIA_EVENTS_ENABLED = TEZ_RUNTIME_PREFIX +
      "empty.partitions.info-via-events.enabled";
  public static final boolean TEZ_RUNTIME_EMPTY_PARTITION_INFO_VIA_EVENTS_ENABLED_DEFAULT = true;

  public static final String TEZ_RUNTIME_BROADCAST_DATA_VIA_EVENTS_ENABLED = TEZ_RUNTIME_PREFIX + "broadcast.data-via-events.enabled";
  public static final boolean TEZ_RUNTIME_BROADCAST_DATA_VIA_EVENTS_ENABLED_DEFAULT = false;
  
  public static final String TEZ_RUNTIME_BROADCAST_DATA_VIA_EVENTS_MAX_SIZE = TEZ_RUNTIME_PREFIX + "broadcast.data-via-events.max-size";
  public static final int TEZ_RUNTIME_BROADCAST_DATA_VIA_EVENTS_MAX_SIZE_DEFAULT = 200 << 10;// 200KB
  
  /** Defines the ProcessTree implementation which will be used to collect resource utilization. */
  public static final String TEZ_RUNTIME_RESOURCE_CALCULATOR_PROCESS_TREE_CLASS = TEZ_RUNTIME_PREFIX + "resource.calculator.process-tree.class";

  /**
   * Whether to scale down memory requested by each component if the total
   * exceeds the available JVM memory
   */
  @Private @Unstable
  public static final String TEZ_RUNTIME_SCALE_TASK_MEMORY_ENABLED = TEZ_RUNTIME_PREFIX
      + "scale.task.memory.enabled";
  public static final boolean TEZ_RUNTIME_SCALE_TASK_MEMORY_ENABLED_DEFAULT = true;
  
  /**
   * The allocator to use for initial memory allocation
   */
  @Private @Unstable
  public static final String TEZ_RUNTIME_SCALE_TASK_MEMORY_ALLOCATOR_CLASS = TEZ_RUNTIME_PREFIX
      + "scale.task.memory.allocator.class";
  public static final String TEZ_RUNTIME_SCALE_TASK_MEMORY_ALLOCATOR_CLASS_DEFAULT = "org.apache.tez.runtime.common.resources.ScalingAllocator";
  
  /**
   * The fraction of the JVM memory which will not be considered for allocation.
   * No defaults, since there are pre-existing defaults based on different scenarios.
   */
  @Private @Unstable
  public static final String TEZ_RUNTIME_SCALE_TASK_MEMORY_RESERVE_FRACTION = TEZ_RUNTIME_PREFIX
      + "scale.task.memory.reserve-fraction";

  /**
   * Fraction of available memory to reserve per input/output. This amount is
   * removed from the total available pool before allocation and is for factoring in overheads.
   */
  @Private @Unstable
  public static final String TEZ_RUNTIME_SCALE_TASK_MEMORY_ADDITIONAL_RESERVATION_FRACTION_PER_IO = TEZ_RUNTIME_PREFIX
      + "scale.task.memory.additional.reservation.fraction.per-io";

  /**
   * Max cumulative total reservation for additional IOs.
   */
  public static final String TEZ_RUNTIME_SCALE_TASK_MEMORY_ADDITIONAL_RESERVATION_FRACTION_MAX = TEZ_RUNTIME_PREFIX
      + "scale.task.memory.additional reservation.fraction.max";
  /*
   * Weighted ratios for individual component types in the RuntimeLibrary.
   * e.g. PARTITIONED_UNSORTED_OUTPUT:0,UNSORTED_INPUT:1,SORTED_OUTPUT:2,SORTED_MERGED_INPUT:3,PROCESSOR:1,OTHER:1
   */
  @Private @Unstable
  public static final String TEZ_RUNTIME_SCALE_TASK_MEMORY_WEIGHTED_RATIOS = TEZ_RUNTIME_PREFIX
      + "scale.task.memory.ratios";

  /**
   * Path to a credentials file located on the local file system with serialized credentials.
   * Note: This property does not follow the standard convention of including tez.runtime in it's name.
   */
  public static final String TEZ_CREDENTIALS_PATH = "tez.credentials.path";


  @Unstable
  @Private
  public static final String TEZ_RUNTIME_RECORDS_BEFORE_PROGRESS = TEZ_RUNTIME_PREFIX +
      "merge.progress.records";
  public static final long TEZ_RUNTIME_RECORDS_BEFORE_PROGRESS_DEFAULT = 10000;

  static {
    keys.add(TEZ_RUNTIME_IFILE_READAHEAD);
    keys.add(TEZ_RUNTIME_IFILE_READAHEAD_BYTES);
    keys.add(TEZ_RUNTIME_IO_FILE_BUFFER_SIZE);
    keys.add(TEZ_RUNTIME_IO_SORT_FACTOR);
    keys.add(TEZ_RUNTIME_SORT_SPILL_PERCENT);
    keys.add(TEZ_RUNTIME_IO_SORT_MB);
    keys.add(TEZ_RUNTIME_INDEX_CACHE_MEMORY_LIMIT_BYTES);
    keys.add(TEZ_RUNTIME_COMBINE_MIN_SPILLS);
    keys.add(TEZ_RUNTIME_SORT_THREADS);
    keys.add(TEZ_RUNTIME_UNORDERED_OUTPUT_BUFFER_SIZE_MB);
    keys.add(TEZ_RUNTIME_UNORDERED_OUTPUT_MAX_PER_BUFFER_SIZE_BYTES);
    keys.add(TEZ_RUNTIME_PARTITIONER_CLASS);
    keys.add(TEZ_RUNTIME_COMBINER_CLASS);
    keys.add(TEZ_RUNTIME_COUNTERS_MAX_KEY);
    keys.add(TEZ_RUNTIME_COUNTER_GROUP_NAME_MAX_KEY);
    keys.add(TEZ_RUNTIME_COUNTER_NAME_MAX_KEY);
    keys.add(TEZ_RUNTIME_COUNTER_GROUPS_MAX_KEY);
    keys.add(TEZ_RUNTIME_SHUFFLE_PARALLEL_COPIES);
    keys.add(TEZ_RUNTIME_SHUFFLE_FETCH_FAILURES_LIMIT);
    keys.add(TEZ_RUNTIME_SHUFFLE_FETCH_MAX_TASK_OUTPUT_AT_ONCE);
    keys.add(TEZ_RUNTIME_SHUFFLE_NOTIFY_READERROR);
    keys.add(TEZ_RUNTIME_SHUFFLE_CONNECT_TIMEOUT);
    keys.add(TEZ_RUNTIME_SHUFFLE_KEEP_ALIVE_ENABLED);
    keys.add(TEZ_RUNTIME_SHUFFLE_KEEP_ALIVE_MAX_CONNECTIONS);
    keys.add(TEZ_RUNTIME_SHUFFLE_READ_TIMEOUT);
    keys.add(TEZ_RUNTIME_SHUFFLE_BUFFER_SIZE);
    keys.add(TEZ_RUNTIME_SHUFFLE_ENABLE_SSL);
    keys.add(TEZ_RUNTIME_SHUFFLE_INPUT_BUFFER_PERCENT);
    keys.add(TEZ_RUNTIME_SHUFFLE_MEMORY_LIMIT_PERCENT);
    keys.add(TEZ_RUNTIME_SHUFFLE_MERGE_PERCENT);
    keys.add(TEZ_RUNTIME_SHUFFLE_MEMTOMEM_SEGMENTS);
    keys.add(TEZ_RUNTIME_SHUFFLE_ENABLE_MEMTOMEM);
    keys.add(TEZ_RUNTIME_INPUT_BUFFER_PERCENT);
    keys.add(TEZ_RUNTIME_GROUP_COMPARATOR_CLASS);
    keys.add(TEZ_RUNTIME_INTERNAL_SORTER_CLASS);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_OUTPUT_KEY_COMPARATOR_CLASS);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_INPUT_KEY_COMPARATOR_CLASS);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_OUTPUT_KEY_CLASS);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_INPUT_KEY_CLASS);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_OUTPUT_VALUE_CLASS);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_INPUT_VALUE_CLASS);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_OUTPUT_SHOULD_COMPRESS);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_INPUT_IS_COMPRESSED);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_OUTPUT_COMPRESS_CODEC);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_INPUT_COMPRESS_CODEC);
    keys.add(TEZ_RUNTIME_INTERMEDIATE_INPUT_KEY_SECONDARY_COMPARATOR_CLASS);
    keys.add(TEZ_RUNTIME_EMPTY_PARTITION_INFO_VIA_EVENTS_ENABLED);
    keys.add(TEZ_RUNTIME_BROADCAST_DATA_VIA_EVENTS_ENABLED);
    keys.add(TEZ_RUNTIME_BROADCAST_DATA_VIA_EVENTS_MAX_SIZE);
    keys.add(TEZ_RUNTIME_RESOURCE_CALCULATOR_PROCESS_TREE_CLASS);
    keys.add(TEZ_RUNTIME_SCALE_TASK_MEMORY_ENABLED);
    keys.add(TEZ_RUNTIME_SCALE_TASK_MEMORY_ALLOCATOR_CLASS);
    keys.add(TEZ_RUNTIME_SCALE_TASK_MEMORY_RESERVE_FRACTION);
    keys.add(TEZ_RUNTIME_SCALE_TASK_MEMORY_ADDITIONAL_RESERVATION_FRACTION_PER_IO);
    keys.add(TEZ_RUNTIME_SCALE_TASK_MEMORY_ADDITIONAL_RESERVATION_FRACTION_MAX);
    keys.add(TEZ_RUNTIME_SCALE_TASK_MEMORY_WEIGHTED_RATIOS);
    keys.add(TEZ_CREDENTIALS_PATH);
    keys.add(TEZ_RUNTIME_RECORDS_BEFORE_PROGRESS);
  }

  public static Set<String> getKeySet() {
    return Collections.unmodifiableSet(keys);
  }
}
