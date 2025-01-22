package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);



        SellerDao sellerDao = DaoFactory.createSellerDao();

        Seller seller = sellerDao.findById(3);

        System.out.println(seller);
        System.out.println();



        Department department = new Department(2, null);

        List<Seller> list = sellerDao.findByDepartment(department);

        for(Seller obj : list)
        {
            System.out.println(obj);
        }
        System.out.println();



        list = sellerDao.findAll();

        for(Seller obj : list)
        {
            System.out.println(obj);
        }
        System.out.println();



        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());
        System.out.println();



        seller = sellerDao.findById(1);
        seller.setName("Martha Waine");
        sellerDao.update(seller);
        System.out.println("Update completed");



        System.out.println("Enter id from delete: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete completed");


        sc.close();

    }
}
