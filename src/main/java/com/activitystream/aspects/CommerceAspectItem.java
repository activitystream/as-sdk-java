package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.Entity;
import com.activitystream.EntityRole;
import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;

import java.util.*;

public class CommerceAspectItem {
    private EntityRole[] involved = new EntityRole[]{};
    private Aspect[] aspects = new Aspect[]{};

    public CommerceAspectItem involves(EntityRole... role) {
        this.involved = role;
        return this;
    }

    public CommerceAspectItem aspects(Aspect... aspects) {
        this.aspects = aspects;
        return this;
    }

    private Double commissionFixed;

    public CommerceAspectItem commissionFixed(Double commissionFixed) {
        this.commissionFixed = commissionFixed;
        return this;
    }

    private Double commissionPercentage;

    public CommerceAspectItem commissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
        return this;
    }

    private Double discountPercentage;

    public CommerceAspectItem discountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    private Double taxPercentage;

    public CommerceAspectItem taxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
        return this;
    }

    private Integer itemCount;

    public CommerceAspectItem itemCount(Integer itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    private Double itemPrice;

    public CommerceAspectItem itemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    private String description;

    public CommerceAspectItem description(String description) {
        this.description = description;
        return this;
    }

    private String variant;

    public CommerceAspectItem variant(String variant) {
        this.variant = variant;
        return this;
    }

    private String priceCategory;

    public CommerceAspectItem priceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
        return this;
    }

    private String currency;

    public CommerceAspectItem currency(String currency) {
        this.currency = currency;
        return this;
    }

    private Double totalInStock;

    public CommerceAspectItem totalInStock(Double totalInStock) {
        this.totalInStock = totalInStock;
        return this;
    }

    private Double totalForSale;

    public CommerceAspectItem totalForSale(Double totalForSale) {
        this.totalForSale = totalForSale;
        return this;
    }

    private List<Entity> serialNumbers = new ArrayList<>();

    public CommerceAspectItem serialNumbers(Entity... serialNumbers) {
        this.serialNumbers.addAll(Arrays.asList(serialNumbers));
        return this;
    }

    private Date validFrom;

    public CommerceAspectItem validFrom(Date validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    private Date validUntil;

    public CommerceAspectItem validUntil(Date validUntil) {
        this.validUntil = validUntil;
        return this;
    }

    private String accountingKey;

    public CommerceAspectItem accountingKey(String accountingKey) {
        this.accountingKey = accountingKey;
        return this;
    }

    public CommerceAspectItem occured(Date timestamp) {
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
        if (validFrom != null) obj.put("valid_from", DateHelpers.dateFormatter.format(validFrom));
        if (validUntil != null) obj.put("valid_until", DateHelpers.dateFormatter.format(validUntil));
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
