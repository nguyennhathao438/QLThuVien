USE QLQuanAn
DROP DATABASE QLThuVien
CREATE DATABASE QLThuVien
USE QLThuVien
CREATE TABLE TACGIA (
    maTacGia VARCHAR(10) PRIMARY KEY,
    tenTacGia NVARCHAR(255),
    namSinh INT,
    soDienThoai NVARCHAR(15),
    trangThai INT
);

CREATE TABLE THELOAI (
    maTheLoai VARCHAR(10) PRIMARY KEY,
    theLoai NVARCHAR(255),
    trangThai INT
);

CREATE TABLE SACH (
    maSach VARCHAR(10) PRIMARY KEY,
    tenSach NVARCHAR(255),
    namXuatBan INT,
    soLuong INT,
    donGia DECIMAL(18,2),
    trangThai INT,
    maTacGia VARCHAR(10),
    maTheLoai VARCHAR(10),
    FOREIGN KEY (maTacGia) REFERENCES TACGIA(maTacGia),
    FOREIGN KEY (maTheLoai) REFERENCES THELOAI(maTheLoai)
);

CREATE TABLE THUTHU (
    maThuThu VARCHAR(10) PRIMARY KEY,
    tenThuThu NVARCHAR(255),
    taiKhoan NVARCHAR(50),
    matKhau NVARCHAR(50),
    soDienThoai NVARCHAR(15),
    trangThai INT
);

CREATE TABLE LOAIDOCGIA (
    maLoaiDG VARCHAR(10) PRIMARY KEY,
    tenLoaiDG NVARCHAR(255),
    gioiHanMuon INT,
    moTa NVARCHAR(255),
    trangThai INT
);

CREATE TABLE DOCGIA (
    maDocGia VARCHAR(10) PRIMARY KEY,
    tenDocGia NVARCHAR(255),
    soDienThoai NVARCHAR(15),
    diaChi NVARCHAR(255),
    maLoaiDG VARCHAR(10),
    FOREIGN KEY (maLoaiDG) REFERENCES LOAIDOCGIA(maLoaiDG),
    trangThai INT
);

CREATE TABLE PHIEUMUON (
    maPMuon VARCHAR(10) PRIMARY KEY,
    ngayMuon DATE,
    ngayTra DATE,
    maDocGia VARCHAR(10),
    maThuThu VARCHAR(10),
    trangThai INT,
    FOREIGN KEY (maDocGia) REFERENCES DOCGIA(maDocGia),
    FOREIGN KEY (maThuThu) REFERENCES THUTHU(maThuThu)
);

CREATE TABLE CTPHIEUMUON (
    maPhieuMuon VARCHAR(10),
    maSach VARCHAR(10),
    soLuongChuaTra INT,
    soLuong INT,
    PRIMARY KEY (maPhieuMuon, maSach),
    FOREIGN KEY (maPhieuMuon) REFERENCES PHIEUMUON(maPMuon),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);

CREATE TABLE PHUTHU (
    maPhuThu VARCHAR(10) PRIMARY KEY,
    tienPhat DECIMAL(18,2),
    trangThai INT
);

CREATE TABLE PHIEUTRA (
    maPTra VARCHAR(10) PRIMARY KEY,
    ngayThucTra DATE,
    maPMuon VARCHAR(10),
    maThuThu VARCHAR(10),
    maPhuThu VARCHAR(10),
    trangThai INT,
    FOREIGN KEY (maPMuon) REFERENCES PHIEUMUON(maPMuon),
    FOREIGN KEY (maThuThu) REFERENCES THUTHU(maThuThu),
    FOREIGN KEY (maPhuThu) REFERENCES PHUTHU(maPhuThu)
);
CREATE TABLE CTPHIEUTRA (
    maPhieuTra VARCHAR(10),
    maSach VARCHAR(10),
    soLuong INT,
    PRIMARY KEY (maPhieuTra, maSach),
    FOREIGN KEY (maPhieuTra) REFERENCES PHIEUTRA(maPTra),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);

CREATE TABLE QUYDINH (
    maQuyDinh VARCHAR(10) PRIMARY KEY,
    noiDung NVARCHAR(255),
    soTien DECIMAL(18,2),
    trangThai INT
);

