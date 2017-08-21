package com.activitystream.aspects;

import com.activitystream.Aspect;
import com.activitystream.Entity;
import com.activitystream.EntityRole;
import com.activitystream.helpers.DateHelpers;
import com.activitystream.underware.Factories;
import com.activitystream.underware.Tuple;

import java.text.SimpleDateFormat;
import java.util.*;

public class AspectItem {
    private List<EntityRole> involved = new ArrayList<>();
    private List<Aspect> aspects = new ArrayList<>();
    private Double commissionFixed;
    private Double commissionPercentage;
    private Double discountPercentage;
    private Double taxPercentage;
    private Integer itemCount;
    private Double itemPrice;
    private String description;
    private String type;
    private String variant;
    private String priceType;
    private String priceCategory;
    private String currency;
    private Double totalInStock;
    private Double totalForSale;
    private List<Entity> serialNumbers = new ArrayList<>();
    private String validFrom;
    private String validUntil;
    private String accountingKey;
    private Map<String, String> dimensions;
    private Map<String, String> properties;
    private String complementary;
    private Set<String> lineIds;

    public AspectItem involves(EntityRole... roles) {
        this.involved.addAll(Arrays.asList(roles));
        return this;
    }

    public AspectItem aspects(Aspect... aspects) {
        this.aspects.addAll(Arrays.asList(aspects));
        return this;
    }

    public AspectItem commissionFixed(Double commissionFixed) {
        this.commissionFixed = commissionFixed;
        return this;
    }

    public AspectItem commissionPercentage(Double commissionPercentage) {
        this.commissionPercentage = commissionPercentage;
        return this;
    }

    public AspectItem discountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
        return this;
    }

    public AspectItem taxPercentage(Double taxPercentage) {
        this.taxPercentage = taxPercentage;
        return this;
    }

    public AspectItem itemCount(Integer itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public AspectItem itemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    public AspectItem description(String description) {
        this.description = description;
        return this;
    }

    public AspectItem type(String type){
        this.type = type;
        return this;
    }

    public AspectItem variant(String variant) {
        this.variant = variant;
        return this;
    }
    
    public AspectItem complementary(String complementary) {
        this.complementary = complementary;
        return this;
    }

    public AspectItem priceType(String priceType) {
        this.priceType = priceType;
        return this;
    }

    public AspectItem priceCategory(String priceCategory) {
        this.priceCategory = priceCategory;
        return this;
    }

    public AspectItem currency(String currency) {
        this.currency = currency;
        return this;
    }

    public AspectItem totalInStock(Double totalInStock) {
        this.totalInStock = totalInStock;
        return this;
    }

    public AspectItem totalForSale(Double totalForSale) {
        this.totalForSale = totalForSale;
        return this;
    }

    public AspectItem serialNumbers(Entity... serialNumbers) {
        this.serialNumbers.addAll(Arrays.asList(serialNumbers));
        return this;
    }

    public AspectItem validFrom(Date validFrom, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);

        this.validFrom = formatter.format(validFrom);
        return this;
    }

    public AspectItem validFrom(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.validFrom = timestamp;
        return this;
    }

    public AspectItem validUntil(Date validUntil, TimeZone timeZone) {
        SimpleDateFormat formatter = (SimpleDateFormat) DateHelpers.dateFormatter.clone();
        formatter.setTimeZone(timeZone);

        this.validUntil = formatter.format(validUntil);
        return this;
    }

    public AspectItem validUntil(String timestamp) {
        DateHelpers.validateDateString(timestamp);
        this.validUntil = timestamp;
        return this;
    }

    public AspectItem accountingKey(String accountingKey) {
        this.accountingKey = accountingKey;
        return this;
    }

    public AspectItem properties(Map<String, String> properties) {
    	if (this.properties == null) {
    		this.properties = new HashMap<String, String>();
    	}
    	this.properties.putAll(properties);
    	return this;
    }
    
    public AspectItem dimensions(Map<String, String> dimensions) {
    	if (this.dimensions == null) {
    		this.dimensions = new HashMap<String, String>();
    	}
    	this.dimensions.putAll(dimensions);
    	return this;
    }

    public AspectItem lineIds(Set<String> lineIds){
        if (this.lineIds == null){
            this.lineIds = new HashSet<String>();
        }
        this.lineIds.addAll(lineIds);
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
        obj.put("type",type);
        obj.put("variant", variant);
        obj.put("price_type",priceType);
        obj.put("price_category", priceCategory);
        obj.put("complementary", complementary);

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
        obj.put("dimensions", dimensions);
        obj.put("properties", properties);
        obj.put("line_ids", lineIds);
        
        return obj;
    }
}
