package com.tutorial;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

// Kelas abstrak MenuItem sebagai kelas dasar
abstract class MenuItem implements Serializable {
    protected String nama;
    protected double harga;
    protected String kategori;

    // Konstruktor
    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Metode abstrak untuk menampilkan informasi menu
    public abstract void tampilMenu();
}

// Kelas Makanan sebagai turunan dari MenuItem
class Makanan extends MenuItem implements Serializable {
    private String jenisMakanan;

    // Konstruktor
    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }

    // Implementasi metode tampilMenu() untuk Makanan
    @Override
    public void tampilMenu() {
        System.out.println("Nama: " + nama);
        System.out.println("Jenis: " + jenisMakanan);
        System.out.println("Harga: " + harga);
        System.out.println("Kategori: " + kategori);
    }
}

// Kelas Minuman sebagai turunan dari MenuItem
class Minuman extends MenuItem implements Serializable {
    private String jenisMinuman;

    // Konstruktor
    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }

    // Implementasi metode tampilMenu() untuk Minuman
    @Override
    public void tampilMenu() {
        System.out.println("Nama: " + nama);
        System.out.println("Jenis: " + jenisMinuman);
        System.out.println("Harga: " + harga);
        System.out.println("Kategori: " + kategori);
    }
}

// Kelas Diskon sebagai turunan dari MenuItem
class Diskon extends MenuItem implements Serializable{
    public double diskon;


    // Konstruktor
    public Diskon(String nama, double harga, double diskon) {
        super(nama, harga, "Diskon");
        this.diskon = diskon;
    }

    // Implementasi metode tampilMenu() untuk Diskon
    @Override
    public void tampilMenu() {
        System.out.println("Nama: " + nama);
        System.out.println("Harga: " + harga);
        System.out.println("Diskon: " + diskon + "%");
        System.out.println("Kategori: " + kategori);
    }
}

// Kelas Menu untuk mengelola semua item menu dalam restoran
class Menu {
    private ArrayList<MenuItem> daftarMenu;

    // Konstruktor
    public Menu() {
        this.daftarMenu = new ArrayList<>();
    }

    // Menambahkan item baru ke menu
    public void tambahMenu(MenuItem item) {
        daftarMenu.add(item);
    }

    // Menampilkan menu restoran
    public void tampilMenuRestoran() {
        System.out.println("Menu Restoran:");
        for (MenuItem item : daftarMenu) {
            item.tampilMenu();
            System.out.println("====================");
        }
    }

    // Mendapatkan item menu berdasarkan nama
    public MenuItem getItemMenu(String nama) {
        for (MenuItem item : daftarMenu) {
            if (item.nama.equalsIgnoreCase(nama)) {
                return item;
            }
        }
        return null;
    }
}

// Kelas Pesanan untuk mencatat pesanan pelanggan
class Pesanan {
    private ArrayList<MenuItem> pesananPelanggan;

    // Konstruktor
    public Pesanan() {
        this.pesananPelanggan = new ArrayList<>();
    }

    // Menambahkan item ke pesanan pelanggan
    public void tambahPesanan(MenuItem item) {
        pesananPelanggan.add(item);
    }

    // Menghitung total biaya pesanan dengan mempertimbangkan diskon
    public double hitungTotalBiaya() {
        double totalBiaya = 0;
        for (MenuItem item : pesananPelanggan) {
            if (item instanceof Diskon) {
                totalBiaya += (item.harga - (item.harga * ((Diskon) item).diskon / 100));
            } else {
                totalBiaya += item.harga;
            }
        }
        return totalBiaya;
    }

    // Menampilkan struk pesanan pelanggan
    public void tampilStrukPesanan() {
        System.out.println("Struk Pesanan:");
        for (MenuItem item : pesananPelanggan) {
            item.tampilMenu();
            System.out.println("====================");
        }
        System.out.println("Total Biaya: " + hitungTotalBiaya());
    }
}

