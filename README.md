# Groups
by Lincoln Edsall, Benjamine Lampe

"Groups" is a custom collections framework designed from scratch for pedagogical purposes.

A "Group" in plain terms is a datatype that stores a group of elements, ranging from Sets, to Series (Lists), to Maps.
Groups can vary widely in their permitted behvaior and underlying representation.

All implementations are a work in progress, but usable (all implemented functionality varified for accuracy) Groups include:

## Series
Enumerated series of elements.

Uses: Easily access, add, remove, any element like a 'primitive' array

Canon implementations: ArraySeries (wrapped array), LinkedSeries (doubly linked)

## Set
Unordered set of unique elements.

Uses: Quickly check if an element is contained, must be unique


## Map
Unordered set of keys : value pairs. Keys must be unique.

Canon implementations: BSTMap (self balancing binary search tree)

Uses: Matching keys to values

## Dict
Ordered set of key : value pairs. Keys must be unique. 

Uses: Less efficient than Map but can be useful for conveniently keeping track of insertion order

Canon implementations: ArrayDict (layered on ArraySeries)
