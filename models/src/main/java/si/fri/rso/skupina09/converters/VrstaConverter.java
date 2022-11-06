package si.fri.rso.skupina09.converters;

import si.fri.rso.skupina09.entities.VrstaEntity;
import si.fri.rso.skupina09.lib.Vrsta;

public class VrstaConverter {

    public static Vrsta toDto(VrstaEntity vrstaEntity) {
        Vrsta dto = new Vrsta();
        dto.setVrsta_id(vrstaEntity.getVrstaId());
        dto.setVrsta(vrstaEntity.getVrsta());
        return dto;
    }

    public static VrstaEntity toEntity(Vrsta vrsta) {
        VrstaEntity entity = new VrstaEntity();
        entity.setVrstaId(vrsta.getVrsta_id());
        entity.setVrsta(vrsta.getVrsta());
        return entity;
    }
}
