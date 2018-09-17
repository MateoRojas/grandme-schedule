package org.grandma.schedule.model;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@QueryEntity
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
