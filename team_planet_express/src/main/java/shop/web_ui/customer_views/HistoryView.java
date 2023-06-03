package shop.web_ui.customer_views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Main")
@Route(value = "history")
@AnonymousAllowed
public class HistoryView extends HorizontalLayout {
}
