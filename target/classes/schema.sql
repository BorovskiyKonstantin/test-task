create table ASSESSMENT (
  id IDENTITY primary key,
  assessed_value DEC(20),
  valuation_date TIMESTAMP
);

create table CAR (
  id IDENTITY primary key,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  power DOUBLE,
  year_of_issue YEAR
);

create table ASSESSMENT_CAR (
    assessment_id BIGINT,
    car_id BIGINT,
    primary key (car_id, assessment_id),
    foreign key (car_id) references CAR (id),
    foreign key (assessment_id) references ASSESSMENT (id)
);

create table AIRPLANE (
  id IDENTITY primary key,
  brand VARCHAR2(150),
  model VARCHAR2(200),
  manufacturer VARCHAR2(500),
  year_of_issue YEAR,
  fuel_capacity INT,
  seats INT
);

create table ASSESSMENT_AIRPLANE (
    assessment_id BIGINT,
    airplane_id BIGINT,
    primary key (airplane_id, assessment_id),
    foreign key (airplane_id) references AIRPLANE (id),
    foreign key (assessment_id) references ASSESSMENT (id)
);