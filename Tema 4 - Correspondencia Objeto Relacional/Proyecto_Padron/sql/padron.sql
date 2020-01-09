create database if not exists padron;

use padron;

drop table if exists localidad;
drop table if exists provincia;
drop table if exists comunidad;

create table comunidad(
    id_com integer not null auto_increment,
    nom_com varchar(255) not null,

    primary key (id_com)


);

create table provincia(
    codigo_postal char(2) not null,
    nom_prov varchar(255) not null,
    id_comunidad integer not null,
    

    primary key (codigo_postal),
    foreign key (id_comunidad) references comunidad(id_com) on update cascade
    
);

create table localidad(
    id_loc integer not null auto_increment,
    nom_loc varchar(255) not null,
    cod_provincia char(2) not null,

    primary key (id_loc),
    foreign key (cod_provincia) references provincia (codigo_postal) on update cascade


);

create user 'padron'@'localhost' identified by 'padron';

grant all privileges on padron.* to 'padron'@'localhost';
