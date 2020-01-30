
drop table if exists empleado;

create table empleado (
    dni char(9) not null,
    nom_emp varchar(255) not null,
    num_emp char(9),
    tipo char(2) not null,

    primary key (dni),
    constraint check_tipos check((tipo='E' and isnull(num_emp)) or (tipo='EP' and not isnull(num_emp)))
);

