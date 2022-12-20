set terminal png  
set title "Degree distribution"
set xlabel 'k' 
set ylabel 'p(k)' 
set output 'dd_random_log.png' 
set logscale xy  
set yrang [1e-6:1] 
lambda = 6.62208890914917 
poisson(k) = lambda ** k * exp(-lambda) /gamma(k +1) 
f(x) = lc - gamma * x 
 fit f(x) 'dd_random.dat' using (log($1)):(log($2)) via lc , gamma
 c = exp(lc) 
power(k) = c * k ** (-gamma) 
plot 'dd_random.dat' title 'Random' , poisson(x) title 'Poisson law' , power(x) title 'Power law' 