
package UI.Panel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;
public class ThongKePanel extends JPanel{
      private JPanel toolBarPanel;
    private JPanel contentPanel;
    private JPanel[] btnPanels;
    private JPanel contentPanels;
    private Color defaultColor = new Color(255, 255, 255);
    private Color selectedColor = new Color(100, 149, 237); 
    private String[] titles = {
            "Hoạt động thư viện",
            "Thống kê theo sách",
            "Thống kê theo độc giả",
            "Thống kê thủ thư"
    };

    public ThongKePanel() {
        setLayout(new BorderLayout());
        setBackground(Color.white);
        toolBarPanel = new JPanel(new FlowLayout(0,10,22));
//        toolBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        toolBarPanel.setBackground(defaultColor);
        toolBarPanel.setPreferredSize(new Dimension(900,68));

        btnPanels = new JPanel[4];
        contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
//        contentPanel.setBackground(Color.white);
        for (int i = 0; i < 4; i++) {
            JPanel p = new JPanel();
            p.setBackground(defaultColor);
            p.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
            p.setCursor(new Cursor(Cursor.HAND_CURSOR));
            JLabel label = new JLabel(titles[i], JLabel.CENTER);
            label.setFont(new Font("Segoe UI", Font.BOLD, 16));
            label.setForeground(Color.BLACK);  
//            label.setBackground(Color.white);
            p.add(label);

            int index = i;
            p.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    setSelectedPanel(index);
                }
            });

            btnPanels[i] = p;
            toolBarPanel.add(p);
        }
        add(toolBarPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setSelectedPanel(0);
    }
    
    private void setSelectedPanel(int selectedIndex) {
        for (int i = 0; i < btnPanels.length; i++) {
            if (i == selectedIndex) {
                btnPanels[i].setBackground(Color.white);
                btnPanels[i].setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, selectedColor));
            } else {
                btnPanels[i].setBackground(defaultColor);
                btnPanels[i].setBorder(null);
            }
        }  
        contentPanel.removeAll();
        JPanel selectedPanel = null;
        switch(selectedIndex){ 

            case 0: selectedPanel= new TKHoatDong();break;
            case 1:selectedPanel= new TKSachPanel();break;
            case 2:selectedPanel =new TKDocGiaPanel();break;
            case 3:selectedPanel= new TKThuThuPanel();break;
            default:selectedPanel= new TKHoatDong();break;

        }
        contentPanel.add(selectedPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
}
