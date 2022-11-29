% CS3210 - Principles of Programming Languages - Fall 2022
% Instructor: Thyago Mota
% Description: A cheese preference finder
% Student(s) Name(s): Alex Emch



:- dynamic cheese/1.
:- dynamic yes/1,no/1.

is_true(Question) :-
  (
      yes(Question) ->  true; %check if fact is currently yes, skip question
      no(Question) ->  false; %check if fact is currently no, skip question
      format('~w?~n', [Question]), %fact hasn't been asked, question the user
      read(Input),
      ( (Input == yes) -> assert(yes(Question)); assert(no(Question)), false)
  ).

cheese(parmesan)  :- is_true('Do you like strong flavor'),      is_true('Do you like hard cheese').
cheese(blue)  :- is_true('Do you like strong flavor'),      is_true('Do you like soft cheese').
cheese(gouda) :- is_true('Do you like soft/weak cheese').


begin :-
( cheese(A) -> format('I think that you would love to try ~w cheese. ~n', [A]); write('I could not find a cheese for your palate')),
 retractall(yes(_)),
 retractall(no(_)).