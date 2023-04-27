accLongeur([],A,A).
accLongeur([_|Q],A,L) :-
    Anew is A+1, accLongeur(Q,Anew,L).

longeur(Liste,Longeur) :-
    accLongeur(Liste,0,Longeur).