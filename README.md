# Huffman Coding Implementation in Java

This project implements Huffman encoding and decoding in Java. It reads text from a file, counts character frequencies, builds a Huffman tree, generates binary codes for each character, encodes the original message into a Huffman bit string, and then decodes it back to verify the result.

## Features
- Records character frequencies, including spaces and punctuation
- Builds a Huffman tree using a binary heap
- Generates Huffman codes recursively from the tree
- Encodes text into a Huffman bit string
- Decodes the bit string back into the original text
- Compares message size in ASCII versus Huffman encoding

## Concepts Used
- Binary trees
- Heaps
- Recursion
- Character frequency analysis
- Encoding and decoding algorithms

## Files
- `HuffmanConverter.java` – main logic for frequency counting, encoding, and decoding
- `HuffmanTree.java` – builds the Huffman tree and prints the code legend
- `HuffmanNode.java` – represents nodes in the Huffman tree
- `BinaryHeap.java` – heap used to construct the Huffman tree
- `UnderflowException.java` – exception handling for heap operations

## How to Run
Compile the Java files:
```bash
javac *.java

Run the program with a text file:
java HuffmanConverter love_poem_58.txt
