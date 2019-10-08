-- Crea base de datos proyecto_orm y usuario con el mismo nombre con los permisos necesarios sobre ella.
-- Debe ejecutarse como usuario root.

create database proyecto_orm;

use proyecto_orm;

create table sede(
id_sede integer auto_increment not null,
nom_sede char(20) not null,
primary key(id_sede)
);

create table departamento(
id_depto integer auto_increment not null,
nom_depto char(32) not null,
id_sede integer not null,
primary key(id_depto),
foreign key fk_depto_sede(id_sede) references sede(id_sede)
);

create table empleado(
dni char(9) not null,
nom_emp char(40) not null,
id_depto integer not null,
primary key(dni),
foreign key fk_empleado_depto(id_depto) references departamento(id_depto)
);

create table empleado_datos_prof(
dni char(9) not null,
categoria char(2) not null,
sueldo_bruto_anual decimal(8,2),
primary key(dni),
foreign key fk_empleado_datosprof_empl(dni) references empleado(dni)
);

create table proyecto (
id_proy integer auto_increment not null,
f_inicio date not null,
f_fin date,
nom_proy char(20) not null,
primary key(id_proy)
);

create table proyecto_sede (
id_proy integer not null,
id_sede integer not null,
f_inicio date not null,
f_fin date,
primary key(id_proy, id_sede),
foreign key fk_proysede_proy (id_proy) references proyecto(id_proy),
foreign key fk_proysede_sede (id_sede) references sede(id_sede)
);

CREATE USER 'libro_ad'@'localhost' IDENTIFIED BY 'libro_ad';
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,EXECUTE
ON proyecto_orm.* TO 'libro_ad'@'localhost';
