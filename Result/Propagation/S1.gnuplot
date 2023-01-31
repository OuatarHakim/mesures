set terminal png  
set title "s1"
set xlabel 'Days' 
set ylabel '%' 
set output 'S1.png' 
plot 'propagationS1.dat' title 's1' with lines ls 1 