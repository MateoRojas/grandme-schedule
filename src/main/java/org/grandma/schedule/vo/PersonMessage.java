package org.grandma.schedule.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class PersonMessage {

    private String firstName;

    private Collection<CellphoneMessage> cellphones;

    public Collection<String> cellphoneNumbers() {

        return cellphones
            .stream()
            .map(cellphoneMessage -> cellphoneMessage.getNumber())
            .collect(Collectors.toList());
    }
}
