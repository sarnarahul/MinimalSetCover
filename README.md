#Calculating Minimal Set Cover

Brief explanation of optimizing techniques used:

The first thing I try to do is map the occurrence of numbers listed in the file. 
This helped me ignore numbers that were single set numbers and if there occurrence is more than 1 and set size is 1, the program will move on. 
Second I tried to restrict construction of candidates. 

I didn’t make the subsets whose set size was zero, occurrence technique explained before and set pruning to check if the solution set found earlier and found right now is of size less.  If any of the conditions is true, the backtracking continues on that set as an option. If false, the candidates are constructed for the next set with Prof. Skiena’s backtracking algorithm used on subsets. 

During process of solution subset’s are mapped to a Boolean array and then checked if they got all the numbers for the given range, if they don’t it just moves and checks the next subset. 

These are some of the optimization I tried and I got around 10 subsets under a minute. 


