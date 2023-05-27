package lv.fitness_app.users.core.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class User {

    private String email;
    private final String username;
    private final String password;

    Date endOfSubscriptionDate;
    Subscription subscription;

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.subscription = subscription.TRAIL;
        endOfSubscriptionDate =  Date.valueOf(LocalDate.now().plusDays(30));
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public Date getEndOfSubscriptionDate() {
        return endOfSubscriptionDate;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(endOfSubscriptionDate, user.endOfSubscriptionDate) && subscription == user.subscription;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, username, password, endOfSubscriptionDate, subscription);
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", endOfSubscriptionDate=" + endOfSubscriptionDate +
                ", subscription=" + subscription +
                '}';
    }

    public boolean hasAccessToPremiumFeatures() {
        if (subscription == Subscription.PREMIUM) {
            return true;
        } else {
            return false;
        }
    }
}