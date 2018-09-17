package org.grandma.schedule.model;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@QueryEntity
public class CellphoneMessage {

    private String number;

    private Boolean whatsapp;
}
