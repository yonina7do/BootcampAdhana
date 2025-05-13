package com.yoninaldo.dealership;

public enum VehicleType {
    CAR("car"),
    TRUCK("truck"),
    SUV("suv"),
    VAN("van"),
    CONVERTIBLE("convertible"),
    SPORTS("sports"),
    LUXURY("luxury");

    private final String displayName;

    VehicleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static VehicleType fromString(String text) {
        if (text == null || text.isEmpty()) {
            return CAR;
        }

        for (VehicleType type : VehicleType.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return type;
            }
        }

        return CAR;
    }

    @Override
    public String toString() {
        return displayName;
    }
}