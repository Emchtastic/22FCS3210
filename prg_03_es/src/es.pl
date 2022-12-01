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
%make sure to exclude cheese that are not in a choice branch (mold cancels bacteria tree -> false for 'like bacteria cheeses')
cheese(brie)  :- is_true('Do you like moldy cheeses'), is_true('Do you like cheese with a thick rind'), is_true('Do you like sweet flavors').
cheese(camembert)  :- is_true('Do you like moldy cheeses'), is_true('Do you like cheese with a thick rind'), is_true('Do you like mushroomy flavors').



begin :-
( cheese(A) -> format('I think that you would love to try ~w cheese. ~n', [A]); write('I could not find a cheese for your palate')),
 retractall(yes(_)),
 retractall(no(_)).