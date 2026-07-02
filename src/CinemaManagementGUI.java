import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Vector;

public class CinemaManagementGUI extends JFrame {

    public CinemaManagementGUI() {
        setTitle("Hệ Thống Quản Lý Rạp Chiếu Phim - Khách Hàng & Báo Cáo");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Sử dụng JTabbedPane để phân chia 2 chức năng thành 2 Tab riêng biệt
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Hệ Thống Gửi Mail", createMailSystemPanel());
        tabbedPane.addTab("Thống Kê & Báo Cáo", createReportingPanel());

        add(tabbedPane);
    }

    // 1. GIAO DIỆN CHỨC NĂNG 1: HỆ THỐNG GỬI MAIL
    private JPanel createMailSystemPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Form nhập liệu (Phía Tây/Trái)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Tạo Tác Vụ Gửi Mail (MailTask)"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1.1. Người nhận
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Email Người Nhận:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        JTextField txtRecipient = new JTextField("customer@example.com", 20);
        formPanel.add(txtRecipient, gbc);

        // 1.2. Mẫu Email (Template)
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Mẫu Email (Template):"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        String[] templates = {"[TEMP-01] Xác nhận đặt vé thành công", "[TEMP-02] Mã giảm giá thành viên", "[TEMP-03] Xác nhận hoàn tiền vé"};
        JComboBox<String> cbTemplate = new JComboBox<>(templates);
        formPanel.add(cbTemplate, gbc);

        // 1.3. Tiêu đề (Subject)
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Tiêu Đề (Subject):"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        JTextField txtSubject = new JTextField("Cảm ơn bạn đã đặt vé tại rạp phim!", 20);
        formPanel.add(txtSubject, gbc);

        // 1.4. Nội dung (Content)
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        formPanel.add(new JLabel("Nội Dung Email:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2;
        JTextArea txtContent = new JTextArea("Mã vé của bạn là: XXXXXX. Lịch chiếu: 19:00.", 5, 20);
        txtContent.setLineWrap(true);
        formPanel.add(new JScrollPane(txtContent), gbc);

        // 1.5. Nút bấm điều khiển
        gbc.gridx = 1; gbc.gridy = 4; gbc.gridwidth = 1;
        JButton btnSend = new JButton("Gửi Trực Tiếp");
        formPanel.add(btnSend, gbc);

        gbc.gridx = 2;
        JButton btnEnqueue = new JButton("Thêm Vào Hàng Đợi");
        formPanel.add(btnEnqueue, gbc);

        mainPanel.add(formPanel, BorderLayout.WEST);

        // Khu vực Log & Lịch sử (Phía Đông/Phải)
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder("Nhật Ký Hệ Thống & Lịch Sử (MailHistory / SystemLog)"));

        JTextArea txtLog = new JTextArea();
        txtLog.setEditable(false);
        txtLog.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtLog.append("[SYSTEM] MailService khởi tạo thành công...\n");
        logPanel.add(new JScrollPane(txtLog), BorderLayout.CENTER);

        mainPanel.add(logPanel, BorderLayout.CENTER);

        // Xử lý sự kiện giả lập cho nút bấm
        btnSend.addActionListener(e -> {
            String email = txtRecipient.getText();
            String subject = txtSubject.getText();
            String time = LocalDateTime.now().toString().substring(11, 19);

            txtLog.append(String.format("[%s] [MailService] Đang xử lý MailTask gửi tới: %s\n", time, email));
            txtLog.append(String.format("[%s] [SUCCESS] MailHistory ID: HIS-%d | Tiêu đề: %s | Trạng thái: SENT\n", time, (int)(Math.random()*1000), subject));
        });

        btnEnqueue.addActionListener(e -> {
            String time = LocalDateTime.now().toString().substring(11, 19);
            txtLog.append(String.format("[%s] [MailQueue] Đã thêm tác vụ vào hàng đợi (Enqueue) thành công.\n", time));
        });

        return mainPanel;
    }

    // 2. GIAO DIỆN CHỨC NĂNG 2: THỐNG KÊ VÀ BÁO CÁO
    private JPanel createReportingPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Bộ lọc báo cáo (ReportFilter) - Phía Bắc/Trên
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Bộ Lọc Báo Cáo (ReportFilter)"));

