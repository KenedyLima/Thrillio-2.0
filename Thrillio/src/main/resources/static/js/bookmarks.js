const filterButtonsWrapper = document.querySelector(".filter-buttons-wrapper");
const filterButtons = document.querySelectorAll(".filter-button");
const bookmarkTypes = document.querySelectorAll(".bookmark-type");

// Event Listeners
filterButtonsWrapper.addEventListener("click", (e) => listenToFilters(e));

// Callbacks

function listenToFilters(e) {
  console.log("listening");
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

// FUNCTIONS
function filterBookmarks(filter) {
  console.log(filter);
  const container = filter + "-container";
  console.log(container);
  if (filter === "all") {
    bookmarkTypes.forEach((type) => {
      type.style.display = "block";
    });
  } else {
    bookmarkTypes.forEach((type) => {
      type.style.display = "none";
      if (type.classList.contains(container)) {
        type.style.display = "block";
      }
    });
  }
}

function updateFilterButtonsStyle(filter) {
  const buttonClass = filter + "-filter";
  filterButtons.forEach((button) => {
    button.classList.remove("current-filter");
    if (button.classList.contains(buttonClass))
      button.classList.add("current-filter");
  });
}
