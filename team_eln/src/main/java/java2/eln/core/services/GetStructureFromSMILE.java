package java2.eln.core.services;

import java2.eln.core.domain.StructureData;
import org.springframework.stereotype.Component;

@Component
public class GetStructureFromSMILE {

    public StructureData execute (String smile){
        return new StructureData(smile);
    }
}
