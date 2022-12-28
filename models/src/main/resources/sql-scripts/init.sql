INSERT INTO vrsta(vrsta) VALUES ('Macbook Pro M1 Pro 512 GB');
INSERT INTO vrsta(vrsta) VALUES ('Macbook Air M2 256 GB');
INSERT INTO vrsta(vrsta) VALUES ('iMac M1 24');
INSERT INTO vrsta(vrsta) VALUES ('Mac Studio M1 Ultra 1 TB');
INSERT INTO vrsta(vrsta) VALUES ('iPhone 14 Pro Max 256 GB');
INSERT INTO vrsta(vrsta) VALUES ('iPad Pro M2 11 128 GB');
INSERT INTO vrsta(vrsta) VALUES ('Apple Watch Series 8');

INSERT INTO trgovina(ime, ustanovitev, sedez) VALUES ('iStyle', '2012-01-01', 'Slovenia');
INSERT INTO trgovina(ime, ustanovitev, sedez) VALUES ('Epl', '2013-05-18', 'Slovenia');
INSERT INTO trgovina(ime, ustanovitev, sedez) VALUES ('Big Bang', '2002-10-12', 'Slovenia');
INSERT INTO trgovina(ime, ustanovitev, sedez) VALUES ('Harvey Norman', '1978-03-27', 'Australia');

INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('1', '1', 'iStyle Macbook Pro', '249900', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('2', '1', 'iStyle Macbook Air', '162901', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('3', '1', 'iStyle iMac', '157900', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('4', '1', 'iStyle Mac Studio', '489501', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('5', '1', 'iStyle iPhone', '159999', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('6', '1', 'iStyle iPad', '114399', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('7', '1', 'iStyle Apple Watch', '50900', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('2', '2', 'Epl Macbook Air', '179900', '2022-10-26');

INSERT INTO kosarica(ime) VALUES ('Apple Kosarica');

INSERT INTO kosarica_izdelek(kosarica_id, izdelek_id) VALUES ('1', '1');