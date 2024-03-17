package com.app.ticketmaster.model

data class TicketmasterResponse(
    val _embedded: Embedded,
    val _links: LinksXXX,
    val page: Page,
)

data class Embedded(
    val events: List<Event>,
    val venues: List<Venue>,
)

data class Venue(
    val name: String,
    val city: City,
    val state: State,
)

data class LinksXXX(
    val first: First,
    val last: Last,
    val next: Next,
    val self: Self,
)

data class Page(
    val number: Int,
    val size: Int,
    val totalElements: Int,
    val totalPages: Int,
)

data class Event(
    val _embedded: Embedded,
    val _links: LinksXX,
    val accessibility: Accessibility,
    val ageRestrictions: AgeRestrictions,
    val classifications: List<Classification>,
    val dates: Dates,
    val doorsTimes: DoorsTimes,
    val id: String,
    val images: List<ImageXX>,
    val info: String,
    val locale: String,
    val name: String,
    val outlets: List<Outlet>,
    val pleaseNote: String,
    val priceRanges: List<PriceRange>,
    val promoter: Promoter,
    val promoters: List<Promoter>,
    val sales: Sales,
    val seatmap: Seatmap,
    val test: Boolean,
    val ticketLimit: TicketLimit,
    val ticketing: Ticketing,
    val type: String,
    val url: String,
)

data class LinksXX(
    val attractions: List<AttractionX>,
    val self: Self,
    val venues: List<VenueX>,
)

data class Accessibility(
    val info: String,
    val ticketLimit: Int,
    val url: String,
    val urlText: String,
)

data class AgeRestrictions(
    val legalAgeEnforced: Boolean,
)

data class Dates(
    val initialStartDate: InitialStartDate,
    val spanMultipleDays: Boolean,
    val start: Start,
    val status: Status,
    val timezone: String,
)

data class DoorsTimes(
    val dateTime: String,
    val localDate: String,
    val localTime: String,
)

data class ImageXX(
    val fallback: Boolean,
    val height: Int,
    val ratio: String,
    val url: String,
    val width: Int,
)

data class Outlet(
    val type: String,
    val url: String,
)

data class PriceRange(
    val currency: String,
    val max: Double,
    val min: Double,
    val type: String,
)

data class Promoter(
    val description: String,
    val id: String,
    val name: String,
)

data class Sales(
    val presales: List<Presale>,
    val `public`: Public,
)

data class Seatmap(
    val staticUrl: String,
)

data class TicketLimit(
    val info: String,
)

data class Ticketing(
    val allInclusivePricing: AllInclusivePricing,
    val safeTix: SafeTix,
)

data class Links(
    val self: Self,
)

data class Classification(
    val family: Boolean,
    val genre: Genre,
    val primary: Boolean,
    val segment: Segment,
    val subGenre: SubGenre,
    val subType: SubType,
    val type: Type,
)

data class ExternalLinks(
    val facebook: List<Facebook>,
    val homepage: List<Homepage>,
    val instagram: List<Instagram>,
    val itunes: List<Itune>,
    val lastfm: List<Lastfm>,
    val musicbrainz: List<Musicbrainz>,
    val spotify: List<Spotify>,
    val twitter: List<Twitter>,
    val wiki: List<Wiki>,
    val youtube: List<Youtube>,
)

data class Self(
    val href: String,
)

data class Genre(
    val id: String,
    val name: String,
)

data class Segment(
    val id: String,
    val name: String,
)

data class SubGenre(
    val id: String,
    val name: String,
)

data class SubType(
    val id: String,
    val name: String,
)

data class Type(
    val id: String,
    val name: String,
)

data class Facebook(
    val url: String,
)

data class Homepage(
    val url: String,
)

data class Instagram(
    val url: String,
)

data class Itune(
    val url: String,
)

data class Lastfm(
    val url: String,
)

data class Musicbrainz(
    val id: String,
)

data class Spotify(
    val url: String,
)

data class Twitter(
    val url: String,
)

data class Wiki(
    val url: String,
)

data class Youtube(
    val url: String,
)

data class Ada(
    val adaCustomCopy: String,
    val adaHours: String,
    val adaPhones: String,
)

data class Address(
    val line1: String,
)

data class BoxOfficeInfo(
    val acceptedPaymentDetail: String,
    val openHoursDetail: String,
    val phoneNumberDetail: String,
    val willCallDetail: String,
)

data class City(
    val name: String,
)

data class Country(
    val countryCode: String,
    val name: String,
)

data class Dma(
    val id: Int,
)

data class GeneralInfo(
    val childRule: String,
    val generalRule: String,
)

data class Location(
    val latitude: String,
    val longitude: String,
)

data class Market(
    val id: String,
    val name: String,
)

data class Social(
    val twitter: TwitterX,
)

data class State(
    val name: String,
    val stateCode: String,
)

data class UpcomingEventsX(
    val _filtered: Int,
    val _total: Int,
    val ticketmaster: Int,
    val tmr: Int,
)

data class TwitterX(
    val handle: String,
)

data class AttractionX(
    val href: String,
)

data class VenueX(
    val href: String,
    val name: String,
)

data class InitialStartDate(
    val dateTime: String,
    val localDate: String,
    val localTime: String,
)

data class Start(
    val dateTBA: Boolean,
    val dateTBD: Boolean,
    val dateTime: String,
    val localDate: String,
    val localTime: String,
    val noSpecificTime: Boolean,
    val timeTBA: Boolean,
)

data class Status(
    val code: String,
)

data class Presale(
    val endDateTime: String,
    val name: String,
    val startDateTime: String,
)

data class Public(
    val endDateTime: String,
    val startDateTime: String,
    val startTBA: Boolean,
    val startTBD: Boolean,
)

data class AllInclusivePricing(
    val enabled: Boolean,
)

data class SafeTix(
    val enabled: Boolean,
)

data class First(
    val href: String,
)

data class Last(
    val href: String,
)

data class Next(
    val href: String,
)
