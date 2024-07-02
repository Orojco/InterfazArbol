import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BSTApp {
    private BST bst = new BST();
    private JFrame frame;
    private JTextField textField;
    private JPanel treePanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                BSTApp window = new BSTApp();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public BSTApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblEnterValue = new JLabel("Ingrese un valor:");
        panel.add(lblEnterValue);

        textField = new JTextField();
        panel.add(textField);
        textField.setColumns(10);

        JButton btnAdd = new JButton("Agregar");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(textField.getText());
                    bst.insert(value);
                    textField.setText("");
                    drawTree();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, ingrese un número válido.");
                }
            }
        });
        panel.add(btnAdd);

        JButton btnInorder = new JButton("Inorden");
        btnInorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTraversal(bst.inorder());
            }
        });
        panel.add(btnInorder);

        JButton btnPreorder = new JButton("Preorden");
        btnPreorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTraversal(bst.preorder());
            }
        });
        panel.add(btnPreorder);

        JButton btnPostorder = new JButton("Postorden");
        btnPostorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTraversal(bst.postorder());
            }
        });
        panel.add(btnPostorder);

        treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bst.root != null) {
                    drawNode(g, bst.root, getWidth() / 2, 30, getWidth() / 4);
                }
            }
        };
        frame.getContentPane().add(treePanel, BorderLayout.CENTER);
    }

    private void drawTree() {
        treePanel.repaint();
    }

    private void drawNode(Graphics g, Node node, int x, int y, int dx) {
        if (node != null) {
            g.drawOval(x - 15, y - 15, 30, 30);
            g.drawString(String.valueOf(node.val), x - 5, y + 5);
            if (node.left != null) {
                g.drawLine(x, y, x - dx, y + 50);
                drawNode(g, node.left, x - dx, y + 50, dx / 2);
            }
            if (node.right != null) {
                g.drawLine(x, y, x + dx, y + 50);
                drawNode(g, node.right, x + dx, y + 50, dx / 2);
            }
        }
    }

    private void showTraversal(List<Integer> traversal) {
        String message = traversal.toString();
        JOptionPane.showMessageDialog(frame, message);
    }
}
