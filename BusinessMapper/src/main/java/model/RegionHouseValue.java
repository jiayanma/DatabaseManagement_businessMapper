package model;

import java.util.Objects;

/**
 * The type Region house value.
 */
public class RegionHouseValue extends Region{
    private Double value2020;
    private Double value2021;
    private Double value2022;

    /**
     * Instantiates a new Region house value.
     *
     * @param ZipCode the zip code
     */
    public RegionHouseValue(String ZipCode) {
        super(ZipCode);
    }

    /**
     * Instantiates a new Region house value.
     *
     * @param ZipCode   the zip code
     * @param value2020 the value 2020
     * @param value2021 the value 2021
     * @param value2022 the value 2022
     */
    public RegionHouseValue(String ZipCode, Double value2020, Double value2021, Double value2022){
        super(ZipCode);
        this.value2020 = value2020;
        this.value2021 = value2022;
        this.value2022 = value2022;
    }

    /**
     * Gets value 2020.
     *
     * @return the value 2020
     */
    public Double getValue2020() {
        return value2020;
    }

    /**
     * Sets value 2020.
     *
     * @param value2020 the value 2020
     */
    public void setValue2020(Double value2020) {
        this.value2020 = value2020;
    }

    /**
     * Gets value 2021.
     *
     * @return the value 2021
     */
    public Double getValue2021() {
        return value2021;
    }

    /**
     * Sets value 2021.
     *
     * @param value2021 the value 2021
     */
    public void setValue2021(Double value2021) {
        this.value2021 = value2021;
    }

    /**
     * Gets value 2022.
     *
     * @return the value 2022
     */
    public Double getValue2022() {
        return value2022;
    }

    /**
     * Sets value 2022.
     *
     * @param value2022 the value 2022
     */
    public void setValue2022(Double value2022) {
        this.value2022 = value2022;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegionHouseValue)) return false;
        RegionHouseValue that = (RegionHouseValue) o;
        return value2020.equals(that.value2020) && value2021.equals(that.value2021) && value2022.equals(that.value2022);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value2020, value2021, value2022);
    }

    @Override
    public String toString() {
        return "RegionHouseValue{" +
                "value2020=" + value2020 +
                ", value2021=" + value2021 +
                ", value2022=" + value2022 +
                '}';
    }
}
