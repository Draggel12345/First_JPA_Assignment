package se.lexicon.JPA_PART1.Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class ProductOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productOrderId;
	
	private LocalDate orderDate;
	private LocalTime orderTime;
	
	@ManyToOne(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
	@JoinColumn(name = "user_id")
	private AppUser customer;
	
	@OneToMany(fetch = FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH},
	mappedBy = "productOrder", orphanRemoval = true)
	private List<OrderItem> items;
	
	public ProductOrder() {}
	
	public ProductOrder(int productOrderId, LocalDate orderDate, LocalTime orderTime, AppUser customer, List<OrderItem> items) {
		this(orderDate, orderTime, customer, items);
		this.productOrderId = productOrderId;
	}
	
	public ProductOrder(LocalDate orderDate, LocalTime orderTime, AppUser customer, List<OrderItem> items) {
		setOrderDate(orderDate);
		setOrderTime(orderTime);
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
	
	public double totalProductPrice() {
		double sum = 0;
		for(OrderItem item : items) {
			sum += item.calculatePrice();
		}
		
		return sum;
	}
	
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	
	public LocalTime getOrderTime() {
		return orderTime;
	}
	
	public void setOrderTime(LocalTime orderTime) {
		this.orderTime = orderTime;
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
		return Objects.hash(customer, orderDate, orderTime, productOrderId);
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
		return Objects.equals(customer, other.customer) && Objects.equals(orderDate, other.orderDate)
				&& Objects.equals(orderTime, other.orderTime) && productOrderId == other.productOrderId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductOrder [productOrderId=");
		builder.append(productOrderId);
		builder.append(", orderDate=");
		builder.append(orderDate);
		builder.append(", orderTime=");
		builder.append(orderTime);
		builder.append(", customer=");
		builder.append(customer);
		builder.append("]");
		return builder.toString();
	}
}
