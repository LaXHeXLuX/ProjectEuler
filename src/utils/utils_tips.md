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
- **factorial** (long, BigInteger) - $\text{factorial}(n)$ gives the number of unique ways to order $n$ unique elements.
- **nChooseM** (long, BigInteger) - $\text{nChooseM}(n, m)$ gives the number of ways to choose $m$ elements (order doesn't matter) from a set of size $n$.
- **catalan** (long, BigInteger) - $\text{catalan}(n)$ gives the number of correctly matched ways to arrange $n$ pairs of parentheses.

# Converter
Contains two kinds of methods: those which convert between Java types (types of arrays or between arr and List), and those which convert between representations of numbers (to/from digitArray, between bases). Pretty self-explanatory.

# Diophantine
For each method I have specified the name and description or use case:
- **continuedFraction** - $\text{continuedFraction}(n)$ gives the array $[n_0, x_0, x_1, ..., x_n]$, where $n_0$ is the integer part of a square root of $n$, and $x_0, ..., x_n$ is the repeating cycle of the continued fraction of $\sqrt{n}$.
- **nthTermOfContinuedFraction** (long[], BigInteger[]) - $\text{nthTermOfContinuedFraction}(\text{continuedFraction}, n)$ gives the two-element array $[num, den]$, where $\frac{num}{den}$ is the fraction when calculating from the $n$-th step of the continued fraction.
- **pell** (long[], BigInteger[]) - $\text{pell}(D)$ gives the fundamental (smallest) $(x, y)$ solution to the equation $x^2 - Dy^2 = 1$. pell($D$, $C$) gives the fundamental (smallest) $(x, y)$ solutions to the equation $x^2 - Dy^2 = C$.
- **root** (int, long) - $\text{root}(n)$ gives integer part of the square root of $n$ - negative iff $n$ was not a whole square.
- **digitSum** (int, long, BigInteger) - $\text{digitSum}(n)$ gives the digit sum of $n$.
- **isPalindromeInBase** (int, long) - $\text{isPalindromeInBase}(n, b)$ returns true iff the number $n$ converted to base $b$ is palindromic.
- **isPalindrome** (int, long) - returns $\text{isPalindromeInBase}(n, 10)$.
- **gcd** (int, long) - $\text{gcd}(a, b)$ gives the greatest common divisor of $a$ and $b$.
- **extendedEuclidean** (int, long) - $\text{extendedEuclidean}(a, b)$ gives the [Bézout coefficients](https://en.wikipedia.org/wiki/B%C3%A9zout%27s_identity) $(x, y)$ such that $ax + by = gcd(a, b)$. For coprime $(a, b)$ this means $ax + by = 1$, or $ax \equiv 1 \pmod{b}$.
- **pow** - $\text{pow}(b, e)$ gives the result $b^e$.
- **powMod** - $\text{powMod}(b, e, m)$ gives the result $x$ such that $b^e \equiv x \pmod{m}$.
- **tetrateMod** - $\text{tetrateMod}(b, e, m)$ gives the result $x$ such that $b↑↑e \equiv x \pmod{m}$. Requirement: $gcd(b, m) = 1$
- **modDivide** - $\text{modDivide}(a, b, c)$ gives the minimal positive integer $x$ such that $ax \equiv b \pmod{c}$.
- **ord** - $\text{ord}(a, n)$ gives the minimal positive integer $k$ such that $a^k \equiv 1 \pmod{n}$.
- **crt** - $\text{crt}(n_1, a_1, n_2, a_2, ...)$ gives the minimal positive integer $x$ such that $x \equiv a_i \pmod{n_i}$ for any $n_i$.

# Divisors
Self-explanatory. 

# Fraction
A quality-of-life class to make operating with fractions easier. Supports types supported by Arithmetic. Self-explanatory.

# Graph
A quality-of-life class to make operating with graphs easier. For each algorithmic method I have specified the name and use case:
- **djikstra** - $\text{graph.djikstra}(O, D)$ gives the shortest path from origin $O$ to destination $D$ in terms of edge weights.
- **clique** - $\text{graph.clique}(s)$ gives the set (size $s$) of nodes that form a complete subgraph (or an empty set, if none exist). $\text{graph.clique}(s, O)$ gives a clique of size $s$ that contains the origin $O$.
- **mst** - $\text{graph.mst}()$ gives the minimum-span tree subgraph. That is, a graph with minimal sub of edge-weights, so that all nodes have a path to each other.

# Matrix
A quality-of-life class to make operating with matrices easier. Supports types supported by Arithmetic. Self-explanatory.

# Pandigital
Self-explanatory.

# Parser
Quality-of-life methods for parsing data from input files. Self-explanatory.

# PolygonalNumber
For each method I have specified the name and use case:
- **polygonalNumber** (long, BigInteger) - $\text{polygonalNumber}(s, n)$ gives the $n$-th polygonal number with $s$ sides. For example polygonalNumber($4$, $3$) gives the third square, $9$.
- **isPolygonalNumber** - $\text{isPolygonalNumber}(s, x)$ returns true iff there exists $n$ so that polygonalNumber($s$, $n$) = $x$. isPolygonalNumber($x$) gives an array of sizes for which such $n$ exists.

# Primes
For each method I have specified the name and use case:
- **primes** - $\text{primes}(L)$ gives prime numbers up to the limit $L$.
- **sieve** - $\text{sieve}(L)$ gives an $L$-sized boolean array where each index is true iff that index number is prime.
- **compositeSieve** - like $\text{sieve}(L)$, but only odd elements (mapping: $n = 2i+1$), index is true iff that index number is not prime.
- **primeFactors** - $\text{primeFactors}(n)$ gives the prime factors of a number, in the form of a (primeFactor, power) array.
- **primeFactorSieve** - $\text{primeFactorSieve}(L)$ gives an $L$-sized array of prime factors - the prime factors of each index.
- **maxPrimorial** - $\text{maxPrimorial}(n)$ gives the first $i$ for which the $i$-th primorial is bigger or equal to $n$.
- **isPrime** - $\text{isPrime}(n)$ returns true iff $n$ is prime.
- **nthPrime** - $\text{nthPrime}(n)$ gives the $n$-th prime.
- **upperBoundForNthPrime** - $\text{upperBoundForNthPrime}(n)$ gives the upper bound for the $n$-th prime.
- **eulersTotient** - $\text{eulersTotient}(n)$ gives the number of numbers below $n$ that are relatively prime to $n$.