"use stricted";


const localStorage = window.localStorage;
const moviesContainer = document.querySelector(
	".movies-container .bookmarks-container"
);
const booksContainer = document.querySelector(".books-container .bookmarks-container");
const weblinksContainer = document.querySelector(".weblinks-container .bookmarks-container");
let currentPageContent = { movies: [], books: [], weblinks: [] };
let isContentLoaded = localStorage.getItem("isContentLoaded");
let bookmarkButtons;
let movieGenres;

// EVENT LISTENERS

window.addEventListener("load", listenToPageLoad);

async function listenToPageLoad() {
	if (!isContentLoaded) await fetchCurrentContentAndCache(); else fetchCurrentContentFromCache();
	await fetchAndCacheGenresIds();
	generateAndInsertContentHTML();
	setIsContentLoaded(true);
	configureBookmarkButtons();
}

window.addEventListener("hashchange", updateBookmarks);

//HELPER FUNCTIONS

function generateMoviesHTML(movies) {
	let html = ``;
	movies.forEach((movie) => {
		html += `<div movie-id=${movie.id} class="bookmark movie">
        <img
          class="cover"
          src="https://image.tmdb.org/t/p/original${movie.poster_path}"
          alt=""
        />
        <div class="info-container flex-column">
          <span class="info"
            >Title: <span class="value">${movie.original_title}</span></span
          >
          <span class="info"
            >Genre: <span class="value">${getMovieGenres(movie)}</span></span
          >
          <span class="info"
            >Publication Year: <span class="value">${movie.release_date
			}</span></span
          >
          <span class="info"
            >Kid Friendly: <span class="value">${movie.adult ? "No" : "Yes"
			}</span></span
          >
          <span class="info"
            >Rate: <span class="value">${movie.vote_average}</span></span
          >
          <button class="reset-button bookmark-button">Bookmark</button>
        </div>
      </div>`;
	});
	return html;
}

function generateBooksHTML(books) { return ""; }

function generateWeblinksHTML(books) { return ""; }

function generateAndInsertContentHTML() {
	const movieHTML = generateMoviesHTML(currentPageContent.movies);
	const bookHTML = generateBooksHTML(currentPageContent.books);
	const weblinkHTML = generateWeblinksHTML(currentPageContent.weblinks);

	moviesContainer.innerHTML = movieHTML;
	booksContainer.innerHTML = bookHTML;
	weblinksContainer.innerHTML = weblinkHTML;
}

function updateBookmarks() {
	console.log(window.location.hash);
}

function fetchCurrentContentFromCache() {
	currentPageContent = JSON.parse(localStorage.getItem("currentPageContent"));
}

function setIsContentLoaded(boolean) {
	localStorage.setItem("isContentLoaded", boolean);
}

function configureBookmarkButtons() {
	bookmarkButtons = document.querySelectorAll('.bookmark-button');
	bookmarkButtons.forEach(button => {
		button.addEventListener('click', e => listenToBookmarkButtons(e))
	})
}

function extractDisplayedMovieInfo(movie) {
	const releaseYear = extractYear(movie.release_date);
	const imgUrl = "https://image.tmdb.org/t/p/original/" + movie.poster_path;
	return {
		id: movie.id,
		title: movie.title,
		releaseYear: releaseYear,
		imageUrl: imgUrl,
		imdbRating: movie.vote_average,
		genre: ["1", "5"]
	}

}

function extractYear(date) {
	const dateObj = new Date(date);
	return String(dateObj.getFullYear());
}

// CALLBACKS

async function listenToBookmarkButtons(e) {
	const id = Number(e.target.closest('.bookmark').getAttribute('movie-id'));
	let movie = null;
	currentPageContent.movies.forEach(instance => { instance.id == id ? movie = instance : null });
	const movieInfo = extractDisplayedMovieInfo(movie);
	const response = await postMovie(movieInfo);
	updateMovieContentAndHTML(movie.id);
	configureBookmarkButtons();
}

async function postMovie(movieInfo) {
	return fetch("/bookmark-management/movies", {
		method: "POST",
		body: JSON.stringify(movieInfo),
		headers: {
			'Content-type': "application/json"
		}
	})
}

function getMovieGenres(movie) {
	let genres = ""
	movieGenres.forEach(genre => {

		movie.genre_ids.forEach((genreId, i) => {
			if (genreId === genre.id) {
				genres = genres.concat((i + 1) === movie.genre_ids.length ? genre.name : genre.name + ", ")
			}
		})
	})
	return genres;
}

function updateMovieContentAndHTML(movieId) {
	currentPageContent.movies.forEach((movie, i) => movie.id === movieId ? currentPageContent.movies.splice(i, 1) : "");
	updateCurrentContentCache();
	generateAndInsertContentHTML();
}

function updateCurrentContentCache() {
	localStorage.setItem("currentPageContent", JSON.stringify(currentPageContent))
}
// ASYNC FUNCTIONS
async function fetchCurrentContentAndCache() {
	const movies = await fetchMovies();
	//const books = await fetchBooks();	
	//const weblinks = await fetchWeblinks();	
	currentPageContent.movies = movies.results;
	updateCurrentContentCache();
}

async function fetchMovies() {
	const url = new URL("http://localhost:8081/media-management/movies");
	const response = await fetch(url);
	return await response.json();
}

async function fetchAndCacheGenresIds() {
	const url = new URL("http://localhost:8081/media-management/movies/genre");
	const response = await fetch(url);
	const json = await response.json();
	movieGenres = json.genres;
}
