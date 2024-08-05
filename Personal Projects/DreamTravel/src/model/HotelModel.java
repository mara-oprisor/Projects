package model;


import java.util.ArrayList;

public class HotelModel {
    private HotelName hotelName;
    private String description;
    private String photoPath;
    private CountryName countryName;

    public HotelName getHotelName() {
        return hotelName;
    }

    public void setHotelName(model.HotelName hotelName) {
        this.hotelName = hotelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public static HotelModel createHotel(HotelName hotelName, String description, String photoPath, CountryName countryName) {
        HotelModel hotel = new HotelModel();
        hotel.hotelName = hotelName;
        hotel.description = description;
        hotel.photoPath = photoPath;
        hotel.countryName = countryName;

        return hotel;
    }

    public static ArrayList<HotelModel> addHotels() {

        ArrayList<HotelModel> hotels = new ArrayList<>();
        String description1 = "<html><body>"
                + "<p><b>LAUR HOTELS Experience & Elegance</b> is located along the beach of Altinkum. It features 3 restaurants and 4 bars, and a full-service spa. Free sun beds and beach towels are offered to hotel guests.</p>"
                + "<p>The rooms of the hotel offer air-conditioned rooms with sea or garden views. Each has a pleasant décor and comes equipped with an LCD TV, mini-bar, hairdryer, and telephone. Shower cabins or tubs are available in the bathrooms. WiFi is free throughout.</p>"
                + "<p>Guests at LAUR HOTELS Experience & Elegance can choose from a variety of dining options, including outdoor and indoor. Buffet meals are served at the main restaurant, while drinks and a lighter fare are provided at the beach bar, pool bar, and lobby bars.</p>"
                + "<p>LAUR HOTELS Experience & Elegance features a pool with slides, a children's pool, and a main swimming pool. The spa comes complete with a hammam, sauna, hot tub, massage rooms, and a fitness center. A diving school and water sports are featured on the beach.</p>"
                + "</body></html>";
        HotelModel hotel1 = createHotel(HotelName.LAUR_HOTELS, description1, "src/images/hotel1 (1).jpg", CountryName.Turkey);

        String description2 = """
                <html>
                  <body>
                    <p>
                      <b>OZEN RESERVE BOLIFUSHI</b> is located in a spot of graceful elegance, offering excitement within endless beauty. Hidden beneath lush tropical vegetation, the resort island is surrounded by expansive white beaches and turquoise waters. The property is a 35-minute luxury catamaran or speedboat ride from Velana International Airport.
                    </p>
                    <p>
                      Our All-Inclusive - The RESERVE Plan includes complimentary Return Airport transfers by Luxury Catamaran, specialty Fine Dining experiences, premium brands of spirits, beer, wines, and cocktails, use of snorkeling equipment & non-motorized water sports, and minibar replenished once daily.
                    </p>
                    <p>
                      Specialty Fine Dining experiences and Spa experiences are applicable for stays 4 nights and above.
                    </p>
                    <p>
                      Dine in Maldivian splendour with sublime views of the Indian Ocean. The resort offers a variety of dining experiences and cuisines, including traditional Indian cuisine, refreshing Middle Eastern dishes by the beach, and gourmet seafood dishes - paired with excellent wines and champagnes.
                    </p>
                  </body>
                </html>""";
        HotelModel hotel2 = createHotel(HotelName.OZEN_RESERVE_BOLIFUSHI, description2, "src/images/hotel2 (1).jpg", CountryName.Maldives);

        String description3 = """
                <html>
                <body>
                  <p>
                    Occupying levels 34-52, the <b>SHANGRI-LA HOTEL THE SHARD, LONDON</b> offers 5-star luxury and breathtaking views of the capital and beyond. It features an exquisite restaurant and serves cocktails until late at the highest bar in London.
                  </p>
                  <p>
                    Each uniquely designed guest room has floor-to-ceiling windows providing spectacular views of the city and maintains the theme of Oriental elegance found throughout the hotel. Featuring the body-contouring-technology ‘Shangri-La Bed’, rooms include a TV, coffee machine, complimentary WiFi, and a set of binoculars.
                  </p>
                  <p>
                    The en suite, marble-clad bathrooms have underfloor heating, separate bath tubs, and a glass-enclosed shower. They include plush bathrobes, Acqua di Parma toiletries, and mirrors with integrated TV screens. All rooms feature climate control to complement the building’s integrated sun shielding.
                  </p>
                  <p>
                    The hotel features 3 dining and drinking venues, including TING, the signature restaurant and lounge on level 35, while Bar 31 is a friendly neighborhood bar. GŎNG, set on level 52, provides the setting for London's highest champagne and cocktail bar.
                  </p>
                </body>
                </html>
                """;
        HotelModel hotel3 = createHotel(HotelName.SHANGRI_LA_THE_SHARD, description3, "src/images/hotel3 (1).jpg", CountryName.UK);

        String description4 = """
                <html>
                  <body>
                    <p>
                      Set in Estepona, less than 1 km from Guadalmansa Beach, <b>IKOS ANDALUSIA</b> offers accommodation with a fitness centre, free private parking, a garden, and a terrace. Among the facilities at this property are a free shuttle service and room service, along with free WiFi throughout the property. The resort features an outdoor swimming pool, sauna, evening entertainment, and a kids' club.
                    </p>
                    <p>
                      The resort will provide guests with air-conditioned rooms offering a desk, a coffee machine, a fridge, a minibar, a safety deposit box, a flat-screen TV, and a private bathroom with a bath or shower. At IKOS ANDALUSIA, every room has bed linen and towels.
                    </p>
                    <p>
                      At the accommodation, you will find a restaurant serving French, Greek, and Italian cuisine. Vegetarian and gluten-free options can also be requested.
                    </p>
                    <p>
                      IKOS ANDALUSIA offers guests a wellness area with a hot tub, a hammam, and an indoor pool. You can play table tennis and tennis at the resort, and the area is popular for canoeing and cycling.
                    </p>
                  </body>
                </html>
                """;
        HotelModel hotel4 = createHotel(HotelName.IKOS_ANDALUSIA, description4, "src/images/hotel4 (1).jpg", CountryName.Spain);

        String description5 = """
                <html>
                  <body>
                    <p>
                      Featuring ski-to-door access, Madonna di Campiglio's <b>CHALET DEL SOGNO</b> offers a free wellness centre and Alpine-style rooms with mountain views. This eco-friendly property is located 25 meters from the Express Spinale ski lifts, while the town center is a 5-minute walk away.
                    </p>
                    <p>
                      All rooms have a spacious balcony, an LED TV, and a bath or shower with Teuco hydromassage jets. A pillow-menu is available on request.
                    </p>
                    <p>
                      After a day on the slopes, guests can unwind at the modern Oasi del Sogno spa, which includes a heated indoor pool with hydromassage jets, a Turkish bath, 2 saunas, and a gym. The relaxation area is equipped with chromotherapy lighting and water beds. Massages and beauty treatments can also be reserved.
                    </p>
                    <p>
                      The extensive sweet and savory buffet breakfast includes local, organic food.
                    </p>
                  </body>
                </html>
                """;
        HotelModel hotel5 = createHotel(HotelName.CHALET_DEL_SOGNO, description5, "src/images/hotel5 (1).jpg", CountryName.Italy);

        hotels.add(hotel1);
        hotels.add(hotel2);
        hotels.add(hotel3);
        hotels.add(hotel4);
        hotels.add(hotel5);

        return hotels;
    }
}
