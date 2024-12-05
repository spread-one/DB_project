-- Created Space Table
CREATE TABLE created_space (
                               space_id INT AUTO_INCREMENT PRIMARY KEY,
                               description VARCHAR(255) NOT NULL,
                               available_start_time TIME NOT NULL,
                               available_end_time TIME NOT NULL
);

-- Reservation Table
CREATE TABLE reservation (
                             reservation_id INT AUTO_INCREMENT PRIMARY KEY,
                             reserver_name VARCHAR(100) NOT NULL,
                             space_id INT,
                             start_time TIME NOT NULL,
                             end_time TIME NOT NULL,
                             FOREIGN KEY (space_id) REFERENCES created_space(space_id) ON DELETE CASCADE
);
