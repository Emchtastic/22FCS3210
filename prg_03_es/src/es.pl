% CS3210 - Principles of Programming Languages - Fall 2022
% Instructor: Thyago Mota
% Description: A cheese preference finder
% Student(s) Name(s): Alex Emch

:- dynamic yes/1,no/1.
:- dynamic fact/2.

member(X, [X | _]).
member(X, [_ | T]) :-member(X, T).

is_true(Question) :-
  (
      yes(Question) ->  true; %check if fact is currently yes, skip question
      no(Question) ->  false; %check if fact is currently no, skip question
      format('~w?~n', [Question]), %fact hasn't been asked, question the user
      read(Input),
      ( (Input == yes) -> assert(yes(Question)); assert(no(Question)), false)
  ).

is_country(Abbrv, Countries) :-
(
  fact(Abbrv, Countries) ->
    true;
    format('~w?~n', [Abbrv]),
    read(Answer),
    ( member(Answer, Countries) ->
        (
            Answer==br -> format('The most popular cheese in Brazil is Queijo Minas~n');
            Answer==ch -> format('The most popular cheese in Switzerland is Emmental~n');
            Answer==de -> format('The most popular cheese in Germany is Limburger~n');
            Answer==es -> format('The most popular cheese in Spain is Manchego~n');
            Answer==fr -> format('The most popular cheese in France is Camembert~n');
            Answer==gb -> format('The most popular cheese in the United Kingdom is Cheddar~n');
            Answer==gr -> format('The most popular cheese in Greece is Feta~n');
            Answer==us -> format('The most popular cheese in the United States is Cheddar~n')
        );
        format("Sorry, but we don't have that country's cheese data! ~n")
    )

).
%make sure to exclude cheese that are not in a choice branch (mold cancels bacteria tree -> false for 'like bacteria cheeses')
cheese(carre-del-lEst) :- is_true('Do you prefer mild flavors (no. means you like stronger flavors)'), is_true('Do you like gooey food texture/mouthfeel'), is_true('Do you like smokey flavors').
cheese(camembert)  :- is_true('Do you prefer mild flavors (no. means you like stronger flavors)'), is_true('Do you like gooey food texture/mouthfeel'), is_true('Do you like earthy flavors').
cheese(brie) :- is_true('Do you prefer mild flavors (no. means you like stronger flavors)'), is_true('Do you like gooey food texture/mouthfeel'), is_true('Do you like buttery flavors').
cheese(queijo-minas) :- is_true('Do you prefer mild flavors (no. means you like stronger flavors)'), not(is_true('Do you like gooey food texture/mouthfeel')), is_true('Do you like spongy food texture'), is_true('Do you like salty flavors').
cheese(queso-fresco) :- is_true('Do you prefer mild flavors (no. means you like stronger flavors)'), not(is_true('Do you like gooey food texture/mouthfeel')), not(is_true('Do you like spongy food texture')), is_true('Do you like softer food texture/mouthfeel'), is_true('Do you like salty flavors').
cheese(mozzarella) :- is_true('Do you prefer mild flavors (no. means you like stronger flavors)'), not(is_true('Do you like gooey food texture/mouthfeel')), not(is_true('Do you like spongy food texture')), is_true('Do you like softer food texture/mouthfeel'), is_true('Do you like milky flavors').
cheese(feta) :- is_true('Do you prefer mild flavors (no. means you like stronger flavors)'), not(is_true('Do you like gooey food texture/mouthfeel')), not(is_true('Do you like spongy food texture')), not(is_true('Do you like softer food texture/mouthfeel')), is_true('Do you like crumbly food texture/mouthfeel'), is_true('Do you like milky flavors').
cheese(chevre) :- is_true('Do you prefer mild flavors (no. means you like stronger flavors)'), not(is_true('Do you like gooey food texture/mouthfeel')), not(is_true('Do you like spongy food texture')), not(is_true('Do you like softer food texture/mouthfeel')), is_true('Do you like crumbly food texture/mouthfeel'), is_true('Do you like tangy flavors').
cheese(gorgonzola) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), is_true('Do you like softer food texture/mouthfeel'), is_true('Are you fine with mold in your cheese'), is_true('Do you like pungent/strong aromas'), is_true('Do you like nutty flavors').
cheese(stilon) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), is_true('Do you like softer food texture/mouthfeel'), is_true('Are you fine with mold in your cheese'), is_true('Do you like pungent/strong aromas'), is_true('Do you like creamy flavors').
cheese(roquefort) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), is_true('Do you like softer food texture/mouthfeel'), is_true('Are you fine with mold in your cheese'), is_true('Do you like pungent/strong aromas'), is_true('Do you like tangy flavors').
cheese(limburger) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), is_true('Do you like softer food texture/mouthfeel'), not(is_true('Are you fine with mold in your cheese')), is_true('Do you like pungent/strong aromas'), is_true('Do you like mushroomy flavors').
cheese(taleggio) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), is_true('Do you like softer food texture/mouthfeel'), not(is_true('Are you fine with mold in your cheese')), is_true('Do you like pungent/strong aromas'), is_true('Do you like tangy flavors').
cheese(havarti) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), is_true('Do you like softer food texture/mouthfeel'), not(is_true('Are you fine with mold in your cheese')), not(is_true('Do you like pungent/strong aromas')), is_true('Do you like tangy flavors').
cheese(emmental/swiss) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), not(is_true('Do you like softer food texture/mouthfeel')), is_true('Do you like hard food textures'), is_true('Do you like very hard and crumbly food textures'), is_true('Do you like nutty flavors'), is_true('Do you like bitter flavors').
cheese(gruyere) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), not(is_true('Do you like softer food texture/mouthfeel')), is_true('Do you like hard food textures'), is_true('Do you like very hard and crumbly food textures'), is_true('Do you like nutty flavors'), is_true('Do you like sweet flavors').
cheese(parmigiano) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), not(is_true('Do you like softer food texture/mouthfeel')), is_true('Do you like hard food textures'), is_true('Do you like very hard and crumbly food textures'), is_true('Do you like sharp flavors').
cheese(manchego) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), not(is_true('Do you like softer food texture/mouthfeel')), is_true('Do you like hard food textures'), is_true('Do you like very hard and crumbly food textures'), is_true('Do you like sweet flavors').
cheese(asiago) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), not(is_true('Do you like softer food texture/mouthfeel')), is_true('Do you like hard food textures'), not(is_true('Do you like very hard and crumbly food textures')), is_true('Do you like nutty flavors').
cheese(cheddar) :- not(is_true('Do you prefer mild flavors (no. means you like stronger flavors)')), not(is_true('Do you like softer food texture/mouthfeel')), is_true('Do you like hard food textures'), not(is_true('Do you like very hard and crumbly food textures')), is_true('Do you like sharp flavors').

begin :-
retractall(yes(_)),
retractall(no(_)),
(format('Welcome to this ES about cheese! ~nI am going to ask you questions about your food preferences to find the cheese best suited for your palate ~nThe questions are yes. or no. unless promopted otherwise.~n')),
(is_true('Ready') -> format('Super! Lets begin~n'), sleep(1); write('Bye!'), halt(0)),
(is_true('Before we begin with your cheese selection, would you like to know the most popular cheese based on country') -> is_country('Please enter a two character abbreviation for the country (lowercase)', [br, ch, de, es, fr, gb, gr, us]) -> sleep(1),(format('Alright, onto the survey~n'), sleep(1); format('Sorry, but we do not have cheese data on that country')); format('Alright, onto the survey~n'), sleep(1)),
(cheese(A) -> (format('I think that you would love to try ~w cheese. ~n', [A]), (is_true('Did you enjoy your cheese selection') -> format('Wonderful!~n'); format("I am terribly sorry, my designer's palate must be unrefined~n"))); format('I could not find a cheese for your palate...~n')),
write('To try again, just type begin.').
