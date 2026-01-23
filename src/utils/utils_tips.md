<!-- TOC -->
* [Arithmetics](#arithmetics)
* [ArrayFunctions](#arrayfunctions)
* [Combinations](#combinations-)
* [Converter](#converter)
* [Diophantine](#diophantine)
* [Divisors](#divisors)
* [Fraction](#fraction)
* [Graph](#graph)
* [Matrix](#matrix)
* [Pandigital](#pandigital)
* [Parser](#parser)
* [PolygonalNumber](#polygonalnumber)
* [Primes](#primes)
<!-- TOC -->

# Arithmetics
An interface to generalise the simple arithmetic operations:
- addition
- negation
- subtraction
- multiplication
- division
- remainder
- gcd
- compare  

As well as storing the values of:
- zero (0)
- one (1)
- ten (10)  

It is used by Matrix and Fraction classes

# ArrayFunctions
Contains quality-of-life array methods. Pretty self-explanatory. I'll be honest, I don't think this one is the best utility class ever

# Combinations  
For each method I have specified the name and use case:
- **factorial** (long, BigInteger) - factorial(n) gives the number of unique ways to order n unique elements.
- **nChooseM** (long, BigInteger) - nChooseM(n, m) gives the number of ways to choose m elements (order doesn't matter) from a set of size n.
- **catalan** (long, BigInteger) - catalan(n) gives the number of correctly matched ways to arrange n pairs of parentheses.

# Converter
Contains two kinds of methods: those which convert between Java types (types of arrays or between arr and List), and those which convert between representations of numbers (to/from digitArray, between bases). Pretty self-explanatory.

# Diophantine
For each method I have specified the name and description or use case:
- **continuedFraction** - continuedFraction(n) gives the array {n0, x0, x1, ..., xn}, where n0 is the integer part of a square root of n, and x0-xn is the repeating cycle of the continued fraction of sqrt(n).
- **nthTermOfContinuedFraction** (long[], BigInteger[]) - nthTermOfContinuedFraction(continuedFraction, n) gives the two-element array {num, den}, where num/den is the fraction when calculating from the n-th step of the continued fraction.
- **pell** (long[], BigInteger[]) - pell(D) gives the fundamental (smallest) (x, y) solution to the equation $x^2 - Dy^2 = 1$. pell(D, C) gives the fundamental (smallest) (x, y) solutions to the equation $x^2 - Dy^2 = C$.
- **root** (int, long) - root(n) gives integer part the square root of n - positive if n was a whole square, negative otherwise.
- **quadratic** - quadratic(a, b, c) gives the two solutions (x1, x2) to the equation $ax^2 + bx + c = 0$.
- **digitSum** (int, long, BigInteger) - digitSum(n) gives the digit sum of n.
- **isPalindromeInBase** (int, long) - isPalindromeInBase(n, b) returns true iff the number n converted to base b is palindromic.
- **gcd** - gcd(a, b) gives the greatest common divisor of a and b.
- **extendedEuclidean** - extendedEuclidean(a, b) gives the minimised coefficients (x, y) such that $ax + by = gcd(a, b)$. For coprime (a, b) this means $ax + by = 1$, or $ax \equiv 1 \pmod{b}$.
- **powMod** - powMod(b, e, m) gives the result x such that $b^e \equiv x \pmod{m}$.
- **modDivide** - modDivide(a, b, c) gives the minimal positive integer x such that $ax \equiv b \pmod{c}$

# Divisors
Self-explanatory. Should make it better, though. 

# Fraction
A quality-of-life class to make operating with fractions easier. Supports types supported by Arithmetic. Self-explanatory.

# Graph
A quality-of-life class to make operating with graphs easier. For each algorithmic method I have specified the name and use case:
- **djikstra** - graph.djikstra(origin, destination) gives the shortest path from origin to destination in terms of edge weights.
- **clique** - graph.clique(s) gives the set (size s) of nodes that form a complete subgraph (or an empty set, if none exist). graph.clique(s, origin) gives a clique of size s that contains the origin node.
- **mst** - graph.mst() gives the minimum-span tree subgraph. That is, a graph with minimal sub of edge-weights, so that all nodes have a path to each other.

# Matrix
A quality-of-life class to make operating with matrices easier. Supports types supported by Arithmetic. Self-explanatory.

# Pandigital
Self-explanatory.

# Parser
Quality-of-life methods for parsing data from input files. Self-explanatory.

# PolygonalNumber
For each method I have specified the name and use case:
- **polygonalNumber** (long, BigInteger) - polygonalNumber(s, n) gives the n-th polygonal number with s sides. For example polygonalNumber(4, 3) gives the third square, 9.
- **isPolygonalNumber** - isPolygonalNumber(s, x) returns true iff there exists n so that polygonalNumber(s, n) = x. isPolygonalNumber(x) gives an array of sizes for which such n exists.

# Primes
For each method I have specified the name and use case:
- **primes** - primes(limit) gives prime numbers up to the limit.
- **compositeSieve** - compositeSieve(limit) gives a limit-sized boolean array where each index is true iff that index number is composite.
- **primeFactors** - primeFactors(n) gives the prime factors of a number, in the form of a (primeFactor, power) array.
- **isPrime** - isPrime(n) returns true iff n is prime.
- **nthPrime** - nthPrime(n) gives the n-th prime.
- **upperBoundForNthPrime** - upperBoundForNthPrime(n) gives the upper bound for the n-th prime.
- **eulersTotient** - eulersTotient(n) gives the number of numbers below n that are relatively prime to n.