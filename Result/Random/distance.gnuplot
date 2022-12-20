set terminal png  
set title "Distance"
set xlabel 'k' 
set ylabel 'p(k)' 
set output 'distance.png' 
plot 'dd_distance_random.dat' title 'Distance' with lines ls 1 