longueur([],0).
longueur([_|Q],N) :- longueur(Q,N1), N is N1+1.