select setval('person_seq', max(id)) from person;
select setval('movie_seq', max(id)) from movie;
