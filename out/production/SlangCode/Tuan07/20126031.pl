
/*EX3*/

scalarmult(A, [], []).
scalarmult(A, [H1|T1], [H2|T2]) :-is(H2,*(A,H1)),scalarmult(A, T1, T2).


/*EX4*/

accdot([],[],A,A).
accdot([H1|T1],[H2|T2],A,Result):- is(B,+(A,*(H1,H2))),accdot(T1, T2, B,Result).
dotProduct(T1,T2,Result) :-accdot(T1,T2,0,Result).




/*EX5*/

puissant(_,0,1).
puissant(X,Y,Result):-Y1 is Y - 1,puissant(X,Y1,R1), Result is R1*X.

/*EX6*/
fibonacci(0,0):-!.
fibonacci(1,1):-!.
fibonacci(N,R):- N>1, N1 is N-1, N2 is N - 2,  fibonacci(N1,R1),fibonacci(N2,R2),R is R1+R2.


/*EX7*/

somme_des_impairs([],0).
somme_des_impairs([T|Q],S):- T mod 2 =:= 1, somme_des_impairs(Q, S1), S is S1+T.
somme_des_impairs([T|Q],S):- T mod 2 =:= 0, somme_des_impairs(Q,S).



/*EX8*/
somme_des_pairs([],0).
somme_des_pairs([T|Q],S):- T mod 2 =:= 0, somme_des_pairs(Q, S1), S is S1+T.
somme_des_pairs([T|Q],S):- T mod 2 =:= 1, somme_des_pairs(Q, S).


/*EX9*/
nombre_des_pairs([],[]).
nombre_des_pairs([H|T],L1):-integer(H),(H mod 2 =:=0 -> L1=[H|T1],nombre_des_pairs(T,T1);nombre_des_pairs(T,L1) ).