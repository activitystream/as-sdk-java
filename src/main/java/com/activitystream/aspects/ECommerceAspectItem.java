package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.DateHelpers;
import com.activitystream.EntityLike;
import com.activitystream.Role;

import java.util.*;

public class ECommerceAspectItem {
    private Role[] involved = new Role[]{};
    private Aspect[] aspects = new Aspect[]{};

    public ECommerceAspectItem involves(Role... role) {
        this.involved = role;
        return this;
    }

    public ECommerceAspectItem aspects(Aspect... aspects) {
        this.aspects = aspects;
        return this;
    }

    private Double commissionFixed;

    public ECommerceAspectItem commissionFixed(Double commissionFixed) {
        this.commissionFixed = commissionFixed;
        return this;
    }

    private Double commissionPercentage;

    public ECommerceAspectItem commissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
        return this;
    }

    private Double discountPercentage;

    public ECommerceAspectItem discountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    private Double taxPercentage;

    public ECommerceAspectItem taxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
        return this;
    }

    private Integer itemCount;

    public ECommerceAspectItem itemCount(Integer itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    private Double itemPrice;

    public ECommerceAspectItem itemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    private String description;

    public ECommerceAspectItem description(String description) {
        this.description = description;
        return this;
    }

    private String variant;

    public ECommerceAspectItem variant(String variant) {
        this.variant = variant;
        return this;
    }

    private String priceCategory;

    public ECommerceAspectItem priceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
        return this;
    }

    private String currency;

    public ECommerceAspectItem currency(String currency) {
        this.currency = currency;
        return this;
    }

    private Double totalInStock;

    public ECommerceAspectItem totalInStock(Double totalInStock) {
        this.totalInStock = totalInStock;
        return this;
    }

    private Double totalForSale;

    public ECommerceAspectItem totalForSale(Double totalForSale) {
        this.totalForSale = totalForSale;
        return this;
    }

    private List<EntityLike> serialNumbers = new ArrayList<>();

    public ECommerceAspectItem serialNumbers(EntityLike... serialNumbers) {
        this.serialNumbers.addAll(Arrays.asList(serialNumbers));
        return this;
    }

    private Date validFrom;

    public ECommerceAspectItem validFrom(Date validFrom) {
        this.validFrom = validFrom;
        return this;
    }

    private Date validUntil;

    public ECommerceAspectItem validUntil(Date validUntil) {
        this.validUntil = validUntil;
        return this;
    }

    private String accountingKey;

    public ECommerceAspectItem accountingKey(String accountingKey) {
        this.accountingKey = accountingKey;
        return this;
    }

    public ECommerceAspectItem occured(Date timestamp) {
        return this;
    }

    public ECommerceAspectItem properties() {
        return this;
    }

    public Map toJson() {
        Map obj = new HashMap();

        if (involved.length > 0) {
            List inv = new ArrayList();
            for (int i = 0; i < involved.length; i++) {
                inv.add(involved[i].toJson());
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
        if (validFrom != null) obj.put("valid_from", DateHelpers.isoDateFormatter.format(validFrom));
        if (validUntil != null) obj.put("valid_until", DateHelpers.isoDateFormatter.format(validUntil));
        if (priceCategory != null) obj.put("price_category", priceCategory);
        if (variant != null) obj.put("variant", variant);
        if (description != null) obj.put("description", description);
        if (itemPrice != null) obj.put("item_price", itemPrice);
        if (itemCount != null) obj.put("item_count", itemCount);

        if (serialNumbers.size() > 0) {
            List serials = new ArrayList();
            for (EntityLike serialNumber : serialNumbers) {
                Map n = new HashMap();
                serialNumber.addToObject(n);
                serials.add(n);
            }
            obj.put("serial_numbers", serials);
        }
        if (aspects.length > 0) {
            Map aspectsJson = new HashMap();
            for (Aspect aspect : aspects) {
                aspect.addToObject(aspectsJson);
            }
            obj.put("aspects", aspectsJson);
        }
        return obj;
    }
}
