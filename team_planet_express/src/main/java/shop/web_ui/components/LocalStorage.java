package shop.web_ui.components;

import com.vaadin.flow.component.*;
import elemental.json.JsonObject;

import java.util.ArrayList;
import java.util.Collection;

@Tag("span")
public class LocalStorage extends Component {

    @FunctionalInterface
    public interface LocalStorageInitListener {
        void onInit(LocalStorage storage);
    }

    private Collection<LocalStorageInitListener> listeners = new ArrayList<>();
    private JsonObject localStorage;


    @Override
    protected void onAttach(AttachEvent attachEvent) {
        UI.getCurrent().getSession().accessSynchronously(() -> {
            UI.getCurrent().getPage().executeJs("$0.$server.init(localStorage)", this.getElement());
        });
    }

    public String getString(String key) {
        try {
            return localStorage.getString(key);
        } catch (NullPointerException e) {
            return "";
        }
    }

    public void addInitListener(LocalStorageInitListener listener) {
        listeners.add(listener);
    }

    public void setValue(String key, String value) {
        UI.getCurrent().getPage().executeJs("localStorage.setItem($0, $1)", key, value);
    }

    @ClientCallable
    public void init(JsonObject localStorage) {
        UI.getCurrent().getSession().access(() -> {
            this.localStorage = localStorage;
            listeners.forEach(v -> v.onInit(this));
        });
    }
}