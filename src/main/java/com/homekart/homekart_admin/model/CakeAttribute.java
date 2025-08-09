package com.homekart.homekart_admin.model;

import com.google.cloud.firestore.annotation.PropertyName;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CakeAttribute {
    private List<Variant> variants;
    private boolean isEgglessAvailable;

    @PropertyName("isEgglessAvailable")
    public Boolean getIsEgglessAvailable() {
        return isEgglessAvailable;
    }

    @PropertyName("isEgglessAvailable")
    public void setIsEgglessAvailable(Boolean isEgglessAvailable) {
        this.isEgglessAvailable = isEgglessAvailable;
    }
}
