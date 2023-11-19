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

package com.hivemq.mqtt.client2.internal.handler.publish.incoming;

import com.hivemq.mqtt.client2.ext.rx.FlowableWithSingleSubscriber;
import com.hivemq.mqtt.client2.internal.MqttClientConfig;
import com.hivemq.mqtt.client2.internal.collections.HandleList;
import com.hivemq.mqtt.client2.internal.datatypes.MqttTopicFilterImpl;
import com.hivemq.mqtt.client2.internal.handler.subscribe.MqttSubscriptionFlow;
import com.hivemq.mqtt.client2.internal.message.subscribe.MqttSubAck;
import com.hivemq.mqtt.client2.mqtt5.message.publish.Mqtt5Publish;
import com.hivemq.mqtt.client2.mqtt5.message.subscribe.Mqtt5SubAck;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Subscriber;

/**
 * @author Silvio Giebl
 */
public class MqttSubscribedPublishFlow extends MqttIncomingPublishFlow implements MqttSubscriptionFlow<MqttSubAck> {

    private final @NotNull HandleList<MqttTopicFilterImpl> topicFilters;

    MqttSubscribedPublishFlow(
            final @NotNull Subscriber<? super Mqtt5Publish> subscriber,
            final @NotNull MqttClientConfig clientConfig,
            final @NotNull MqttIncomingQosHandler incomingQosHandler,
            final boolean manualAcknowledgement) {

        super(subscriber, clientConfig, incomingQosHandler, manualAcknowledgement);
        topicFilters = new HandleList<>();
    }

    @Override
    public void onSuccess(final @NotNull MqttSubAck subAck) {
        if (subscriber instanceof FlowableWithSingleSubscriber) {
            ((FlowableWithSingleSubscriber<? super Mqtt5Publish, ? super Mqtt5SubAck>) subscriber).onSingle(subAck);
        }
    }

    @Override
    void runCancel() {
        incomingPublishService.incomingPublishFlows.cancel(this);
        super.runCancel();
    }

    @NotNull HandleList<MqttTopicFilterImpl> getTopicFilters() {
        return topicFilters;
    }
}