        filterPanel.add(new JLabel("Loại Báo Cáo:"));
        String[] reportTypes = {"Báo cáo doanh thu vé phim", "Báo cáo bán hàng đồ ăn (Food)", "Thống kê tỷ lệ lấp đầy ghế (Occupancy)"};
        JComboBox<String> cbReportType = new JComboBox<>(reportTypes);
        filterPanel.add(cbReportType);

        filterPanel.add(new JLabel("Từ Ngày:"));
        JTextField txtStartDate = new JTextField("2026-06-01", 10);
        filterPanel.add(txtStartDate);

        filterPanel.add(new JLabel("Đến Ngày:"));
        JTextField txtEndDate = new JTextField("2026-06-30", 10);
        filterPanel.add(txtEndDate);

        JButton btnQuery = new JButton("Truy Vấn Dữ Liệu");
        filterPanel.add(btnQuery);

        JButton btnExport = new JButton("Xuất File Báo Cáo");
        filterPanel.add(btnExport);

        mainPanel.add(filterPanel, BorderLayout.NORTH);

        // Bảng hiển thị dữ liệu (ReportData) - Trung tâm
        JPanel dataPanel = new JPanel(new BorderLayout());
        dataPanel.setBorder(BorderFactory.createTitledBorder("Dữ Liệu Hiển Thị (ReportData)"));

        // Định nghĩa cấu trúc bảng mặc định
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable reportTable = new JTable(tableModel);
        dataPanel.add(new JScrollPane(reportTable), BorderLayout.CENTER);

        mainPanel.add(dataPanel, BorderLayout.CENTER);

        // Giả lập xử lý sự kiện hiển thị ReportData
        btnQuery.addActionListener(e -> {
            int selectedIndex = cbReportType.getSelectedIndex();
            tableModel.setRowCount(0); // Xóa dữ liệu cũ

            if (selectedIndex == 0) { // Doanh thu phim
                tableModel.setColumnIdentifiers(new String[]{"Mã Phim", "Tên Phim", "Số Vé Bán Ra", "Doanh Thu (VND)"});
                tableModel.addRow(new Object[]{"M-101", "Hành Tinh Khỉ", "1,250", "112,500,000"});
                tableModel.addRow(new Object[]{"M-102", "Kẻ Trộm Mặt Trăng 4", "2,100", "189,000,000"});
                tableModel.addRow(new Object[]{"M-103", "Lật Mặt 7", "3,450", "310,500,000"});
            } else if (selectedIndex == 1) { // Đồ ăn
                tableModel.setColumnIdentifiers(new String[]{"Tên Sản Phẩm (Food)", "Đơn Giá", "Số Lượng Bán", "Tổng Doanh Thu"});
                tableModel.addRow(new Object[]{"Bắp Rang Bơ (Ngọt)", "60,000", "850", "51,000,000"});
                tableModel.addRow(new Object[]{"Nước Ngọt Combo", "45,000", "1,100", "49,500,000"});
                tableModel.addRow(new Object[]{"Snack Khoai Tây", "30,000", "400", "12,000,000"});
            } else { // Tỷ lệ lấp đầy
                tableModel.setColumnIdentifiers(new String[]{"Mã Lịch Chiếu", "Phim", "Thời Gian", "Tỷ Lệ Lấp Đầy Ghế"});
                tableModel.addRow(new Object[]{"ST-501", "Lật Mặt 7", "19:00", "94.5%"});
                tableModel.addRow(new Object[]{"ST-502", "Hành Tinh Khỉ", "21:30", "72.0%"});
                tableModel.addRow(new Object[]{"ST-503", "Kẻ Trộm Mặt Trăng 4", "15:00", "85.1%"});
            }
        });

        btnExport.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainPanel,
                    "Đã tạo file báo cáo thành công (ReportFile)!\n" +
                            "Định dạng: Excel (.xlsx)\n" +
                            "Đường dẫn lưu: /exports/report_" + System.currentTimeMillis() + ".xlsx",
                    "Thông báo xuất file",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        return mainPanel;
    }

    // Main Method khởi chạy ứng dụng
    public static void main(String[] args) {
        // Đảm bảo giao diện hiển thị mượt mà trên các luồng hệ thống
        SwingUtilities.invokeLater(() -> {
            CinemaManagementGUI app = new CinemaManagementGUI();
            app.setVisible(true);
        });
    }
}