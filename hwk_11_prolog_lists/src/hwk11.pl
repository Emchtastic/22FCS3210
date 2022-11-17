% CS3210 - Principles of Programming Languages - Fall 2022
% Instructor: Thyago Mota
% Description: Homework 11 - Prolog Lists
% Student Name: Alex Emch

sum([], 0).
sum([H|T], Sum) :-
   sum(T, X), % returning base case as sum of sums
   Sum is H + X. % add base case to head

max([X],X).
max([H|T], Y) :-
    max(T,X),
    (H > X -> H = Y; Y = X).


