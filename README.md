### About ###
Our company 


### Assumptions ###
* All time is EST
* Each employee has their own view
* Tasks are pre-populated 

### Main Requirements ###
* Employee can add a task (from pre-saved tasks) 
* Employee can start/stop and edit time in a task
* Employee can complete a project (with a warning)

### Stretch Goals ###
* Billing Report - time per task
* Admin view for adding and removing tasks/projects
* Enroll a new user

### How to run ###
* Open terminal
* Change directory to the root
* mvn spring-boot:run
* Go to localhost:8081


### Create Tables ###

CREATE TABLE customer (
	id SERIAL PRIMARY KEY,
    name TEXT,
    description TEXT
)

CREATE TABLE project (
	id SERIAL PRIMARY KEY,
    customer_id INT,
    name TEXT,
    description TEXT,
    FOREIGN KEY (customer_id) REFERENCES customer (id)
)

CREATE TABLE task (
	id SERIAL PRIMARY KEY,
    project_id INT,
    customer_id INT,
    name TEXT,
    description TEXT,
    completed_on DATETIME,
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (project_id) REFERENCES project (id)
)

CREATE TABLE employee (
	id SERIAL PRIMARY KEY,
    name TEXT
)

CREATE TABLE task_work (
	id SERIAL PRIMARY KEY,
    task_id INT,
    employee_id INT,
    started_on DATETIME,
    time_spent INT,
    completed_on DATETIME,
    in_progress BOOLEAN,
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (task_id) REFERENCES task (id)
)

INSERT INTO customer 
(name, description)

VALUES
("Tesla", "Car Company"),
("American Airlines", "Plane Company")


INSERT INTO project
(customer_id, name, description)
VALUES
(1, "Anti-Gravity", "Develop a pill to defy gravity"),
(1, "Teleportation", "Teleport a person to a new location"),
(2, "Self-Cleaning Cars", "Clean the car automatically")

INSERT INTO task (name, project_id, customer_id, description)
VALUES
("Build Prototype 1", 1, 1, "Build the quantum physics approach"),
("Build Prototype 2", 1, 1, "Build the radiation approach"),

INSERT INTO employee (name)
VALUES
("Jeremy Renner"),
("Carey Mulligan")

INSERT INTO task_work 
(task_id, employee_id, started_on, time_spent, in_progress)
VALUES
(1, 1, CURDATE(), 100, TRUE)
(1, 2, CURDATE(), 200, false)