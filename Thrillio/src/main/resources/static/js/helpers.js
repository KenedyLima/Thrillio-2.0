let filterButtons;
let bookmarksTypeContainers;
let content = {
	movies: [],
	books: [],
	weblinks: []
}
let movieGenres;



// EXPORTED FUNCTIONS

export function generateContentHTML(content) {
	console.log('Content: ', content)
	const moviesHTML = generateMoviesHTML(content.movies);
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
	console.log('HTML: ', HTML)
	console.log('containers: ', containers)
	containers.moviesContainer.innerHTML = HTML.moviesHTML;
	containers.booksContainer.innerHTML = HTML.booksHTML;
	containers.weblinksContainer.innerHTML = HTML.weblinksHTML;
	console.log('MoviesContainerInnerHTML: ', containers.moviesContainer.innerHTML)
	
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

// EXPORTED FUNCTIONS HELPERS

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


function generateBooksHTML(books) { return ""; }

function generateWeblinksHTML(books) { return ""; }


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
	localStorage.clear();
}


function alterWindowLocationHREF(href) {
	window.location.href = href;

}

