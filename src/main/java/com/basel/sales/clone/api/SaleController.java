package com.basel.sales.clone.api;

import com.basel.sales.clone.model.Client;
import com.basel.sales.clone.model.Product;
import com.basel.sales.clone.model.Sale;
import com.basel.sales.clone.model.User;
import com.basel.sales.clone.repository.ClientRepository;
import com.basel.sales.clone.repository.ProductRepository;
import com.basel.sales.clone.repository.SaleRepository;
import com.basel.sales.clone.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleRepository saleRepository;
    private final ClientRepository clientRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public SaleController(SaleRepository saleRepository, ClientRepository clientRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }


    @GetMapping
    public ResponseEntity<List<Sale>> getSales() {
        try {
            List<Sale> sales = new ArrayList<Sale>();
            saleRepository.findAll().forEach(sales::add);

            if (sales.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(sales, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    record Transaction(long productId, int quantity, double price ) {}
    record CreateSaleRequest(long clientId, long sellerId, List<Transaction> transactions) {}

    @PostMapping
    public ResponseEntity<Sale> createSale(@RequestBody CreateSaleRequest request) {
        try {
            Optional<Client> clientData = clientRepository.findById(request.clientId);
            Optional<User> sellerData = userRepository.findById(request.sellerId);
            Client client;
            User seller;
            if (clientData.isPresent()) {
                client = clientData.get();
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            if (sellerData.isPresent()) {
                seller = sellerData.get();
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            Sale sale = new Sale(client, seller, 0.0);
            double totalValue = 0.0;
            for(Transaction t : request.transactions) {
                Optional<Product> pData = productRepository.findById(t.productId);
                Product _product = pData.get();
                sale.addProduct(_product);
                totalValue += t.price;
            }
            sale.setTotal(totalValue);
            Sale _sale = saleRepository.save(new Sale(client, seller, totalValue));
            return new ResponseEntity<>(_sale, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
