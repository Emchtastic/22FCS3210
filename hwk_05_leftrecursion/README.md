# Left Recursion Elimination

Eliminate any left-recursive productions (including indirect ones). When you are done, submit this README.md file with your answers on Canvas. 

## Q1

```
X -> XYz | Xw | w
Y -> Yp | q

X -> YX'
Y -> zY'
X' -> wX' | w | e
Y' -> pY' | q | e
```


## Q2

```
S -> aA | Sd
A -> b

S -> aAS'
S' -> dS'
A -> b 

## Q3

```
A -> Bxy | x
B -> CD
C -> A | c
D -> d   



```
