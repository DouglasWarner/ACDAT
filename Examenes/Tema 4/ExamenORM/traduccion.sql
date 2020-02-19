drop database if exists traduccion;
create database traduccion;

use traduccion;


create table idioma(

cod_iso char(2) not null,

nom_idioma char(16) not null,

primary key(cod_iso)

);


create table traductor(

dni_nie char(9) not null,

nom_trad varchar(50) not null,

id_lengua_nativa char(2) not null,

foreign key fk_trad_leng_nat(id_lengua_nativa) references idioma(cod_iso),

primary key(dni_nie)

);


create table traductor_lengua(

dni_nie char(9) not null,

id_lengua char(2) not null,

foreign key fk_tradleng_trad(dni_nie) references traductor(dni_nie),

foreign key fk_tradleng_leng(id_lengua) references idioma(cod_iso),

primary key(dni_nie,id_lengua)

);


create table texto(

id_texto integer not null auto_increment,

num_palabras integer,

cod_idioma char(2) not null,

primary key(id_texto),

foreign key fk_texto_idioma(cod_idioma) references idioma(cod_iso)

);


create table asig_traduccion(

id_texto integer not null,

cod_idioma_dest char(2) not null,

dni_nie_trad char(9) not null,

foreign key fk_asig_trad_texto(id_texto) references texto(id_texto),

foreign key fk_asig_trad_idioma_dest(cod_idioma_dest) references idioma(cod_iso),

foreign key fk_asig_trad_trad(dni_nie_trad) references traductor(dni_nie),

primary key(id_texto, cod_idioma_dest)

);


create user 'traduccion'@'localhost' identified with mysql_native_password by 'traduccion';

grant all privileges on traduccion.* to 'traduccion'@'localhost';
