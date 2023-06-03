package shop.web_ui.components;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import shop.core.services.actions.shared.SearchItemService;
import shop.core.services.actions.shared.SecurityService;
import shop.web_ui.common_views.ItemListView;
import shop.web_ui.common_views.SearchItemsListView;

import java.util.Optional;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;
    private final SearchItemService searchItemService;

    @Autowired
    public MainLayout(SecurityService securityService, SearchItemService searchItemService) {
        this.securityService = securityService;
        this.searchItemService = searchItemService;
        setDrawerOpened(false);
        createHeader();
        createDrawer();
    }


    private void createHeader() {
        H1 logo = new H1("Shop");
        logo.addClassNames(
                LumoUtility.FontSize.LARGE,
                LumoUtility.Margin.MEDIUM);

        Optional<UserDetails> authenticatedUser = securityService.getAuthenticatedUser();
        Button logout;
        if (authenticatedUser.isPresent()) {
            String u = authenticatedUser.get().getUsername();
            logout = new Button("Log out " + u, e -> securityService.logout());
        } else {
            logout = new Button("Log in ", e -> getUI().ifPresent(ui -> ui.navigate("login")));
        }
        TextField textField = new TextField();
        textField.setPlaceholder("Searching...");
        Icon iconSearch = new Icon(VaadinIcon.SEARCH);
        Button searchButton = new Button(iconSearch,
                e -> getUI().ifPresent(ui -> ui.navigate(SearchItemsListView.class, textField.getValue())
                ));
        textField.setSuffixComponent(searchButton);


        Icon iconCart = new Icon(VaadinIcon.CART);
        Button cartButton = new Button(iconCart, e -> getUI().ifPresent(ui -> ui.navigate("cart")));
        var header = new HorizontalLayout(new DrawerToggle(), logo, textField, cartButton, logout);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidthFull();
        header.addClassNames(
                LumoUtility.Padding.Vertical.NONE,
                LumoUtility.Padding.Horizontal.MEDIUM);

        addToNavbar(header);


    }

    private void createDrawer() {
        addToDrawer(new VerticalLayout(
                new RouterLink("Items", ItemListView.class)
        ));
    }
}