CREATE TABLE CTPHAT (
    maPhuThu VARCHAR(10),
    maQuyDinh VARCHAR(10),
    PRIMARY KEY (maPhuThu, maQuyDinh),
    FOREIGN KEY (maPhuThu) REFERENCES PHUTHU(maPhuThu),
    FOREIGN KEY (maQuyDinh) REFERENCES QUYDINH(maQuyDinh)
);

CREATE TABLE NHACUNGCAP (
    maNCC VARCHAR(10) PRIMARY KEY,
    tenNCC NVARCHAR(255),
    soDienThoai NVARCHAR(15),
    trangThai INT
);

CREATE TABLE PHIEUNHAP (
    maPNhap VARCHAR(10) PRIMARY KEY,
    thoiGian DATETIME,
    tongTien DECIMAL(18,2),
    maNCC VARCHAR(10),
    maThuThu VARCHAR(10),
    trangThai INT,
    FOREIGN KEY (maNCC) REFERENCES NHACUNGCAP(maNCC),
    FOREIGN KEY (maThuThu) REFERENCES THUTHU(maThuThu)
);

CREATE TABLE CTPHIEUNHAP (
    maPNhap VARCHAR(10),
    maSach VARCHAR(10),
    soLuong INT,
    donGia DECIMAL(18,2),
    PRIMARY KEY (maPNhap, maSach),
    FOREIGN KEY (maPNhap) REFERENCES PHIEUNHAP(maPNhap),
    FOREIGN KEY (maSach) REFERENCES SACH(maSach)
);

-- INSER DỮ LIỆU --
-- Thêm dữ liệu vào bảng TACGIA
INSERT INTO TACGIA (maTacGia, tenTacGia, namSinh, soDienThoai, trangThai) VALUES
('TG001', N'Ngô Văn Long', 1975, N'0901122334', 1),
('TG002', N'Lê Thị Mai', 1980, N'0912233445', 1),
('TG003', N'Phạm Hoàng Nam', 1968, N'0923344556', 1),
('TG004', N'Vũ Thị Hạnh', 1990, N'0934455667', 1),
('TG005', N'Đỗ Minh Trí', 1985, N'0945566778', 1),
('TG006', N'Trần Văn Dũng', 1970, N'0956677889', 1),
('TG007', N'Huỳnh Gia Bảo', 1992, N'0967788990', 1),
('TG008', N'Nguyễn Thị Bích', 1983, N'0978899001', 1),
('TG009', N'Lý Văn Hùng', 1978, N'0989900112', 1),
('TG010', N'Tạ Quang Minh', 1987, N'0990011223', 1),
('TG011', N'Cao Thị Tuyết', 1995, N'0901122445', 1),
('TG012', N'Bùi Anh Tú', 1982, N'0912233556', 1);

-- Thêm dữ liệu vào bảng THELOAI
INSERT INTO THELOAI (maTheLoai, theLoai, trangThai) VALUES
('TL001', N'Tiểu thuyết', 1),
('TL002', N'Truyện ngắn', 1),
('TL003', N'Khoa học viễn tưởng', 1),
('TL004', N'Kỹ năng sống', 1),
('TL005', N'Lịch sử', 1),
('TL006', N'Tâm lý học', 1),
('TL007', N'Giáo dục', 1),
('TL008', N'Kinh doanh', 1),
('TL009', N'Truyện trinh thám', 1),
('TL010', N'Thiếu nhi', 1),
('TL011', N'Truyện tranh', 1),
('TL012', N'Nấu ăn', 1);

