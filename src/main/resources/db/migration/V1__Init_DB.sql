CREATE TABLE users (
    users_id int primary key generated by default as IDENTITY,
    password VARCHAR(2048),
    firstname VARCHAR(100) NOT NULL,
    lastname VARCHAR(100) NOT NULL,
    username varchar(100) unique not null,
    user_role VARCHAR(100) NOT NULL DEFAULT 'ROLE_USER'
);

create table Project(
    project_id int primary key generated by default as IDENTITY,
    project_name varchar(100) not null unique,
    description varchar(10000),
    start_date date not null
);

create table record(
    user_id int references users(users_id),
    project_id int references Project(project_id),
    notes varchar(10000),
    task varchar(1000),
    time_in_hour int,
    date_at_which DATE,
    primary key (user_id,project_id)
)