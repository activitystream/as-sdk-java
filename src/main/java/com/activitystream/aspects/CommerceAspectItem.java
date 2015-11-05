package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.Entity;
import com.activitystream.EntityRole;
import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;
import com.activitystream.underware.Tuple;

import java.text.SimpleDateFormat;
import java.util.*;

public class CommerceAspectItem {
    private List<EntityRole> involved = new ArrayList<>();
    private List<Aspect> aspects = new ArrayList<>();
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

    public CommerceAspectItem involves(EntityRole... roles) {
        this.involved.addAll(Arrays.asList(roles));
        return this;
    }

    public CommerceAspectItem aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
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

        List inv = new ArrayList();
        for (EntityRole anInvolved : involved) {
            if (anInvolved != null) inv.add(anInvolved.render(processed));
        }
        obj.put("involves", inv);

        obj.put("accounting_key", accountingKey);
        obj.put("commission_fixed", commissionFixed);
        obj.put("commission_percentage", commissionPercentage);
        obj.put("discount_percentage", discountPercentage);
        obj.put("tax_percentage", taxPercentage);
        obj.put("total_in_stock", totalInStock);
        obj.put("total_for_sale", totalForSale);
        obj.put("currency", currency);
        obj.put("valid_from", validFrom);
        obj.put("valid_until", validUntil);
        obj.put("price_category", priceCategory);
        obj.put("variant", variant);
        obj.put("description", description);
        obj.put("item_price", itemPrice);
        obj.put("item_count", itemCount);

        List serials = new ArrayList();
        for (Entity serialNumber : serialNumbers) {
            if (serialNumber != null) {
                Map n = Factories.getMap();
                final Tuple<String, Object> entity = serialNumber.render(processed);
                if (entity != null){
                    n.put(entity.x, entity.y);
                    serials.add(n);
                }
            }
        }
        obj.put("serial_numbers", serials);
        Map aspectsJson = Factories.getMap();
        for (Aspect aspect : aspects) {
            if (aspect != null) aspect.addToObject(aspectsJson, processed);
        }
        obj.put("aspects", aspectsJson);
        return obj;
    }
}
