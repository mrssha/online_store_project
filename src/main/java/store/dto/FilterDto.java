package store.dto;

import javax.validation.constraints.Positive;

public class FilterDto {

    private String category;

    private String brand;

    @Positive(message = "Min price should be greater than 0")
    private Integer minPrice;

    @Positive(message = "Max price should be greater than 0")
    private Integer maxPrice;
}
