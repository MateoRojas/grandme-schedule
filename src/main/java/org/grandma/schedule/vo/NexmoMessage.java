package org.grandma.schedule.vo;

import lombok.AllArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Sms Message in Nexmo API Format.
 *
 * @author mateo
 * @since 1.0.0
 */
@AllArgsConstructor
public class NexmoMessage {

    private static final String FROM = "from";

    private static final String TO = "to";

    private static final String TEXT = "text";

    private static final String API_KEY = "api_key";

    private static final String API_SECRET = "api_secret";

    private static final String FROM_NAME = "Aplicacion Horarios";

    private String key;

    private String secret;

    private SmsMessage message;

    /**
     * Maps the nexmo message to a multivaluemap
     * format.
     *
     * @return a {@link MultiValueMap} containing all
     *      the nexmo message information.
     */
    public MultiValueMap<String,String> valueMap() {

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        map.add(FROM, NexmoMessage.FROM_NAME);
        map.add(TEXT, this.message.getText());
        map.add(TO, this.message.getTo());
        map.add(API_KEY, this.key);
        map.add(API_SECRET, this.secret);

        return map;
    }
}
