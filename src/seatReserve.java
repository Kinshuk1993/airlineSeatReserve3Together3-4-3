import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class seatReserve {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solution(int N, String S) {
		//variable to store hashmap to store key value pairs where keys are the row number and the values are the rows having seats
		//with 3-4-3 internal lists
		Map<Integer, List<List<Integer>>> allAirplaneRows = new HashMap<>();
		//to store splitted input
		String[] occupiedSeatList = null;
		int possibleFamilySeatingArrangements = 0;
		//split by the space if not empty input
		if (S.trim().length() > 0) {
			occupiedSeatList = S.split(" ");
		}
		
		//nested loop to create seating arrangement, put into row which is put into a collection of rows
		for (int rowCounter = 1; rowCounter <= N; rowCounter++) {
			//to store seating arrangement of a row
			List<List<Integer>> singleRow = new ArrayList<>(3);
			//to store first 3 seats
			List<Integer> sectionOne = new ArrayList<Integer>(3);
			sectionOne.add(0);
			sectionOne.add(0);
			sectionOne.add(0);
			//to store 4 seats in the middle
			List<Integer> sectionTwo = new ArrayList<Integer>(4);
			sectionTwo.add(0);
			sectionTwo.add(0);
			sectionTwo.add(0);
			sectionTwo.add(0);
			//to store 3 seats on the other end of the plane
			List<Integer> sectionThree = new ArrayList<Integer>(3);
			sectionThree.add(0);
			sectionThree.add(0);
			sectionThree.add(0);
			//add seating arrangement to one row to make it 3-4-3
			singleRow.add(sectionOne);
			singleRow.add(sectionTwo);
			singleRow.add(sectionThree);
			//add the row to the collection of rows of a airplane
			allAirplaneRows.put(rowCounter, singleRow);
			//checking if assigned seats is not null to avoid changing of 0 to 1 in each row arrangement
			if (S.trim().length() > 0) {
				//for every assigned seats, loop to change the originally saved row data from 0 to 1
				for (int eachOccupiedSeatCounter = 0; eachOccupiedSeatCounter < occupiedSeatList.length; eachOccupiedSeatCounter++) {
					//take first seat
					String occupiedSeat = occupiedSeatList[eachOccupiedSeatCounter];
					//take seat alphabet number
					String occupiedSeatAlphabet = occupiedSeat.substring(1);
					//take input seat row number
					int occupiedSeatRow = Integer.parseInt(occupiedSeat.substring(0, 1));
					//check if match is found
					if (occupiedSeatRow == rowCounter) {
						//cases to change values of assigned seats according to seat alphabet
						switch (occupiedSeatAlphabet) {
						case "A":
							//change c1 0th index value to 1 if occupied seat is A
							allAirplaneRows.get(rowCounter).get(0).set(0, 1);
							break;
						case "B":
							//change c1 1st index value if occupied seat is B
							allAirplaneRows.get(rowCounter).get(0).set(1, 1);
							break;
						case "C":
							//change c1 2nd index value if occupied seat is C
							allAirplaneRows.get(rowCounter).get(0).set(2, 1);
							break;
						case "D":
							//change c2 0th index value if occupied seat is D
							allAirplaneRows.get(rowCounter).get(1).set(0, 1);
							break;
						case "E":
							//change c2 1st index value if occupied seat is E
							allAirplaneRows.get(rowCounter).get(1).set(1, 1);
							break;
						case "F":
							//change c2 2nd index value if occupied seat is F
							allAirplaneRows.get(rowCounter).get(1).set(2, 1);
							break;
						case "G":
							//change c2 3rd index value if occupied seat is G
							allAirplaneRows.get(rowCounter).get(1).set(3, 1);
							break;
						case "H":
							//change c3 0th index value if occupied seat is H
							allAirplaneRows.get(rowCounter).get(2).set(0, 1);
							break;
						case "J":
							//change c3 1st index value if occupied seat is J
							allAirplaneRows.get(rowCounter).get(2).set(1, 1);
							break;
						case "K":
							//change c3 2nd index value if occupied seat is K
							allAirplaneRows.get(rowCounter).get(2).set(2, 1);
							break;
						}
					}
				}
			}
		}
		
		//loop through all rows one by one till N and check if 3 seats together are empty or not
		for (int rowCounter = 1; rowCounter <= N; rowCounter++) {
			//if section 1 contains an occupied seat, then not possible to assign 3 seats
			if (!allAirplaneRows.get(rowCounter).get(0).contains(1)) {
				//increment possible family seating
				possibleFamilySeatingArrangements++;
			}
			//if section 2 has no occupied seats 
			if (!allAirplaneRows.get(rowCounter).get(1).contains(1)) {
				//increment possible family seating
				possibleFamilySeatingArrangements++;
			}
			//if section 3 has 2nd and 3rd seat empty, then check if either 1st or 4th seat in section 2 is empty 
			else if (allAirplaneRows.get(rowCounter).get(1).get(1) == 0 && allAirplaneRows.get(rowCounter).get(1).get(2) == 0) {
				 if (allAirplaneRows.get(rowCounter).get(1).get(0) == 0 || allAirplaneRows.get(rowCounter).get(1).get(3) == 0) {
					//increment possible family seating if above conditions fulfill
					 possibleFamilySeatingArrangements++;
				}
			}
			//if section 3 has no occupied seat
			if (!allAirplaneRows.get(rowCounter).get(2).contains(1)) {
				//increment possible family seating
				possibleFamilySeatingArrangements++;
			}
		}
		//return the final result after all rows have been checked
		return possibleFamilySeatingArrangements;
	}
}
