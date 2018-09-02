package org.grandma.schedule.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CellphoneMessage {

    private String number;

    private Boolean whatsapp;
}
