package si.fri.rso.skupina09.converters;

import si.fri.rso.skupina09.entities.IzdelekEntity;
import si.fri.rso.skupina09.lib.Izdelek;

public class IzdelekConverter {

    public static Izdelek toDto(IzdelekEntity entity) {
        Izdelek dto = new Izdelek();
        dto.setIzdelek_id(entity.getIzdelekId());
        dto.setVrsta(VrstaConverter.toDtoWithoutIzdelki(entity.getVrstaEntity()));
        dto.setTrgovina(TrgovinaConverter.toDtoWithoutIzdelki(entity.getTrgovinaEntity()));
        dto.setIme(entity.getIme());
        dto.setCena(entity.getCena());
        dto.setZadnja_sprememba(entity.getZadnjaSprememba());
        return dto;
    }

    public static Izdelek toDtoWithoutVrsta(IzdelekEntity entity) {
        Izdelek dto = new Izdelek();
        dto.setIzdelek_id(entity.getIzdelekId());
        dto.setTrgovina(TrgovinaConverter.toDtoWithoutIzdelki(entity.getTrgovinaEntity()));
        dto.setIme(entity.getIme());
        dto.setCena(entity.getCena());
        dto.setZadnja_sprememba(entity.getZadnjaSprememba());
        return dto;
    }

    public static Izdelek toDtoWithoutTrgovina(IzdelekEntity entity) {
        Izdelek dto = new Izdelek();
        dto.setIzdelek_id(entity.getIzdelekId());
        dto.setVrsta(VrstaConverter.toDtoWithoutIzdelki(entity.getVrstaEntity()));
        dto.setIme(entity.getIme());
        dto.setCena(entity.getCena());
        dto.setZadnja_sprememba(entity.getZadnjaSprememba());
        return dto;
    }

    public static IzdelekEntity toEntity(Izdelek izdelek) {
        IzdelekEntity izdelekEntity = new IzdelekEntity();
        izdelekEntity.setVrstaEntity(VrstaConverter.toEntityWithoutIzdelkiEntities(izdelek.getVrsta()));
        izdelekEntity.setTrgovinaEntity(TrgovinaConverter.toEntityWithoutIzdelkiEntities(izdelek.getTrgovina()));
        izdelekEntity.setIme(izdelek.getIme());
        izdelekEntity.setCena(izdelek.getCena());
        izdelekEntity.setZadnjaSprememba(izdelek.getZadnja_sprememba());
        return izdelekEntity;
    }

    public static IzdelekEntity toEntityWithoutVrstaEntity(Izdelek izdelek) {
        IzdelekEntity izdelekEntity = new IzdelekEntity();
        izdelekEntity.setTrgovinaEntity(TrgovinaConverter.toEntityWithoutIzdelkiEntities(izdelek.getTrgovina()));
        izdelekEntity.setIme(izdelek.getIme());
        izdelekEntity.setCena(izdelek.getCena());
        izdelekEntity.setZadnjaSprememba(izdelek.getZadnja_sprememba());
        return izdelekEntity;
    }

    public static IzdelekEntity toEntityWithoutTrgovinaEntity(Izdelek izdelek) {
        IzdelekEntity izdelekEntity = new IzdelekEntity();
        izdelekEntity.setVrstaEntity(VrstaConverter.toEntityWithoutIzdelkiEntities(izdelek.getVrsta()));
        izdelekEntity.setIme(izdelek.getIme());
        izdelekEntity.setCena(izdelek.getCena());
        izdelekEntity.setZadnjaSprememba(izdelek.getZadnja_sprememba());
        return izdelekEntity;
    }
}
