drop table if exists datosProf;
drop table if exists asigProyecto;
drop table if exists proyecto;
drop table if exists empleado;

create table if not exists empleado(
	dni char(9) not null,
	nom_Emp varchar(200) not null,

	primary key (dni)
);

create table if not exists proyecto(
	id_Proy integer auto_increment not null,
	nom_Proy varchar(200) not null,
	f_Inicio date not null,
	f_Fin date null,
	dni_Jefe_Proy char(9) not null,

	primary key (id_Proy),
	foreign key (dni_Jefe_Proy) references empleado (dni) on update cascade
);

create table if not exists asigProyecto(
	dni_Emp char(9),
	id_Proy integer not null,
	f_Inicio date not null,
	f_Fin date null,

	primary key (f_Inicio , dni_Emp, id_Proy),
	foreign key (dni_Emp) references empleado (dni) on update cascade,
	foreign key (id_Proy) references proyecto (id_Proy) on update restrict
);

##create table if not exists datosProf(
##	dni char(9) not null,
##	categoria char(2) not null,
##	sueldo_Bruto_Anual decimal(8,2),
##
##	primary key (dni),
##	foreign key (dni) references empleado(dni)
##);

##create user 'gestionproy'@'%' identified by 'gestionproy';

##grant all privileges on gestionProyecto.* to 'gestionproy'@'%' WITH GRANT OPTION;