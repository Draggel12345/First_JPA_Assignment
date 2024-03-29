package se.lexicon.JPA_PART1.Entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private int quantity;
	
	@ManyToOne(fetch = FetchType.EAGER,
			cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name = "productOrder_id")
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
	
	public double calculatePrice() {
		return product.getPrice() * quantity;
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
		return Objects.hash(orderId, product, quantity);
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
		return orderId == other.orderId && Objects.equals(product, other.product) && quantity == other.quantity;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderItem [orderId=");
		builder.append(orderId);
		builder.append(", product=");
		builder.append(product);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append("]");
		return builder.toString();
	}
}