// Kelas utama sebagai menu utama program
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menuRestoran = new Menu();
        Pesanan pesananPelanggan = new Pesanan();

        // Contoh menambahkan beberapa item ke menu
        menuRestoran.tambahMenu(new Makanan("Nasi Goreng", 25000, "Nasi"));
        menuRestoran.tambahMenu(new Makanan("Ramen Spicy", 30000, "Mie"));
        menuRestoran.tambahMenu(new Makanan("Florida Toast", 20000, "Roti"));
        menuRestoran.tambahMenu(new Makanan("Miso Soup", 15000, "Sup"));
        menuRestoran.tambahMenu(new Minuman("Es Teh", 5000, "Teh"));
        menuRestoran.tambahMenu(new Minuman("Jus Mango", 25000, "Jus"));
        menuRestoran.tambahMenu(new Minuman("Jus Avocado", 28000, "Jus"));
        menuRestoran.tambahMenu(new Minuman("Sprinkle Warm", 89000, "Cocktail"));
        menuRestoran.tambahMenu(new Diskon("Diskon Lebaran", 0, 10));

        int pilihan;

        do {
            System.out.println("Menu Utama:");
            System.out.println("1. Tambah Item ke Menu");
            System.out.println("2. Tampilkan Menu Restoran");
            System.out.println("3. Pesan Menu");
            System.out.println("4. Tampilkan Struk Pesanan");
            System.out.println("5. Keluar");

            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama item: ");
                    scanner.nextLine(); // Membersihkan newline character
                    String namaItem = scanner.nextLine();
                    System.out.print("Masukkan harga item: ");
                    double hargaItem = scanner.nextDouble();
                    System.out.print("Pilih kategori item (Makanan/Minuman/Diskon): ");
                    String kategoriItem = scanner.next();

                    MenuItem newItem;
                    if (kategoriItem.equalsIgnoreCase("Makanan")) {
                        System.out.print("Masukkan jenis makanan: ");
                        String jenisMakanan = scanner.next();
                        newItem = new Makanan(namaItem, hargaItem, jenisMakanan);
                    } else if (kategoriItem.equalsIgnoreCase("Minuman")) {
                        System.out.print("Masukkan jenis minuman: ");
                        String jenisMinuman = scanner.next();
                        newItem = new Minuman(namaItem, hargaItem, jenisMinuman);
                    } else if (kategoriItem.equalsIgnoreCase("Diskon")) {
                        System.out.print("Masukkan besaran diskon (%): ");
                        double besaranDiskon = scanner.nextDouble();
                        newItem = new Diskon(namaItem, hargaItem, besaranDiskon);
                    } else {
                        System.out.println("Kategori tidak valid.");
                        continue;
                    }

                    menuRestoran.tambahMenu(newItem);
                    System.out.println("Item berhasil ditambahkan ke menu.");
                    break;

                case 2:
                    menuRestoran.tampilMenuRestoran();
                    break;

                case 3:
                    System.out.print("Masukkan nama item yang dipesan: ");
                    scanner.nextLine(); // Membersihkan newline character
                    String pesanan = scanner.nextLine();
                    MenuItem itemDipesan = menuRestoran.getItemMenu(pesanan);

                    if (itemDipesan != null) {
                        pesananPelanggan.tambahPesanan(itemDipesan);
                        System.out.println("Item berhasil ditambahkan ke pesanan.");
                    } else {
                        System.out.println("Item tidak ditemukan dalam menu.");
                    }
                    break;

                case 4:
                    pesananPelanggan.tampilStrukPesanan();
                    break;

                case 5:
                    System.out.println("Terima kasih! Program keluar.");
                    break;

                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih kembali.");
            }

        } while (pilihan != 5);

        // Simpan menu ke file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("menu.txt"))) {
            outputStream.writeObject(menuRestoran);
            System.out.println("Menu berhasil disimpan ke dalam file.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        //simpan ke order file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("struk_pesanan.txt"))) {
            outputStream.writeObject(pesananPelanggan);
            System.out.println("Struk pesanan berhasil disimpan ke dalam file.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }


        scanner.close();
    }
}
