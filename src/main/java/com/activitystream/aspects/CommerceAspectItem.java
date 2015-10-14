package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.Entity;
import com.activitystream.EntityRole;
import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommerceAspectItem {
    private EntityRole[] involved = new EntityRole[]{};
    private Aspect[] aspects = new Aspect[]{};
    private Double commissionFixed;
    private Double commissionPercentage;
    private Double discountPercentage;
    private Double taxPercentage;
    private Integer itemCount;
    private Double itemPrice;
    private String description;
    private String variant;
    private String priceCategory;
    private String currency;
    private Double totalInStock;
    private Double totalForSale;
    private List<Entity> serialNumbers = new ArrayList<>();
    private String validFrom;
    private String validUntil;
    private String accountingKey;

    public CommerceAspectItem involves(EntityRole... role) {
        this.involved = role;
        return this;
    }

    public CommerceAspectItem aspects(Aspect... aspects) {
        this.aspects = aspects;
        return this;
    }

    public CommerceAspectItem commissionFixed(Double commissionFixed) {
        this.commissionFixed = commissionFixed;
        return this;
    }

    public CommerceAspectItem commissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
        return this;
    }

    public CommerceAspectItem discountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public CommerceAspectItem taxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
        return this;
    }

    public CommerceAspectItem itemCount(Integer itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public CommerceAspectItem itemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public CommerceAspectItem description(String description) {
        this.description = description;
        return this;
    }

    public CommerceAspectItem variant(String variant) {
        this.variant = variant;
        return this;
    }

    public CommerceAspectItem priceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
        return this;
    }

    public CommerceAspectItem currency(String currency) {
        this.currency = currency;
        return this;
    }

    public CommerceAspectItem totalInStock(Double totalInStock) {
        this.totalInStock = totalInStock;
        return this;
    }

    public CommerceAspectItem totalForSale(Double totalForSale) {
        this.totalForSale = totalForSale;
        return this;
    }

    public CommerceAspectItem serialNumbers(Entity... serialNumbers) {
        this.serialNumbers.addAll(Arrays.asList(serialNumbers));
        return this;
    }

    public CommerceAspectItem validFrom(Date validFrom, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);

        this.validFrom = formatter.format(validFrom);
        return this;
    }

    public CommerceAspectItem validFrom(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.validFrom = timestamp;
        return this;
    }

    public CommerceAspectItem validUntil(Date validUntil, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);

        this.validUntil = formatter.format(validUntil);
        return this;
    }

    public CommerceAspectItem validUntil(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.validUntil = timestamp;
        return this;
    }

    public CommerceAspectItem accountingKey(String accountingKey) {
        this.accountingKey = accountingKey;
        return this;
    }

    public CommerceAspectItem properties() {
        return this;
    }

    public Map toJson(Set<String> processed) {
        Map obj = Factories.getMap();

        if (involved.length > 0) {
            List inv = new ArrayList();
            for (int i = 0; i < involved.length; i++) {
                inv.add(involved[i].toJson(processed));
            }
            obj.put("involves", inv);
        }

        if (accountingKey != null) obj.put("accounting_key", accountingKey);
        if (commissionFixed != null) obj.put("commission_fixed", commissionFixed);
        if (commissionPercentage != null) obj.put("commission_percentage", commissionPercentage);
        if (discountPercentage != null) obj.put("discount_percentage", discountPercentage);
        if (taxPercentage != null) obj.put("tax_percentage", taxPercentage);
        if (totalInStock != null) obj.put("total_in_stock", totalInStock);
        if (totalForSale != null) obj.put("total_for_sale", totalForSale);
        if (currency != null) obj.put("currency", currency);
        if (validFrom != null) obj.put("valid_from", validFrom);
        if (validUntil != null) obj.put("valid_until", validUntil);
        if (priceCategory != null) obj.put("price_category", priceCategory);
        if (variant != null) obj.put("variant", variant);
        if (description != null) obj.put("description", description);
        if (itemPrice != null) obj.put("item_price", itemPrice);
        if (itemCount != null) obj.put("item_count", itemCount);

        if (serialNumbers.size() > 0) {
            List serials = new ArrayList();
            for (Entity serialNumber : serialNumbers) {
                Map n = Factories.getMap();
                serialNumber.addToObject(n, processed);
                serials.add(n);
            }
            obj.put("serial_numbers", serials);
        }
        if (aspects.length > 0) {
            Map aspectsJson = Factories.getMap();
            for (Aspect aspect : aspects) {
                aspect.addToObject(aspectsJson, processed);
            }
            obj.put("aspects", aspectsJson);
        }
        return obj;
    }
}
