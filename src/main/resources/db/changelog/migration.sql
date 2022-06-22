-- liquibase formatted sql

-- changeset fedor:1
create sequence candidate_sequence
    start with 1
    increment by 1
    cache 1;

create table respondent
(
    id bigint default nextval('candidate_sequence') not null primary key
);

create sequence test_sequence
    increment by 1
    start with 1
    cache 1;

create table test
(
    id bigint default nextval('test_sequence') not null primary key,
    title varchar(50) not null,
    url varchar(20) not null,
    region varchar(20) not null,
    comment varchar(200)
);

create sequence question_block_sequence
    increment by 1
    start with 1
    cache 1;

create table question_block
(
    id bigint default nextval('question_block_sequence') not null primary key,
    title varchar(50) not null
);

create sequence question_sequence
    increment by 1
    start with 1
    cache 1;

create table question
(
    id bigint default nextval('question_sequence') not null primary key,
    text varchar(500) not null,
    picture varchar(500)
);

create sequence question_variant_sequence
    increment by 1
    start with 1
    cache 1;

create table question_variant
(
    id bigint default nextval('question_variant_sequence') not null primary key,
    question_id bigint not null,
    text varchar(100) not null,
    picture varchar(20) not null,

    foreign key (question_id) references question(id)
);

create sequence task_block_sequence
    increment by 1
    start with 1
    cache 10;

create sequence task_sequence
    increment by 1
    start with 1
    cache 10;

create table task
(
    id bigint default nextval('task_sequence') not null primary key,
    text varchar(100) not null
);

create table task_block
(
    id bigint default nextval('task_block_sequence') not null primary key,
    task_id bigint not null,
    title varchar(100) not null,

    foreign key (task_id) references task(id)
);

create sequence test_result_sequence
    increment by 1
    start with 1
    cache 10;

create sequence task_answer_sequence
    increment by 1
    start with 1
    cache 10;

create sequence question_answer_sequence
    increment by 1
    start with 1
    cache 10;

create sequence group_sequence
    increment by 1
    start with 1
    cache 10;

create sequence phrase_dictionary_sequence
    increment by 1
    start with 1
    cache 10;

create sequence test_phrase_sequence
    increment by 1
    start with 1
    cache 10;

create table test_result
(
    id bigint default nextval('test_result_sequence') not null primary key,
    test_id bigint not null,
    respondent_id bigint not null,
    creation_time timestamp not null,
    respondent_type int not null,

    foreign key (test_id) references test(id),
    foreign key (respondent_id) references respondent(id)
);

create table task_answer
(
    id bigint default nextval('task_answer_sequence') not null primary key,
    test_result_id bigint not null,
    task_id bigint not null,
    text varchar(500) not null,
    number int not null,
    foreign key (test_result_id) references test_result(id),
    foreign key (task_id) references task(id)
);

create table question_answer
(
    id bigint default nextval('question_answer_sequence') not null primary key,
    test_result_id bigint not null,
    question_id bigint not null,
    variant_id bigint not null,
    foreign key (test_result_id) references test_result(id),
    foreign key (question_id) references question(id),
    foreign key (variant_id) references question_variant(id)
);

create table phrase_group
(
    id bigint default nextval('group_sequence') not null primary key,
    title varchar(50) not null
);

create table phrase_dictionary
(
    id bigint default nextval('phrase_dictionary_sequence') not null primary key,
    phrase varchar(100) not null,
    group_id bigint not null,
    foreign key (group_id) references phrase_group(id)
);

create table test_phrase
(
    id bigint default nextval('test_phrase_sequence') not null primary key,
    intensivity int not null,
    test_id bigint not null,
    foreign key (test_id) references test(id)
);

create sequence codes_sequence
    increment by 1
    start with 1
    cache 1;

create table codes
(
    id bigint default nextval('codes_sequence') not null primary key,
    code varchar(7) not null,
    result_id bigint not null,
    respondent_id bigint not null,
    given timestamp not null,
    status varchar(8) not null,
    foreign key (result_id) references test_result(id),
    foreign key (respondent_id) references respondent(id)
);

create sequence test_statistic_sequence
    increment by 1
    start with 1
    cache 1;

create table test_statistics
(
    id bigint default nextval('test_statistic_sequence') not null primary key,
    test_id bigint not null,
    phrase_id bigint not null,
    timestamp date not null,
    click_amount int not null,
    foreign key (test_id) references test(id),
    foreign key (phrase_id) references test_phrase(id)
);

create table test_to_question_block
(
    test_id bigint not null,
    question_block_id bigint not null,
    foreign key (test_id) references test(id),
    foreign key (question_block_id) references question_block(id)
);

create table test_to_block
(
    test_id bigint not null,
    task_block_id bigint not null,
    number int not null,
    foreign key (test_id) references test(id),
    foreign key (task_block_id) references task_block(id)
);

create table question_block_to_question
(
    question_block_id bigint not null,
    question_id bigint not null,
    foreign key (question_block_id) references question_block(id),
    foreign key (question_id) references question(id)
);

create table task_block_to_task
(
    task_block_id bigint not null,
    task_id bigint not null,
    foreign key (task_block_id) references task_block(id),
    foreign key (task_id) references task(id)
);

create table result_to_phrase
(
    result_id bigint not null,
    phrase_id bigint not null,
    foreign key (result_id) references test_phrase(id),
    foreign key (phrase_id) references phrase_dictionary(id)
);
