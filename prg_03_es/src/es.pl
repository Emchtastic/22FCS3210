% CS3210 - Principles of Programming Languages - Fall 2022
% Instructor: Thyago Mota
% Description: A cheese preference finder
% Student(s) Name(s): Alex Emch


:- dynamic fact/1.

is_true(Question) :-
  (
    fact(Question) ->
      true;
      format('~w?~n', [Question]),
      Answer = read(yes),
      ( Answer -> assert(fact(Question)) )
  ).

cheese(parmesan)  :- is_true('Do you like strong flavor'),      is_true('Do you like hard cheese').
cheese(blue_cheese)  :- is_true('Do you like strong flavor'),      is_true('Do you like soft cheese').
cheese(gouda) :- is_true('Do you like soft cheese').


begin :- ( cheese(A) -> format('I think your cheese is a ~w.~n', [A]); false ).