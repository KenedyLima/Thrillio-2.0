let filterButtons;
let bookmarksTypeContainers;
let movieGenres;

// EXPORTED FUNCTIONS

export function generateContentHTML(content, isBookmarks) {
	const moviesHTML = generateMoviesHTML(content.movies, isBookmarks);
	const booksHTML = generateBooksHTML(content.books);
	const weblinksHTML = generateWeblinksHTML(content.weblinks);
	return {
		moviesHTML: moviesHTML,
		booksHTML: booksHTML,
		weblinksHTML: weblinksHTML
	}
}


export async function fetchAndCacheGenresIds() {
	const url = new URL("http://localhost:8081/media-management/movies/genre");
	const response = await fetch(url);
	const json = await response.json();
	movieGenres = json.genres;
	return json.genres;
}

export function fetchMovieGenresFromCache(array) {
	array = JSON.parse(localStorage.getItem('movieGenres'));
}

export function insertHTMLContent(HTML, containers) {
	containers.moviesContainer.innerHTML = HTML.moviesHTML;
	containers.booksContainer.innerHTML = HTML.booksHTML;
	containers.weblinksContainer.innerHTML = HTML.weblinksHTML;

}


// EXPORTED LISTENER FUNCTIONS

export function listenToProfileBoxClick(element) {
	element.addEventListener('click', e => {
		if (e.target.closest(".profile-box-body")) return;
		element.classList.toggle("opened");

	})
}

export function listenToFilterButtonsClick(buttonsParentNode, containers) {
	bookmarksTypeContainers = containers;
	filterButtons = buttonsParentNode.querySelectorAll('.filter-button');
	buttonsParentNode.addEventListener('click', e => {
		listenToFilters(e);
	})
}

export function listenToConfigButtonClick(button) {
	button.addEventListener('click', e => {
		alterWindowLocationHREF("/auth/user");
	})
}

export function listenToLogoutButtonClick(button) {
	button.addEventListener('click', e => {
		alterWindowLocationHREF("/logout");
		clearLocalStorage();
	})
}

export function listenToPageUnload() {
	clearLocalStorage();
}

export function extractYear(date) {
	const dateObj = new Date(date);
	console.log(dateObj.getFullYear())
	return String(dateObj.getFullYear());
}

export function generateMoviesHTML(movies, isBookmarks) {
	let html = ``;
	movies.forEach((movie) => {
		html += `<div bookmark-id=${movie.id} type="movie" class="bookmark">
        <img
          class="cover"
          src="https://image.tmdb.org/t/p/original${isBookmarks ? movie.imageUrl : movie.poster_path}"
          alt=""
        />
        <div class="info-container flex-column">
          <span class="info"
            >Title: <span class="value">${isBookmarks ? movie.title : movie.original_title}</span></span
          >
          <span class="info"
            >Genre: <span class="value">${getMovieGenres(movie, isBookmarks)}</span></span
          >
          <span class="info"
            >Publication Year: <span class="value">${isBookmarks ? movie.releaseYear : movie.release_date
			}</span></span
          >
          <span class="info"
            >Kid Friendly: <span class="value">${isBookmarks ? movie.kidFriendlyElegible : movie.adult ? "No" : "Yes"
			}</span></span
          >
          <span class="info"
            >Rate: <span class="value">${isBookmarks ? movie.voteAverage : movie.vote_average}</span></span
          >
          <button class="reset-button ${isBookmarks ? "remove" : "bookmark"}-button">${isBookmarks ? "Remove" : "Bookmark"}</button>
        </div>
      </div>`;
	});
	return html;
}

export function generateBooksHTML(books) { return ""; }

export function generateWeblinksHTML(books) { return ""; }

export function updateContentAndHTML(mediaId, type, elementContainer) {
	const domElements = document.querySelectorAll(`[type=${type}]`);
	console.log('ElementContainerBefore: ', elementContainer)
	
	elementContainer.forEach((element, i) => {
		Number(element.id) === Number(mediaId) ? elementContainer.splice(i, 1) : "";
	})
	console.log('ElementContainerAfter: ', elementContainer)
	console.log('mediaId: ', mediaId)
	domElements.forEach(element => {
		Number(element.getAttribute('bookmark-id')) === Number(mediaId) ? element.remove() : ""
		console.log('ElementId: ', typeof mediaId)
	})
}


// EXPORTED FUNCTIONS HELPERS



function getMovieGenres(movie, isBookmarks) {

	let genres = ""
	const genreIds = isBookmarks ? movie.genreIds : movie.genre_ids;
	movieGenres.forEach(genre => {
		genreIds.forEach((genreId, i) => {
			if (genreId === genre.id) {
				genres = genres.concat((i + 1) === genreIds.length ? genre.name : genre.name + ", ")
			}
		})
	})
	return genres;
}


function filterBookmarks(filter) {
	const container = filter + "-container";
	if (filter === "all") {
		bookmarksTypeContainers.forEach((type) => {
			type.style.display = "block";
		});
	} else {
		bookmarksTypeContainers.forEach((type) => {
			type.style.display = "none";
			if (type.classList.contains(container)) {
				type.style.display = "block";
			}
		});
	}
}


//CALLBACKS

function listenToFilters(e) {
	const target = e.target;
	let filter = "";
	if (!target.classList.contains("filter-button")) return;
	if (target.classList.contains("movies-filter")) filter = "movies";
	else if (target.classList.contains("books-filter")) filter = "books";
	else if (target.classList.contains("weblinks-filter")) filter = "weblinks";
	else filter = "all";
	filterBookmarks(filter);
	updateFilterButtonsStyle(filter);
}

// UPDATE STYLES FUNCTIONS

function updateFilterButtonsStyle(filter) {
	const buttonClass = filter + "-filter";
	filterButtons.forEach((button) => {
		button.classList.remove("current-filter");
		if (button.classList.contains(buttonClass))
			button.classList.add("current-filter");
	});
}

// SMALL HELPER FUNCTIONS

function clearLocalStorage() {
	console.log('Clearing localStorage')
	localStorage.clear();
}


function alterWindowLocationHREF(href) {
	window.location.href = href;

}

