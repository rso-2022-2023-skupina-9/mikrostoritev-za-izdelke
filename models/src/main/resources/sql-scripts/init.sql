INSERT INTO vrsta(vrsta) VALUES ('jajca');
INSERT INTO vrsta(vrsta) VALUES ('moka');
INSERT INTO vrsta(vrsta) VALUES ('mleko');
INSERT INTO vrsta(vrsta) VALUES ('sladkor');
INSERT INTO vrsta(vrsta) VALUES ('sol');
INSERT INTO trgovina(ime, ustanovitev, sedez) VALUES ('Spar', '1932-02-16', 'Netherlands');
INSERT INTO trgovina(ime, ustanovitev, sedez) VALUES ('Mercator', '2002-04-07', 'Slovenia');
INSERT INTO trgovina(ime, ustanovitev, sedez) VALUES ('Hofer', '1946-07-10', 'Germany');
INSERT INTO trgovina(ime, ustanovitev, sedez) VALUES ('Lidl', '1932-05-19', 'Germany');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('1', '1', 'Spar jajca', '107', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('2', '1', 'Spar moka', '89', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('3', '2', 'Mercator mleko', '96', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('4', '3', 'Hofer sladkor', '102', '2022-10-26');
INSERT INTO izdelek(vrsta_id, trgovina_id, ime, cena, zadnja_sprememba) VALUES ('5', '4', 'Lidl sol', '93', '2022-10-26');
