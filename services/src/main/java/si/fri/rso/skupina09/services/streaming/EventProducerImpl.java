package si.fri.rso.skupina09.services.streaming;

import com.kumuluz.ee.streaming.common.annotations.StreamProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
public class EventProducerImpl {

    private static final Logger log = Logger.getLogger(EventProducerImpl.class.getName());

    private static final String TOPIC_NAME = "utvuvjbr-default";

    @Inject
    @StreamProducer
    private Producer producer;

    public Response produceMessage(String imeIzdelka, Integer cenaIzdelka) {

        JSONObject obj = new JSONObject();
        obj.put("imeIzdelka", imeIzdelka);
        obj.put("cenaIzdelka", cenaIzdelka);
        obj.put("status", "new");

        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, imeIzdelka, obj.toString());

        producer.send(record,
                (metadata, e) -> {
                    if (e != null) {
                        e.printStackTrace();
                    } else {
                        log.info("The offset of the produced message record is: " + metadata.offset());
                    }
                });

        return Response.ok().build();

    }
}