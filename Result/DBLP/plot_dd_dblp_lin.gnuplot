set terminal png  
set title "DBLP lineaire"
set xlabel 'k' 
set ylabel 'p(k)' 
set output 'dd_dblp_lineaire.png' 
plot 'dd_dblp.dat' title 'DBLP lineaire' with lines ls 1 