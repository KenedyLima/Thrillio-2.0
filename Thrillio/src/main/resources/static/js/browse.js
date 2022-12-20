"use stricted";
import * as helpers from './helpers.js';

// DOM ELEMENTS

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
let isContentLoaded;
let bookmarkButtons;
let movieGenres = JSON.parse(localStorage.getItem('movieGenres'));
const containers = { moviesContainer: moviesContainer, booksContainer: booksContainer, weblinksContainer: weblinksContainer };

// EVENT LISTENERS

window.addEventListener("load", listenToPageLoad);

window.addEventListener("hashchange", updateBookmarks);

// CALLBACKS 

async function listenToPageLoad() {
	isContentLoaded = localStorage.getItem("isContentLoaded");
	console.log('isContentLoaded: ', isContentLoaded)
	if (!isContentLoaded || isContentLoaded === "false") {await fetchCurrentContentAndCache(); console.log("content is NOT loaded")} else fetchCurrentContentFromCache();
	if (!movieGenres) await helpers.fetchAndCacheGenresIds(); else helpers.fetchMovieGenresFromCache(movieGenres);
	setIsContentLoaded(true);
	console.log(currentPageContent)
	const HTML = helpers.generateContentHTML(currentPageContent, false);
	helpers.insertHTMLContent(HTML, containers)
	configureBookmarkButtons();
	helpers.listenToProfileBoxClick(profileBox);
	helpers.listenToConfigButtonClick(configButton);
	helpers.listenToFilterButtonsClick(filterButtonsWrapper, bookmarksTypeContainers);
	helpers.listenToLogoutButtonClick(logoutButton)

}

function configureBookmarkButtons() {
		bookmarkButtons = document.querySelectorAll('.bookmark-button');
		bookmarkButtons.forEach(button => {
			button.addEventListener('click', e => listenToBookmarkButton(e))
		})
	}
// ASYNC FUNCTIONS

async function listenToBookmarkButton(e) {
	const mediaElement = e.target.closest('.bookmark');
	const id = Number(mediaElement.getAttribute('bookmark-id'));
	const type = mediaElement.getAttribute('type');
	const typeContainer = mediaElement.getAttribute('type').concat('s');
	let mediaObj = null;
	currentPageContent[typeContainer].forEach(element => {
		element.id === id ? mediaObj = element : null;
	})
	const mediaInfo = extractDisplayedBookmarkInfo(type, mediaObj)
	const response = await postBookmark(type, mediaInfo)
	helpers.updateContentAndHTML(id, type, currentPageContent[typeContainer]);
	updateCurrentContentCache();
	localStorage.setItem('isBookmarksCached', false);
}

async function postBookmark(type, info) {
	return fetch(`/bookmark-management/${type}s`, {
		method: "POST",
		headers: {
			'Content-type': "application/json"
		},
		body: JSON.stringify(info)
	})
}

async function fetchCurrentContentAndCache() {
	console.log('Fetching from database');
	const movies = await fetchMovies();
	//const books = await fetchBooks();	
	//const weblinks = await fetchWeblinks();	
	console.log(movies)
	currentPageContent.movies = movies;
	updateCurrentContentCache();
}

async function fetchMovies() {
	const urlMedia = new URL("http://localhost:8081/media-management/movies");
	const urlBookmark = new URL("http://localhost:8081/bookmark-management/movies");
	const mediaResponse = await fetch(urlMedia);
	const bookmarkReponse = await fetch(urlBookmark);

	return getNonBookmarkedMedia(await mediaResponse.json(), await bookmarkReponse.json());
}

// OTHER FUNCTIONS

function updateBookmarks() {
	console.log(window.location.hash);
}

function fetchCurrentContentFromCache() {
	console.log('Fetching from cache')
	currentPageContent = JSON.parse(localStorage.getItem("currentPageContent"));
}

function setIsContentLoaded(boolean) {
	localStorage.setItem("isContentLoaded", boolean);
}

function extractDisplayedBookmarkInfo(type, media) {
	let info = null;

	switch (type) {
		case "movie": { info = extractDisplayedMovieInfo(media); break; }
		case "book": { info = extractDisplayedBookInfo(media); break; }
		case "weblink": { info = extractDisplayedWeblinkInfo(media); break; }
	}

	return info;
}

function extractDisplayedMovieInfo(movie) {
	const releaseYear = helpers.extractYear(movie.release_date);
	const imgUrl = movie.poster_path;
	console.log(movie)
	return {
		id: movie.id,
		title: movie.title,
		releaseYear: releaseYear,
		imageUrl: imgUrl,
		imdbRating: movie.vote_average,
		genreIds: movie.genre_ids
	}

}

function extractDisplayedBookInfo() { }

function extractDisplayedWeblinkInfo() { }
function updateCurrentContentCache() {
	localStorage.setItem("currentPageContent", JSON.stringify(currentPageContent))
}

function getNonBookmarkedMedia(media, bookmarks) {
	let nonBookmarkedMedia = []
	media.results.forEach(element => {
		let isBookmarked = false
		bookmarks.forEach(bookmark => {
			if(element.id === bookmark.id) {
				isBookmarked = true;	
			}			
		})
		if (!isBookmarked) nonBookmarkedMedia.push(element)
	})

	return nonBookmarkedMedia
}