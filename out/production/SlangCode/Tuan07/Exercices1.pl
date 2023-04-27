/*sans accumulateur*/


/*avec accumulateur*/

accMin([],A,A).
accMin([T|Q],A,M):- T=<A, accMin(A,T,M).
accMin([T|Q],A,M):- T>A, accMin(Q,A,M).
min([T|Q],M):-accMin(Q,T,M).


/*sans accumulateur*/
minimum([X],X).
minimum([X,Y|L], X) :-minimum([Y|L],M),X=<M.
minimum([X,Y|L], M) :-minimum([Y|L],M),X>M.

maxList([T],T).
maxList([T,K|Q],M) :- T >= K, maxList([T|Q],M).
maxList([T,K|Q],M) :- T < K, maxList([K|Q],M).