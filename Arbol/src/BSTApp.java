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
    private List<Integer> currentTraversal;
    private int currentTraversalIndex;

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
        frame.setBounds(100, 100, 900, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(60, 63, 65));
        panel.setLayout(new GridLayout(2, 1));
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(new Color(60, 63, 65));
        panel.add(inputPanel);

        JLabel lblEnterValue = new JLabel("Ingrese un valor:");
        lblEnterValue.setForeground(Color.WHITE);
        lblEnterValue.setFont(new Font("Arial", Font.BOLD, 14));
        inputPanel.add(lblEnterValue);

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputPanel.add(textField);
        textField.setColumns(10);

        JButton btnAdd = new JButton("Agregar");
        styleButton(btnAdd);
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
        inputPanel.add(btnAdd);

        JPanel traversalPanel = new JPanel();
        traversalPanel.setBackground(new Color(60, 63, 65));
        panel.add(traversalPanel);

        JButton btnInorder = new JButton("Inorden");
        styleButton(btnInorder);
        btnInorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTraversal(bst.inorder());
            }
        });
        traversalPanel.add(btnInorder);

        JButton btnPreorder = new JButton("Preorden");
        styleButton(btnPreorder);
        btnPreorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTraversal(bst.preorder());
            }
        });
        traversalPanel.add(btnPreorder);

        JButton btnPostorder = new JButton("Postorden");
        styleButton(btnPostorder);
        btnPostorder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showTraversal(bst.postorder());
            }
        });
        traversalPanel.add(btnPostorder);

        treePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bst.root != null) {
                    drawNode(g, bst.root, getWidth() / 2, 30, getWidth() / 4);
                }
            }
        };
        treePanel.setBackground(new Color(255, 255, 255));
        frame.getContentPane().add(treePanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
    }

    private void drawTree() {
        treePanel.repaint();
    }

    private void drawNode(Graphics g, Node node, int x, int y, int dx) {
        if (node != null) {
            Color nodeColor = Color.BLACK;
            if (currentTraversal != null && currentTraversalIndex < currentTraversal.size()) {
                if (currentTraversal.get(currentTraversalIndex).equals(node.val)) {
                    nodeColor = Color.RED;
                    currentTraversalIndex++;
                }
            }
            g.setColor(nodeColor);
            g.fillOval(x - 20, y - 20, 40, 40);
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 14));
            g.drawString(String.valueOf(node.val), x - 10, y + 5);
            g.setColor(Color.BLACK);
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
        currentTraversal = traversal;
        currentTraversalIndex = 0;
        String message = traversal.toString();
        JOptionPane.showMessageDialog(frame, message);
        new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentTraversalIndex < currentTraversal.size()) {
                    drawTree();
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();
    }
}
