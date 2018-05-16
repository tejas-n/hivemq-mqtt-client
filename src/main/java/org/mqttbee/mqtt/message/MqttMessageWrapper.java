/*
 * Copyright 2018 The MQTT Bee project
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
 *
 */

package org.mqttbee.mqtt.message;

import org.mqttbee.annotations.NotNull;
import org.mqttbee.api.mqtt.mqtt5.message.Mqtt5MessageType;

/**
 * Base class for wrappers around MQTT messages with additional state-specific data.
 *
 * @param <M> the type of the wrapped MQTT message.
 * @author Silvio Giebl
 */
public abstract class MqttMessageWrapper<M extends MqttWrappedMessage> implements MqttMessage {

    private final M wrapped;

    protected MqttMessageWrapper(@NotNull final M wrapped) {
        this.wrapped = wrapped;
    }

    @NotNull
    @Override
    public Mqtt5MessageType getType() {
        return wrapped.getType();
    }

    /**
     * @return the wrapped MQTT message.
     */
    @NotNull
    public M getWrapped() {
        return wrapped;
    }


    /**
     * Base class for wrappers around MQTT messages with a packet identifier and additional state-specific data.
     *
     * @param <M> the type of the wrapped MQTT message.
     * @author Silvio Giebl
     */
    public abstract static class MqttMessageWrapperWithId<M extends MqttWrappedMessage> extends MqttMessageWrapper<M> {

        private final int packetIdentifier;

        protected MqttMessageWrapperWithId(@NotNull final M wrapped, final int packetIdentifier) {
            super(wrapped);
            this.packetIdentifier = packetIdentifier;
        }

        public int getPacketIdentifier() {
            return packetIdentifier;
        }

    }

}
