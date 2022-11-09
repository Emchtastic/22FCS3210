% CS3210 - Principles of Programming Languages - Fall 2022
% Instructor: Thyago Mota
% Description: Homework 10 - A KB in Prolog with queries
% Student Name: Alex Emch

mountain('Everest').
mountain('Mckinley').
mountain('Elbrus').
mountain('Aconcagua').
mountain('Kilimanjaro').
mountain('Vinson').
mountain('Puncak Jaya').
location('Asia').
location('South America').
location('North America').
location('Africa').
location('Europe').
location('Antarctica').
location('Australia').
height('Everest', 29029).
height('Mckinley', 20312).
height('Elbrus', 18510).
height('Aconcagua', 22841).
height('Kilimanjaro', 19340).
height('Vinson', 16050).
height('Puncak Jaya', 16023).
climber('John').
climber('Kelly').
climber('Maria').
climber('Derek').
not(climber('Thyago')).
isLocated('Everest', 'Asia') :- mountain('Everest'), location('Asia').
isLocated('Mckinley', 'North America') :- mountain('Mckinley'), location('North America').
isLocated('Elbrus', 'Europe') :- mountain('Elbrus'), location('Europe').
isLocated('Aconcagua', 'South America') :- mountain('Aconcagua'), location('South America').
isLocated('Kilimanjaro', 'Africa') :- mountain('Kilimanjaro'), location('Africa').
isLocated('Vinson', 'Antarctica') :- mountain('Vinson'), location('Antarctica').
isLocated('Puncak Jaya', 'Australia') :- mountain('Puncak Jaya'), location('Australia').

wouldClimb('John', Y) :- climber('John'), isLocated(Y, 'North America'), height(Y, Z), Z >= 20000.
wouldClimb('Kelly', Y) :- climber('Kelly'), height(Y, Z), Z >= 20000.
wouldClimb('Maria', Y) :- climber('Maria'), mountain(Y).
wouldClimb('Derek', Y) :- climber('Derek'), (isLocated(Y, 'Europe'); isLocated(Y, 'Africa'); isLocated(Y, 'Australia')), height(Y, Z), Z =< 19000.

isTallest(X) :- (height(X, Z), forall(height(_, Y),(Z>=Y->true;fail))).
tallerThan(X,Y) :- height(X, H), H > Y.




% Queires
% isTallest('Everest').
% isLocated('Kilimanjaro', 'Africa')
% tallerThan('Elbrus', 18000).
% climber('Thyago').
% climber('John').
% climber(X).
% wouldClimb('John', X)
% wouldClimb('Kelly', X)
% wouldClimb('Maria', X)
% wouldClimb('Derek', X)
% wouldClimb('Thyago', X)