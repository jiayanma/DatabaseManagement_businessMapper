package model;

import java.util.Objects;

/**
 * The type Region house rental.
 */
public class RegionHouseRental extends Region{
    private Double rental2020;
    private Double rental2021;
    private Double rental2022;

    /**
     * Instantiates a new Region house rental.
     *
     * @param ZipCode the zip code
     */
    public RegionHouseRental(String ZipCode) {
        super(ZipCode);
    }

    /**
     * Instantiates a new Region house rental.
     *
     * @param ZipCode   the zip code
     * @param value2020 the value 2020
     * @param value2021 the value 2021
     * @param value2022 the value 2022
     */
    public RegionHouseRental(String ZipCode, Double value2020, Double value2021, Double value2022){
        super(ZipCode);
        this.rental2020 = value2020;
        this.rental2021 = value2022;
        this.rental2022 = value2022;
    }

    /**
     * Gets rental 2020.
     *
     * @return the rental 2020
     */
    public Double getRental2020() {
        return rental2020;
    }

    /**
     * Sets rental 2020.
     *
     * @param rental2020 the rental 2020
     */
    public void setRental2020(Double rental2020) {
        this.rental2020 = rental2020;
    }

    /**
     * Gets rental 2021.
     *
     * @return the rental 2021
     */
    public Double getRental2021() {
        return rental2021;
    }

    /**
     * Sets rental 2021.
     *
     * @param rental2021 the rental 2021
     */
    public void setRental2021(Double rental2021) {
        this.rental2021 = rental2021;
    }

    /**
     * Gets rental 2022.
     *
     * @return the rental 2022
     */
    public Double getRental2022() {
        return rental2022;
    }

    /**
     * Sets rental 2022.
     *
     * @param rental2022 the rental 2022
     */
    public void setRental2022(Double rental2022) {
        this.rental2022 = rental2022;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RegionHouseRental)) return false;
        RegionHouseRental that = (RegionHouseRental) o;
        return rental2020.equals(that.rental2020) && rental2021.equals(that.rental2021) && rental2022.equals(that.rental2022);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rental2020, rental2021, rental2022);
    }

    @Override
    public String toString() {
        return "RegionHouseValue{" +
                "value2020=" + rental2020 +
                ", value2021=" + rental2021 +
                ", value2022=" + rental2022 +
                '}';
    }
}
