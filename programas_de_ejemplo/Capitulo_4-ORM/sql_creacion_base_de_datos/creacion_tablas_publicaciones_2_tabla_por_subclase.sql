-- Modelo relacional para correspondencia de la herencia con una tabla para superclase y por subclase.
-- Previamente se borra, si existe, cualquier tabla relacionada con esta jerarquía, tanto
-- para esta solución con una única tabla como para la solución alternativa con una tabla
-- para la superclase y una tabla por subclase.

drop table if exists revista;
drop table if exists libro;
drop table if exists publicacion;

create table publicacion(
  id_pub integer auto_increment not null,
  nom_pub varchar(50) not null,
  primary key(id_pub)
);

create table libro(
  id_pub integer not null,
  isbn char(13) not null,
  autor varchar(40),
  primary key(id_pub),
  constraint fk_libro_publicacion foreign key(id_pub) references publicacion(id_pub)
);

create table revista(
  id_pub integer not null,
  issn char(10) not null,
  primary key(id_pub),
  constraint fk_revista_publicacion foreign key(id_pub) references publicacion(id_pub)
);
