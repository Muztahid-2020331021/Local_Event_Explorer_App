package com.example.eventexplorerapp.data

var selectedEvent = -1
var eventCategory : Category = Category.Basketball
var isHome: Boolean = false


object EventList {
    val eventsNearby = listOf(
        Event(
            category= Category.Basketball,
            title= "SUST vs SEC Basketball Match",
            time= "10:00 am",
            date= "19/03/2024",
            description = "Two teams, five players each, battle on a rectangular court. Players dribble, pass, and shoot the ball to score through the opponent's hoop. Each basket earns two points, except from beyond the three-point line which is worth three. The team with the most points at the end wins. Tensions rise with every buzzer beater and slam dunk!",
            location= "Zilla stadium, Sylhet",
            id=0,
            organizer = "SUST CSE department",
            phoneNumber = "01XXXXXXXXX"
        ),
        Event(
            category= Category.Concert,
            title= "Joy Bangla Live Concert",
            time= "8:00 PM",
            date= "20/03/2024",
            description= "Experience the magic of the legendary composer A.R. Rahman!",
            location= "Sylhet Stadium, Sylhet",
            id= 1,
            organizer = "Joy Bangla Team",
            phoneNumber = "01XXXXXXXXX"
        ),
        Event(
            category= Category.Cooking,
            title= "Sylhet Cooking Competition",
            time= "7:00 PM",
            date= "18/03/2024",
            description= "Sample dishes from different cultures and expand your palate",
            location= "Le Meridien Sylhet",
            id= 2
        ),
        Event(
            category = Category.Drama,
            title = "Hamlet by William Shakespeare",
            time = "7:30 PM",
            date = "21/03/2024",
            description = "Experience the magic and humor of Shakespeare's classic comedy about love, fairies, and mischievous spirits. A group of Athenian lovers find themselves entangled in a fantastical world created by mischievous fairies, leading to confusion, mistaken identities, and ultimately, love's triumph. This timeless tale explores themes of love, deception, and the power of imagination.",
            location = "Sylhet Drama Circle Auditorium",
            id = 3
        ),
        Event(Category.Football, "Super Bowl LVIII", "6:30 PM", "29/02/2024",id=4),
        Event(Category.IUPC, "Inter University Programming Contest", "12:00 PM", "01/03/2024",id=5),
        Event(Category.Karate, "World Karate Championship", "9:00 AM", "02/03/2024",id=6),
        Event(Category.Marathon, "Tokyo Marathon", "3:00 AM", "03/03/2024",id=7),
        Event(Category.ScienceFair, "National Science Fair", "10:00 AM", "04/03/2024",id=8),
        Event(Category.Tour, "Grand Tour of Europe", "9:00 AM", "05/03/2024",id=9),
        Event(Category.Basketball, "CSE vs EEE", "9:00 AM", "10/02/2024",id=10),
    )
    val eventsSuggested: List<Event> = listOf(
        Event(
            category = Category.Drama,
            title = "Hamlet by William Shakespeare",
            time = "7:30 PM",
            date = "21/03/2024",
            description = "Experience the magic and humor of Shakespeare's classic comedy about love, fairies, and mischievous spirits. A group of Athenian lovers find themselves entangled in a fantastical world created by mischievous fairies, leading to confusion, mistaken identities, and ultimately, love's triumph. This timeless tale explores themes of love, deception, and the power of imagination.",
            location = "Sylhet Drama Circle Auditorium",
            id = 3
        ),
        Event(
            category= Category.Basketball,
            title= "SUST vs SEC Basketball Match",
            time= "10:00 am",
            date= "19/03/2024",
            description = "Two teams, five players each, battle on a rectangular court. Players dribble, pass, and shoot the ball to score through the opponent's hoop. Each basket earns two points, except from beyond the three-point line which is worth three. The team with the most points at the end wins. Tensions rise with every buzzer beater and slam dunk!",
            location= "Zilla stadium, Sylhet",
            id=0
        ),
        Event(Category.Marathon, "Tokyo Marathon", "3:00 AM", "03/03/2024",id=7),
        Event(Category.Football, "Super Bowl LVIII", "6:30 PM", "29/02/2024",id=4),
    )
    val scheduledEvents = mutableListOf<Event>()
    val myEventList = mutableListOf<Event>(
        Event(
            category = Category.Drama,
            title = "Hamlet by William Shakespeare",
            time = "7:30 PM",
            date = "21/03/2024",
            description = "Experience the magic and humor of Shakespeare's classic comedy about love, fairies, and mischievous spirits. A group of Athenian lovers find themselves entangled in a fantastical world created by mischievous fairies, leading to confusion, mistaken identities, and ultimately, love's triumph. This timeless tale explores themes of love, deception, and the power of imagination.",
            location = "Sylhet Drama Circle Auditorium",
            id = 3
        ),
        Event(
            category= Category.Basketball,
            title= "SUST vs SEC Basketball Match",
            time= "10:00 am",
            date= "19/03/2024",
            description = "Two teams, five players each, battle on a rectangular court. Players dribble, pass, and shoot the ball to score through the opponent's hoop. Each basket earns two points, except from beyond the three-point line which is worth three. The team with the most points at the end wins. Tensions rise with every buzzer beater and slam dunk!",
            location= "Zilla stadium, Sylhet",
            id=0
        ),
        Event(Category.Marathon, "Tokyo Marathon", "3:00 AM", "03/03/2024",id=7),
        Event(Category.Football, "Super Bowl LVIII", "6:30 PM", "29/02/2024",id=4),
    )

    val typeList = listOf(
        Event(Category.Basketball, "Basketball"),
        Event(Category.Concert, "Concert"),
        Event(Category.Cooking, "Cooking"),
        Event(Category.Drama, "Drama"),
        Event(Category.Football, "Football"),
        Event(Category.IUPC, "IUPC"),
        Event(Category.Karate, "Karate"),
        Event(Category.Marathon, "Marathon"),
        Event(Category.ScienceFair, "Science Fair"),
        Event(Category.Tour, "Tour")
    )
}