-- Thêm dữ liệu vào bảng SACH
INSERT INTO SACH (maSach, tenSach, namXuatBan, soLuong, donGia, trangThai, maTacGia, maTheLoai)
VALUES
('SACH001', N'Tiếng Chim Hót Trong Bụi Mận Gai', 2015, 10, 250000, 1, 'TG001', 'TL001'),
('SACH002', N'Bố Già', 2010, 8, 260000, 1, 'TG001', 'TL001'),
('SACH003', N'Truyện Ngắn Nam Cao', 2018, 15, 180000, 1, 'TG002', 'TL002'),
('SACH004', N'Truyện Ngắn Nguyễn Huy Thiệp', 2012, 10, 195000, 1, 'TG002', 'TL002'),
('SACH005', N'Dune – Hành Tinh Cát', 2020, 20, 320000, 1, 'TG003', 'TL003'),
('SACH006', N'Ký Ức Của Trái Đất', 2019, 12, 295000, 1, 'TG003', 'TL003'),
('SACH007', N'Đắc Nhân Tâm', 2010, 12, 150000, 1, 'TG004', 'TL004'),
('SACH008', N'7 Thói Quen Hiệu Quả', 2013, 18, 220000, 1, 'TG004', 'TL004'),
('SACH009', N'Lược Sử Thế Giới', 2019, 8, 275000, 1, 'TG005', 'TL005'),
('SACH010', N'Lịch Sử Việt Nam', 2016, 10, 240000, 1, 'TG005', 'TL005'),
('SACH011', N'Tâm Lý Học Hành Vi', 2016, 18, 300000, 1, 'TG006', 'TL006'),
('SACH012', N'Phân Tích Tâm Lý Tội Phạm', 2021, 14, 330000, 1, 'TG006', 'TL006'),
('SACH013', N'Giáo Dục Sớm Cho Trẻ', 2021, 25, 200000, 1, 'TG007', 'TL007'),
('SACH014', N'Phương Pháp Montessori', 2020, 10, 210000, 1, 'TG007', 'TL007'),
('SACH015', N'Tư Duy Nhanh Và Chậm', 2017, 10, 350000, 1, 'TG008', 'TL008'),
('SACH016', N'Cha Giàu Cha Nghèo', 2018, 20, 190000, 1, 'TG008', 'TL008'),
('SACH017', N'Mật Mã Da Vinci', 2022, 30, 270000, 1, 'TG009', 'TL009'),
('SACH018', N'Sherlock Holmes Toàn Tập', 2015, 16, 310000, 1, 'TG009', 'TL009'),
('SACH019', N'Những Câu Chuyện Cổ Tích', 2014, 5, 120000, 1, 'TG010', 'TL010'),
('SACH020', N'Chuyện Kể Cho Bé Trước Giờ Ngủ', 2023, 12, 130000, 1, 'TG010', 'TL010'),
('SACH021', N'Doraemon Tập 1', 2011, 20, 40000, 1, 'TG011', 'TL011'),
('SACH022', N'Thám Tử Lừng Danh Conan Tập 5', 2012, 25, 45000, 1, 'TG011', 'TL011'),
('SACH023', N'One Piece Tập 10', 2013, 18, 50000, 1, 'TG011', 'TL011'),
('SACH024', N'365 Món Ăn Ngon Mỗi Ngày', 2023, 22, 180000, 1, 'TG012', 'TL012'),
('SACH025', N'Món Chay Thanh Đạm', 2020, 10, 160000, 1, 'TG012', 'TL012'),
('SACH026', N'Nấu Ăn Cùng MasterChef', 2021, 14, 250000, 1, 'TG012', 'TL012');

-- Thêm dữ liệu vào bảng THUTHU
INSERT INTO THUTHU (maThuThu, tenThuThu, taiKhoan, matKhau, soDienThoai, trangThai)
VALUES
('THUTHU001', N'Nguyễn Văn Cường', 'admin', '123456', '0123456789', 1),
('THUTHU002', N'Trần Thị Diệu', 'thuThu1', '654321', '0987654321', 1),
('THUTHU003', N'Phạm Minh Tuấn', 'thuThu2', '111222', '0123984567', 1),
('THUTHU004', N'Lê Thanh Bình', 'thuThu3', '333444', '0987123456', 1),
('THUTHU005', N'Vũ Hải Nam', 'thuThu4', '555666', '0123456789', 1),
('THUTHU006', N'Trương Thị Kim', 'thuThu5', '777888', '0987654321', 1),
('THUTHU007', N'Ngô Đức Tuấn', 'thuThu6', '999000', '0123984567', 1),
('THUTHU008', N'Hồ Quang Hải', 'thuThu7', '123123', '0987123456', 1),
('THUTHU009', N'Đỗ Minh Hieu', 'thuThu8', '456789', '0123456789', 1),
('THUTHU010', N'Hoàng Thị Lan', 'thuThu9', '987654', '0987654321', 1),
('THUTHU011', N'Phan Ngọc Ánh', 'thuThu10', '654321', '0123984567', 1),
('THUTHU012', N'Bùi Thị Phương', 'thuThu11', '112233', '0987123456', 1);



