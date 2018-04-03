package hmeng.lc.common;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode {
    public int label;
    public List<UndirectedGraphNode> neighbors = new ArrayList<>();
    public UndirectedGraphNode(int label) {
        this.label = label;
    }
}
