set terminal png  
set title "Perferenciel"
set xlabel 'k' 
set ylabel 'p(k)' 
set output 'dd_perferenciel_lin.png' 
plot 'dd_perferenciel.dat' title 'Perferenciel' with lines ls 1 