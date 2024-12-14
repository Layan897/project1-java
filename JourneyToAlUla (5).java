import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Booking {
    private ArrayList<String> bookings;
    private ArrayList<Double> bookingPrices;
    private double totalCost;

    public Booking() {
        this.bookings = new ArrayList<>();
        this.bookingPrices = new ArrayList<>();
        this.totalCost = 0.0;
    }

    public void addBooking(String booking, double cost) {
        bookings.add(booking);
        bookingPrices.add(cost);
        totalCost += cost;
        saveBookingToFile(booking, cost);
    }

    private void saveBookingToFile(String booking, double cost) {
        try (FileWriter writer = new FileWriter("bookings.txt", true)) {
            writer.write(booking + " - $" + String.format("%.2f", cost) + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void viewBookings() {
        System.out.println("Your Bookings:");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.printf("%s - $%.2f%n", bookings.get(i), bookingPrices.get(i));
        }
        System.out.printf("Total Cost: $%.2f%n", totalCost);

        System.out.println("\nBookings from file:");
        try (BufferedReader reader = new BufferedReader(new FileReader("bookings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous bookings found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    
    public void greet() {
        System.out.println("Hello, " + name + "!");
    }
}

class Customer extends User {
    String ipAddress;
    Booking booking;

    public Customer(String name) {
        super(name);
        this.booking = new Booking();
    }

    public void setIpAddress(String name, String phoneNumber) {
        this.ipAddress = name.substring(0, 2) + phoneNumber.substring(0, 2);
    }

    @Override
    public void greet() {
        System.out.println("Welcome, Customer " + name + "!");
    }

    public void viewBookings() {
        booking.viewBookings();
    }

    public void addBooking(String booking, double cost) {
        this.booking.addBooking(booking, cost);
    }
}

class Admin extends User {
    String username = "Admin";
    String password = "1234";
    ArrayList<Event> events;
    ArrayList<Food> foods;

    public Admin(String name, ArrayList<Event> events, ArrayList<Food> foods) {
        super(name);
        this.events = events;
        this.foods = foods;
    }

    @Override
    public void greet() {
        System.out.println("Hello, Admin " + name + "!");
    }

    public boolean login(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(this.username) && enteredPassword.equals(this.password);
    }
}

class Event {
    String name;
    String day;
    String location;
    double price;

    public Event(String name, String day, String location, double price) {
        this.name = name;
        this.day = day;
        this.location = location;
        this.price = price;
    }

    public static void addEvent(ArrayList<Event> events, String name, String day, String location, double price) {
        events.add(new Event(name, day, location, price));
        System.out.println("Event added successfully!");
    }

    public String getName() {
        return name;
    }

    public String getDay() {
        return day;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    
    public static void addEvent(ArrayList<Event> events, String name, String day, double price) {
        events.add(new Event(name, day, "Unknown", price));
        System.out.println("Event added with unknown location!");
    }
}

class Food {
    String name;
    double price;

    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public static void addFood(ArrayList<Food> foods, String name, double price) {
        foods.add(new Food(name, price));
        System.out.println("Food added successfully!");
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Hotel {
    String name;
    String roomType;
    double pricePerNight;

    public Hotel(String name, String roomType, double pricePerNight) {
        this.name = name;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    public String getName() {
        return name;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}

class Flight {
    String name;
    String seatType;
    String day;
    String time;
    double price;

    public Flight(String name, String seatType, String day, String time, double price) {
        this.name = name;
        this.seatType = seatType;
        this.day = day;
        this.time = time;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSeatType() {
        return seatType;
    }

    public String getDay() {
        return day;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }
}

class RentCar {
    String carType;
    double pricePerDay;

    public RentCar(String carType, double pricePerDay) {
        this.carType = carType;
        this.pricePerDay = pricePerDay;
    }

    public String getCarType() {
        return carType;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }
}

public class JourneyToAlUla {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        ArrayList<Event> events = new ArrayList<>();
        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Hotel> hotels = new ArrayList<>();
        ArrayList<Flight> flights = new ArrayList<>();
        ArrayList<RentCar> cars = new ArrayList<>();

        events.add(new Event("Desert Safari", "Friday", "North", 100));
        events.add(new Event("Museum", "Saturday", "West", 150));
        events.add(new Event("Hot Air Balloon", "Sunday", "South", 200));

        foods.add(new Food("Just breakfust", 25));
        foods.add(new Food("Just Lunch", 35));
        foods.add(new Food("Just Dinner", 45));
        foods.add(new Food("All Meals Plus desserts", 95));
        
        

        hotels.add(new Hotel("AlUla Golden", "Deluxe room ", 100));
        hotels.add(new Hotel("AlUla Golden", "Standard room ", 30));
        hotels.add(new Hotel("AlUla Golden", "Suite room ", 150));
        hotels.add(new Hotel("AlUla Golden", "Normal room ", 50));

        flights.add(new Flight("Saudi Arabian Airlines", "Economy", "Friday", "10:00 AM", 100));
        flights.add(new Flight("Saudi Arabian Airlines", "Business", "Saturday", "2:00 PM", 200));
        flights.add(new Flight("Saudi Arabian Airlines", "First Class", "Sunday", "6:00 PM", 300));

        cars.add(new RentCar("Sedan", 50));
        cars.add(new RentCar("SUV", 100));
        cars.add(new RentCar("Luxury", 200));

        Admin admin = new Admin("Admin", events, foods);
        Customer customer = new Customer("Customer");

        while (!exit) {
            System.out.println("Welcome to Journey to AlUla!");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.greet(); 
                    System.out.println("Admin Login:");
                    System.out.print("Username: ");
                    String adminUsername = scanner.nextLine();
                    System.out.print("Password: ");
                    String adminPassword = scanner.nextLine();

                    if (admin.login(adminUsername, adminPassword)) {
                        System.out.println("Admin logged in successfully!");
                        boolean adminExit = false;
                        while (!adminExit) {
                            System.out.println("Admin Panel:");
                            System.out.println("1. Add Event");
                            System.out.println("2. Add Food");
                            System.out.println("3. Back to Main Menu");

                            int adminChoice = scanner.nextInt();
                            scanner.nextLine();

                            switch (adminChoice) {
                                case 1:
                                    System.out.println("Enter new event details (Format: Name, Day, Location, Price):");
                                    String[] eventDetails = scanner.nextLine().split(", ");
                                    Event.addEvent(events, eventDetails[0], eventDetails[1], eventDetails[2], Double.parseDouble(eventDetails[3]));
                                    break;

                                case 2:
                                    System.out.println("Enter new food details (Format: Name, Price):");
                                    String[] foodDetails = scanner.nextLine().split(", ");
                                    Food.addFood(foods, foodDetails[0], Double.parseDouble(foodDetails[1]));
                                    break;

                                case 3:
                                    adminExit = true;
                                    break;

                                default:
                                    System.out.println("Invalid choice! Try again.");
                            }
                        }
                    } else {
                        System.out.println("Invalid login!");
                    }
                    break;

                case 2:
                    customer.greet(); 
                    System.out.println("Enter your name: ");
                    String customerName = scanner.nextLine();
                    System.out.print("Enter your phone number: ");
                    String customerPhoneNumber = scanner.nextLine();
                    customer.setIpAddress(customerName, customerPhoneNumber);
                    System.out.println("Your IP Address: " + customer.ipAddress);

                    boolean customerExit = false;
                    while (!customerExit) {
                        System.out.println("Customer Menu:");
                        System.out.println("1. Book Flight");
                        System.out.println("2. Book Hotel");
                        System.out.println("3. Rent Car");
                        System.out.println("4. Book Event");
                        System.out.println("5. View Bookings");
                        System.out.println("6. Exit");

                        int customerMenuChoice = scanner.nextInt();
                        scanner.nextLine();

                        switch (customerMenuChoice) {
                            case 1:
                                System.out.println("Available Flights:");
                                for (int i = 0; i < flights.size(); i++) {
                                    Flight flight = flights.get(i);
                                    System.out.println((i + 1) + ". " + flight.getName() + " - " 
                                        + flight.getSeatType() + " - " + flight.getDay() + " - " 
                                        + flight.getTime() + " - $" + flight.getPrice());
                                }
                                System.out.print("Enter your choice: ");
                                int flightChoice = scanner.nextInt();
                                if (flightChoice >= 1 && flightChoice <= flights.size()) {
                                    Flight flight = flights.get(flightChoice - 1);
                                    customer.addBooking("Flight: " + flight.getSeatType(), flight.getPrice());
                                }
                                break;

                            case 2:
                                System.out.println("Available Hotels:");
                                for (int i = 0; i < hotels.size(); i++) {
                                    Hotel hotel = hotels.get(i);
                                    System.out.println((i + 1) + ". " + hotel.getName() + " - " 
                                        + hotel.getRoomType() + " - $" + hotel.getPricePerNight() + " per night");
                                }
                                System.out.print("Enter your choice: ");
                                int hotelChoice = scanner.nextInt();
                                if (hotelChoice >= 1 && hotelChoice <= hotels.size()) {
                                    Hotel hotel = hotels.get(hotelChoice - 1);
                                    System.out.print("How many nights do you want to stay? ");
                                    int nights = scanner.nextInt();
                                    double totalCost = hotel.getPricePerNight() * nights;
                                    customer.addBooking("Hotel: " + hotel.getRoomType(), totalCost);

                                    
                                    System.out.println("Food Services");
                                    for (int i = 0; i < foods.size(); i++) {
                                        System.out.println((i + 1) + ". " + foods.get(i).getName() + " - $" + foods.get(i).getPrice());
                                    }
                                    System.out.print("Select food: ");
                                    int foodChoice = scanner.nextInt();
                                    if (foodChoice >= 1 && foodChoice <= foods.size()) {
                                        Food food = foods.get(foodChoice - 1);
                                        customer.addBooking("Food: " + food.getName(), food.getPrice());
                                        System.out.println("Food added successfully!");
                                    }
                                }
                                break;

                            case 3:
                                System.out.println("Available Cars:");
                                for (int i = 0; i < cars.size(); i++) {
                                    RentCar car = cars.get(i);
                                    System.out.println((i + 1) + ". " + car.getCarType() + " - $" + car.getPricePerDay() + " per day");
                                }
                                System.out.print("Enter your choice: ");
                                int carChoice = scanner.nextInt();
                                if (carChoice >= 1 && carChoice <= cars.size()) {
                                    RentCar car = cars.get(carChoice - 1);
                                    System.out.print("How many days do you want to rent the car? ");
                                    int days = scanner.nextInt();
                                    double totalCost = car.getPricePerDay() * days;
                                    customer.addBooking("Car: " + car.getCarType(), totalCost);
                                }
                                break;

                            case 4:
                                System.out.println("Available Events:");
                                for (int i = 0; i < events.size(); i++) {
                                    Event event = events.get(i);
                                    System.out.println((i + 1) + ". " + event.getName() + " - " 
                                        + event.getDay() + " - " + event.getLocation() + " - $" 
                                        + event.getPrice());
                                }
                                System.out.print("Enter your choice: ");
                                int eventChoice = scanner.nextInt();
                                if (eventChoice >= 1 && eventChoice <= events.size()) {
                                    Event event = events.get(eventChoice - 1);
                                    customer.addBooking("Event: " + event.getName(), event.getPrice());
                                }
                                break;

                            case 5:
                                customer.viewBookings();
                                break;

                            case 6:
                                customerExit = true;
                                break;

                            default:
                                System.out.println("Invalid choice! Try again.");
                        }
                    }
                    break;

                case 3:
                    
                    exit = true;
                    System.out.println("Thank you for using our program. Enjoy your trip :) ");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }

        scanner.close();
    }
}