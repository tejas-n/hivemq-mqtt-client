package org.mqttbee.mqtt.message.ping;

import jdk.nashorn.internal.ir.annotations.Immutable;
import org.mqttbee.annotations.NotNull;
import org.mqttbee.api.mqtt.mqtt5.message.ping.Mqtt5PingReq;
import org.mqttbee.mqtt.codec.encoder.MqttMessageEncoder;
import org.mqttbee.mqtt.codec.encoder.MqttPingReqEncoder;
import org.mqttbee.mqtt.message.MqttMessage;

/**
 * @author Silvio Giebl
 */
@Immutable
public class MqttPingReqImpl implements MqttMessage, Mqtt5PingReq {

    public static final MqttPingReqImpl INSTANCE = new MqttPingReqImpl();

    private MqttPingReqImpl() {
    }

    @NotNull
    @Override
    public MqttMessageEncoder getEncoder() {
        return MqttPingReqEncoder.INSTANCE;
    }

}