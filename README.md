# IP-Gen
IP generation for a mix of IPv4 and IPv6 data points, have extra code within that allows for easy alterations to allow for the creation of just IPv4 or IPv6, without calling mixdata function. 
# Testing
Has been tested up to 1.5 million Ipdata points for each the IPv4 and IPv6 functions, allowed by the use of parallelization and threading. 
only used when not generating the mixdata, at least in the IPv4 and IPv6 portions, due to the fact that it is un-needed, The O() is not terrible without the parrallel processing when calling from that specific function, but, if you would like to generate just one or the other edits to the main class are required. 
