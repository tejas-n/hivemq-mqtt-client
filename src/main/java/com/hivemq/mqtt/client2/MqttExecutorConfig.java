/*
 * Copyright 2018-present HiveMQ and the HiveMQ Community
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

package com.hivemq.mqtt.client2;

import com.hivemq.mqtt.client2.internal.MqttExecutorConfigImplBuilder;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.concurrent.Executor;

/**
 * Configuration for the executors and threads to use by {@link MqttClient MQTT clients}.
 *
 * @author Silvio Giebl
 * @since 1.0
 */
@ApiStatus.NonExtendable
public interface MqttExecutorConfig {

    @NotNull Scheduler DEFAULT_APPLICATION_SCHEDULER = Schedulers.computation();

    /**
     * Creates a builder for an executor configuration.
     *
     * @return the created builder for an executor configuration.
     */
    static @NotNull MqttExecutorConfigBuilder builder() {
        return new MqttExecutorConfigImplBuilder.Default();
    }

    /**
     * @return the optional user defined executor for Netty (network communication framework).
     */
    @NotNull Optional<Executor> getNettyExecutor();

    /**
     * @return the optional user defined amount of threads Netty (network communication framework) will use.
     */
    @Range(from = 1, to = Integer.MAX_VALUE) @NotNull OptionalInt getNettyThreads();

    /**
     * @return the {@link Scheduler} used for executing application specific code, such as callbacks.
     */
    @NotNull Scheduler getApplicationScheduler();

    /**
     * Creates a builder for extending this executor configuration.
     *
     * @return the created builder.
     * @since 1.1
     */
    @NotNull MqttExecutorConfigBuilder extend();
}