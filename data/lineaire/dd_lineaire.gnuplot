set terminal png  
set title "dd_lineaire"
set xlabel 'k' 
set ylabel 'p(k)' 
set output 'dd_lineaire.png' 
plot 'dd_lineaire.dat' title 'dd_lineaire' with lines ls 1 