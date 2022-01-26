PERSONAJE

CREATE TABLE `mundo-disney`.`personaje` 
( `id_personaje` INT NOT NULL AUTO_INCREMENT , 
`imagen` VARCHAR , 
`nombre` VARCHAR NOT NULL , 
`edad` INT NOT NULL , 
`peso` DOUBLE NOT NULL , 
`historia` VARCHAR NOT NULL ,
PRIMARY KEY (`id_personaje`)) 
ENGINE = InnoDB; 


PELICULA_SERIE
CREATE TABLE `mundo-disney`.`pelicula_serie` 
( `id_pelicula_serie` INT NOT NULL AUTO_INCREMENT , 
`imagen` VARCHAR(1000) NOT NULL , 
`titulo` VARCHAR(50) NOT NULL , 
`fecha_creacion` DATETIME NOT NULL , 
`calificacion` INT NOT NULL , 
PRIMARY KEY (`id_pelicula_serie`)) 
ENGINE = InnoDB; 


GENERO
CREATE TABLE `mundo-disney`.`genero` 
( `id_genero` INT NOT NULL AUTO_INCREMENT , 
`nombre` VARCHAR(50) NOT NULL , 
`imagen` VARCHAR(1000) NOT NULL , 
PRIMARY KEY (`id_genero`)) 
ENGINE = InnoDB; 