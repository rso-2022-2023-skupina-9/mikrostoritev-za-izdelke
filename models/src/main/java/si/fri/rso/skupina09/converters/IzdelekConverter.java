package si.fri.rso.skupina09.converters;

import si.fri.rso.skupina09.entities.IzdelekEntity;
import si.fri.rso.skupina09.entities.TrgovinaEntity;
import si.fri.rso.skupina09.entities.VrstaEntity;
import si.fri.rso.skupina09.lib.Izdelek;

public class IzdelekConverter {

    public static Izdelek toDto(IzdelekEntity entity) {
        Izdelek dto = new Izdelek();
        dto.setIzdelekId(entity.getIzdelekId());
        dto.setTrgovinaId(entity.getTrgovinaEntity().getTrgovinaId());
        dto.setVrstaId(entity.getVrstaEntity().getVrstaId());
        dto.setIme(entity.getIme());
        dto.setCena(entity.getCena());
        dto.setZadnjaSprememba(entity.getZadnjaSprememba());
        return dto;
    }

    public static IzdelekEntity toEntity(Izdelek izdelek, TrgovinaEntity trgovinaEntity, VrstaEntity vrstaEntity) {
        IzdelekEntity izdelekEntity = new IzdelekEntity();
        izdelekEntity.setTrgovinaEntity(trgovinaEntity);
        izdelekEntity.setVrstaEntity(vrstaEntity);
        izdelekEntity.setIme(izdelek.getIme());
        izdelekEntity.setCena(izdelek.getCena());
        izdelekEntity.setZadnjaSprememba(izdelek.getZadnjaSprememba());
        return izdelekEntity;
    }
}
