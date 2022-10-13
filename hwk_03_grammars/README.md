# Formal Grammars

For each of the grammars from Q1-3, classify them as either regular or context-free. Then answer yes/no whether each word can be generated from the grammar. If you answer yes, show the sequence of productions to generate the word.  When you are done, submit this README.md file with your answers on Canvas. 

## Q1

```
G = (V, T, P, S) 
V = {S, NP, NOM, VP, Det, Noun, Verb, Aux}
T = {that, this, a, the, man, book, flight, meal, include, read, does}
P = {
    S → NP VP 
    S → Aux NP VP 
    S → VP 
    NP → Det NOM 
    NOM → Noun
    NOM → Noun NOM
    VP → Verb
    VP → Verb NP
    Det → that | this | a | the
    Noun → book | flight | meal | man
    Verb → book | include | read
    Aux → does
} 

Context Free Grammar
```

 
### a)

```
does a meal include a book 

YES
S -> Aux NP VP -> does NP VP -> does Det NOM VP -> does a NOM VP -> does a Noun VP -> does a meal VP -> does a meal Verb NP -> 
does a meal include NP -> does a meal include Det NOM -> does a meal include a NOM -> does a meal include a Noun
-> does a meal include a book
```

### b) 

```
this flight book a meal

YES
S -> NP VP -> Det NOM VP -> this NOM VP -> this Noun VP -> this flight VP
-> this flight Verb NP -> this flight book NP -> this flight book Det NOM -> this flight book a NOM
-> this flight book a Noun -> this flight book a meal
``` 

### c) 

```
a man read a book

YES
S -> NP VP -> Det NOM VP -> a NOM VP -> a Noun VP -> a man VP -> a man Verb NP -> a man read NP -> a man read Det NOM
-> a man read a NOM -> a man read a Noun -> a man read a book
```

## Q2

```
G = ({S, A, B}, {a, b}, P, S) 
P = {S → aA, A → bB | ε, B → aA}  

Right linear = Regular grammar  
```

### a) 

```
a

YES
S -> aA -> a
```

### b) 

```
aba

YES
S -> aA -> abB -> abaA -> aba
```

### c) 

```
bb

NO - always needs an a in the leftmost position
```

### d) 

```
abb

NO - b followed by a or ε
```

## Q3

```
G = ({S, A}, {0, 1}, P, S) 
P = {S → A0, A→A01|ε}    

Left Linear grammar = Regular Grammar       
```
 
### a) 

```
00

NO
```
 
### b) 

```
ε

NO
```

### c)

```
010

YES
S -> A0 -> A010 -> 010
```
 
### d) 

```
0101010

YES
S -> A0 -> A010 -> A01010 -> A0101010 -> 0101010
```
 