-- Thêm dữ liệu vào bảng LOAIDOCGIA
INSERT INTO LOAIDOCGIA (maLoaiDG, tenLoaiDG, gioiHanMuon, moTa, trangThai)
VALUES
('LDG001', N'Thường', 5, N'Mượn sách bình thường', 1),
('LDG002', N'VIP', 10, N'Mượn sách ưu tiên', 1),
('LDG003', N'Giáo viên', 15, N'Mượn sách cho mục đích giảng dạy', 1),
('LDG004', N'Học sinh', 3, N'Mượn sách với số lượng ít', 1),
('LDG005', N'Sinh viên', 7, N'Mượn sách phục vụ học tập, nghiên cứu', 1),
('LDG006', N'Nhân viên thư viện', 20, N'Mượn sách ưu tiên công việc', 1); 

-- Thêm dữ liệu vào bảng DOCGIA
INSERT INTO DOCGIA (maDocGia, tenDocGia, soDienThoai, diaChi, maLoaiDG, trangThai)
VALUES
('DG001', N'Nguyễn Văn Cường', '0123456789', N'Hà Nội', 'LDG001', 1),  
('DG002', N'Trần Thị Diệu', '0987654321', N'Hồ Chí Minh', 'LDG001', 1),  
('DG003', N'Phạm Minh Tuấn', '0123984567', N'Hải Phòng', 'LDG001', 1),   
('DG004', N'Nguyễn Thị Hiền', '0123456789', N'Hà Nội', 'LDG001', 1),  
('DG005', N'Lê Văn Minh', '0987654321', N'Hồ Chí Minh', 'LDG002', 1),
('DG006', N'Phạm Minh Tiến', '0123456789', N'Đà Nẵng', 'LDG003', 1),  
('DG007', N'Trần Thi Huyền', '0987654321', N'Hải Phòng', 'LDG004', 1),
('DG008', N'Nguyễn Lê Ngọc Ánh', '0123456789', N'Hà Nội', 'LDG005', 1),
('DG009', N'Lê Minh Vũ', '0987654321', N'Bình Dương', 'LDG006', 1),
('DG010', N'Vũ Thị Hà', '0123456789', N'Quảng Ninh', 'LDG003', 1),  
('DG011', N'Hoàng Bảo MInh', '0987654321', N'Hà Nam', 'LDG001', 1),  
('DG012', N'Phan Thanh Tùng', '0123456789', N'Thái Bình', 'LDG002', 1); 
-- Thêm dữ liệu vào bảng PHIEUMUON
INSERT INTO PHIEUMUON (maPMuon, ngayMuon, ngayTra, maDocGia, maThuThu, trangThai)
VALUES
('PM001', '2024-01-01', '2024-01-10', 'DG001', 'THUTHU001', 2),
('PM002', '2024-03-01', '2024-03-10', 'DG002', 'THUTHU001', 2),
('PM003', '2024-03-10', '2024-03-25', 'DG003', 'THUTHU002', 2),
('PM004', '2024-04-01', '2024-04-10', 'DG001', 'THUTHU003', 2),
('PM005', '2024-05-01', '2024-05-10', 'DG005', 'THUTHU003', 2),
('PM006', '2024-06-01', '2024-06-10', 'DG002', 'THUTHU003', 2),
('PM007', '2024-08-25', '2024-08-30', 'DG007', 'THUTHU004', 2),
('PM008', '2024-10-01', '2024-10-20', 'DG008', 'THUTHU005', 2),
('PM009', '2025-01-01', '2025-01-05', 'DG001', 'THUTHU006', 2),
('PM010', '2025-02-01', '2025-02-10', 'DG010', 'THUTHU007', 2),
('PM011', '2025-03-07', '2025-03-20', 'DG010', 'THUTHU008', 1),
('PM012', '2025-04-12', '2025-04-20', 'DG012', 'THUTHU009', 1),
('PM013', '2025-04-15', '2025-04-25', 'DG003', 'THUTHU002', 1),
('PM014', '2025-05-01', '2025-05-10', 'DG004', 'THUTHU001', 1),
('PM015', '2025-05-01', '2025-05-15', 'DG005', 'THUTHU003', 1);

