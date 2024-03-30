package ru.astaf.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BookingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingServiceApplication.class, args);
		/*ZonedDateTime start = ZonedDateTime.parse("2024-04-01T10:00:00+01:00");
		ZonedDateTime end = ZonedDateTime.parse("2024-04-10T10:00:00+01:00");
		Range<ZonedDateTime> bookingRange = Range.closed(start, end);
		System.out.println(bookingRange.asString());*/
    }

}
