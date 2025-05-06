
package UI.Panel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class ThongKePanel extends JPanel{
      private JPanel toolBarPanel;
    private JPanel contentPanel;
    private JPanel[] btnPanels;
    private JPanel contentPanels;
    private Color defaultColor = new Color(200, 200, 200);
    private Color selectedColor = new Color(100, 149, 237); 
    private String[] titles = {
            "Hoạt động thư viện",
            "Thống kê theo sách",
            "Thống kê theo độc giả",
            "Thống kê thủ thư"
    };

    public ThongKePanel() {
        setLayout(new BorderLayout());

        toolBarPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        toolBarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnPanels = new JPanel[4];
        contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));

        for (int i = 0; i < 4; i++) {
            JPanel p = new JPanel();
            p.setBackground(defaultColor);
            p.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel label = new JLabel(titles[i], JLabel.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 14));
            label.setForeground(Color.BLACK);
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
                btnPanels[i].setBackground(selectedColor);
            } else {
                btnPanels[i].setBackground(defaultColor);
            }
        }  
        contentPanel.removeAll();
        JPanel selectedPanel = null;
        switch(selectedIndex){ 
            case 0: selectedPanel= new TKHoatDong();break;
            case 1:selectedPanel= new TKSachPanel();break;
             case 2:selectedPanel =new TKDocGiaPanel();break;
//            case 3:selectedPanel= new TKThuThuPanel();break;
            default:selectedPanel= new TKHoatDong();break;
        }
        contentPanel.add(selectedPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
}
