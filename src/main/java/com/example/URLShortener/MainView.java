package com.example.URLShortener;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout {

    URLRepository urlRepository = new URLRepository();
    String localURL = "http://localhost:8080/";

    public MainView() {
        TextField longUrlField = new TextField("Enter Long URL");
        Button shortenButton = new Button("Shorten URL");
        Span shortUrlSpan = new Span();

        shortenButton.addClickListener(event -> {
            String longUrl = longUrlField.getValue();
            if (URLValidator.INSTANCE.validateURL(longUrl)) {
                URLConverterService urlConverterService = new URLConverterService(urlRepository);
                String shortUrl = urlConverterService.shortenURL(localURL, longUrl);
                shortUrlSpan.setText(shortUrl);
            }
            else {
                Notification.show("Invalid URL", 3000, Notification.Position.TOP_CENTER);
            }
        });

        add(longUrlField, shortenButton, shortUrlSpan);
    }
}