-- Thêm dữ liệu vào bảng CTPHIEUMUON
INSERT INTO CTPHIEUMUON (maPhieuMuon, maSach, soLuong, soLuongChuaTra) VALUES
('PM001', 'SACH001', 2, 0),
('PM001', 'SACH002', 3, 0),
('PM002', 'SACH003', 1, 0),
('PM002', 'SACH005', 2, 0),
('PM002', 'SACH012', 2, 0),
('PM003', 'SACH006', 3, 0),
('PM004', 'SACH007', 2, 0),
('PM004', 'SACH008', 3, 0),
('PM005', 'SACH009', 2, 0),
('PM006', 'SACH010', 1, 0),
('PM007', 'SACH011', 2, 0),
('PM007', 'SACH021', 2, 0),
('PM007', 'SACH013', 2, 0),
('PM008', 'SACH010', 1, 0),
('PM008', 'SACH015', 3, 0),
('PM009', 'SACH016', 2, 0),
('PM009', 'SACH017', 2, 0),
('PM010', 'SACH018', 5, 0),
('PM010', 'SACH022', 1, 0),
('PM011', 'SACH020', 1, 1),
('PM011', 'SACH002', 2, 2),
('PM011', 'SACH003', 2, 2),
('PM012', 'SACH004', 4, 4),
('PM013', 'SACH008', 3, 3),
('PM013', 'SACH006', 3, 3),
('PM013', 'SACH007', 5, 5),
('PM014', 'SACH008', 5, 5),
('PM014', 'SACH009', 2, 2),
('PM015', 'SACH010', 1, 1),
('PM015', 'SACH021', 2, 2);

-- Thêm dữ liệu vào bảng QUYDINH
INSERT INTO QUYDINH (maQuyDinh, noiDung, soTien, trangThai) VALUES
('QD001', N'Phạt trễ hạn', 50000, 1),
('QD002', N'Phạt mất sách', 100000, 1),
('QD003', N'Phạt làm hư hỏng sách nhẹ', 30000, 1),
('QD004', N'Phạt làm hư hỏng sách nặng', 80000, 1),
('QD005', N'Phạt viết vẽ, đánh dấu trong sách', 15000, 1),
('QD006', N'Phạt sử dụng điện thoại trong khu vực cấm', 10000, 1),
('QD007', N'Phạt sao chép tài liệu', 150000, 1),
('QD008', N'Phạt gây mất trật tự', 100000, 1),
('QD009', N'Phạt ăn uống trong khu vực đọc sách', 20000, 1),
('QD010', N'Phạt sai quy cách khi trả sách', 50000, 1);





-- Thêm dữ liệu vào bảng PHUTHU
INSERT INTO PHUTHU (maPhuThu, tienPhat)
VALUES
('PT001', 60000),
('PT002', 250000),
('PT003', 130000),
('PT004', 100000),
('PT005', 65000);

-- Thêm dữ liệu vào bảng CTPHAT
INSERT INTO CTPHAT (maPhuThu, maQuyDinh)
VALUES
('PT001', 'QD001'),
('PT002', 'QD002'),
('PT003', 'QD003'),
('PT004', 'QD004'),
('PT005', 'QD005'),
('PT001', 'QD006'),
('PT002', 'QD007'),
('PT003', 'QD008'),
('PT004', 'QD009'),
('PT005', 'QD010');
-- Thêm dữ liệu vào bảng PHIEUTRA
INSERT INTO PHIEUTRA (maPTra, ngayThucTra, maPMuon, maThuThu, maPhuThu,trangThai) VALUES
('PTRA001', '2024-01-09', 'PM001', 'THUTHU001', NULL,1),
('PTRA002', '2024-03-10', 'PM002', 'THUTHU001', 'PT001',1),
('PTRA003', '2024-03-25', 'PM003', 'THUTHU002', 'PT002',1),
('PTRA004', '2024-04-09', 'PM004', 'THUTHU003', 'PT003',1),
('PTRA005', '2024-07-09', 'PM005', 'THUTHU003', NULL,1),
('PTRA006', '2025-06-09', 'PM006', 'THUTHU003', NULL,1),
('PTRA007', '2025-08-29', 'PM007', 'THUTHU004', 'PT004',1),
('PTRA008', '2025-10-19', 'PM008', 'THUTHU005', NULL,1),
('PTRA009', '2025-01-04', 'PM009', 'THUTHU006', NULL,1),
('PTRA010', '2025-02-09', 'PM010', 'THUTHU007', 'PT005',1);



