public class HuffmanTree{
    HuffmanNode root;
    // public HuffmanTree(HuffmanNode huff)
    // public void printLegend()
    // public static BinaryHeap legendToHeap(String legend)
    // public static HuffmanTree createFromHeap(BinaryHeap b)
    // public static void main(String[] args)

    //constructor
    public HuffmanTree(HuffmanNode huff) {
        this.root = huff;
    }

    //public method
    public void printLegend() {
        printLegend(root, "");
    }

    //recursive helper
    private void printLegend(HuffmanNode t, String s) {
        if (t == null){
            return;
        }
        //if NOT a leaf
        if (t.letter.length() > 1) {
            printLegend(t.left, s + "0");
            printLegend(t.right, s + "1");
        } 
        else {
            //leaf node
            System.out.println(t.letter + "=" + s);
        }
    }

    //convert legend string into heap
    public static BinaryHeap<HuffmanNode> legendToHeap(String legend) {
        String[] parts = legend.split(" ");
        HuffmanNode[] nodes = new HuffmanNode[parts.length / 2];
        int index = 0;

        for (int i = 0; i < parts.length; i += 2) {
            String letter = parts[i];
            Double freq = Double.parseDouble(parts[i + 1]);
            nodes[index++] = new HuffmanNode(letter, freq);
        }
        return new BinaryHeap<>(nodes);
    }

    //build Huffman Tree from heap
    public static HuffmanTree createFromHeap(BinaryHeap<HuffmanNode> b) {
        while (b.getSize() > 1) {
            HuffmanNode left = b.deleteMin();
            HuffmanNode right = b.deleteMin();
            HuffmanNode merged = new HuffmanNode(left, right);
            b.insert(merged);
        }
        return new HuffmanTree(b.deleteMin());
    }

    //main method
    public static void main(String[] args) {
        String legend = "A 20 E 24 G 3 H 4 I 17 L 6 N 5 O 10 S 8 V 1 W 2";
        BinaryHeap<HuffmanNode> heap = legendToHeap(legend);
        System.out.println("Initial Heap:");
        heap.printHeap();
        HuffmanTree tree = createFromHeap(heap);
        System.out.println("\nHuffman Codes:");
        tree.printLegend();
    }
}