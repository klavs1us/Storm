package fourSquare;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public class FourSquareUtility{	


	public String getTweetNationality(String ll) throws FoursquareApiException{

		FoursquareApi foursquareApi = new FoursquareApi("QDIG2SIMY43W5IHPYTHVUCMHAZD1CI5DZVRC2TID3D4WHWYV", "T0DI5TTRNTBU32Q1MAG0WWMECJQF0LJUGQM2VCQVMVUVGAV1", "http://www.4sqarestorm.it");

		String nationality = "";
		fi.foyt.foursquare.api.Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll, null, null, null, null, null, null, null, null, null, null);
		if (result.getMeta().getCode() == 200) {

			nationality = result.getResult().getVenues()[0].getLocation().getCountry().toString();
		}

		return nationality;

	}

	public String getBestCategory(String ll) throws FoursquareApiException{
		String category = "";
		FoursquareApi foursquareApi = new FoursquareApi("QDIG2SIMY43W5IHPYTHVUCMHAZD1CI5DZVRC2TID3D4WHWYV", "T0DI5TTRNTBU32Q1MAG0WWMECJQF0LJUGQM2VCQVMVUVGAV1", "http://www.4sqarestorm.it");
		fi.foyt.foursquare.api.Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll, null, null, null, null, null, null, null, null, null, null);
		if (result.getMeta().getCode() == 200) {
			Double distanceMin = 2000000.0;
			for (CompactVenue venue : result.getResult().getVenues()){
				if(venue.getLocation().getDistance() < distanceMin){
					try {
						category = venue.getCategories()[0].getName();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						category = "NOT FOUND";
					}
					distanceMin = venue.getLocation().getDistance();
				}
			}

		}




		return category;

	}





	public boolean isValidCategoryVenue(String ll,String category) throws FoursquareApiException {

		boolean isValidVenue = false;

		FoursquareApi foursquareApi = new FoursquareApi("QDIG2SIMY43W5IHPYTHVUCMHAZD1CI5DZVRC2TID3D4WHWYV", "T0DI5TTRNTBU32Q1MAG0WWMECJQF0LJUGQM2VCQVMVUVGAV1", "http://www.4sqarestorm.it");
		fi.foyt.foursquare.api.Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll, null, null, null, null, null, null, null, null, null, null);
		if (result.getMeta().getCode() == 200) {
			for (CompactVenue venue : result.getResult().getVenues()) {
				for (Category cat : venue.getCategories()){
					if(cat.getName().equals(category)) isValidVenue=true;

				}

			}
		} 
			else {
				System.out.println("Error occured: ");
				System.out.println("  code: " + result.getMeta().getCode());
				System.out.println("  type: " + result.getMeta().getErrorType());
				System.out.println("  detail: " + result.getMeta().getErrorDetail()); 
			}

		return isValidVenue;
	}

	public boolean isValidNationVenue(String ll,String nation) throws FoursquareApiException{

		boolean isValidVenue = false;

		FoursquareApi foursquareApi = new FoursquareApi("QDIG2SIMY43W5IHPYTHVUCMHAZD1CI5DZVRC2TID3D4WHWYV", "T0DI5TTRNTBU32Q1MAG0WWMECJQF0LJUGQM2VCQVMVUVGAV1", "http://www.4sqarestorm.it");
		fi.foyt.foursquare.api.Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll, null, null, null, null, null, null, null, null, null, null);
		if (result.getMeta().getCode() == 200) {
			for (CompactVenue venue : result.getResult().getVenues()) {

					if(venue.getLocation().getCountry() == nation) isValidVenue=true;

				}

			}




		return isValidVenue;
	}





}
