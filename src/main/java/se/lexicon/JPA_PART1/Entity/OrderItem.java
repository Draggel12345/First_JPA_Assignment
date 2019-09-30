package se.lexicon.JPA_PART1.Entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private int quantity;
	
	private Product product;
	
	private ProductOrder productOrder;
	
	public OrderItem() {}
	
	public OrderItem(int orderId, int quantity, Product product, ProductOrder productOrder) {
		this(quantity, product, productOrder);
		this.orderId = orderId;
	}
	
	public OrderItem(int quantity, Product product, ProductOrder productOrder) {
		setQuantity(quantity);
		setProduct(product);
		setProductOrder(productOrder);
	}
	
	public double calculatePrice(double price, int quantity) {
		return price * quantity;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public ProductOrder getProductOrder() {
		return productOrder;
	}
	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}
	
	public int getOrderId() {
		return orderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(orderId, product, productOrder, quantity);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return orderId == other.orderId && Objects.equals(product, other.product)
				&& Objects.equals(productOrder, other.productOrder) && quantity == other.quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderItem [orderId=");
		builder.append(orderId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", product=");
		builder.append(product);
		builder.append(", productOrder=");
		builder.append(productOrder);
		builder.append("]");
		return builder.toString();
	}
}
