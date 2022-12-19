import * as helpers from './helpers.js';

const removeButton = document.querySelector(".remove-button");
const localStorage = window.localStorage;
const bookmarks = { movies: [], books: [], weblinks: [] }
let isBookmarksCached = localStorage.getItem('isBookmarksCached');
const moviesContainer = document.querySelector(
	".movies-container .bookmarks-container"
);
const booksContainer = document.querySelector(".books-container .bookmarks-container");
const weblinksContainer = document.querySelector(".weblinks-container .bookmarks-container");
const containers = { moviesContainer: moviesContainer, booksContainer: booksContainer, weblinksContainer: weblinksContainer };
const movieGenres = JSON.parse(localStorage.getItem('movieGenres'));
// EVENT LISTENERS
window.addEventListener('load', listenToPageLoad)

// CALLBACK	
async function listenToPageLoad() {
	if (!isBookmarksCached) await fetchBookmarksAndCache(); else fetchBookmarksFromCache();
	if (!movieGenres) await helpers.fetchAndCacheGenresIds(); else helpers.fetchMovieGenresFromCache(movieGenres);
	console.log('listenTo: ', bookmarks.movies)
	setIsBookmarkCached(true);
	const HTML = helpers.generateContentHTML(bookmarks);
	helpers.insertHTMLContent(HTML)
}	

async function removeBookmark(e) {
	console.log("removing");
	const bookmark = e.target;
	const id = bookmark.closest(".bookmark").getAttribute("movie-id");
	if (bookmark.classList.contains("movie")) {
		const response = await fetch(`@{/bookmark-management/movies/${id}}`, {
			method: "DELETE",
		});
		console.log("SERVER RESPONSE:", response);
	}
	console.log("removed");
}

async function fetchBookmarksAndCache() {
	const movies = await fetchMovies();
	//const books = await fetchBooks();
	//const weblinks = await fetchWeblinks();
console.log('fetchBok: ', movies)
	bookmarks.movies = movies;
	cacheBookmarks();
}

async function fetchMovies() {
	const response = await fetch('/bookmark-management/movies')
	return response.json();
}

function fetchBookmarksFromCache() {
	bookmarks = JSON.parse(localStorage.getItem("bookmarks"))
}
function cacheBookmarks() {
	localStorage.setItem("bookmarks", JSON.stringify(bookmarks))
}

function setIsBookmarkCached(boolean) {
	localStorage.setItem('isBookmarkCached', boolean);
}

function generateBookmarksHTMLAndInsert() {
	const moviesHTML = helpers.generateMoviesHTML(bookmarks.movies);
	console.log('movies: ', bookmarks.movies)
	console.log(moviesHTML)
	moviesContainer.innerHTML = moviesHTML;
}