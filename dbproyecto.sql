CREATE TABLE race(
    race_id int NOT NULL AUTO_INCREMENT,
    race_name VARCHAR(200) NOT NULL,
    hp int NOT NULL,
    strength int NOT NULL,
    speed int NOT NULL,
    agility int NOT NULL,
    defense int NOT NULL,
    points int NOT NULL,
    PRIMARY KEY (race_id)
);
CREATE TABLE warriors(
    warrior_id int NOT NULL AUTO_INCREMENT,
    warrior_name VARCHAR(200) NOT NULL,
    warrior_image_path VARCHAR(200) NOT NULL,
    race_id int NOT NULL,
    PRIMARY KEY (warrior_id),
    FOREIGN KEY (race_id) REFERENCES race(race_id)
);
CREATE TABLE players(
    player_id int NOT NULL AUTO_INCREMENT,
    player_name int NOT NULL,
    PRIMARY KEY (player_id)
);
CREATE TABLE ranking(
    player_id int NOT NULL,
    total_points int NOT NULL,
    warrior_id int NOT NULL,
    FOREIGN KEY (player_id) REFERENCES players(player_id),
    FOREIGN KEY (warrior_id) REFERENCES warriors(warrior_id)
);
CREATE TABLE weapons(
    weapon_id int NOT NULL AUTO_INCREMENT,
    weapon_name VARCHAR(200) NOT NULL,
    weapon_image_path VARCHAR(200) NOT NULL,
    strength int NOT NULL,
    speed int NOT NULL,
    weapon_race VARCHAR(200) NOT NULL,
    points int NOT NULL,
    PRIMARY KEY (weapon_id)
);
CREATE TABLE battles(
    battle_id int NOT NULL AUTO_INCREMENT,
    player_id int NOT NULL,
    warrior_id int NOT NULL,
    weapon_id int NOT NULL,
    opponent_id int NOT NULL,
    opponent_weapon_id int NOT NULL,
    injures_caused int NOT NULL,
    injures_suffered int NOT NULL,
    battle_points int NOT NULL,
    PRIMARY KEY (battle_id)
);

INSERT INTO race (race_id, race_name, hp, strength, speed, agility, defense, points) 
VALUES (0,"nan",60,6,3,5,4,21);
INSERT INTO race (race_id, race_name, hp, strength, speed, agility, defense, points) 
VALUES (0,"human",50,5,5,6,3,20);
INSERT INTO race (race_id, race_name, hp, strength, speed, agility, defense, points) 
VALUES (0,"elf",40,4,7,7,2,19);

INSERT INTO weapons (weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points) 
VALUES (0,"Daga","daga.png",0,3,"human;elf",10);
INSERT INTO weapons (weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points) 
VALUES (0,"Espada","espada.png",1,1,"human;elf;nan",10);
INSERT INTO weapons (weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points) 
VALUES (0,"Destral","destral.png",3,0,"human;nan",10);
INSERT INTO weapons (weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points) 
VALUES (0,"Espadas dobles","edobles.png",2,2,"human;elf",14);
INSERT INTO weapons (weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points) 
VALUES (0,"Simitarra","simitarra.png",1,2,"human;elf",14);
INSERT INTO weapons (weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points) 
VALUES (0,"Arco","arco.png",1,5,"elf",15);
INSERT INTO weapons (weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points) 
VALUES (0,"Katana","katana.png",2,3,"human",18);
INSERT INTO weapons (weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points) 
VALUES (0,"Punyal","punyal.png",0,4,"human;elf;nan",12);
INSERT INTO weapons (weapon_id, weapon_name, weapon_image_path, strength, speed, weapon_race, points) 
VALUES (0,"Destral de dos manos","destral2m.png",5,0,"nan",20);

INSERT INTO warriors (warrior_id, warrior_name, warrior_image_path, race_id) 
VALUES (0,"Gato Mascarilla","mCat.png",1);
INSERT INTO warriors (warrior_id, warrior_name, warrior_image_path, race_id) 
VALUES (0,"Ganso","goose.png",2);
INSERT INTO warriors (warrior_id, warrior_name, warrior_image_path, race_id) 
VALUES (0,"Bebe Mascarilla","bebe_mascarilla.png",3);
INSERT INTO warriors (warrior_id, warrior_name, warrior_image_path, race_id) 
VALUES (0,"Cocodrilo Fuego","cocodrilo_fuego.png",2);
INSERT INTO warriors (warrior_id, warrior_name, warrior_image_path, race_id) 
VALUES (0,"Homer Caramelo Acido","homer_caramelo_acido.png",3);
INSERT INTO warriors (warrior_id, warrior_name, warrior_image_path, race_id) 
VALUES (0,"Pato Fuego","pato_fuego.png",1);
