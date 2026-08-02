package com.example.backendhelpdesk.model.postgres;

import com.example.backendhelpdesk.model.enums.Category;
import com.example.backendhelpdesk.model.enums.Priority;
import com.example.backendhelpdesk.model.enums.State;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private State status;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.title, this.description, this.category, this.priority, this.status, this.createdAt);
    }

    @Override
    public boolean equals(Object obj) {
        if  (this == obj)
            return true;
        if (!(obj instanceof Ticket))
            return false;
        Ticket other = (Ticket) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.title, other.title)
                && Objects.equals(this.description, other.description) && Objects.equals(this.category, other.category)
                && Objects.equals(this.priority, other.priority) && Objects.equals(this.status, other.status)
                && Objects.equals(this.createdAt, other.createdAt);
    }

    @Override
    public String toString() {
        return "Ticket{" + "id=" + this.id + ", title=" + this.title + ", description=" + this.description
                + ", category=" + this.category + ", priority=" + this.priority + ", status=" + this.status
                + ", createdAt=" + this.createdAt + '}';
    }
}
