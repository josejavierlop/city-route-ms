insert into cities(id, name) values(1,'Zaragoza');
insert into cities(id, name) values(2,'Madrid');
insert into cities(id, name) values(3,'Barcelona');
insert into cities(id, name) values(4,'Teruel');
insert into cities(id, name) values(5,'Valencia');
insert into cities(id, name) values(6,'La Coruña');
insert into cities(id, name) values(7,'Cáceres');
insert into cities(id, name) values(8,'Sevilla');

-- Zgz - Sev
insert into routes(id, origin_id, destination_id) values(1,1,8);
insert into routes(id, origin_id, destination_id) values(2,1,8);
insert into routes(id, origin_id, destination_id) values(3,1,8);
-- Mad - Bcn
insert into routes(id, origin_id, destination_id) values(4,2,3);

-- Zgz - Sev (1)
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(1,1,1,1,2,3);
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(2,1,2,2,3,3);
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(3,1,3,3,8,4);

-- Zgz - Sev (2)
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(4,2,1,1,2,3);
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(5,2,2,2,8,6);

-- Zgz - Sev (3)
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(6,3,1,1,8,11);

-- Mad - Bcn (1)
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(7,4,1,2,1,3);
insert into steps(id, route_id, step_order, origin_id, destination_id, hours) values(8,4,2,1,3,3);

commit;