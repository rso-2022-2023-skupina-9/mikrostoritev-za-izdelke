package si.fri.rso.skupina09.converters;

import si.fri.rso.skupina09.entities.TrgovinaEntity;
import si.fri.rso.skupina09.lib.Trgovina;

public class TrgovinaConverter {

    public static Trgovina toDto(TrgovinaEntity trgovinaEntity) {
        Trgovina dto = new Trgovina();
        dto.setTrgovina_id(trgovinaEntity.getTrgovinaId());
        dto.setIme(trgovinaEntity.getIme());
        dto.setUstanovitev(trgovinaEntity.getUstanovitev());
        dto.setSedez(trgovinaEntity.getSedez());
        return dto;
    }

    public static TrgovinaEntity toEntity(Trgovina trgovina) {
        TrgovinaEntity entity = new TrgovinaEntity();
        entity.setTrgovinaId(trgovina.getTrgovina_id());
        entity.setIme(trgovina.getIme());
        entity.setUstanovitev(trgovina.getUstanovitev());
        entity.setSedez(trgovina.getSedez());
        return entity;
    }
}
