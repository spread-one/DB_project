-- 공간 데이터 삽입
INSERT INTO created_space (description, available_start_time, available_end_time)
VALUES ('Test Space', '06:00:00', '23:00:00');

-- 예약 데이터 삽입
INSERT INTO reservation (reserver_name, space_id, start_time, end_time)
VALUES ('Jane Doe', 1, '08:00:00', '09:00:00');