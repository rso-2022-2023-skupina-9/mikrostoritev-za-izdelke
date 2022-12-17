package si.fri.rso.skupina09.converters;

import si.fri.rso.skupina09.entities.IzdelekEntity;
import si.fri.rso.skupina09.entities.TrgovinaEntity;
import si.fri.rso.skupina09.lib.Trgovina;

import java.util.stream.Collectors;

public class TrgovinaConverter {

    public static Trgovina toDto(TrgovinaEntity trgovinaEntity) {
        Trgovina dto = new Trgovina();
        dto.setTrgovina_id(trgovinaEntity.getTrgovina_id());
        dto.setIme(trgovinaEntity.getIme());
        dto.setUstanovitev(trgovinaEntity.getUstanovitev());
        dto.setSedez(trgovinaEntity.getSedez());
        dto.setIzdelki(trgovinaEntity.getIzdelki().stream().map(IzdelekConverter::toDtoWithoutTrgovina).collect(Collectors.toList()));
        return dto;
    }

    public static Trgovina toDtoWithoutIzdelki(TrgovinaEntity trgovinaEntity) {
        Trgovina dto = new Trgovina();
        dto.setTrgovina_id(trgovinaEntity.getTrgovina_id());
        dto.setIme(trgovinaEntity.getIme());
        dto.setUstanovitev(trgovinaEntity.getUstanovitev());
        dto.setSedez(trgovinaEntity.getSedez());
        return dto;
    }

    public static TrgovinaEntity toEntity(Trgovina trgovina) {
        TrgovinaEntity entity = new TrgovinaEntity();
        entity.setTrgovina_id(trgovina.getTrgovina_id());
        entity.setIme(trgovina.getIme());
        entity.setUstanovitev(trgovina.getUstanovitev());
        entity.setSedez(trgovina.getSedez());
        entity.setIzdelki(trgovina.getIzdelki().stream().map(IzdelekConverter::toEntityWithoutTrgovinaEntity).collect(Collectors.toList()));
        return entity;
    }

    public static TrgovinaEntity toEntityWithoutIzdelkiEntities(Trgovina trgovina) {
        TrgovinaEntity entity = new TrgovinaEntity();
        entity.setTrgovina_id(trgovina.getTrgovina_id());
        entity.setIme(trgovina.getIme());
        entity.setUstanovitev(trgovina.getUstanovitev());
        entity.setSedez(trgovina.getSedez());
        return entity;
    }
}
