CREATE TABLE reservations (
                              id bigint generated by default as identity primary key,
                              room_id varchar not null ,
                              booking_range tstzrange not null ,
                              EXCLUDE USING GiST (room_id with =, booking_range with &&)
);

CREATE EXTENSION btree_gist;

drop table reservations;
delete from reservations where id=id;