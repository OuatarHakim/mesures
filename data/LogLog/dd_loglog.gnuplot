set terminal png  
set title "dd_loglog"
set xlabel 'k' 
set ylabel 'p(k)' 
set output 'dd_loglog.png' 
set logscale xy
set yrange [1e-6:1]
# Poisson
lambda = 6.62208890914917
poisson(k) = lambda ** k * exp(-lambda) / gamma(k + 1)
f(x) = lc - gamma * x  
fit f(x) 'dd_loglog.dat' using (log($1)):(log($2)) via lc, gamma 
c = exp(lc) 
power(k) = c *k ** (-gamma) 
plot 'dd_loglog.dat' title 'dd_loglog', poisson(x) title 'Poisson law' , power(x) title 'Power law'