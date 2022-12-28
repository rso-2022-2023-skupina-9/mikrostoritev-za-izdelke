package si.fri.rso.skupina09.converters;

import si.fri.rso.skupina09.entities.KosaricaEntity;
import si.fri.rso.skupina09.lib.Izdelek;
import si.fri.rso.skupina09.lib.Kosarica;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KosaricaConverter {

    public static Kosarica toDto(KosaricaEntity kosaricaEntity) {
        Kosarica dto = new Kosarica();
        dto.setKosarica_id(kosaricaEntity.getKosarica_id());
        if (kosaricaEntity.getIzdelki() != null) {
            dto.setIzdelki(kosaricaEntity.getIzdelki().stream().map(IzdelekConverter::toDto).collect(Collectors.toList()));
        }
        dto.setIme(kosaricaEntity.getIme());
        return dto;
    }

    public static KosaricaEntity toEntity(Kosarica kosarica) {
        KosaricaEntity kosaricaEntity = new KosaricaEntity();
        if (kosarica.getIzdelki() != null) {
            kosaricaEntity.setIzdelki(kosarica.getIzdelki().stream().map(IzdelekConverter::toEntity).collect(Collectors.toList()));
        }
        kosaricaEntity.setIme(kosarica.getIme());
        return kosaricaEntity;
    }
}
