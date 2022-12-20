import * as helpers from './helpers.js';

// DOM ELEMENTS

const localStorage = window.localStorage;
const filterButtonsWrapper = document.querySelector('.filter-buttons-wrapper');
const configButton = document.querySelector('.config-button')
const bookmarksTypeContainers = document.querySelectorAll('.bookmark-type')
const logoutButton = document.querySelector('.logout-button')
const profileBox = document.querySelector('.profile-box');
let bookmarks = { movies: [], books: [], weblinks: [] }
let isBookmarksCached; 
const moviesContainer = document.querySelector(
	".movies-container .bookmarks-container"
);
const booksContainer = document.querySelector(".books-container .bookmarks-container");
const weblinksContainer = document.querySelector(".weblinks-container .bookmarks-container");
const containers = { moviesContainer: moviesContainer, booksContainer: booksContainer, weblinksContainer: weblinksContainer };
const movieGenres = JSON.parse(localStorage.getItem('movieGenres'));

// EVENT LISTENERS

window.addEventListener('load', listenToPageLoad)

window.addEventListener('beforeunload', helpers.listenToPageUnload)
// CALLBACK	

async function listenToPageLoad() {
	isBookmarksCached = localStorage.getItem('isBookmarksCached');
	console.log('isBookmarkCached ', isBookmarksCached)
	if (!isBookmarksCached || isBookmarksCached === "false") await fetchBookmarksAndCache(); else fetchBookmarksFromCache();
	if (!movieGenres) await helpers.fetchAndCacheGenresIds(); else helpers.fetchMovieGenresFromCache(movieGenres);
	setIsBookmarksCached(true);
	if (bookmarks.movies.length === 0 && bookmarks.books.length === 0 && bookmarks.weblinks.length === 0) { loadNoBookmarksAvailableMessage(); return; }
	const HTML = helpers.generateContentHTML(bookmarks, true);
	helpers.insertHTMLContent(HTML, containers)
	configureRemoveButtons();
	helpers.listenToProfileBoxClick(profileBox);
	helpers.listenToConfigButtonClick(configButton);
	helpers.listenToFilterButtonsClick(filterButtonsWrapper, bookmarksTypeContainers);
	helpers.listenToLogoutButtonClick(logoutButton)

}

async function listenToRemoveButton(e) {
	const bookmark = e.target;
	const id = bookmark.closest(".bookmark").getAttribute("bookmark-id");
	const type = bookmark.closest(".bookmark").getAttribute("type");
	const typeContainer = type.concat('s')
	console.log('bookmarkType: ', type)
	const response = await fetch(`/bookmark-management/${type}s/${id}`, {
		method: "DELETE",
	})
	helpers.updateContentAndHTML(id, type, bookmarks[typeContainer])
	localStorage.setItem('isContentLoaded', false);
	updateBookmarksCache();
}


//ASYNC FUNCTIONS

async function fetchBookmarksAndCache() {
	console.log('Fetching from database')
	const movies = await fetchMovies();
	//const books = await fetchBooks();
	//const weblinks = await fetchWeblinks();
	bookmarks.movies = movies;
	console.log('Movies: ', movies)
	console.log('movies from fetchBookmarks: ', movies)
	updateBookmarksCache();
}

async function fetchMovies() {
	const response = await fetch('/bookmark-management/movies')
	return response.json();
}

// OTHER FUNCTIONS

function configureRemoveButtons() {
	const removeButtons = document.querySelectorAll(".remove-button");
	removeButtons.forEach(button => button.addEventListener('click', e => listenToRemoveButton(e)))
}

function fetchBookmarksFromCache() {
	console.log('Fetching from cache')
	bookmarks = JSON.parse(localStorage.getItem("bookmarks"))
}

function setIsBookmarksCached(boolean) {
	localStorage.setItem('isBookmarksCached', boolean);
	console.log('Setting ', localStorage.getItem('isBookmarksCached'))
}

function updateBookmarksCache() {
	localStorage.setItem("bookmarks", JSON.stringify(bookmarks))
}

function loadNoBookmarksAvailableMessage() {
	console.log('Message: ')
	const message = "<h1 style='text-align: center'>No bookmarks available ;(</h1>"
	moviesContainer.innerHTML = message
	booksContainer.innerHTML = message
	weblinksContainer.innerHTML = message
}