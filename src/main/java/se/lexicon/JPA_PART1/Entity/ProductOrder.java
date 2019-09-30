package se.lexicon.JPA_PART1.Entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProductOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productOrderId;
	
	private LocalDateTime orderDateTime;

	private AppUser customer;
	
	private List<OrderItem> items;
	
	public ProductOrder() {}
	
	public ProductOrder(int productOrderId, LocalDateTime orderDateTime, AppUser customer, List<OrderItem> items) {
		this(orderDateTime, customer, items);
		this.productOrderId = productOrderId;
	}
	
	public ProductOrder(LocalDateTime orderDateTime, AppUser customer, List<OrderItem> items) {
		setOrderDateTime(orderDateTime);
		setCustomer(customer);
		setItems(items);
	}
	
	public void addItem(OrderItem item) throws IllegalArgumentException {
		if(!items.contains(item)) {
			items.add(item);
			item.setProductOrder(this);
		} else {
			throw new IllegalArgumentException("Cant add - " + item);
		}
	}
	
	public void removeItem(OrderItem item) throws IllegalArgumentException {
		for(OrderItem i : items) {
			if(i.equals(item)) {
				items.remove(item);
				item.setProductOrder(null);
			} else {
				throw new IllegalArgumentException("Cant remove - " + item);
			}
		}
	}
	
	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}
	
	public AppUser getCustomer() {
		return customer;
	}
	public void setCustomer(AppUser customer) {
		this.customer = customer;
	}
	
	public List<OrderItem> getItems() {
		return items;
	}
	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
	
	public int getProductOrderId() {
		return productOrderId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer, items, orderDateTime, productOrderId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductOrder other = (ProductOrder) obj;
		return Objects.equals(customer, other.customer) && Objects.equals(items, other.items)
				&& Objects.equals(orderDateTime, other.orderDateTime) && productOrderId == other.productOrderId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductOrder [productOrderId=");
		builder.append(productOrderId);
		builder.append(", orderDateTime=");
		builder.append(orderDateTime);
		builder.append(", customer=");
		builder.append(customer);
		builder.append("]");
		return builder.toString();
	}	
}