--Thêm dữ liệu vào bảng CTPHIEUTRA
INSERT INTO CTPHIEUTRA (maPhieuTra, maSach, soLuong) VALUES
('PTRA001', 'SACH001', 2),
('PTRA001', 'SACH002', 3),
('PTRA002', 'SACH003', 1),
('PTRA002', 'SACH005', 2),
('PTRA002', 'SACH012', 2),
('PTRA003', 'SACH006', 3),
('PTRA004', 'SACH007', 2),
('PTRA004', 'SACH008', 3),
('PTRA005', 'SACH009', 2),
('PTRA006', 'SACH010', 1),
('PTRA007', 'SACH011', 2),
('PTRA007', 'SACH021', 2),
('PTRA007', 'SACH013', 2),
('PTRA008', 'SACH010', 1),
('PTRA008', 'SACH015', 3),
('PTRA009', 'SACH016', 2),
('PTRA009', 'SACH017', 2),
('PTRA010', 'SACH018', 5),
('PTRA010', 'SACH022', 1);

-- Thêm dữ liệu vào bảng NHACUNGCAP
INSERT INTO NHACUNGCAP (maNCC, tenNCC, soDienThoai, trangThai)
VALUES
('NCC001', N'Công Ty A', '0123456789', 1),
('NCC002', N'Công Ty B', '0987654321', 1),
('NCC003', N'Công Ty C', '0112233445', 1),
('NCC004', N'Công Ty D', '0223344556', 1),
('NCC005', N'Công Ty E', '0334455667', 1),
('NCC006', N'Công Ty F', '0445566778', 1),
('NCC007', N'Công Ty G', '0556677889', 1),
('NCC008', N'Công Ty H', '0667788990', 1),
('NCC009', N'Công Ty I', '0778899001', 1),
('NCC010', N'Công Ty J', '0889900112', 1);
INSERT INTO PHIEUNHAP (maPNhap, thoiGian, tongTien, maNCC, maThuThu, trangThai)
VALUES
('PN001', '2024-01-01 08:00:00', 725000, 'NCC001', 'THUTHU001', 1),   
('PN002', '2024-05-02 09:00:00', 425000, 'NCC002', 'THUTHU002', 1),       
('PN003', '2024-10-03 10:00:00',870000, 'NCC003', 'THUTHU003', 1),      
('PN004', '2024-12-04 11:00:00',644000, 'NCC004', 'THUTHU004', 1),       
('PN005', '2025-01-15 12:00:00',716000, 'NCC005', 'THUTHU005', 1),       
('PN006', '2025-01-30 13:00:00',696000, 'NCC006', 'THUTHU006', 1),       
('PN007', '2025-02-17 14:00:00',560000 , 'NCC007', 'THUTHU007', 1),       
('PN008', '2025-03-08 15:00:00',475000, 'NCC008', 'THUTHU008', 1),       
('PN009', '2025-04-09 16:00:00',470000, 'NCC009', 'THUTHU009', 1),      
('PN010', '2025-05-01 17:00:00',760000, 'NCC010', 'THUTHU010', 1);       


INSERT INTO CTPHIEUNHAP (maPNhap, maSach, soLuong, donGia)
VALUES
('PN001', 'SACH001', 10, 20000),
('PN001', 'SACH011', 15, 35000),
('PN002', 'SACH002', 5, 25000),
('PN002', 'SACH012', 10, 30000),
('PN003', 'SACH003', 15, 40000),
('PN003', 'SACH013', 9, 30000),
('PN004', 'SACH004', 7, 28000),
('PN004', 'SACH021', 14, 32000),
('PN005', 'SACH005', 8, 22000),
('PN005', 'SACH015', 18, 30000),
('PN006', 'SACH006', 12, 26000),
('PN006', 'SACH016', 12, 32000),
('PN007', 'SACH007', 10, 24000),
('PN007', 'SACH017', 8, 40000),
('PN008', 'SACH008', 6, 50000),
('PN008', 'SACH018', 7, 25000),
('PN009', 'SACH009', 5, 28000),
('PN009', 'SACH019', 11, 30000),
('PN010', 'SACH010', 20, 30000),
('PN010', 'SACH020', 5, 32000);
