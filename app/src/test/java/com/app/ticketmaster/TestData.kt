package com.app.ticketmaster

import com.app.ticketmaster.model.Accessibility
import com.app.ticketmaster.model.AgeRestrictions
import com.app.ticketmaster.model.AllInclusivePricing
import com.app.ticketmaster.model.AttractionX
import com.app.ticketmaster.model.City
import com.app.ticketmaster.model.Classification
import com.app.ticketmaster.model.Dates
import com.app.ticketmaster.model.DoorsTimes
import com.app.ticketmaster.model.Embedded
import com.app.ticketmaster.model.Event
import com.app.ticketmaster.model.First
import com.app.ticketmaster.model.Genre
import com.app.ticketmaster.model.ImageXX
import com.app.ticketmaster.model.InitialStartDate
import com.app.ticketmaster.model.Last
import com.app.ticketmaster.model.LinksXX
import com.app.ticketmaster.model.LinksXXX
import com.app.ticketmaster.model.Next
import com.app.ticketmaster.model.Outlet
import com.app.ticketmaster.model.Page
import com.app.ticketmaster.model.Presale
import com.app.ticketmaster.model.PriceRange
import com.app.ticketmaster.model.Promoter
import com.app.ticketmaster.model.Public
import com.app.ticketmaster.model.SafeTix
import com.app.ticketmaster.model.Sales
import com.app.ticketmaster.model.Seatmap
import com.app.ticketmaster.model.Segment
import com.app.ticketmaster.model.Self
import com.app.ticketmaster.model.Start
import com.app.ticketmaster.model.State
import com.app.ticketmaster.model.Status
import com.app.ticketmaster.model.SubGenre
import com.app.ticketmaster.model.SubType
import com.app.ticketmaster.model.TicketLimit
import com.app.ticketmaster.model.Ticketing
import com.app.ticketmaster.model.Type
import com.app.ticketmaster.model.Venue
import com.app.ticketmaster.model.VenueX

object TestData {
    val venue = Venue("venueName", City("cityName"), State("stateName", "stateCode"))
    val classification = Classification(
        family = false,
        genre = Genre("genreId", "genreName"),
        primary = true,
        segment = Segment("segmentId", "segmentName"),
        subGenre = SubGenre("subGenreId", "subGenreName"),
        subType = SubType("subTypeId", "subTypeName"),
        type = Type("typeId", "typeName"),
    )
    val dates = Dates(
        initialStartDate = InitialStartDate("2024-03-15T19:00:00Z", "2024-03-15", "19:00:00"),
        spanMultipleDays = false,
        start = Start(
            dateTBA = false,
            dateTBD = false,
            dateTime = "2024-03-15T19:00:00Z",
            localDate = "2024-03-15",
            localTime = "19:00:00",
            noSpecificTime = false,
            timeTBA = false,
        ),
        status = Status("statusId"),
        timezone = "timezone",
    )

    val image = ImageXX(
        fallback = false,
        height = 100,
        ratio = "1:1",
        url = "https://example.com/image.jpg",
        width = 100,
    )
    val outlet = Outlet("outletType", "https://example.com/outlet")
    val priceRange = PriceRange("USD", 50.0, 100.0, "priceType")
    val promoter = Promoter("promoterDescription", "promoterId", "promoterName")
    val sale = Sales(
        presales = listOf(Presale("startDateTime", "endDateTime", "presaleName")),
        `public` = Public("startDateTime", "endDateTime", startTBA = false, startTBD = false),
    )
    val seatmap = Seatmap("https://example.com/seatmap.jpg")
    val ticketLimit = TicketLimit("ticketLimitInfo")
    val ticketing = Ticketing(AllInclusivePricing(enabled = true), SafeTix(enabled = true))

    val event = Event(
        _embedded = Embedded(listOf(), listOf()),
        _links = LinksXX(
            attractions = listOf(AttractionX("https://example.com/attraction")),
            self = Self("https://example.com/self"),
            venues = listOf(VenueX("https://example.com/venue", "venueName")),
        ),
        accessibility = Accessibility(
            "accessibilityInfo",
            5,
            "https://example.com/accessibility",
            "accessibilityUrlText",
        ),
        ageRestrictions = AgeRestrictions(true),
        classifications = listOf(classification),
        dates = dates,
        doorsTimes = DoorsTimes("2024-03-15T18:00:00Z", "2024-03-15", "18:00:00"),
        id = "eventId",
        images = listOf(image),
        info = "eventInfo",
        locale = "en_US",
        name = "eventName",
        outlets = listOf(outlet),
        pleaseNote = "pleaseNote",
        priceRanges = listOf(priceRange),
        promoter = promoter,
        promoters = listOf(promoter),
        sales = sale,
        seatmap = seatmap,
        test = true,
        ticketLimit = ticketLimit,
        ticketing = ticketing,
        type = "eventType",
        url = "https://example.com/event",
    )

    val embedded = Embedded(listOf(event), listOf(venue))
    val links = LinksXXX(
        First("firstHref"),
        Last("lastHref"),
        Next("nextHref"),
        Self("selfHref"),
    )
    val page = Page(0, 0, 0, 0)
}
