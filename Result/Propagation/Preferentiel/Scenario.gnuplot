set terminal png
set title "Scénario Perferentiel"
set xlabel 'jours'
set ylabel 'infection % '
set output 'Scenario.png'
plot 'propagationS1.dat' title 's1' with lines ls 1  ,\
     'propagationS2.dat' title 's2' with lines ls 2 ,\
      'propagationS3.dat' title 's3' with lines ls 3