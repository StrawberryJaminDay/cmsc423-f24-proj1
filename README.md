# cmsc423-f24-proj1
## CMSC 423 Project 1: Using the command line and parsing input

Takes in a file name (string) of a FASTA file and calculates the following statistics and prints them in JSON format to stdout: \
min_len - The length of the shortest sequence observed for any record \
max_len - The length of the longest sequence observed for any record \
mean_len - The average length of sequences observed for the records in the input file. \
tot_len - The total length of sequences observed for the records in the input file. \
num_records - The total number of input records observed in the input file. \
count_a - The total number of occurrences of the nucleotide A appearing in this file. \
count_c - The total number of occurrences of the nucleotide C appearing in this file. \
count_t - The total number of occurrences of the nucleotide T appearing in this file. \
count_g - The total number of occurrences of the nucleotide G appearing in this file. \
