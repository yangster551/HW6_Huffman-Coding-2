import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

//constructor
public class HuffmanConverter{

        // ASCII number of characters
        public static final int NUMBER_OF_CHARACTERS = 256;

        private String contents;
        private HuffmanTree huffmanTree;
        private int count[];
        private String code[];

        // Construct using an input string.
        // Initialize count and code.
        public HuffmanConverter(String input) {
          this.contents = input;
          this.count = new int[NUMBER_OF_CHARACTERS];
          this.code = new String[NUMBER_OF_CHARACTERS];
        }

        // Record how often each character occurs and store in count.
        public void recordFrequencies() {
            // count frequencies
            for (int i = 0; i < contents.length(); i++) {
                char c = contents.charAt(i);
                count[(int)c]++;
            }

            // print frequencies (only ones that appear)
            for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
                if (count[i] > 0) {
                    System.out.println("<" + (char)i + ", " + count[i] + ">");
                }
            }
        }

        // Construct a Huffman tree from count and 
        // store the tree in huffmanTree.
        //______________________________________________________________
        public void frequenciesToTree() {
            int size = 0;

            //count how many unique characters there are
            for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
                if (count[i] > 0) {
                    size++;
                }
            }
            //make array
            HuffmanNode[] arr = new HuffmanNode[size];
            
            int index = 0;

            //fill array
            for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
                if (count[i] > 0) {
                    arr[index] = new HuffmanNode(((char)i) + "", (double)count[i]);
                    index++;
                }
            }

            //build heap
            BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>(arr);

            //build tree
            huffmanTree = HuffmanTree.createFromHeap(heap);
        }

        //______________________________________________________________
        // Construct code from huffmanTree.
        public void treeToCode() {
            for (int i = 0; i < NUMBER_OF_CHARACTERS; i++) {
                code[i] = "";
            }
            treeToCode(huffmanTree.root, "");
           
            System.out.println();
            huffmanTree.printLegend();
            System.out.println();
        }

        //______________________________________________________________
        private void treeToCode(HuffmanNode t, String encoding) {
            if (t == null) {
                return;
            }
            //if leaf
            if (t.left == null && t.right == null) {
                code[(int)t.letter.charAt(0)] = encoding;
                return;
            }

            treeToCode(t.left, encoding + "0");
            treeToCode(t.right, encoding + "1");
        }

        //______________________________________________________________
        // Encode content using code.
        public String encodeMessage() {
            String encoded = "";
            for (int i = 0; i < contents.length(); i++) {
                char c = contents.charAt(i);
                encoded += code[(int)c];
            }
            return encoded;
        }
        
        //______________________________________________________________
        // Decode a Huffman encoding.
        public String decodeMessage(String encodedStr) {
            String decoded = "";
            HuffmanNode current = huffmanTree.root;

            for (int i = 0; i < encodedStr.length(); i++) {
                char bit = encodedStr.charAt(i);
                if (bit == '0') {
                    current = current.left;
                }
                else {
                    current = current.right;
                }

                //if leaf
                if (current.left == null && current.right == null) {
                    decoded += current.letter;
                    current = huffmanTree.root;
                }
            }
            return decoded;
        }

        //______________________________________________________________
        // Read an input file.
        public static String readContents(String filename) {
            String temp = "";
            try {
                File file = new File(filename);
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    temp += sc.nextLine();
                    temp += "\n";
                }
                sc.close();
                return temp;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return "";
        }

        //______________________________________________________________
        public static void main(String args[]) {
                String input = HuffmanConverter.readContents(args[0]);
                HuffmanConverter h = new HuffmanConverter(input);
                h.recordFrequencies();
                // Print a list of characters and frequencies here!
                h.frequenciesToTree();
                h.treeToCode();
                // Print the Huffman encoding here!
                String encoded = h.encodeMessage();
                System.out.println(encoded+"\n");
                System.out.println("Message size in ASCII encoding: "+h.contents.length()*8);
                System.out.println("Message size in Huffman coding: "+encoded.length()+"\n");
                System.out.println(h.decodeMessage(encoded));
        }

}