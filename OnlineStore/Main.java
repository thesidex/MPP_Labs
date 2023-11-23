import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

class Main {

    private JFrame mainFrame;
    private JPanel panel1, panel2, panel3;
    private JTabbedPane tabPane;
    private JLabel lbl_Email, lbl_ID, lbl_Name, lbl_ItemName, lbl_Qty, lbl_BillAmt, lblSCAmount;
    private JTextField txtField_Name, txtField_Email, txtField_Qty;
    private JComboBox<String> txtField_ID;
    private JComboBox<String> txtField_Item;
    private JButton savebtn, resetbtn, addbtn, proceedbtn, removebtn;
    private JScrollPane scrollPane;
    private JList<String> itemJList;
    private DefaultListModel<String> itemsList;
    List<User> listUsers;
    List<Item> listItems;

    public static void main(String[] args) {
        Main pro = new Main();
        pro.displayAppGUI();
    }

    Main() {
        //Function calls to initialize all the GUI components and to populate the drop-down boxes.
        initializeAppGUI();
        loadItems();
        displayBill();

        //clears user text boxes
        resetbtn.addActionListener(ae -> {
            txtField_Name.setText("");
            txtField_Email.setText("");
        });

        //Displays the bill total
        txtField_ID.addActionListener(e -> displayBill());

        //INSERTS USER AND SHOPPING CART
        savebtn.addActionListener(ae -> {

            if (!Objects.equals(txtField_Name.getText(), "") && !Objects.equals(txtField_Email.getText(), "")) {
                Connection con;
                PreparedStatement st;
                User user = new User(0, txtField_Name.getText(), txtField_Email.getText());

                try {
                    con = getConnection();

                    //Insert Shopping Cart
                    st = con.prepareStatement("INSERT INTO shoppingcart (totalAmount) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                    st.setDouble(1, 0);
                    st.executeUpdate();
                    ResultSet rs = st.getGeneratedKeys();
                    if (rs.next())
                        user.getCart().setShoppingCartId(rs.getInt(1));

                    //Insert User
                    st = con.prepareStatement("INSERT INTO user (name, email, ShoppingCartID) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                    st.setString(1, user.getName());
                    st.setString(2, user.getEmail());
                    st.setInt(3, user.getCart().getShoppingCartId());

                    st.executeUpdate();
                    JOptionPane.showMessageDialog(panel1, "Data is successfully inserted into database.");

                    rs = st.getGeneratedKeys();
                    if (rs.next())
                        user.SetUserId(rs.getInt(1));

                    txtField_ID.addItem(user.getName());
                    listUsers.add(user);
                    txtField_Name.setText("");
                    txtField_Email.setText("");

                } catch (ClassNotFoundException e) {
                    JOptionPane.showMessageDialog(panel1, "Error in submitting data!");
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //ADD ITEMS TO SHOPPING CART
        addbtn.addActionListener(ae -> {
            Connection con;
            PreparedStatement st;
            int QtyValue = 1;

            try {
                con = getConnection();
                String itemValue = Objects.requireNonNull(txtField_Item.getSelectedItem()).toString().split("-")[0];
                assert itemValue != null;
                if (itemValue.equals("Choose an Item")) {
                    JOptionPane.showMessageDialog(panel2, "Please select an Item", "Item Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String userValue = (String) txtField_ID.getSelectedItem();
                assert userValue != null;
                if (userValue.equals("Choose an User")) {
                    JOptionPane.showMessageDialog(panel2, "Please select an User", "User Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtField_Qty.getText().isEmpty())
                    JOptionPane.showMessageDialog(panel2, "Please enter a Quantity", "Quantity Not Entered", JOptionPane.ERROR_MESSAGE);
                else {
                    try {
                        QtyValue = Integer.parseInt(txtField_Qty.getText());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel2, "Quantity needs to be an integer.", "Quantity Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                Optional<User> selectedUser = listUsers.stream().filter(u -> u.getName().equals(userValue)).findFirst();
                Optional<Item> selectedItem = listItems.stream().filter(i -> i.getName().equals(itemValue.replaceAll("\\s+", ""))).findFirst();

                //is possible to add to cart
                if (selectedItem.isPresent() && selectedUser.isPresent()
                        && QtyValue > 0
                        && selectedItem.get().getStockQuantity() >= QtyValue) {

                    //Adds the item to the sc with the total price * quantity
                    st = con.prepareStatement("INSERT INTO scitem (cart_id, item_id, amount) VALUES (?, ?, ?)");
                    st.setInt(1, selectedUser.get().getCart().getShoppingCartId());
                    st.setInt(2, selectedItem.get().getItemId());
                    st.setDouble(3, QtyValue * selectedItem.get().getPrice());

                    st.executeUpdate();

                    //Rests the quantity added to shopping cart
                    st = con.prepareStatement("update item set stockQuantity = ? where id = ?");
                    st.setInt(1, selectedItem.get().getStockQuantity() - QtyValue);
                    st.setInt(2, selectedItem.get().getItemId());
                    st.executeUpdate();

                    JOptionPane.showMessageDialog(panel2, "Item is successfully added to cart!");

                    txtField_Qty.setText("1");
                    txtField_Item.setSelectedIndex(0);
                    displayBill();

                } else {
                    //not possible
                    JOptionPane.showMessageDialog(panel2, "Required Quantity not available.", "Quantity Not Available", JOptionPane.ERROR_MESSAGE);
                    txtField_Qty.setText("1");
                }
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //Remove item from shopping cart
        removebtn.addActionListener(ae ->
        {
            int selectedIndex = itemJList.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedValue = itemJList.getSelectedValue();
                Item item = new Item(selectedValue.split("-")[0].replaceAll("\\s+", ""));
                String userValue = (String) txtField_ID.getSelectedItem();
                listUsers.stream()
                        .filter(u -> u.getName().equals(userValue))
                        .forEach(u -> u.getCart().removeItem(item));

                // Remove the selected item
                itemsList.remove(selectedIndex);

                Optional<User> selectedUser = listUsers.stream().filter(u -> u.getName().equals(userValue)).findFirst();
                Optional<Item> selectedItem = listItems.stream().filter(u -> u.getName().equals(item.getName())).findFirst();

                try {
                    Connection conn = getConnection();

                    try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM scitem WHERE cart_id = ? AND item_id = ?")) {
                        preparedStatement.setInt(1, selectedUser.map(user -> user.getCart().getShoppingCartId()).orElse(0));
                        preparedStatement.setInt(2, selectedItem.get().getItemId());
                        preparedStatement.executeUpdate();

                    } catch (SQLException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                displayBill();
            }
        });

        //MAKE A PURCHASE
        proceedbtn.addActionListener(ae ->
        {
            /*Connection con;
            PreparedStatement st;
            ResultSet res;

            double bill = 0;        //used to store the bill amount
            try {
                con = getConnection();
                st = con.prepareStatement("SELECT SUM(amount) AS total FROM scitem where cart_id = ? and item_id = ?");
                st.setInt(1, value);
                st.setInt(2, value);
                res = st.executeQuery();
                while (res.next()) {
                    bill = res.getDouble(1);
                }
                txtField_BillAmt.setText(Float.toString(bill));
            } catch (Exception ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        });
    }// end of constructor

    public void displayBill() {
        Connection con;
        PreparedStatement st;
        ResultSet res;
        String userValue = (String) txtField_ID.getSelectedItem();
        Optional<User> selectedUser = listUsers.stream().filter(u -> u.getName().equals(userValue)).findFirst();
        double bill = 0;
        itemsList.clear();
        try {
            con = getConnection();
            st = con.prepareStatement("SELECT SUM(amount) AS total FROM scitem where cart_id = ?");
            st.setInt(1, selectedUser.map(user -> user.getCart().getShoppingCartId()).orElse(0));
            res = st.executeQuery();
            while (res.next()) {
                bill = res.getDouble(1);
            }
            lbl_BillAmt.setText("Bill Amount: $" + bill);
            lblSCAmount.setText("Total: $" + bill);

            double finalBill = bill;
            listUsers.stream()
                    .filter(u -> u.getName().equals(userValue))
                    .forEach(u -> u.getCart().setTotalAmount(finalBill));

            //Get the list of items
            st = con.prepareStatement("SELECT item.name, scitem.amount FROM item INNER JOIN scitem ON item.id = scitem.item_id where cart_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            st.setInt(1, selectedUser.map(user -> user.getCart().getShoppingCartId()).orElse(0));
            res = st.executeQuery();
            //String[] itemList = new String[getRowCount(res)];
            while (res.next()) {
                String itemName = res.getString(1);
                listUsers.stream()
                        .filter(u -> u.getName().equals(userValue))
                        .forEach(u -> u.getCart().addItem(new Item(itemName)));
                itemsList.addElement(String.format("%s - $%s", res.getString(1), res.getDouble(2)));
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //POPULATE txtField_ID and txtField_Item from the USER and ITEM tables.
    public void loadItems() {
        Connection con;
        Statement st1, st2;
        ResultSet rs1, rs2;
        String q1, q2;
        listUsers = new ArrayList<>();
        listItems = new ArrayList<>();

        try {
            con = getConnection();
            st1 = con.createStatement();
            q1 = "select id, name, email, ShoppingCartID from user";
            rs1 = st1.executeQuery(q1);
            while (rs1.next()) {
                User user = new User(rs1.getInt(1), rs1.getString(2), rs1.getString(3));
                user.getCart().setShoppingCartId(rs1.getInt(4));
                listUsers.add(user);
                txtField_ID.addItem(user.getName());
            }

            st2 = con.createStatement();
            q2 = "select id, name, price, stockQuantity from item";
            rs2 = st2.executeQuery(q2);
            while (rs2.next()) {
                Item item = new Item(rs2.getInt(1), rs2.getString(2), rs2.getDouble(3), rs2.getInt(4));
                if (item.getStockQuantity() > 0) {
                    listItems.add(item);
                    txtField_Item.addItem(String.format("%s - $%s", item.getName(), item.getPrice()));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //TO INITIALIZE APP GUI
    public void initializeAppGUI() {
        //JFrame
        mainFrame = new JFrame("Online Store System");
        tabPane = new JTabbedPane();

        panel1 = new JPanel(new GridLayout(3, 2, 40, 40));
        panel1.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        panel2 = new JPanel(new GridLayout(4, 2, 20, 20));
        panel2.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        panel3 = new JPanel(new GridLayout(2, 2, 60, 60));
        panel3.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        tabPane.addTab("Browse", panel2);
        tabPane.addTab("Shopping Cart", panel3);
        tabPane.addTab("New User", panel1);

        lbl_Name = new JLabel("User Name: ");
        lbl_Name.setFont(new Font("Arial", Font.BOLD, 16));
        lbl_Email = new JLabel("User Email: ");
        lbl_Email.setFont(new Font("Arial", Font.BOLD, 16));

        txtField_Name = new JTextField(15);
        txtField_Name.setFont(new Font("Arial", Font.BOLD, 16));
        txtField_Email = new JTextField(15);
        txtField_Email.setFont(new Font("Arial", Font.BOLD, 16));

        savebtn = new JButton("Add");
        savebtn.setFont(new Font("Arial", Font.BOLD, 16));
        resetbtn = new JButton("Reset");
        resetbtn.setFont(new Font("Arial", Font.BOLD, 16));

        lbl_ID = new JLabel("User: ");
        lbl_ID.setFont(new Font("Arial", Font.BOLD, 16));
        txtField_ID = new JComboBox<>();
        txtField_ID.addItem("Choose an User");
        txtField_ID.setSelectedIndex(0);
        txtField_ID.setFont(new Font("Arial", Font.BOLD, 16));

        lbl_ItemName = new JLabel("Item Name: ");
        lbl_ItemName.setFont(new Font("Arial", Font.BOLD, 16));
        txtField_Item = new JComboBox<>();
        txtField_Item.addItem("Choose an Item");
        txtField_Item.setSelectedItem(0);
        txtField_Item.setFont(new Font("Arial", Font.BOLD, 16));
        lbl_Qty = new JLabel("Quantity: ");
        lbl_Qty.setFont(new Font("Arial", Font.BOLD, 16));
        txtField_Qty = new JTextField("1", 10);
        txtField_Qty.setFont(new Font("Arial", Font.BOLD, 16));
        addbtn = new JButton("Add to Cart");
        addbtn.setFont(new Font("Arial", Font.BOLD, 16));
        lblSCAmount = new JLabel("Total: $0");
        lblSCAmount.setFont(new Font("Arial", Font.BOLD, 16));

        lbl_BillAmt = new JLabel("Bill Amount: $0");
        lbl_BillAmt.setFont(new Font("Arial", Font.BOLD, 16));

        itemsList = new DefaultListModel<>();
        itemJList = new JList(itemsList);
        itemJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        itemJList.setFont(new Font("Arial", Font.BOLD, 16));
        scrollPane = new JScrollPane(itemJList);
        scrollPane.setFont(new Font("Arial", Font.BOLD, 16));

        proceedbtn = new JButton("Check out");
        proceedbtn.setFont(new Font("Arial", Font.BOLD, 16));

        removebtn = new JButton("Remove Item");
        removebtn.setFont(new Font("Arial", Font.BOLD, 16));
    }

    //TO DISPLAY ON APP GUI
    void displayAppGUI() {

        //panel1.setLayout(new FlowLayout());
        panel1.add(lbl_Name);
        panel1.add(txtField_Name);
        panel1.add(lbl_Email);
        panel1.add(txtField_Email);
        panel1.add(savebtn);
        panel1.add(resetbtn);

        //panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.add(lbl_ID);
        panel2.add(txtField_ID);
        panel2.add(lbl_ItemName);
        panel2.add(txtField_Item);
        panel2.add(lbl_Qty);
        panel2.add(txtField_Qty);
        panel2.add(addbtn);
        panel2.add(lblSCAmount);

        panel3.add(lbl_BillAmt);
        panel3.add(scrollPane);
        panel3.add(removebtn);
        panel3.add(proceedbtn);

        mainFrame.getContentPane().add(tabPane);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setSize(700, 400);
        mainFrame.setPreferredSize(new Dimension(700, 400));
        mainFrame.setMinimumSize(new Dimension(700, 400));
        mainFrame.setLocationRelativeTo(null);
    }

    //TO GET DATABASE CONNECTION
    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn;
        Class.forName("com.mysql.cj.jdbc.Driver");
        String jdbcUrl = "jdbc:mysql://localhost:3306/store";
        String userName = "root";
        String password = "puravida";
        conn = DriverManager.getConnection(jdbcUrl, userName, password);
        System.out.println("Connected!");
        return conn;
    }

    //TO INSERT THREE ITEMS IN THE ITEM TABLE
    private void insertItemInItemTable() {
        String query;
        Connection con;
        Statement stmt;

        try {
            con = getConnection();
            stmt = con.createStatement();
            query = "insert into item values('Shirt',9.99,'Orange Shirt',25)";
            stmt.executeUpdate(query);

            query = "insert into item values('Tennis',70.25,'Nike shoes',10)";
            stmt.executeUpdate(query);

            query = "insert into item values('Socks',5.25,'multicolor',50)";
            stmt.executeUpdate(query);

            query = "insert into item values('Pants',22.50,'navy blue',8)";
            stmt.executeUpdate(query);

            query = "insert into item values('Laptop',400.50,'mac',3)";
            stmt.executeUpdate(query);

            query = "insert into item values('Hat',10.99,'brown old style',15)";
            stmt.executeUpdate(query);

        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //DELETES ALL ITEMS ON A TABLE
    private void deleteAllRecordsFromTable(String tableName) throws ClassNotFoundException, SQLException {
        String query;
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        query = "DELETE FROM " + tableName;
        stmt.executeUpdate(query);
    }

    // Method to get the row count from a ResultSet
    private static int getRowCount(ResultSet resultSet) throws SQLException {
        int rowCount = 0;
        if (resultSet != null) {
            resultSet.last();
            rowCount = resultSet.getRow();
            resultSet.beforeFirst();
        }
        return rowCount;
    }
}
