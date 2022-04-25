package client;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProducerClient {
    public void publish() {
        long ufoId = Math.round(Math.random() * Integer.MAX_VALUE);

        Properties props = new Properties();

        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "hostname1:port1,hostname2:port2,hostname3:port3");
        props.setProperty(ProducerConfig.ACKS_CONFIG, "1");
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            String key = Long.toString(ufoId++);
            String msg = "Increase Counter";
            ProducerRecord<String, String> data = new ProducerRecord<String, String>("ufo_sightings", key, msg);
            producer.send(data);
            long wait = Math.round(Math.random() * 25);
            Thread.sleep(wait);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
