START TRANSACTION ;
ALTER SEQUENCE marc_id_seq RESTART WITH 1;
ALTER SEQUENCE model_car_id_seq RESTART WITH 1;
ALTER SEQUENCE body_id_seq RESTART WITH 1;
ALTER SEQUENCE characteristic_id_seq RESTART WITH 1;
insert into marc(marc) values('honda');
insert into marc(marc) values('volkswagen');
insert into marc(marc) values('kia');
insert into marc(marc) values('chevrolet');

insert into model_car(model) values( 'cr-v');
insert into model_car(model) values('touareg');
insert into model_car(model) values('rio');
insert into model_car(model) values('camaro');

insert into body(name_body) values ('crossover');
insert into body(name_body) values ('sedan');
insert into body(name_body) values ('coupe');

insert into characteristic(id_marc, id_model, id_body, year_issue, color, volume, price)
        values (1, 1, 1, 2019, 'grey',2.0, 2000000);
insert into characteristic(id_marc, id_model, id_body, year_issue, color, volume, price)
        values (2, 2, 1, 2017, 'red',2.8, 5000000);
insert into characteristic(id_marc, id_model, id_body, year_issue, color, volume, price)
        values (3, 3, 2, 2018, 'brown',1.8, 1200000);
insert into characteristic(id_marc, id_model, id_body, year_issue, color, volume, price)
        values (4, 4, 3, 2013, 'grey',2.3, 2000000);
COMMIT;