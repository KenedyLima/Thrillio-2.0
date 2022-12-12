"use stricted";

const moviesContainer = document.querySelector(
  ".movies-container .bookmarks-container"
);
const booksContainer = document.querySelector(".books-container");
const weblinksContainer = document.querySelector(".weblinks-container");
let currentPageCache = { movies: [], books: [], weblinks: [] };
// EVENT LISTENERS

let pageLoaded = false;

window.addEventListener("load", listenToPageLoad);

async function listenToPageLoad() {
  if (!pageLoaded) {
    console.log("fetchMovies");
    //    currentPageCache.movies = await fetchMovies();
  }
  html = generateMoviesHTML(currentPageCache.movies);
  moviesContainer.innerHTML = html;
  console.log(html);
  pageLoaded = true;
}

window.addEventListener("hashchange", updateBookmarks);

async function updateBookmarks() {
  console.log(window.location.hash);
}

// FUNCTIONS

function generateMoviesHTML(movies) {
  let html = ``;

  console.log(movies);
  movies.results.forEach((movie) => {
    html += `<div class="bookmark">
        <img
          class="cover"
          src="https://image.tmdb.org/t/p/original/${movie.poster_path}"
          alt=""
        />
        <div class="info-container flex-column">
          <span class="info"
            >Title: <span class="value">${movie.original_title}</span></span
          >
          <span class="info"
            >Genre: <span class="value"></span></span
          >
          <span class="info"
            >Publication Year: <span class="value">${
              movie.release_date
            }</span></span
          >
          <span class="info"
            >Kid Friendly: <span class="value">${
              movie.adult ? "No" : "Yes"
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

async function fetchMovies() {
  console.log("getMovies()");
  const url = new URL("https://api.themoviedb.org/3/discover/movie");
  url.searchParams.append("api_key", "c477b0a44244708a7d2aa95516f71908");
  url.searchParams.append("api_key", "c477b0a44244708a7d2aa95516f71908");

  const response = await fetch(url);
  return await response.json();
}

function getBookmarksHTML(movies) {}

function getMoviesHTML(movies) {}
