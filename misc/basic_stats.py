#!/usr/bin/python3

import math
import sys
from functools import reduce

def compute_median(sorted_numbers, n):
    if (1 == (n % 2)):
        # n is odd
        median = sorted_numbers[n//2]
    else:
        # n is even
        first_half = sorted_numbers[n//2 - 1]
        second_half = sorted_numbers[n//2]
        median = (first_half+second_half)/2.0
    return median

def standard_deviation(numbers, mean, n):
    square_diffs = [(x-mean)*(x-mean) for x in numbers]
    sum_of_square_diffs = reduce(lambda x, y: x+y, square_diffs)
    square_stddev = (1.0*sum_of_square_diffs)/n
    stddev = math.sqrt(square_stddev)
    return stddev

def basic_stats(numbers):
    numbers.sort()
    n = len(numbers)
    min = numbers[0]
    max = numbers[n-1]
    sum = reduce(lambda x, y: x+y, numbers)
    mean = (1.0*sum)/n
    median = compute_median(numbers, n)
    stddev = standard_deviation(numbers, mean, n)
    return { 'n': n,
             'min': min,
             'max': max, 
             'sum': sum,
             'mean': mean,
             'median': median,
             'stddev': stddev
             }

def print_stats(stats):
    print("sum is %s" % stats['sum'])
    print("count is %s" % stats['n'])
    print("mean is %s" % stats['mean'])
    print("stddev is %s" % stats['stddev'])
    print("min is %s" % stats['min'])
    print("max is %s" % stats['max'])
    print("median is %s" % stats['median'])

def main(argv=None):
    if argv is None:
        argv = sys.argv

    for filename in argv[1:]:
        f = open(filename, 'r')
        number_strings = f.readlines()
        numbers = list(map(float, number_strings))
        stats = basic_stats(numbers)
        print_stats(stats)

if __name__ == '__main__':
    sys.exit(main())

