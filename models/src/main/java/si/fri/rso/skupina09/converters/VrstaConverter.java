package si.fri.rso.skupina09.converters;

import si.fri.rso.skupina09.entities.VrstaEntity;
import si.fri.rso.skupina09.lib.Vrsta;

import java.util.stream.Collectors;

public class VrstaConverter {

    public static Vrsta toDto(VrstaEntity vrstaEntity) {
        Vrsta dto = new Vrsta();
        dto.setVrsta_id(vrstaEntity.getVrsta_id());
        dto.setVrsta(vrstaEntity.getVrsta());
        dto.setIzdelki(vrstaEntity.getIzdelki().stream().map(IzdelekConverter::toDtoWithoutVrsta).collect(Collectors.toList()));
        return dto;
    }

    public static Vrsta toDtoWithoutIzdelki(VrstaEntity vrstaEntity) {
        Vrsta dto = new Vrsta();
        dto.setVrsta_id(vrstaEntity.getVrsta_id());
        dto.setVrsta(vrstaEntity.getVrsta());
        return dto;
    }

    public static VrstaEntity toEntity(Vrsta vrsta) {
        VrstaEntity entity = new VrstaEntity();
        entity.setVrsta_id(vrsta.getVrsta_id());
        entity.setVrsta(vrsta.getVrsta());
        entity.setIzdelki(vrsta.getIzdelki().stream().map(IzdelekConverter::toEntityWithoutVrstaEntity).collect(Collectors.toList()));
        return entity;
    }

    public static VrstaEntity toEntityWithoutIzdelkiEntities(Vrsta vrsta) {
        VrstaEntity entity = new VrstaEntity();
        entity.setVrsta_id(vrsta.getVrsta_id());
        entity.setVrsta(vrsta.getVrsta());
        return entity;
    }
}
