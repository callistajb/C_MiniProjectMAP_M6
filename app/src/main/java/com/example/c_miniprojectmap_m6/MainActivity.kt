package com.example.c_miniprojectmap_m6

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.c_miniprojectmap_m6.model.MovieModel
import com.example.c_miniprojectmap_m6.model.Genre
import com.example.c_miniprojectmap_m6.model.Type
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val recyclerView: RecyclerView by lazy {
        findViewById(R.id.recyclerView)
    }

    private val movieAdapter by lazy {
        MovieAdapter(layoutInflater, GlideImageLoader(this), object : MovieAdapter.OnClickListener {
            override fun onMovieClick(movie : MovieModel) = showMovieDialog(movie)
        })
    }

    private val newMovies = listOf(
        MovieModel(
            title = "Atonement",
            type = Type.Movie,
            year = "2007",
            genre = listOf(Genre.ROMANCE, Genre.DRAMA, Genre.WAR),
            synopsis = "As a 13-year-old, fledgling writer Briony Tallis irrevocably changes the course of several lives when she accuses her older sister’s lover of a crime he did not commit.",
            imageUrl = "https://a.ltrbxd.com/resized/sm/upload/44/vx/ah/pc/quaWuwYZd8IbZMCOhW3CZEvyCWJ-0-2000-0-3000-crop.jpg?v=690eaa9389",
            cast = listOf("James McAvoy", "Keira Knightley", "Soirse Ronan")
        ),
        MovieModel(
            "Pet Sematary II",
            Type.Movie,
            "1992",
            listOf(Genre.HORROR),
            "The “sematary” is up to its old zombie-raising tricks again. This time, the protagonists are Jeff Matthews, whose mother died in a Hollywood stage accident, and Drew Gilbert, a boy coping with an abusive stepfather.",
            "https://a.ltrbxd.com/resized/film-poster/4/5/7/8/6/45786-pet-sematary-ii-0-2000-0-3000-crop.jpg?v=0b5e53caee",
            listOf("Edward Furlong, Anthony Edward, Clancy Brown")
        ),
        MovieModel(
            title="Black Swan",
            Type.Movie,
            year = "2010",
            genre = listOf(Genre.DRAMA, Genre.THRILLER, Genre.HORROR),
            synopsis = "The story of Nina, a ballerina in a New York City ballet company whose life, like all those in her profession, is completely consumed with dance. She lives with her retired ballerina mother Erica who zealously supports her daughter’s professional ambition. When artistic director Thomas Leroy decides to replace prima ballerina Beth MacIntyre for the opening production of their new season, Swan Lake, Nina is his first choice.",
            imageUrl = "https://a.ltrbxd.com/resized/sm/upload/yt/ae/iz/kj/bIjkE9Og0nulRycj144sCcQcsZ6-0-2000-0-3000-crop.jpg?v=a571e4c644",
            cast = listOf("Natalie Portman", "Mila Kunis", "Vincent Cassel")
        ),
        MovieModel(
            "The Devil All the Time",
            Type.Movie,
            "2020",
            listOf(Genre.CRIME, Genre.DRAMA, Genre.THRILLER),
            "Everyone ends up in the same damned place. In Knockemstiff, Ohio and its neighboring backwoods, sinister characters converge around young Arvin Russell as he fights the evil forces that threaten him and his family.",
            "https://a.ltrbxd.com/resized/film-poster/4/2/9/9/4/5/429945-the-devil-all-the-time-0-2000-0-3000-crop.jpg?v=4b048ec678",
            listOf("Tom Holland, Robert Pattinson, Bill Skarsgård")
        ),
        MovieModel(
            "Hannibal",
            Type.TVSeries,
            "2013-2015",
            listOf(Genre.THRILLER, Genre.MYSTERY),
            "Explores the early relationship between renowned psychiatrist Hannibal Lecter and a young FBI criminal profiler who is haunted by his ability to empathize with serial killers.",
            "https://static1.srcdn.com/wordpress/wp-content/uploads/trailers/episode_poster/20559_Season%201.jpg",
            listOf("Hugh Dancy, Mads Mikkelsen, Caroline Dhavernas")
        )
    )

    private val remainingMovies = newMovies.toMutableList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView.adapter = movieAdapter
        recyclerView.layoutManager =
            LinearLayoutManager(
                this,
                RecyclerView.VERTICAL,
                false
            )

        val itemTouchHelper = ItemTouchHelper(movieAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        movieAdapter.setData(
            listOf(
                MovieModel(
                    "Finding Nemo",
                    Type.Movie,
                    year = "2003",
                    genre = listOf(Genre.FAMILY, Genre.ANIMATION),
                    synopsis = "Nemo, an adventurous young clownfish, is unexpectedly taken from his Great Barrier Reef home to a dentist’s office aquarium. It’s up to his worrisome father Marlin and a friendly but forgetful fish Dory to bring Nemo home – meeting vegetarian sharks, surfer dude turtles, hypnotic jellyfish, hungry seagulls, and more along the way.",
                    imageUrl = "https://a.ltrbxd.com/resized/film-poster/2/7/0/5/2705-finding-nemo-0-2000-0-3000-crop.jpg?v=b329bc5740",
                    listOf("Albert Brooks, Ellen DeGeneres, Alexander Gould")
                ),
                MovieModel(
                    "Twin Peaks",
                    Type.TVSeries,
                    "1990-1991",
                    listOf(Genre.MYSTERY, Genre.THRILLER),
                    "An idiosyncratic FBI agent investigates the murder of a young woman in the even more idiosyncratic town of Twin Peaks.",
                    "https://tse3.mm.bing.net/th/id/OIP.9dGmuR7NavfuZhPB3cVICwHaKF?rs=1&pid=ImgDetMain&o=7&rm=3",
                    listOf("Kyle MacLachlan, Michael Ontkean")
                ),
                MovieModel(
                    "Lolita",
                    Type.Movie,
                    "1997",
                    listOf(Genre.ROMANCE, Genre.DRAMA),
                    "A forbidden love. An unthinkable attraction. The ultimate price.",
                    "https://a.ltrbxd.com/resized/sm/upload/iu/d0/hk/uj/2cAvrSWTkhTbS6BfES58ZqHw7JS-0-1000-0-1500-crop.jpg?v=db2264bca7",
                    listOf("Jeremy Irons, Dominique Swain, Melanie Griffith")
                ),
                MovieModel(
                    title = "Inglourious Basterds",
                    Type.Movie,
                    year = "2009",
                    listOf(Genre.WAR, Genre.THRILLER, Genre.DRAMA),
                    "Once upon a time in Nazi occupied France… In Nazi-occupied France during World War II, a group of Jewish-American soldiers known as “The Basterds” are chosen specifically to spread fear throughout the Third Reich by scalping and brutally killing Nazis. The Basterds, lead by Lt. Aldo Raine soon cross paths with a French-Jewish teenage girl who runs a movie theater in Paris which is targeted by the soldiers.",
                    "https://a.ltrbxd.com/resized/film-poster/4/1/3/5/2/41352-inglourious-basterds-0-1000-0-1500-crop.jpg?v=0c74c673e0",
                    listOf("Brad Pitt, Melanie Laurent, Christoph Waltz")
                ),
                MovieModel(
                    "Pretty Woman",
                    Type.Movie,
                    "1990",
                    listOf(Genre.ROMANCE, Genre.COMEDY, Genre.DRAMA),
                    "A corporate raider hires a hooker to act as a business escort in Beverly Hills, California.",
                    "https://a.ltrbxd.com/resized/film-poster/5/1/9/3/6/51936-pretty-woman-0-1000-0-1500-crop.jpg?v=b190c1d6bc",
                    listOf("Richard Gere, Julia Roberts, Ralph Bellamy")
                ),
                MovieModel(
                    "Love Actually",
                    Type.Movie,
                    "2003",
                    listOf(Genre.ROMANCE, Genre.DRAMA, Genre.COMEDY),
                    "The ultimate romantic comedy. Eight very different couples deal with their love lives in various loosely interrelated tales all set during a frantic month before Christmas in London.",
                    "https://a.ltrbxd.com/resized/film-poster/5/1/6/0/2/51602-love-actually-0-2000-0-3000-crop.jpg?v=02e0f1f133",
                    listOf("Hugh Grant, Alan Rickman, Emma Thompson")
                ),
                MovieModel(
                    "High & Low The Worst X",
                    Type.Movie,
                    "2022",
                    listOf(Genre.ACTION),
                    "Fists fly when Oya High’s street fighters defend themselves from a three-school alliance of brawlers.",
                    "https://a.ltrbxd.com/resized/film-poster/8/6/9/1/9/9/869199-high-low-the-worst-x-0-2000-0-3000-crop.jpg?v=e9be5b4792",
                    listOf("Kazuma Kawamura, Hokuto Yoshino, Yuta")
                ),
                MovieModel(
                    "Saltburn",
                    Type.Movie,
                    "2023",
                    listOf(Genre.COMEDY, Genre.DRAMA, Genre.THRILLER),
                    "We’re all about to lose our minds. Struggling to find his place at Oxford University, student Oliver Quick finds himself drawn into the world of the charming and aristocratic Felix Catton, who invites him to Saltburn, his eccentric family’s sprawling estate, for a summer never to be forgotten.",
                    "https://a.ltrbxd.com/resized/film-poster/8/3/5/7/7/4/835774-saltburn-0-2000-0-3000-crop.jpg?v=b58cbd3b08",
                    listOf("Barry Keoghan, Jacob Elordi, Rosamund Pike")
                ),
                MovieModel(
                    "Eyes Wide Shut",
                    Type.Movie,
                    "1999",
                    listOf(Genre.THRILLER, Genre.DRAMA, Genre.MYSTERY),
                    "After Dr. Bill Harford’s wife, Alice, admits to having sexual fantasies about a man she met, Bill becomes obsessed with having a sexual encounter. He discovers an underground sexual group and attends one of their meetings – and quickly discovers that he is in over his head.",
                    "https://a.ltrbxd.com/resized/film-poster/5/1/7/1/7/51717-eyes-wide-shut-0-2000-0-3000-crop.jpg?v=7010402aeb",
                    listOf("Tom Cruise, Nicole Kidman, Sydney Pollack")
                ),
                MovieModel(
                    "Krampus",
                    Type.Movie,
                    "2015",
                    listOf(Genre.HORROR, Genre.COMEDY, Genre.FANTASY),
                    "When his dysfunctional family clashes over the holidays, young Max is disillusioned and turns his back on Christmas. Little does he know, this lack of festive spirit has unleashed the wrath of Krampus: a demonic force of ancient evil intent on punishing non-believers.",
                    "https://a.ltrbxd.com/resized/film-poster/2/1/3/1/6/9/213169-krampus-0-2000-0-3000-crop.jpg?v=ab48a31e73",
                    listOf("Emjay Anthony, Adam Scott, Toni Collette")
                ),
                MovieModel(
                    "Crazy Rich Asian",
                    Type.Movie,
                    "2018",
                    listOf(Genre.ROMANCE, Genre.COMEDY),
                    "The only thing crazier than love is family. An American-born Chinese economics professor accompanies her boyfriend to Singapore for his best friend’s wedding, only to get thrust into the lives of Asia’s rich and famous.",
                    "https://a.ltrbxd.com/resized/film-poster/3/8/7/2/3/8/387238-crazy-rich-asians-0-2000-0-3000-crop.jpg?v=21695f4b1f",
                    listOf("Constance Wu, Henry Golding, Michelle Yeoh")
                )
            )
        )

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            if (remainingMovies.isNotEmpty()) {
                val movieTitles = remainingMovies.map { it.title }.toTypedArray()

                AlertDialog.Builder(this)
                    .setTitle("Select movie to add")
                    .setItems(movieTitles) { _, which ->
                        val selectedMovie = remainingMovies[which]
                        movieAdapter.addItem(selectedMovie)
                        remainingMovies.removeAt(which)
                    }
                    .show()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("No movies available")
                    .setMessage("All movies have already been added.")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }
    }

    private fun showMovieDialog(movie: MovieModel) {
        val typeLabel = if (movie.type == Type.Movie) "Movie" else "TV Show"
        val details = """
            Title: ${movie.title}
            Type: $typeLabel
            Year: ${movie.year}
            Genre: ${movie.genre.joinToString(", ") {it.displayName}}
            
            Synopsis: ${movie.synopsis}
            
            Cast: ${movie.cast.joinToString(", ")}""".trimIndent()

        AlertDialog.Builder(this)
            .setTitle(movie.title)
            .setMessage(details)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}