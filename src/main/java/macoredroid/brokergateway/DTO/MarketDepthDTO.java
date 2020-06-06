package macoredroid.brokergateway.DTO;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
@Data
@Document
public class MarketDepthDTO {
    class Composite{
        int price;
        int count;

        public Composite(int count, int price) {
            this.price = price;
            this.count = count;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
    @Id
    String id;
    List<Composite> buyers = new ArrayList<>();
    List<Composite> sellers = new ArrayList<>();

    public MarketDepthDTO() {
    }

    public void addBuyer(int count, int price){
        this.buyers.add(new Composite(count, price));
    }

    public void addSeller(int count, int price){
        this.sellers.add(new Composite(count, price));
    }

}
