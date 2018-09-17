package org.grandma.schedule.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SmsMessage {

    private String to;

    private String text;
}
