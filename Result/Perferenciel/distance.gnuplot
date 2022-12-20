set terminal png  
set title "distance_perferenciel"
set xlabel 'k' 
set ylabel 'p(k)' 
set output 'distance_perferenciel.png' 
plot 'dd_distance_perferenciel.dat' title 'distance_perferenciel' with lines ls 1 