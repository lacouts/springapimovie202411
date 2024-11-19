select setval('person_seq', max(id)) from persons;
select setval('movie_seq', max(id)) from movies;
