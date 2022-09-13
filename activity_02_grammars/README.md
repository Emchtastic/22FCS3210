# Formal Grammars

For each of the grammars from Q1-3, classify them as either regular or context-free. Then answer yes/no whether each word can be generated from the grammar (if you answer yes, show the sequence of productions to generate the word).  

## Q1

```
G = (S, {x, y}, P, S) 
P = {S → xyS | x}  
```
 
### a)

```
x    
```

### b) 

```
y
``` 

### c) 

```
xy
```

### d)

```
xyxyx
```

## Q2

```
G = ({S,A}, {a, b}, P, S) 
P = {S → AA | a, A → SS | b} 
```

### a) 

```
a
```

### b) 

```
aab
```

### c) 

```
abab
```

### d) 

```
abba
```

## Q3

```
G = ({S1, S2, S3}, {a, b}, P, S1) 
P = {S1→S2ab, S2→S2ab | S3, S3→a}    
```
 
### a) 

```
aab
```
 
### b) 

```
a
```

### c)

```
ababa
```
 
### d) 

```
aabab
```
## Q4

Build a context-free grammar that is able to generate L = {w | w is a word from {x, y, (, )}* such that parentheses are balanced.    