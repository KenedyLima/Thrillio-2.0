"use stricted";
import * as helpers from './helpers.js';

const localStorage = window.localStorage;
const profileBox = document.querySelector('.profile-box');
const bookmarksTypeContainers = document.querySelectorAll('.bookmark-type')
const filterButtonsWrapper = document.querySelector('.filter-buttons-wrapper');
const configButton = document.querySelector('.config-button')
const logoutButton = document.querySelector('.logout-button')
const moviesContainer = document.querySelector(
	".movies-container .bookmarks-container"
);
const booksContainer = document.querySelector(".books-container .bookmarks-container");
const weblinksContainer = document.querySelector(".weblinks-container .bookmarks-container");
let currentPageContent = { movies: [], books: [], weblinks: [] };
let isContentLoaded = localStorage.getItem("isContentLoaded");
let bookmarkButtons;
let movieGenres = JSON.parse(localStorage.getItem('movieGenres'));
const containers = { moviesContainer: moviesContainer, booksContainer: booksContainer, weblinksContainer: weblinksContainer };
// EVENT LISTENERS

window.addEventListener("load", listenToPageLoad);

async function listenToPageLoad() {
	if (!isContentLoaded) await fetchCurrentContentAndCache(); else fetchCurrentContentFromCache();
	if (!movieGenres) await helpers.fetchAndCacheGenresIds(); else helpers.fetchMovieGenresFromCache(movieGenres);
	setIsContentLoaded(true);
	const HTML = helpers.generateContentHTML(currentPageContent, false);
	helpers.insertHTMLContent(HTML, containers)
	configureBookmarkButtons();
	helpers.listenToProfileBoxClick(profileBox);
	helpers.listenToConfigButtonClick(configButton);
	helpers.listenToFilterButtonsClick(filterButtonsWrapper, bookmarksTypeContainers);
	helpers.listenToLogoutButtonClick(logoutButton)

}

window.addEventListener("hashchange", updateBookmarks);

//HELPER FUNCTIONS

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
		genresIds: movie.genres_ids
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
		headers: {
			'Content-type': "application/json"
		},
		body: JSON.stringify(movieInfo)
	})
}

function updateMovieContentAndHTML(movieId) {
	currentPageContent.movies.forEach((movie, i) => movie.id === movieId ? currentPageContent.movies.splice(i, 1) : "");
	updateCurrentContentCache();
	const HTML = helpers.generateContentHTML(currentPageContent);
	helpers.insertHTMLContent(HTML, containers)
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

