/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI.Component;

/**
 *
 * @author Nghia0605
 */
import UI.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuTaskBar extends JPanel {
    private ItemTaskBar itemTaskBars[];
    private String[][] info = {
            { "homepage.svg", "Trang chủ" },
            { "Book.svg", "Sách" },
            { "theloai.svg", "Thể loại" },
            { "docgia.svg", "Độc giả" },
            { "author.svg", "Tác giả" },
            { "loan.svg", "Phiếu mượn" },
            { "payment.svg", "Phiếu trả" },
            { "import.svg", "Phiếu nhập" },
            { "supplier.svg", "Nhà cung cấp" },
            { "regulations.svg", "Quy định" },
            { "statistic.svg", "Thống kê" },
            { "logout.svg", "Đăng xuất" }
    };
    private MainFrame mainFrame;

    public MenuTaskBar(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.init();
    }

    public void init() {
        itemTaskBars = new ItemTaskBar[info.length];
        this.setLayout(new GridLayout(info.length, 1, 0, 0));
        this.setPreferredSize(new Dimension(300, 660));
        this.setBackground(null);
        for (int i = 0; i < info.length; i++) {
            itemTaskBars[i] = new ItemTaskBar(info[i][0], info[i][1]);
            this.add(itemTaskBars[i]);
        }

//        for (int i = 0; i < info.length; i++) {
//            int index = i;
//            itemTaskBars[i].addMouseListener(new MouseAdapter() {
//                @Override
//                public void mousePressed(MouseEvent e) {
//                    switch (index) {
//                        case 0:
//                            TrangChuPanel trangchu = new TrangChuPanel();
//                            mainFrame.setRightPanel(trangchu);
//                            break;
//                        case 1:
//                            SachPanel sach = new SachPanel();
//                            mainFrame.setRightPanel(sach);
//                            break;
//                        case 2:
//                            TheLoaiPanel theloai = new TheLoaiPanel();
//                            mainFrame.setRightPanel(theloai);
//                            break;
//                        case 3:
//                            DocGiaPanel docgia = new DocGiaPanel();
//                            mainFrame.setRightPanel(docgia);
//                            break;
//                        case 4:
//                            TacGiaPanel tacgia = new TacGiaPanel();
//                            mainFrame.setRightPanel(tacgia);
//                            break;
//                        case 5:
//                            PhieuMuonPanel phieumuon = new PhieuMuonPanel();
//                            mainFrame.setRightPanel(phieumuon);
//                            break;
//                        case 6:
//                            PhieuTraPanel phieutra = new PhieuTraPanel();
//                            mainFrame.setRightPanel(phieutra);
//                            break;
//                        case 7:
//                            PhieuNhapPanel phieunhap = new PhieuNhapPanel();
//                            mainFrame.setRightPanel(phieunhap);
//                            break;
//                        case 8:
//                            NhaCungCapPanel nhacungcap = new NhaCungCapPanel();
//                            mainFrame.setRightPanel(nhacungcap);
//                            break;
//                        case 9:
//                            QuyDinhPanel quydinh = new QuyDinhPanel();
//                            mainFrame.setRightPanel(quydinh);
//                            break;
//                        case 10:
//                            ThongKePanel thongke = new ThongKePanel();
//                            mainFrame.setRightPanel(thongke);
//                            break;
//                        default:
//                            break;
//                    }
//                }
//            });
//        }
    }

}
