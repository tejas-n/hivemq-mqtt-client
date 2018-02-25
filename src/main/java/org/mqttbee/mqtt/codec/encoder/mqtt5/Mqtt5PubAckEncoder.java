package org.mqttbee.mqtt.codec.encoder.mqtt5;

import org.mqttbee.api.mqtt.mqtt5.message.Mqtt5MessageType;
import org.mqttbee.api.mqtt.mqtt5.message.publish.puback.Mqtt5PubAckReasonCode;
import org.mqttbee.mqtt.codec.encoder.mqtt5.Mqtt5MessageWithUserPropertiesEncoder.Mqtt5MessageWithIdAndOmissibleReasonCodeEncoder;
import org.mqttbee.mqtt.codec.encoder.provider.MqttMessageEncoderProvider;
import org.mqttbee.mqtt.codec.encoder.provider.MqttMessageEncoderProvider.ThreadLocalMqttMessageEncoderProvider;
import org.mqttbee.mqtt.message.publish.puback.MqttPubAckImpl;

import static org.mqttbee.mqtt.message.publish.puback.MqttPubAckImpl.DEFAULT_REASON_CODE;

/**
 * @author Silvio Giebl
 */
public class Mqtt5PubAckEncoder extends
        Mqtt5MessageWithIdAndOmissibleReasonCodeEncoder<MqttPubAckImpl, Mqtt5PubAckReasonCode, MqttMessageEncoderProvider<MqttPubAckImpl>> {

    public static final MqttMessageEncoderProvider<MqttPubAckImpl> PROVIDER =
            new ThreadLocalMqttMessageEncoderProvider<>(Mqtt5PubAckEncoder::new);

    private static final int FIXED_HEADER = Mqtt5MessageType.PUBACK.getCode() << 4;

    @Override
    protected int getFixedHeader() {
        return FIXED_HEADER;
    }

    @Override
    protected Mqtt5PubAckReasonCode getDefaultReasonCode() {
        return DEFAULT_REASON_CODE;
    }

}