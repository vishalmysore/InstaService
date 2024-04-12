package org.example;

public class Ticket {
    private long id;
    private String description;
    private String status;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method for logging
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
